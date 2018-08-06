package net.heronation.zeyo.rest.dmodel.service;

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
import net.heronation.zeyo.rest.dmodel.controller.DmodelDto;
import net.heronation.zeyo.rest.dmodel.repository.Dmodel;
import net.heronation.zeyo.rest.dmodel.repository.DmodelRepository;
import net.heronation.zeyo.rest.dmodel_measure_map.repository.DmodelMeasureMap;
import net.heronation.zeyo.rest.dmodel_measure_map.repository.DmodelMeasureMapRepository;
import net.heronation.zeyo.rest.dmodel_measure_map.repository.QDmodelMeasureMap;
import net.heronation.zeyo.rest.dmodel_ratio.repository.DmodelRatio;
import net.heronation.zeyo.rest.dmodel_ratio.repository.DmodelRatioRepository;
import net.heronation.zeyo.rest.dmodel_ratio.repository.QDmodelRatio;
import net.heronation.zeyo.rest.measure_item.repository.MeasureItemRepository;
import net.heronation.zeyo.rest.sub_category.repository.QSubCategory;
import net.heronation.zeyo.rest.sub_category.repository.SubCategory;
import net.heronation.zeyo.rest.sub_category.repository.SubCategoryRepository;
import net.heronation.zeyo.rest.sub_category.repository.V2Category;
import net.heronation.zeyo.rest.sub_category.service.V2CategoryService;

@Slf4j
@Service
public class DmodelServiceImpl implements DmodelService {

	@Autowired
	private V2CategoryService v2Service;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DmodelRepository dmodelRepository;

	@Autowired
	private MeasureItemRepository mirepo;

	@Autowired
	private DmodelRatioRepository drrepo;

	@Autowired
	EntityManager entityManager;

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private DmodelMeasureMapRepository dmodelMeasureMapRepository;

	@Autowired
	private MeasureItemRepository measureItemRepository;
	@Autowired
	private DmodelRatioRepository dmodelRatioRepository;

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> search(Map<String, Object> param, Pageable page) {

		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) from ( ");

		// id bigint(20) PK
		// controller varchar(255)
		// create_dt datetime
		// svgdata varchar(255)
		// title varchar(255)
		// update_dt datetime
		// use_yn varchar(255)

		StringBuffer select_query = new StringBuffer();
		select_query.append("SELECT ");
		select_query.append("    d.id				as id, ");
		select_query.append("    d.title			as title, ");
		select_query.append("    d.update_dt		as update_dt ");

		StringBuffer where_query = new StringBuffer();
		where_query.append("FROM ");
		where_query.append("    dmodel d ");
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
			search_R.put("title", row[1]);
			search_R.put("update_dt", row[2]);

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

			Dmodel dm = dmodelRepository.findOne(Long.valueOf(tv));
			if (dm != null)
				dm.setUseYn("N");

		}

