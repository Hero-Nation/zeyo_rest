package net.heronation.zeyo.rest.sub_category.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.category.repository.Category;
import net.heronation.zeyo.rest.category.repository.CategoryRepository;
import net.heronation.zeyo.rest.common.constants.CommonConstants;
import net.heronation.zeyo.rest.common.controller.CommonException;
import net.heronation.zeyo.rest.common.dto.IdNameDto;
import net.heronation.zeyo.rest.common.dto.LIdMapIdDto;
import net.heronation.zeyo.rest.common.util.CommandLine;
import net.heronation.zeyo.rest.fit_info.repository.FitInfo;
import net.heronation.zeyo.rest.fit_info.repository.FitInfoRepository;
import net.heronation.zeyo.rest.measure_item.repository.MeasureItem;
import net.heronation.zeyo.rest.measure_item.repository.MeasureItemRepository;
import net.heronation.zeyo.rest.sub_category.controller.V2CategoryDto;
import net.heronation.zeyo.rest.sub_category.repository.SubCategory;
import net.heronation.zeyo.rest.sub_category.repository.SubCategoryRepository;
import net.heronation.zeyo.rest.sub_category.repository.V2Category;
import net.heronation.zeyo.rest.sub_category_fit_info_map.repository.QSubCategoryFitInfoMap;
import net.heronation.zeyo.rest.sub_category_fit_info_map.repository.SubCategoryFitInfoMap;
import net.heronation.zeyo.rest.sub_category_fit_info_map.repository.SubCategoryFitInfoMapRepository;
import net.heronation.zeyo.rest.sub_category_measure_map.repository.QSubCategoryMeasureMap;
import net.heronation.zeyo.rest.sub_category_measure_map.repository.SubCategoryMeasureMap;
import net.heronation.zeyo.rest.sub_category_measure_map.repository.SubCategoryMeasureMapRepository;

@Slf4j
@Service
@Transactional
public class V2CategoryServiceImpl implements V2CategoryService {

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

	@Value(value = "${v2.category.id}")
	private Long v2_category_id;

	@Value(value = "${server.list.ip}")
	private String server_list_ip;
	
	@Value(value = "${zeyo.path.upload.temp}")
	private String path_temp_upload;

	@Value(value = "${zeyo.path.subcategory.item.image}")
	private String path_subcategory_item_image;

	@Value(value = "${zeyo.path.subcategory.sizemeasure.image}")
	private String path_subcategory_sizemeasure_image;

	private List<V2Category> v2_list = Collections.synchronizedList(new ArrayList<V2Category>());
	private Map<Long, V2Category> map = new ConcurrentHashMap<Long, V2Category>();

	public void refresh() {
		log.debug("FUNCTION refresh ");

		List<SubCategory> all = sub_categoryRepository.findAll();

		v2_list.clear();
		map.clear();

		for (SubCategory sc : all) {

			// 현재 시스템은 트리방식과 단순방식이 혼합된 상태이다.
			// 트리방식의 카테고리의 category는 모두 V2로 설정되어있다.

			if (sc.getCategory().getId() == v2_category_id) {

				V2Category this_v2 = new V2Category();
				this_v2.setName(sc.getName());
				this_v2.setInfo(sc);

				v2_list.add(this_v2);
				map.put(sc.getId(), this_v2);

			}
		}

		log.debug("FUNCTION refresh list.size()");
		log.debug(v2_list.size() + "");
		log.debug("FUNCTION refresh map.size()");
		log.debug(map.size() + "");
		// 부모 값 가지고 오기 , 부하가 많이 걸라뭃,, list 에서 가지고 올때마다.. 없으면 가지고 온다
		// for (V2Category vc : list) {
		// vc.setParent(getParent(vc.getInfo().getParentId(), new
		// ArrayList<IdNameDto>()));
		// }

	}

