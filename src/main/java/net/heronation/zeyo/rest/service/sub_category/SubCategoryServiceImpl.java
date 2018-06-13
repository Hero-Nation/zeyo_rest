package net.heronation.zeyo.rest.service.sub_category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.value.LIdVO;
import net.heronation.zeyo.rest.constants.CommonConstants;
import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.category.CategoryRepository;
import net.heronation.zeyo.rest.repository.fit_info.FitInfo;
import net.heronation.zeyo.rest.repository.fit_info.FitInfoRepository;
import net.heronation.zeyo.rest.repository.measure_item.MeasureItem;
import net.heronation.zeyo.rest.repository.measure_item.MeasureItemRepository;
import net.heronation.zeyo.rest.repository.sub_category.QSubCategory;
import net.heronation.zeyo.rest.repository.sub_category.SubCategory;
import net.heronation.zeyo.rest.repository.sub_category.SubCategoryDto;
import net.heronation.zeyo.rest.repository.sub_category.SubCategoryRepository;
import net.heronation.zeyo.rest.repository.sub_category_fit_info_map.QSubCategoryFitInfoMap;
import net.heronation.zeyo.rest.repository.sub_category_fit_info_map.SubCategoryFitInfoMap;
import net.heronation.zeyo.rest.repository.sub_category_fit_info_map.SubCategoryFitInfoMapRepository;
import net.heronation.zeyo.rest.repository.sub_category_measure_map.QSubCategoryMeasureMap;
import net.heronation.zeyo.rest.repository.sub_category_measure_map.SubCategoryMeasureMap;
import net.heronation.zeyo.rest.repository.sub_category_measure_map.SubCategoryMeasureMapRepository;