		return "Y";
	}

	@Override
	@Transactional
	public String insert(DmodelDto insertDto) {
		Dmodel this_model = insertDto.getAsEntity();

		
		List<DmodelMeasureMap> dmodelMeasureMaps = this_model.getDmodelMeasureMaps();
		List<DmodelRatio> dmodelRatios = this_model.getDmodelRatios();
		List<SubCategory> subCategorys = this_model.getSubCategorys();
		
		this_model.setDmodelMeasureMaps(null);
		this_model.setDmodelRatios(null);
		this_model.setSubCategorys(null);
		
		Dmodel db_model = dmodelRepository.save(this_model);



		for (DmodelMeasureMap this_dmm : dmodelMeasureMaps) {
			this_dmm.setDmodel(db_model);
			dmodelMeasureMapRepository.save(this_dmm);
		}

		for (DmodelRatio this_dmm : dmodelRatios) {
			this_dmm.setDmodel(db_model);
			dmodelRatioRepository.save(this_dmm);
		}

		for (SubCategory this_sc : subCategorys) {
			SubCategory db_sc = subCategoryRepository.findOne(this_sc.getId());
			db_sc.setDmodel(db_model);
		}

		return CommonConstants.SUCCESS;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> single(long id) {
		log.debug("single ");
		Dmodel dmodel = dmodelRepository.findOne(id);

		QDmodelMeasureMap qdmm = QDmodelMeasureMap.dmodelMeasureMap;

		Iterable<DmodelMeasureMap> dmm_list_iter = dmodelMeasureMapRepository
				.findAll(qdmm.dmodel.id.eq(id).and(qdmm.useYn.eq("Y")));

		// List<DmodelMeasureMap> dmm_list = new ArrayList<DmodelMeasureMap>();
		//
		// dmm_list_iter.forEach(dmm_list::add);

		QDmodelRatio qdr = QDmodelRatio.dmodelRatio;

		Iterable<DmodelRatio> dr_list_iter = dmodelRatioRepository.findAll(qdr.dmodel.id.eq(id).and(qdr.useYn.eq("Y")));

		// List<DmodelRatio> dr_list = new ArrayList<DmodelRatio>();
		//
		// dr_list_iter.forEach(dr_list::add);

		QSubCategory qsc = QSubCategory.subCategory;

		Iterable<SubCategory> sc_list_iter = subCategoryRepository.findAll(qsc.dmodel.id.eq(id).and(qsc.useYn.eq("Y")));
		List<V2Category> v2_list = new ArrayList<V2Category>();
		// 기존의 서브카테고리정보를 v2카테고리로 전환해서 입력해준다.
		sc_list_iter.forEach(sc -> {
			log.debug("sc.getId() " + sc.getId());
			V2Category this_v2_sc = v2Service.single_info(sc.getId());
			if (this_v2_sc != null) {
				v2_list.add(this_v2_sc);
				log.debug(this_v2_sc.toString());
			}

		});

		// List<SubCategory> sc_list = new ArrayList<SubCategory>();
		//
		// sc_list_iter.forEach(sc_list::add);

		Map<String, Object> R = new HashMap<String, Object>();
		R.put("Dmodel", dmodel);
		R.put("DmodelMeasureMap", dmm_list_iter);
		R.put("DmodelRatio", dr_list_iter);
		R.put("SubCategory", v2_list);

		return R;

	}

	@Override
	@Transactional
	public String update(DmodelDto updateDto) {
		log.debug("update");
		log.debug(updateDto.toString());
		Dmodel this_model = updateDto.getAsEntity();
		log.debug(this_model.toString());

		Dmodel db_model = dmodelRepository.findOne(this_model.getId());
		db_model.setController(this_model.getController());
		db_model.setSvgdata(this_model.getSvgdata());
		db_model.setTitle(this_model.getTitle());
		db_model.setUpdateDt(new DateTime());
		db_model.setUseYn("Y");

		QSubCategory qsc = QSubCategory.subCategory;

		Iterable<SubCategory> db_sc_list_iter = subCategoryRepository.findAll(qsc.dmodel.id.eq(this_model.getId()));

		List<SubCategory> user_sc_list = updateDto.getSubCategorys();
		List<SubCategory> db_sc_list = new ArrayList<SubCategory>();
		db_sc_list_iter.forEach(db_sc_list::add);

		// 새로 추가된 sub category를 dmodel과 연결 시킨다.
		for (SubCategory user_sc : user_sc_list) {

			boolean is_new_connected = true;

			for (SubCategory db_sc : db_sc_list) {
				if (db_sc.getId().compareTo(user_sc.getId()) == 0) {
					is_new_connected = false;
				}
			}

			log.debug(user_sc.getId() + "   " + is_new_connected);

			if (is_new_connected) {
				SubCategory new_connected_sc = subCategoryRepository.findOne(user_sc.getId());
				new_connected_sc.setDmodel(db_model);
				new_connected_sc.setUseYn("Y");
			}

		}

		// 기존에 연결괸 sub category중 유저가 삭제한것은 연결을 끊어준다.
		for (SubCategory db_sc : db_sc_list) {

			boolean did_user_delete_this = true;

			for (SubCategory user_sc : user_sc_list) {
				if (db_sc.getId().compareTo(user_sc.getId()) == 0) {
					did_user_delete_this = false;
				}
			}
			log.debug(db_sc.getId() + "   " + did_user_delete_this);

			if (did_user_delete_this) { // 유저가 삭제한 값이면 삭제한다.
				db_sc.setDmodel(null);
			}

		}

		List<DmodelMeasureMap> user_dmm_list = updateDto.getDmodelMeasureMaps();
		QDmodelMeasureMap qdmm = QDmodelMeasureMap.dmodelMeasureMap;
		Iterable<DmodelMeasureMap> db_dmm_list_iter = dmodelMeasureMapRepository
				.findAll(qdmm.dmodel.id.eq(this_model.getId()));
		List<DmodelMeasureMap> db_dmm_list = new ArrayList<DmodelMeasureMap>();
		db_dmm_list_iter.forEach(db_dmm_list::add);

		// 새로 추가된 Measure item 를 dmodel과 연결 시킨다.
		for (DmodelMeasureMap user_dmm : user_dmm_list) {

			boolean is_new_connected = true;
			boolean is_old_db_exist = false;
			for (DmodelMeasureMap db_dmm : db_dmm_list) {

				if (user_dmm.getId() == null) {
					is_new_connected = true;
				} else {
					if (db_dmm.getId().compareTo(user_dmm.getId()) == 0) {
						is_old_db_exist = true;
						if (db_dmm.getUseYn().equals("Y")) {
							is_new_connected = false;
						} else {
							is_new_connected = true;
						}

					}
				}

			}

			if (is_new_connected) {
				if (is_old_db_exist) {
					DmodelMeasureMap old_db = dmodelMeasureMapRepository.getOne(user_dmm.getId());
					old_db.setUseYn("Y");
					old_db.setDmodel(db_model);
					old_db.setMaxValue(user_dmm.getMaxValue());
					old_db.setMinValue(user_dmm.getMinValue());
					old_db.setMeasureItem(user_dmm.getMeasureItem());
				} else {
					user_dmm.setDmodel(db_model);
					dmodelMeasureMapRepository.save(user_dmm);
				}

			} else {
				DmodelMeasureMap old_db = dmodelMeasureMapRepository.getOne(user_dmm.getId());
				old_db.setUseYn("Y");
				old_db.setDmodel(db_model);
				old_db.setMaxValue(user_dmm.getMaxValue());
				old_db.setMinValue(user_dmm.getMinValue());
				old_db.setMeasureItem(user_dmm.getMeasureItem());
			}

		}

		// 기존에 연결괸 Measure item 중 유저가 삭제한것은 연결을 끊어준다.

		for (DmodelMeasureMap db_dmm : db_dmm_list) {

			boolean did_user_delete_this = true;

			for (DmodelMeasureMap user_dmm : user_dmm_list) {
				if (user_dmm.getId() == null) {
					did_user_delete_this = false;
				} else {
					if (db_dmm.getId().compareTo(user_dmm.getId()) == 0) {
						if (db_dmm.getUseYn().equals("Y")) {
							did_user_delete_this = false;
						} else {
							did_user_delete_this = true;
						}
					}
				}

			}

			if (did_user_delete_this) { // 유저가 삭제한 값이면 삭제한다.
				db_dmm.setUseYn("N");
			}

		}

		List<DmodelRatio> user_dr_list = updateDto.getDmodelRatios();
		QDmodelRatio qdr = QDmodelRatio.dmodelRatio;
		Iterable<DmodelRatio> db_dr_list_iter = dmodelRatioRepository.findAll(qdr.dmodel.id.eq(this_model.getId()));
		List<DmodelRatio> db_dr_list = new ArrayList<DmodelRatio>();
		db_dr_list_iter.forEach(db_dr_list::add);



		// 기존에 연결괸 QDmodelRatio 중 유저가 삭제한것은 연결을 끊어준다.

		for (DmodelRatio db_dr : db_dr_list) {

			boolean did_user_delete_this = true;

			for (DmodelRatio user_dr : user_dr_list) {

				if (db_dr.getId() == user_dr.getId()) {
					if (db_dr.getUseYn().equals("Y")) {
						did_user_delete_this = false;
					}
				}

			}

			if (did_user_delete_this) { // 유저가 삭제한 값이면 삭제한다.
				db_dr.setUseYn("N");
			}

		}
		
		
		// 새로 추가된QDmodelRatio 를 dmodel과 연결 시킨다.
		for (DmodelRatio user_dr : user_dr_list) {

			boolean is_new_connected = false;
			if(user_dr.getId().compareTo(0L) == 0) {
				is_new_connected = true;
			}

			if (is_new_connected) {
 
					user_dr.setDmodel(db_model);
					dmodelRatioRepository.save(user_dr);
				 
			} else {
				DmodelRatio old_db = dmodelRatioRepository.getOne(user_dr.getId());
				old_db.setUseYn("Y");
				old_db.setDmodel(db_model);
				old_db.setMaxValue(user_dr.getMaxValue());
				old_db.setMinValue(user_dr.getMinValue());
				old_db.setRatioValue(user_dr.getRatioValue());
			}

		}

		return CommonConstants.SUCCESS;
	}
}