	private List<IdNameDto> getParentList(long p_parent_id, List<IdNameDto> p_parent_list) {
//		log.debug("FUNCTION getParentList ");
//		log.debug("FUNCTION getParentList this_id");
//		log.debug(p_parent_id + "");
//		log.debug("FUNCTION getParentList list");
//		log.debug(p_parent_list.size() + "");

		V2Category parent_vc = map.get(p_parent_id);
		if (p_parent_id == 0L || parent_vc == null || parent_vc.getInfo() == null) {
			return p_parent_list;
		} else {

			IdNameDto indto = new IdNameDto();
			indto.setId(p_parent_id);

			if (parent_vc.getInfo().getUseYn().equals("N")) {
				indto.setName("[삭]" + parent_vc.getInfo().getName());
			} else {
				indto.setName(parent_vc.getInfo().getName());
			}

			p_parent_list.add(indto);

			return getParentList(parent_vc.getInfo().getParentId(), p_parent_list);
		}

	}

	private List<IdNameDto> getChildList(long p_parent_id, List<IdNameDto> p_child_list) {
//		log.debug("FUNCTION getChildList ");
//		log.debug("FUNCTION getChildList p_parent_id");
//		log.debug(p_parent_id + "");
//		log.debug("FUNCTION getChildList list");
//		log.debug(p_child_list.size() + "");

		for (V2Category vc : v2_list) {

//			log.debug((vc != null)+ "  1 ");
//			log.debug((vc.getInfo() != null)+ " 2 ");
//			log.debug((vc.getInfo().getParentId() != null)+ " 3 ");
			
			if (vc != null && vc.getInfo() != null && vc.getInfo().getParentId() != null && vc.getInfo().getParentId() == p_parent_id) {
//				log.debug("FUNCTION getChildList child");
//				log.debug(vc.getInfo().getId() + "  |  "+vc.getInfo().getParentId());
				IdNameDto ind = new IdNameDto();
				ind.setId(vc.getInfo().getId());
				ind.setName(vc.getInfo().getName());

				p_child_list.add(ind);
			}

		}

		log.debug("FUNCTION getChildList list RETURN");
		log.debug(p_child_list.size() + "");
		return p_child_list;
	}

