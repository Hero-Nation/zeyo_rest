package net.heronation.zeyo.rest.v2_rule.service;

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

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.constants.CommonConstants; 
import net.heronation.zeyo.rest.dmodel_measure_map.repository.DmodelMeasureMap;
import net.heronation.zeyo.rest.dmodel_measure_map.repository.DmodelMeasureMapRepository;
import net.heronation.zeyo.rest.dmodel_measure_map.repository.QDmodelMeasureMap;
import net.heronation.zeyo.rest.dmodel_ratio.repository.DmodelRatio;
import net.heronation.zeyo.rest.dmodel_ratio.repository.QDmodelRatio;
import net.heronation.zeyo.rest.sub_category.repository.QSubCategory;
import net.heronation.zeyo.rest.sub_category.repository.SubCategory;
import net.heronation.zeyo.rest.sub_category.repository.SubCategoryRepository;
import net.heronation.zeyo.rest.sub_category.repository.V2Category;
import net.heronation.zeyo.rest.v2_rule.controller.V2RuleDto;
import net.heronation.zeyo.rest.v2_rule.repository.V2Rule;
import net.heronation.zeyo.rest.v2_rule.repository.V2RuleRepository;

@Slf4j
@Service
@Transactional
public class V2RuleServiceImpl implements V2RuleService {

	@Autowired
	private RestTemplate restTemplate;
	

	@Autowired
	EntityManager entityManager;


	@Autowired
	private V2RuleRepository v2_ruleRepository;
	@Autowired
	private SubCategoryRepository scRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> search(Map<String, Object> param, Pageable page) {

		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) from ( "); 

		StringBuffer select_query = new StringBuffer();
		select_query.append("SELECT ");
		select_query.append("    d.id				as id, ");

		select_query.append("    d.first_ct			as first_ct, ");
		select_query.append("    d.second_ct		as second_ct, ");
		select_query.append("    d.create_dt		as create_dt, ");
		select_query.append("    d.rule_type			as rule_type ");
		
		StringBuffer where_query = new StringBuffer();
		where_query.append("FROM ");
		where_query.append("    v2_rule d ");
		where_query.append("WHERE ");
		where_query.append("    d.use_yn = 'Y'");

		String name = (String) param.get("name");
		if (name != null) {
			where_query.append("        AND   d.title like '%" + name + "%' ");
		}

		StringBuffer sort_query = new StringBuffer();

		Sort sort = page.getSort();
		if (sort != null) {
			sort_query.append("  ORDER BY d.");

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
				count_query.append(select_query).append(where_query).append(" ) count_table ").toString());

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

			search_R.put("id", row[0]);
			search_R.put("first_ct", row[1]);
			search_R.put("second_ct", row[2]);
			search_R.put("create_dt", row[3]);
			search_R.put("rule_type", row[4]);

			
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

	}

	@Override
	@Transactional
	public String delete(List<String> param, Long seq) {
		for (String tv : param) {

			V2Rule dm = v2_ruleRepository.findOne(Long.valueOf(tv));
			if (dm != null)
				dm.setUseYn("N");

		}

		return "Y";
	}

	@Override
	@Transactional
	public String insert(V2RuleDto insertDto) {
		
		V2Rule this_model = insertDto.getAsEntity();
 		
		SubCategory first_ct = scRepository.findOne(this_model.getFirstCt().getId());
		
		this_model.setFirstCt(first_ct);
		
		SubCategory second_ct = scRepository.findOne(this_model.getSecondCt().getId());
		
		this_model.setSecondCt(second_ct);
		
		V2Rule db_model = v2_ruleRepository.save(this_model);
 

		return CommonConstants.SUCCESS;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> single(long id) {
		log.debug("single ");
		V2Rule V2Rule = v2_ruleRepository.findOne(id);

		QDmodelMeasureMap qdmm = QDmodelMeasureMap.dmodelMeasureMap;

		//Iterable<DmodelMeasureMap> dmm_list_iter = dmodelMeasureMapRepository .findAll(qdmm.V2Rule.id.eq(id).and(qdmm.useYn.eq("Y")));

		// List<DmodelMeasureMap> dmm_list = new ArrayList<DmodelMeasureMap>();
		//
		// dmm_list_iter.forEach(dmm_list::add);

		QDmodelRatio qdr = QDmodelRatio.dmodelRatio;

		//Iterable<DmodelRatio> dr_list_iter = dmodelRatioRepository.findAll(qdr.V2Rule.id.eq(id).and(qdr.useYn.eq("Y")));

		// List<DmodelRatio> dr_list = new ArrayList<DmodelRatio>();
		//
		// dr_list_iter.forEach(dr_list::add);

		QSubCategory qsc = QSubCategory.subCategory;

//		Iterable<SubCategory> sc_list_iter = subCategoryRepository.findAll(qsc.V2Rule.id.eq(id).and(qsc.useYn.eq("Y")));
//		List<V2Category> v2_list = new ArrayList<V2Category>();
//		// 기존의 서브카테고리정보를 v2카테고리로 전환해서 입력해준다.
//		sc_list_iter.forEach(sc -> {
//			log.debug("sc.getId() " + sc.getId());
//			V2Category this_v2_sc = v2Service.single_info(sc.getId());
//			if (this_v2_sc != null) {
//				v2_list.add(this_v2_sc);
//				log.debug(this_v2_sc.toString());
//			}
//
//		});

		// List<SubCategory> sc_list = new ArrayList<SubCategory>();
		//
		// sc_list_iter.forEach(sc_list::add);

		Map<String, Object> R = new HashMap<String, Object>();
		R.put("V2Rule", V2Rule);
//		R.put("DmodelMeasureMap", dmm_list_iter);
//		R.put("DmodelRatio", dr_list_iter);
//		R.put("SubCategory", v2_list);

		return R;

	}

	@Override
	@Transactional
	public String update(V2RuleDto updateDto) {
		log.debug("update");
		log.debug(updateDto.toString());
		V2Rule this_model = updateDto.getAsEntity();
		log.debug(this_model.toString());

		V2Rule db_model = v2_ruleRepository.findOne(this_model.getId()); 
		db_model.setTitle(this_model.getTitle()); 
		db_model.setUseYn("Y");
 

		return CommonConstants.SUCCESS;
	}
}