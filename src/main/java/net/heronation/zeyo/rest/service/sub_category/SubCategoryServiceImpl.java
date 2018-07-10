package net.heronation.zeyo.rest.service.sub_category;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.CommonException;
import net.heronation.zeyo.rest.common.util.CommandLine;
import net.heronation.zeyo.rest.common.dto.LIdDto;
import net.heronation.zeyo.rest.common.dto.LIdMapIdDto;
import net.heronation.zeyo.rest.common.constants.CommonConstants;
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

	@Value(value = "${zeyo.path.upload.temp}")
	private String path_temp_upload;

	@Value(value = "${zeyo.path.subcategory.item.image}")
	private String path_subcategory_item_image;

	@Value(value = "${zeyo.path.subcategory.sizemeasure.image}")
	private String path_subcategory_sizemeasure_image;

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
	public Map<String, Object> subsearch(Map<String, Object> param, Pageable page) {

		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) from ( ");

		StringBuffer select_query = new StringBuffer();
		select_query.append("SELECT sc.id                                  AS subcate_id, ");
		select_query.append("       (SELECT Count(*) ");
		select_query.append("        FROM   sub_category_fit_info_map a ");
		select_query.append("        WHERE  a.use_yn = 'Y' ");
		select_query.append("               AND a.sub_category_id = sc.id) AS subCategoryFitInfoMaps_count, ");
		select_query.append("       (SELECT Count(*) ");
		select_query.append("        FROM   sub_category_measure_map a ");
		select_query.append("        WHERE  a.use_yn = 'Y' ");
		select_query.append("               AND a.sub_category_id = sc.id) AS subCategoryMeasureMaps_count, ");
		select_query.append("       sc.name                                AS subcate_name, ");
		select_query.append("       sc.cloth_image, ");
		select_query.append("       sc.item_image, ");
		select_query.append("       sc.create_dt ");

		StringBuffer where_query = new StringBuffer();
		where_query.append(" FROM   sub_category sc ");
		where_query.append(" WHERE  sc.use_yn = 'Y' ");

		String cate = (String) param.get("cate");
		if (cate != null) {
			where_query.append("       AND sc.category_id = " + cate);
		}

		String name = (String) param.get("name");
		if (name != null) {
			where_query.append("       AND sc.n ame = '%" + name + "%'");
		}

		String start = (String) param.get("start");
		if (start != null) {
			where_query.append("        AND sc.create_dt >= STR_TO_DATE('" + start + "', '%Y-%m-%d %H:%i:%s')");
		}

		String end = (String) param.get("end");
		if (end != null) {
			where_query.append("        AND sc.create_dt <= STR_TO_DATE('" + end + "', '%Y-%m-%d %H:%i:%s')");
		}

		StringBuffer group_query = new StringBuffer();

		// group_query.append(" GROUP BY sc.id ");

		StringBuffer sort_query = new StringBuffer();

		Sort sort = page.getSort();
		if (sort != null) {
			sort_query.append("  ORDER BY sc.");
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

		Query count_q = entityManager.createNativeQuery(count_query.append(select_query).append(where_query)
				.append(group_query).append(" ) count_table ").toString());
		BigInteger count_list = BigInteger.ZERO;

		List<BigInteger> count_result = count_q.getResultList();
		if (count_result.isEmpty()) {

		} else {
			count_list = count_result.get(0);
		}

		Query q = entityManager.createNativeQuery(
				select_query.append(where_query).append(group_query).append(sort_query).append(page_query).toString());
		List<Object[]> list = q.getResultList();

		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Object[] row : list) {
			Map<String, Object> search_R = new HashMap<String, Object>();

			// "content" : [ {
			// "subcate_id" : 7,
			// "subCategoryFitInfoMaps_count" : 2,
			// "subCategoryMeasureMaps_count" : 1,
			// "subcate_name" : "2차카테고리2",
			// "clothImage" : "hopangmen_1.jpg",
			// "createDt" : "2018-06-18T02:15:59.000Z",
			// "itemImage" : "다운로드.jpg"
			// } ],

			// sc.id AS subcate_id,
			// subCategoryFitInfoMaps_count,
			// subCategoryMeasureMaps_count,
			// sc.name AS subcate_name,
			// sc.cloth_image,
			// sc.item_image,
			// sc.create_dt

			search_R.put("subcate_id", row[0]);
			search_R.put("subCategoryFitInfoMaps_count", row[1]);
			search_R.put("subCategoryMeasureMaps_count", row[2]);
			search_R.put("subcate_name", row[3]);
			search_R.put("clothImage", row[4]);
			search_R.put("itemImage", row[5]);
			search_R.put("createDt", row[6]);

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

		// JPAQuery<Category> query = new JPAQuery<Category>(entityManager);
		//
		// QSubCategory sc = QSubCategory.subCategory;
		// QSubCategoryFitInfoMap scfi = QSubCategoryFitInfoMap.subCategoryFitInfoMap;
		// QSubCategoryMeasureMap scmm = QSubCategoryMeasureMap.subCategoryMeasureMap;
		//
		// PathBuilder<SubCategory> queryPath = new
		// PathBuilder<SubCategory>(SubCategory.class, "subCategory");
		//
		// for (Order order : page.getSort()) {
		// PathBuilder<Object> path = queryPath.get(order.getProperty());
		// query.orderBy(new
		// OrderSpecifier(com.querydsl.core.types.Order.valueOf(order.getDirection().name()),
		// path));
		// }
		//
		// QueryResults<Tuple> R = query
		// .select(sc.id, sc.name, sc.itemImage, sc.clothImage, scfi.id.countDistinct(),
		// scmm.id.countDistinct(),
		// sc.createDt)
		// .from(sc).leftJoin(sc.subCategoryFitInfoMaps, scfi).on(scfi.useYn.eq("Y"))
		// .leftJoin(sc.subCategoryMeasureMaps,
		// scmm).on(scmm.useYn.eq("Y")).groupBy(sc.id).where(where)
		// .offset((page.getPageNumber() - 1) *
		// page.getPageSize()).limit(page.getPageSize()).fetchResults();
		//
		// List<Tuple> search_list = R.getResults();
		// List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();
		//
		// for (Tuple row : search_list) {
		// Map<String, Object> search_R = new HashMap<String, Object>();
		//
		// search_R.put("subcate_name", row.get(sc.name));
		// search_R.put("subcate_id", row.get(sc.id));
		// search_R.put("itemImage", row.get(sc.itemImage));
		// search_R.put("clothImage", row.get(sc.clothImage));
		// search_R.put("subCategoryFitInfoMaps_count",
		// row.get(scfi.id.countDistinct()));
		// search_R.put("subCategoryMeasureMaps_count",
		// row.get(scmm.id.countDistinct()));
		// search_R.put("createDt", row.get(sc.createDt));
		//
		// return_list.add(search_R);
		// }
		// return new PageImpl<Map<String, Object>>(return_list, page, R.getTotal());

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
	public String insert(SubCategoryDto param) throws CommonException {

		log.debug(param.toString());
		Category c = categoryRepository.findOne(param.getCategory());
		SubCategory sc = param.convertToEntity();
		sc.setCategory(c);

		if (param.getClothImage() != null && param.getClothImage().size() > 0) {

			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

			File source = new File(path_temp_upload.concat(File.separator).concat(dtf.format(now))
					.concat(File.separator).concat(param.getClothImage().get(0).getTemp_name()));
			File dest = new File(path_subcategory_sizemeasure_image.concat(File.separator).concat(param.getClothImage()
					.get(0).getTemp_name().concat("_").concat(param.getClothImage().get(0).getReal_name())));

			try {
				FileUtils.copyFile(source, dest);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new CommonException("cloth.image.upload.failed");
			}

			sc.setClothImage(param.getClothImage().get(0).getTemp_name().concat("_")
					.concat(param.getClothImage().get(0).getReal_name()));
		}

		if (param.getItemImage() != null && param.getItemImage().size() > 0) {

			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

			File source = new File(path_temp_upload.concat(File.separator).concat(dtf.format(now))
					.concat(File.separator).concat(param.getItemImage().get(0).getTemp_name()));
			File dest = new File(path_subcategory_item_image.concat(File.separator).concat(param.getItemImage().get(0)
					.getTemp_name().concat("_").concat(param.getItemImage().get(0).getReal_name())));

			try {
				FileUtils.copyFile(source, dest);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new CommonException("item.image.upload.failed");
			}

			sc.setItemImage(param.getItemImage().get(0).getTemp_name().concat("_")
					.concat(param.getItemImage().get(0).getReal_name()));

		}

		sc = sub_categoryRepository.save(sc);

		List<LIdMapIdDto> milist = param.getMeasureItem();

		for (LIdMapIdDto vo : milist) {

			if (vo.getId() == 0) {
				continue;
			}

			SubCategoryMeasureMap temp_scmm = new SubCategoryMeasureMap();

			MeasureItem this_item = miRepository.findOne(vo.getId());
			temp_scmm.setSubCategory(sc);
			temp_scmm.setMeasureItem(this_item);
			temp_scmm.setUseYn("Y");

			scmmRepository.save(temp_scmm);
		}

		List<LIdMapIdDto> filist = param.getFitinfos();
		for (LIdMapIdDto vo : filist) {

			if (vo.getId() == 0) {
				continue;
			}

			SubCategoryFitInfoMap temp_scfi = new SubCategoryFitInfoMap();

			FitInfo this_item = fiRepository.findOne(vo.getId());

			temp_scfi.setSubCategory(sc);
			temp_scfi.setFitInfo(this_item);
			temp_scfi.setUseYn("Y");

			scfimRepository.save(temp_scfi);
		}

		// 검수용
		CommandLine.Sync_file();
		return CommonConstants.SUCCESS;
	}

	@Override
	@Transactional
	public String update(SubCategoryDto param) throws CommonException {
		log.debug(param.toString());

		SubCategory sc = sub_categoryRepository.findOne(param.getId());
		sc.setBleachYn(param.getBleachYn());

		if (param.getClothImage() != null && param.getClothImage().size() > 0) {

			if (sc.getClothImage() != null) {
				File old_image = new File(
						path_subcategory_sizemeasure_image.concat(File.separator).concat(sc.getClothImage()));

				if (old_image.exists())
					old_image.delete();
			}

			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

			File source = new File(path_temp_upload.concat(File.separator).concat(dtf.format(now))
					.concat(File.separator).concat(param.getClothImage().get(0).getTemp_name()));
			File dest = new File(path_subcategory_sizemeasure_image.concat(File.separator).concat(param.getClothImage()
					.get(0).getTemp_name().concat("_").concat(param.getClothImage().get(0).getReal_name())));

			try {
				FileUtils.copyFile(source, dest);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new CommonException("cloth.image.upload.failed");
			}

			sc.setClothImage(param.getClothImage().get(0).getTemp_name().concat("_")
					.concat(param.getClothImage().get(0).getReal_name()));
		}

		sc.setDrycleaningYn(param.getDrycleaningYn());
		sc.setDrymethodYn(param.getDrymethodYn());
		sc.setIroningYn(param.getIroningYn());

		if (param.getItemImage() != null && param.getItemImage().size() > 0) {

			if (sc.getItemImage() != null) {

				File old_image = new File(path_subcategory_item_image.concat(File.separator).concat(sc.getItemImage()));

				if (old_image.exists())
					old_image.delete();
			}

			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

			File source = new File(path_temp_upload.concat(File.separator).concat(dtf.format(now))
					.concat(File.separator).concat(param.getItemImage().get(0).getTemp_name()));
			File dest = new File(path_subcategory_item_image.concat(File.separator).concat(param.getItemImage().get(0)
					.getTemp_name().concat("_").concat(param.getItemImage().get(0).getReal_name())));

			try {
				FileUtils.copyFile(source, dest);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new CommonException("item.image.upload.failed");
			}

			sc.setItemImage(param.getItemImage().get(0).getTemp_name().concat("_")
					.concat(param.getItemImage().get(0).getReal_name()));

		}

		sc.setLaundryYn(param.getLaundryYn());
		sc.setName(param.getName());

		QSubCategoryMeasureMap qscmm = QSubCategoryMeasureMap.subCategoryMeasureMap;

		List<LIdMapIdDto> use_selected_measure_item = param.getMeasureItem();

		Iterable<SubCategoryMeasureMap> db_measure_item_list = scmmRepository
				.findAll(qscmm.subCategory.id.eq(param.getId()));

		for (LIdMapIdDto vo : use_selected_measure_item) {

			boolean is_this_option_added = true;

			for (SubCategoryMeasureMap db_scmm : db_measure_item_list) {

				if (db_scmm.getId() == vo.getMap_id()) {
					if (db_scmm.getUseYn().equals("Y")) {
						is_this_option_added = false;
					} else {
						is_this_option_added = false;
						db_scmm.setUseYn("Y");
					}

				}

			}

			if (is_this_option_added) { // 다시 선택한 값이 아니면 새로 추가 한다.

				MeasureItem new_mi = miRepository.findOne(vo.getId());

				SubCategoryMeasureMap new_map = new SubCategoryMeasureMap();
				new_map.setMeasureItem(new_mi);
				new_map.setSubCategory(sc);
				new_map.setUseYn("Y");

				scmmRepository.save(new_map);
			}

		}

		for (SubCategoryMeasureMap db_scmm : db_measure_item_list) {

			boolean did_user_delete_this_option = true;

			for (LIdMapIdDto vo : use_selected_measure_item) {

				if (db_scmm.getId() == vo.getMap_id()) {

					if (db_scmm.getUseYn().equals("Y")) {
						did_user_delete_this_option = false;
					} else {
						did_user_delete_this_option = false;
						db_scmm.setUseYn("N");
					}
				}

			}

			if (did_user_delete_this_option) { // 다시 선택한 값이 아니면 새로 추가 한다.

				db_scmm.setUseYn("N");
				// scmmRepository.save(db_scmm);
			}

		}

		QSubCategoryFitInfoMap qscfim = QSubCategoryFitInfoMap.subCategoryFitInfoMap;

		Iterable<SubCategoryFitInfoMap> db_scfi_list = scfimRepository.findAll(qscfim.subCategory.id.eq(param.getId()));

		List<LIdMapIdDto> user_selected_fitinfo_list = param.getFitinfos();

		for (LIdMapIdDto vo : user_selected_fitinfo_list) {

			boolean is_this_option_added = true;

			for (SubCategoryFitInfoMap scfi : db_scfi_list) {

				if (scfi.getId() == vo.getMap_id()) {

					if (scfi.getUseYn().equals("Y")) {
						is_this_option_added = false;
					} else {
						is_this_option_added = false;
						scfi.setUseYn("Y");
					}

				}

			}

			if (is_this_option_added) { // 다시 선택한 값이 아니면 새로 추가 한다.

				FitInfo new_fi = fiRepository.findOne(vo.getId());

				SubCategoryFitInfoMap new_map = new SubCategoryFitInfoMap();
				new_map.setFitInfo(new_fi);
				new_map.setSubCategory(sc);
				new_map.setUseYn("Y");

				scfimRepository.save(new_map);
			}

		}

		for (SubCategoryFitInfoMap scfi : db_scfi_list) {

			boolean did_user_delete_this_option = true;

			for (LIdMapIdDto vo : user_selected_fitinfo_list) {

				if (scfi.getId() == vo.getMap_id()) {

					if (scfi.getUseYn().equals("Y")) {
						did_user_delete_this_option = false;
					} else {
						did_user_delete_this_option = false;
						scfi.setUseYn("N");
					}

				}

			}

			if (did_user_delete_this_option) { // 다시 선택한 값이 아니면 새로 추가 한다.

				scfi.setUseYn("N");
				// scfimRepository.save(scfi);
			}

		}

		// 검수용
		CommandLine.Sync_file();

		return CommonConstants.SUCCESS;
	}

	private Exception CommonException(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public String delete(List<LIdDto> param) {

		for (LIdDto v : param) {
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

		Iterable<SubCategoryFitInfoMap> scfimr = scfimRepository
				.findAll(scfi.subCategory.id.eq(id).and(scfi.useYn.eq("Y")));
		Iterable<SubCategoryMeasureMap> scmmr = scmmRepository
				.findAll(scmm.subCategory.id.eq(id).and(scmm.useYn.eq("Y")));

		R.put("sub_category", scr);
		R.put("fit_infos", scfimr);
		R.put("measure_items", scmmr);

		return R;
	}

}