	@Override
	public Map<String, Object> list(String p_name, Long p_parent_id, Pageable p_page) {
		// TODO Auto-generated method stub
		log.debug("FUNCTION list ");
		log.debug("FUNCTION list name");
		log.debug(p_name + "");
		log.debug("FUNCTION list parent_id");
		log.debug(p_parent_id + "");
		log.debug("FUNCTION list getPageSize");
		log.debug(p_page.getPageSize() + "");
		log.debug("FUNCTION list getPageNumber");
		log.debug(p_page.getPageNumber() + "");
		log.debug("FUNCTION list v2_list.size()");
		log.debug(v2_list.size() + "");

		int page_size = p_page.getPageSize();
		int page = p_page.getPageNumber();

		List<V2Category> search_list = new ArrayList<V2Category>();

		int count_index = 0;

		// 검색 이름이 없으면 parent_id로 목록을 만든다.
		if (p_name == null || p_name.equals("")) {

			//log.debug("FUNCTION list IF p_name == null");

			for (V2Category vc : v2_list) {
//				 log.debug("vc.getInfo() ");
//				 log.debug(vc.getInfo().getId()+" "+vc.getInfo().getParentId()+"");

				if (vc != null &&
						vc.getInfo() != null && 
								vc.getInfo().getParentId() != null &&
								vc.getInfo().getParentId().compareTo(p_parent_id) == 0) {

					//log.debug("FUNCTION list IF p_name == null |>| vc.getInfo().getParentId() == p_parent_id " + count_index);

					if ((count_index >= ((page - 1) * page_size)) && (count_index < (page * page_size))) {

						//log.debug( "FUNCTION list IF p_name == null |>| vc.getInfo().getParentId() == p_parent_id |>| if ((count_index >= ((page - 1) * page_size)) && (count_index < (page * page_size))) {");

						if (vc.getParent() == null && p_parent_id != 0L) {
							vc.setParent(getParentList(vc.getInfo().getParentId(), new ArrayList<IdNameDto>()));
						}

						if (vc.getChild() == null) {
							vc.setChild(getChildList(vc.getInfo().getId(), new ArrayList<IdNameDto>()));
						}

						search_list.add(vc);

					}

					// else {
					//
					// if ((count_index >= (page * page_size))) {
					// break;
					// }
					//
					// }

					count_index++;

				}

			}

			// 검색 결과가 있으면 검색결과를 중심으로 만든다.
		} else {
			//log.debug("FUNCTION list IF p_name != null");
			for (V2Category vc : v2_list) {
				// log.debug("vc.getInfo() ");
				// log.debug(vc.getInfo().getId()+" "+vc.getInfo().getParentId()+"");

				if (vc.getName().contains(p_name)) {

					//log.debug("FUNCTION list IF p_name != null |>| vc.getName().contains(p_name) " + count_index);

					if ((count_index >= ((page - 1) * page_size)) && (count_index < (page * page_size))) {
						//log.debug("FUNCTION list IF p_name != null |>| vc.getName().contains(p_name)  |>|  (count_index >= ((page - 1) * page_size)) && (count_index < (page * page_size)) ");
						
						if (vc.getParent() == null) {
							vc.setParent(getParentList(vc.getInfo().getParentId(), new ArrayList<IdNameDto>()));
						}

						if (vc.getChild() == null) {
							vc.setChild(getChildList(vc.getInfo().getId(), new ArrayList<IdNameDto>()));
						}

						search_list.add(vc);

					}
					// else {
					//
					// if ((count_index >= (page * page_size))) {
					// break;
					// }
					//
					// }

					count_index++;
				}

			}

		}

		int totalPages = (count_index / page_size);
		if (count_index % page_size > 0)
			totalPages = totalPages + 1;

		Map<String, Object> R = new HashMap<String, Object>();
		R.put("content", search_list);
		R.put("totalPages", totalPages);
		R.put("totalElements", count_index);
		R.put("number", page);
		R.put("size", page_size);

		return R;
	}

	@Override
	public void refresh_all() {
		log.debug("FUNCTION refresh_all ");
		String[] ip = server_list_ip.split(",");

	}

	@Override
	public V2Category single_info(Long p_id) {
		log.debug("FUNCTION single_info ");
		log.debug("FUNCTION single_info p_id");
		log.debug(p_id + "");

		V2Category this_v2 = map.get(p_id);

		if (this_v2 == null) {
			return null;
		}

		if (this_v2.getParent() == null) {
			this_v2.setParent(getParentList(this_v2.getInfo().getParentId(), new ArrayList<IdNameDto>()));
		}
		if (this_v2.getChild() == null) {
			this_v2.setChild(getChildList(this_v2.getInfo().getId(), new ArrayList<IdNameDto>()));
		}

		return map.get(p_id);
	}

	@Override
	@Transactional
	public void delete(List<Long> list) {
		log.debug("FUNCTION delete ");
		log.debug("FUNCTION delete list");
		log.debug(list.size() + "");

		for (Long id : list) {
			SubCategory sc = sub_categoryRepository.findOne(id);
			sc.setUseYn("N");
		}

		refresh_all();
	}

	@Override
	@Transactional
	public String insert(V2CategoryDto param)  throws CommonException  {
		log.debug(param.toString());
		Category c = categoryRepository.findOne(v2_category_id);
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
	public String update(V2CategoryDto param) throws CommonException {
		log.debug(param.toString());

		SubCategory sc = sub_categoryRepository.findOne(param.getId());
		sc.setBleachYn(param.getBleachYn());
		sc.setParentId(param.getParent_id());

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

}