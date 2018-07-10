package net.heronation.zeyo.rest.service.size_option;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.value.LIdDto;
import net.heronation.zeyo.rest.constants.CommonConstants;
import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.category.CategoryRepository;
import net.heronation.zeyo.rest.repository.category.QCategory;
import net.heronation.zeyo.rest.repository.kindof.Kindof;
import net.heronation.zeyo.rest.repository.kindof.KindofRepository;
import net.heronation.zeyo.rest.repository.kindof.QKindof;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.size_option.QSizeOption;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;
import net.heronation.zeyo.rest.repository.size_option.SizeOptionDto;
import net.heronation.zeyo.rest.repository.size_option.SizeOptionRepository;
import net.heronation.zeyo.rest.repository.sub_category.QSubCategory;
import net.heronation.zeyo.rest.repository.sub_category.SubCategory;
import net.heronation.zeyo.rest.repository.sub_category.SubCategoryRepository;

@Slf4j
@Service
@Transactional
public class SizeOptionServiceImpl implements SizeOptionService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private SizeOptionRepository size_optionRepository;

	@Autowired
	private CategoryRepository categroyRepository;

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private KindofRepository kindRepository;

	@Autowired
	EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> search(Map<String, Object> param, Pageable page) {

		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) from ( ");

		StringBuffer select_query = new StringBuffer();
		select_query.append("SELECT ");
		select_query.append("    c.name 				AS category_name, ");
		select_query.append("    sc.name 				AS sub_category_name, ");
		select_query.append("    k.kvalue 				AS kindof_name, ");
		select_query.append("    so.id 					AS size_option_id, ");
		select_query.append("    so.name 				AS size_option_name, ");
		select_query.append("    COUNT(isom.item_id) 	AS size_option_item_count, ");
		select_query.append("    so.create_dt 			AS size_option_create_dt ");

		// search_R.put("size_option_id", row.get(so.id));
		// search_R.put("category_name", row.get(c.name));
		// search_R.put("sub_category_name", row.get(sc.name));
		// search_R.put("kindof_name", row.get(ko.kvalue));
		// search_R.put("size_option_name", row.get(so.name));
		// // search_R.put("size_option_item_count",
		// row.get(so.itemSizeOptionMaps.size()));
		// search_R.put("size_option_create_dt", row.get(so.createDt));

		StringBuffer where_query = new StringBuffer();
		where_query.append("FROM ");
		where_query.append("    size_option so ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    category c ON so.category_id = c.id AND c.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    sub_category sc ON so.sub_category_id = sc.id ");
		where_query.append("        AND sc.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    kindof k ON so.kindof_id = k.id AND k.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    item_size_option_map isom ON isom.size_option_id = so.id ");
		where_query.append("        AND isom.use_yn = 'Y' ");
		where_query.append("WHERE ");
		where_query.append("    so.use_yn = 'Y' and so.kindof_id != 8 ");

		String name = (String) param.get("name");
		if (name != null) {
			where_query.append("        AND   so.name like '%" + name + "%' ");
		}

		String category = (String) param.get("category");
		if (category != null) {
			where_query.append("        AND   c.id = " + category + " ");
		}

		String sub_category = (String) param.get("sub_category");
		if (sub_category != null) {
			where_query.append("        AND   sc.id = " + sub_category + " ");
		}

		String kindof = (String) param.get("kindof");
		if (kindof != null) {
			where_query.append("        AND   k.id = " + kindof + " ");
		}

		String start = (String) param.get("start");
		if (start != null) {
			where_query.append("        AND so.create_dt >= STR_TO_DATE('" + start + "', '%Y-%m-%d %H:%i:%s')");
		}

		String end = (String) param.get("end");
		if (end != null) {
			where_query.append("        AND so.create_dt <= STR_TO_DATE('" + end + "', '%Y-%m-%d %H:%i:%s')");
		}

		where_query.append("GROUP BY so.id");

		StringBuffer sort_query = new StringBuffer();

		Sort sort = page.getSort();
		if (sort != null) {
			sort_query.append("  ORDER BY so.");
			String sep = "";
			for (Sort.Order order : sort) {
				sort_query.append(sep);
				sort_query.append(order.getProperty());
				sort_query.append(" ");
				sort_query.append(order.getDirection());
				sep = ", ";
			}
		}

		StringBuffer page_query = new StringBuffer();
		page_query.append("  limit ");
		page_query.append((page.getPageNumber() - 1) * page.getPageSize());
		page_query.append(" , ");
		page_query.append(page.getPageSize());

		Query count_q = entityManager.createNativeQuery(
				count_query.append(select_query).append(where_query).append("  ) count_table      ").toString());
		BigInteger count_list = BigInteger.ZERO;

		List<BigInteger> count_result = count_q.getResultList();
		if (count_result.isEmpty()) {

		} else {
			count_list = count_result.get(0);
		}

		Query q = entityManager
				.createNativeQuery(select_query.append(where_query).append(sort_query).append(page_query).toString());
		List<Object[]> list = q.getResultList();

		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Object[] row : list) {
			Map<String, Object> search_R = new HashMap<String, Object>();

			// select_query.append(" c.name AS category_name, ");
			// select_query.append(" sc.name AS sub_category_name, ");
			// select_query.append(" k.kvalue AS kindof_name, ");
			// select_query.append(" so.id AS size_option_id, ");
			// select_query.append(" so.name AS size_option_name, ");
			// select_query.append(" COUNT(isom.item_id) AS size_option_item_count, ");
			// select_query.append(" so.create_dt AS size_option_create_dt ");
			//

			search_R.put("category_name", row[0]);
			search_R.put("sub_category_name", row[1]);
			search_R.put("kindof_name", row[2]);
			search_R.put("size_option_id", row[3]);
			search_R.put("size_option_name", row[4]);
			search_R.put("size_option_item_count", row[5]);
			search_R.put("size_option_create_dt", row[6]);

			return_list.add(search_R);
		}

		int totalPages = (count_list.intValue() / page.getPageSize());
		if (count_list.intValue() % page.getPageSize() > 0)
			totalPages = totalPages + 1;

		Map<String, Object> R = new HashMap<String, Object>();
		R.put("content", return_list);
		R.put("totalPages", totalPages);
		R.put("totalElements", count_list.intValue());
		R.put("number", page.getPageNumber());
		R.put("size", return_list.size());

		return R;

		// QSizeOption so = QSizeOption.sizeOption;
		// QCategory c = QCategory.category;
		// QSubCategory sc = QSubCategory.subCategory;
		// QKindof ko =QKindof.kindof;
		//
		// JPAQuery<Member> query = new JPAQuery<Member>(entityManager);
		//
		//
		// QueryResults<Tuple> R = query.select(
		// so.id,c.name,sc.name,ko.kvalue,so.name
		// //,so.itemSizeOptionMaps.size()
		// ,so.createDt
		// ).from(so)
		// .innerJoin(so.category,c)
		// .innerJoin(so.subCategory,sc)
		// .innerJoin(so.kindof,ko)
		// //.innerJoin(so.itemSizeOptionMaps)
		//
		// .where(c.useYn.eq("Y")
		// .and(sc.useYn.eq("Y"))
		// .and(ko.useYn.eq("Y"))
		// .and(where))
		//
		// .fetchResults();
		//
		// List<Tuple> search_list = R.getResults();
		// List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();
		//
		// for (Tuple row : search_list) {
		// Map<String, Object> search_R = new HashMap<String, Object>();
		//
		// search_R.put("size_option_id", row.get(so.id));
		// search_R.put("category_name", row.get(c.name));
		// search_R.put("sub_category_name", row.get(sc.name));
		// search_R.put("kindof_name", row.get(ko.kvalue));
		// search_R.put("size_option_name", row.get(so.name));
		// // search_R.put("size_option_item_count",
		// row.get(so.itemSizeOptionMaps.size()));
		// search_R.put("size_option_create_dt", row.get(so.createDt));
		//
		//
		// return_list.add(search_R);
		// }
		// return new PageImpl<Map<String, Object>>(return_list, page, R.getTotal());

	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> category_count(Predicate where) {

		Map<String, Object> RV = new HashMap<String, Object>();

		QSizeOption so = QSizeOption.sizeOption;
		QCategory c = QCategory.category;
		QSubCategory sc = QSubCategory.subCategory;
		QKindof ko = QKindof.kindof;

		JPAQuery<Member> query = new JPAQuery<Member>(entityManager);

		QueryResults<Tuple> R = query.select(so.id.count(), so.id.countDistinct()).from(so).groupBy(so.subCategory)
				.where(where).fetchResults();

		RV.put("category_count", R.getResults().size());

		return RV;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> single(Predicate where) {

		Map<String, Object> R = new HashMap<String, Object>();

		QSizeOption so = QSizeOption.sizeOption;
		QCategory c = QCategory.category;
		QSubCategory sc = QSubCategory.subCategory;
		QKindof ko = QKindof.kindof;

		JPAQuery<Member> query = new JPAQuery<Member>(entityManager);

		// private Long id;
		//
		// private Category category;
		//
		// private SubCategory subCategory;
		//
		// private Kindof kindof;
		//
		// private String name;
		//
		// private DateTime createDt;
		//
		// private String useYn;

		Tuple db_R = query.select(so.name, so.createDt, c.id, sc.id, ko.id, so.createDt).from(so)
				.innerJoin(so.category, c).innerJoin(so.subCategory, sc).innerJoin(so.kindof, ko)
				// .innerJoin(so.itemSizeOptionMaps)

				.where(c.useYn.eq("Y").and(sc.useYn.eq("Y")).and(ko.useYn.eq("Y")).and(where))

				.fetchOne();

		R.put("size_option_name", db_R.get(so.name));
		R.put("size_option_create_dt", db_R.get(so.createDt));
		R.put("category_id", db_R.get(c.id));
		R.put("sub_category_id", db_R.get(sc.id));
		R.put("kindof_id", db_R.get(ko.id));

		return R;
	}

	@Override
	@Transactional
	public String insert(SizeOptionDto param) {

		Category cate = categroyRepository.findOne(param.getCategory());
		SubCategory subCate = subCategoryRepository.findOne(param.getSubCategory());
		Kindof ko = kindRepository.findOne(param.getKindof());

		SizeOption insertvo = new SizeOption();
		insertvo.setCategory(cate);
		insertvo.setSubCategory(subCate);
		insertvo.setCreateDt(new DateTime());
		insertvo.setKindof(ko);
		insertvo.setName(param.getName());
		insertvo.setUseYn("Y");

		size_optionRepository.save(insertvo);
		return CommonConstants.SUCCESS;
	}

	@Override
	@Transactional
	public String update(SizeOptionDto param) {
		SizeOption so = size_optionRepository.findOne(param.getId());

		Category cate = categroyRepository.findOne(param.getCategory());
		SubCategory subCate = subCategoryRepository.findOne(param.getSubCategory());
		Kindof ko = kindRepository.findOne(param.getKindof());

		so.setCategory(cate);
		so.setSubCategory(subCate);
		so.setCreateDt(so.getCreateDt());
		so.setKindof(ko);
		so.setName(param.getName());
		so.setUseYn("Y");

		return CommonConstants.SUCCESS;
	}

	@Override
	@Transactional
	public String delete(List<LIdDto> param) {
		// TODO Auto-generated method stub

		for (LIdDto v : param) {
			SizeOption a = size_optionRepository.findOne(v.getId());
			a.setUseYn("N");
		}

		return CommonConstants.SUCCESS;
	}

}