@Slf4j
@Service
@Transactional
public class SubCategoryServiceImpl implements SubCategoryService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private SubCategoryRepository sub_categoryRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private SubCategoryFitInfoMapRepository scfimRepository;

	@Autowired
	private SubCategoryMeasureMapRepository scmmRepository;

	@Autowired
	private MeasureItemRepository miRepository;

	@Autowired
	private FitInfoRepository fiRepository;

	@Autowired
	EntityManager entityManager;

	// @Override
	// public Page<SubCategory> search(Predicate where, Pageable page) {
	//
	// JPAQuery<SubCategory> query = new JPAQuery<SubCategory>(entityManager);
	//
	// QSubCategory target = QSubCategory.subCategory;
	//
	// QueryResults<SubCategory> R = query.from(target)
	// .leftJoin(target.subCategoryMeasureMaps)
	// .leftJoin(target.subCategoryFitInfoMaps)
	// .where(where)
	// //.orderBy(target.id.desc())
	// .offset((page.getPageNumber() - 1)* page.getPageSize())
	// .limit(page.getPageSize()).fetchResults();
	//
	//
	// return new PageImpl<SubCategory>(R.getResults(), page, R.getTotal());
	//
	// }

	@Override
	@Transactional(readOnly = true)
	public Page<Map<String, Object>> subsearch(Predicate where, Pageable page) {

		JPAQuery<Category> query = new JPAQuery<Category>(entityManager);

		QSubCategory sc = QSubCategory.subCategory;
		QSubCategoryFitInfoMap scfi = QSubCategoryFitInfoMap.subCategoryFitInfoMap;
		QSubCategoryMeasureMap scmm = QSubCategoryMeasureMap.subCategoryMeasureMap;

		PathBuilder<SubCategory> queryPath = new PathBuilder<SubCategory>(SubCategory.class, "subCategory");

		for (Order order : page.getSort()) {
			PathBuilder<Object> path = queryPath.get(order.getProperty());
			query.orderBy(new OrderSpecifier(com.querydsl.core.types.Order.valueOf(order.getDirection().name()), path));
		}

		QueryResults<Tuple> R = query
				.select(sc.id, sc.name, sc.itemImage, sc.clothImage, scfi.id.countDistinct(), scmm.id.countDistinct(),
						sc.createDt)
				.from(sc).leftJoin(sc.subCategoryFitInfoMaps, scfi).on(scfi.useYn.eq("Y"))
				.leftJoin(sc.subCategoryMeasureMaps, scmm).on(scmm.useYn.eq("Y")).groupBy(sc.id).where(where)
				.offset((page.getPageNumber() - 1) * page.getPageSize()).limit(page.getPageSize()).fetchResults();

		List<Tuple> search_list = R.getResults();
		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Tuple row : search_list) {
			Map<String, Object> search_R = new HashMap<String, Object>();

			search_R.put("subcate_name", row.get(sc.name));
			search_R.put("subcate_id", row.get(sc.id));
			search_R.put("itemImage", row.get(sc.itemImage));
			search_R.put("clothImage", row.get(sc.clothImage));
			search_R.put("subCategoryFitInfoMaps_count", row.get(scfi.id.countDistinct()));
			search_R.put("subCategoryMeasureMaps_count", row.get(scmm.id.countDistinct()));
			search_R.put("createDt", row.get(sc.createDt));

			return_list.add(search_R);
		}
		return new PageImpl<Map<String, Object>>(return_list, page, R.getTotal());

	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> distinct_name(String cate) {

		StringBuffer select_query = new StringBuffer();
		select_query.append("SELECT ");
		select_query.append("    sc.id ");
		select_query.append("   , sc.name ");
		select_query.append("FROM ");
		select_query.append("    sub_category sc ");
		select_query.append("WHERE ");
		select_query.append("    sc.use_yn = 'Y' and sc.category_id = " + cate);

		Query count_q = entityManager.createNativeQuery(select_query.toString());
		List<Object[]> list = count_q.getResultList();

		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Object[] row : list) {
			Map<String, Object> search_R = new HashMap<String, Object>();
			search_R.put("sub_cate_id", row[0]);
			search_R.put("sub_cate_name", row[1]);

			return_list.add(search_R);
		}

		Map<String, Object> R = new HashMap<String, Object>();
		R.put("content", return_list);

		return R;
	}

	@Override
	@Transactional
	public String insert(SubCategoryDto param) {

		Category c = categoryRepository.findOne(param.getCategory());
		SubCategory sc = param.convertToEntity();
		sc.setCategory(c);
		sc = sub_categoryRepository.save(sc);

		List<LIdVO> milist = param.getMeasureItem();

		for (LIdVO vo : milist) {
			SubCategoryMeasureMap temp_scmm = new SubCategoryMeasureMap();

			MeasureItem this_item = miRepository.findOne(vo.getId());
			temp_scmm.setSubCategory(sc);
			temp_scmm.setMeasureItem(this_item);
			temp_scmm.setUseYn("Y");

			scmmRepository.save(temp_scmm);
		}

		List<LIdVO> filist = param.getFitinfos();
		for (LIdVO vo : filist) {
			SubCategoryFitInfoMap temp_scfi = new SubCategoryFitInfoMap();

			FitInfo this_item = fiRepository.findOne(vo.getId());

			temp_scfi.setSubCategory(sc);
			temp_scfi.setFitInfo(this_item);
			temp_scfi.setUseYn("Y");

			scfimRepository.save(temp_scfi);
		}

		return CommonConstants.SUCCESS;
	}

	@Override
	@Transactional
	public String update(SubCategoryDto param) {

		SubCategory sc = sub_categoryRepository.findOne(param.getId());
		sc.setBleachYn(param.getBleachYn());
		sc.setClothImage(param.getClothImage());
		sc.setDrycleaningYn(param.getDrycleaningYn());
		sc.setDrymethodYn(param.getDrymethodYn());
		sc.setIroningYn(param.getIroningYn());
		sc.setItemImage(param.getItemImage());
		sc.setLaundryYn(param.getLaundryYn());
		sc.setName(param.getName());

		QSubCategoryMeasureMap qscmm = QSubCategoryMeasureMap.subCategoryMeasureMap;

		List<LIdVO> milist = param.getMeasureItem();

		Iterable<SubCategoryMeasureMap> scmm = scmmRepository.findAll(qscmm.subCategory.id.eq(param.getId()));

		for (LIdVO vo : milist) {

			boolean shouldBeDeleted = true;
			boolean db_exist = false;
			SubCategoryMeasureMap should_delete_map = new SubCategoryMeasureMap();
			for (SubCategoryMeasureMap db_scmm : scmm) {
				should_delete_map = db_scmm;
				if (db_scmm.getMeasureItem().getId() == vo.getId()) {
					shouldBeDeleted = false;
					db_exist = true;
				}
			}

			if (shouldBeDeleted) {
				should_delete_map.setUseYn("N");
			}

			if (!db_exist) {
				SubCategoryMeasureMap temp_scmm = new SubCategoryMeasureMap();

				MeasureItem this_item = miRepository.findOne(vo.getId());
				temp_scmm.setSubCategory(sc);
				temp_scmm.setMeasureItem(this_item);
				temp_scmm.setUseYn("Y");

				scmmRepository.save(temp_scmm);
			}

		}

		QSubCategoryFitInfoMap qscfim = QSubCategoryFitInfoMap.subCategoryFitInfoMap;

		Iterable<SubCategoryFitInfoMap> scfilist = scfimRepository.findAll(qscfim.subCategory.id.eq(param.getId()));

		List<LIdVO> filist = param.getFitinfos();
		for (LIdVO vo : filist) {

			boolean shouldBeDeleted = true;
			boolean db_exist = false;
			SubCategoryFitInfoMap should_delete_map = new SubCategoryFitInfoMap();
			for (SubCategoryFitInfoMap db_scfim : scfilist) {
				should_delete_map = db_scfim;
				if (db_scfim.getFitInfo().getId() == vo.getId()) {
					shouldBeDeleted = false;
					db_exist = true;
				}
			}

			if (shouldBeDeleted) {
				should_delete_map.setUseYn("N");
				scfimRepository.save(should_delete_map);
			}

			if (!db_exist) {
				SubCategoryFitInfoMap temp_scfim = new SubCategoryFitInfoMap();

				FitInfo this_item = fiRepository.findOne(vo.getId());
				temp_scfim.setSubCategory(sc);
				temp_scfim.setFitInfo(this_item);
				temp_scfim.setUseYn("Y");

				scfimRepository.save(temp_scfim);
			}

			SubCategoryFitInfoMap temp_scfi = new SubCategoryFitInfoMap();

			FitInfo this_item = fiRepository.findOne(vo.getId());

			temp_scfi.setSubCategory(sc);
			temp_scfi.setFitInfo(this_item);
			temp_scfi.setUseYn("Y");

			scfimRepository.save(temp_scfi);
		}

		return CommonConstants.SUCCESS;
	}

	@Override
	public String delete(List<LIdVO> param) {
		for (LIdVO v : param) {
			SubCategory a = sub_categoryRepository.findOne(v.getId());
			a.setUseYn("N");
		}

		return CommonConstants.SUCCESS;
	}

	@Override
	public Map<String, Object> single_info(Long id) {
		QSubCategory sc = QSubCategory.subCategory;
		QSubCategoryFitInfoMap scfi = QSubCategoryFitInfoMap.subCategoryFitInfoMap;
		QSubCategoryMeasureMap scmm = QSubCategoryMeasureMap.subCategoryMeasureMap;

		Map<String, Object> R = new HashMap<String, Object>();

		SubCategory scr = sub_categoryRepository.findOne(id);

		Iterable<SubCategoryFitInfoMap> scfimr = scfimRepository.findAll(scfi.subCategory.id.eq(id));
		Iterable<SubCategoryMeasureMap> scmmr = scmmRepository.findAll(scmm.subCategory.id.eq(id));

		R.put("sub_category", scr);
		R.put("fit_infos", scfimr);
		R.put("measure_items", scmmr);

		return R;
	}

}