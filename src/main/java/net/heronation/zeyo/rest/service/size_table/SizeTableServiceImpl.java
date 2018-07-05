package net.heronation.zeyo.rest.service.size_table;

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
import org.joda.time.DateTime;
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
import net.heronation.zeyo.rest.common.value.ToggleDto;
import net.heronation.zeyo.rest.repository.fit_info.FitInfo;
import net.heronation.zeyo.rest.repository.fit_info.FitInfoRepository;
import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOption;
import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOptionRepository;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.item.ItemRepository;
import net.heronation.zeyo.rest.repository.item_bleach_map.ItemBleachMap;
import net.heronation.zeyo.rest.repository.item_bleach_map.ItemBleachMapRepository;
import net.heronation.zeyo.rest.repository.item_bleach_map.QItemBleachMap;
import net.heronation.zeyo.rest.repository.item_cloth_color_map.ItemClothColorMap;
import net.heronation.zeyo.rest.repository.item_cloth_color_map.ItemClothColorMapRepository;
import net.heronation.zeyo.rest.repository.item_cloth_color_map.QItemClothColorMap;
import net.heronation.zeyo.rest.repository.item_drycleaning_map.ItemDrycleaningMap;
import net.heronation.zeyo.rest.repository.item_drycleaning_map.ItemDrycleaningMapRepository;
import net.heronation.zeyo.rest.repository.item_drycleaning_map.QItemDrycleaningMap;
import net.heronation.zeyo.rest.repository.item_drymethod_map.ItemDrymethodMap;
import net.heronation.zeyo.rest.repository.item_drymethod_map.ItemDrymethodMapRepository;
import net.heronation.zeyo.rest.repository.item_drymethod_map.QItemDrymethodMap;
import net.heronation.zeyo.rest.repository.item_fit_info_option_map.ItemFitInfoOptionMap;
import net.heronation.zeyo.rest.repository.item_fit_info_option_map.ItemFitInfoOptionMapRepository;
import net.heronation.zeyo.rest.repository.item_fit_info_option_map.QItemFitInfoOptionMap;
import net.heronation.zeyo.rest.repository.item_ironing_map.ItemIroningMap;
import net.heronation.zeyo.rest.repository.item_ironing_map.ItemIroningMapRepository;
import net.heronation.zeyo.rest.repository.item_ironing_map.QItemIroningMap;
import net.heronation.zeyo.rest.repository.item_laundry_map.ItemLaundryMap;
import net.heronation.zeyo.rest.repository.item_laundry_map.ItemLaundryMapRepository;
import net.heronation.zeyo.rest.repository.item_laundry_map.QItemLaundryMap;
import net.heronation.zeyo.rest.repository.item_material_map.ItemMaterialMap;
import net.heronation.zeyo.rest.repository.item_material_map.ItemMaterialMapRepository;
import net.heronation.zeyo.rest.repository.item_material_map.QItemMaterialMap;
import net.heronation.zeyo.rest.repository.item_scmm_so_value.ItemScmmSoValue;
import net.heronation.zeyo.rest.repository.item_scmm_so_value.ItemScmmSoValueRepository;
import net.heronation.zeyo.rest.repository.item_scmm_so_value.QItemScmmSoValue;
import net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMap;
import net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMapRepository;
import net.heronation.zeyo.rest.repository.item_shopmall_map.QItemShopmallMap;
import net.heronation.zeyo.rest.repository.item_size_option_map.ItemSizeOptionMap;
import net.heronation.zeyo.rest.repository.item_size_option_map.ItemSizeOptionMapRepository;
import net.heronation.zeyo.rest.repository.item_size_option_map.QItemSizeOptionMap;
import net.heronation.zeyo.rest.repository.measure_item.MeasureItem;
import net.heronation.zeyo.rest.repository.size_table.QSizeTable;
import net.heronation.zeyo.rest.repository.size_table.SizeTable;
import net.heronation.zeyo.rest.repository.size_table.SizeTableDto;
import net.heronation.zeyo.rest.repository.size_table.SizeTableRepository;
import net.heronation.zeyo.rest.repository.sub_category_measure_map.SubCategoryMeasureMapRepository;

@Slf4j
@Service 
public class SizeTableServiceImpl implements SizeTableService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private SizeTableRepository size_tableRepository;

	@Autowired
	private ItemRepository itemRepository;

	
	@Autowired
	private FitInfoRepository fitInfoRepository;

	@Autowired
	private FitInfoOptionRepository fitInfoOptionRepository;

	@Value(value = "${zeyo.path.upload.temp}")
	private String path_temp_upload;

	@Value(value = "${zeyo.path.sizetable.image}")
	private String path_sizetable_upload;
	
	@Autowired
	EntityManager entityManager;

	@Override
	@Transactional(readOnly=true)
	public Map<String, Object> search(Map<String, Object> param, Pageable page) {

		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) ");

		StringBuffer select_query = new StringBuffer();
		select_query.append("SELECT ");
		select_query.append("    i.id				as item_id, ");
		select_query.append("    i.name				as item_name, ");
		select_query.append("    m.name				as member_name, ");
		select_query.append("    b.name				as brand_name, ");
		select_query.append("    s.name				as shopmall_name, ");
		// select_query.append(" c.name as category_name, ");
		// select_query.append(" sc.name as sub_category_name, ");
		// select_query.append(" i.price as price, ");
		select_query.append("    i.create_dt		as item_create_dt, ");
		select_query.append("    i.size_table_yn			as item_size_table_yn , ");
		select_query.append("( SELECT ");
		select_query.append("    cnh.name ");
		select_query.append("FROM ");
		select_query.append("    company_no_history cnh ");
		select_query.append("        INNER JOIN ");
		select_query.append("    (SELECT ");
		select_query.append("        MAX(id) AS id ");
		select_query.append("    FROM ");
		select_query.append("        company_no_history ");
		select_query.append(
				"    GROUP BY member_id) pcnh ON cnh.id = pcnh.id where cnh.member_id = m.id ) as company_name  , ");
		select_query.append("    st.table_image			as table_image  ");

		StringBuffer where_query = new StringBuffer();
		where_query.append("FROM ");
		where_query.append("    item i ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    member m ON i.member_id = m.id AND m.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    brand b ON i.brand_id = b.id AND b.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    item_shopmall_map ism ON i.id = ism.item_id AND ism.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    shopmall s ON ism.shopmall_id = s.id ");
		where_query.append("        AND s.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    category c ON i.category_id = c.id AND c.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    sub_category sc ON i.sub_category_id = sc.id ");
		where_query.append("        AND sc.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    size_table st ON i.id = st.item_id ");
		where_query.append("        AND st.use_yn = 'Y' ");
		where_query.append("WHERE ");
		where_query.append("    i.use_yn = 'Y'");

		// param.put("name", name);
		// param.put("company", company);
		// param.put("brand", brand);
		// param.put("shopmall", shopmall);
		// param.put("size_link", size_link);
		// param.put("category", category);
		// param.put("sub_category", sub_category);
		// param.put("start_price", start_price);
		// param.put("end_price", end_price);

		String name = (String) param.get("name");
		if (name != null) {
			where_query.append("        AND   i.name like '%" + name + "%' ");
		}

		String company = (String) param.get("company");
		if (company != null) {
			where_query.append("        AND   m.id = " + company + " ");
		}

		String brand = (String) param.get("brand");
		if (brand != null) {
			where_query.append("        AND   b.id = " + brand + " ");
		}

		String shopmall = (String) param.get("shopmall");
		if (shopmall != null) {
			where_query.append("        AND   s.id = " + shopmall + " ");
		}

		String size_table = (String) param.get("size_table");
		if (size_table != null && size_table.equals("Y")) {
			where_query.append("        AND   i.size_table_yn = 'Y' ");
		}

		String start_price = (String) param.get("start_price");
		if (start_price != null) {
			where_query.append("        AND   i.price >= " + start_price + " ");
		}

		String end_price = (String) param.get("end_price");
		if (end_price != null) {
			where_query.append("        AND   i.price <= " + end_price + " ");
		}

		String start = (String) param.get("start");
		if (start != null) {
			where_query.append("        AND i.create_dt >= STR_TO_DATE('" + start + "', '%Y-%m-%d %H:%i:%s')");
		}

		String end = (String) param.get("end");
		if (end != null) {
			where_query.append("        AND i.create_dt <= STR_TO_DATE('" + end + "', '%Y-%m-%d %H:%i:%s')");
		}

		StringBuffer sort_query = new StringBuffer();
		sort_query.append("  ORDER BY i.");
		Sort sort = page.getSort();
		String sep = "";
		for (Sort.Order order : sort) {
			sort_query.append(sep);
			sort_query.append(order.getProperty());
			sort_query.append(" ");
			sort_query.append(order.getDirection());
			sep = ", ";
		}

		StringBuffer page_query = new StringBuffer();
		page_query.append("  limit ");
		page_query.append((page.getPageNumber() - 1) * page.getPageSize());
		page_query.append(" , ");
		page_query.append(page.getPageSize());

		Query count_q = entityManager.createNativeQuery(count_query.append(where_query).toString()); 
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

			// select_query.append(" i.id as item_id, ");
			// select_query.append(" i.name as item_name, ");
			// select_query.append(" m.name as member_name, ");
			// select_query.append(" b.name as brand_name, ");
			// select_query.append(" s.name as shopmall_name, ");
			// select_query.append(" i.create_dt as item_create_dt, ");
			// select_query.append(" i.size_table_yn as item_size_table_yn , ");
			// select_query.append(" i.size_table_yn as company_name , ");

			search_R.put("item_id", row[0]);
			search_R.put("item_name", row[1]);
			search_R.put("member_name", row[2]);
			search_R.put("brand_name", row[3]);
			search_R.put("shopmall_name", row[4]);
			search_R.put("item_create_dt", row[5]);
			search_R.put("item_size_table_yn", row[6]);
			search_R.put("company_name", row[7]);
			search_R.put("table_image", row[8]);

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

		// QSizeOption target = QSizeOption.sizeOption;
		//
		//
		// QItem i = QItem.item;
		// QBrand b = QBrand.brand;
		// QShopmall s = QShopmall.shopmall;
		// QCategory c = QCategory.category;
		// QMember m = QMember.member;
		// QSubCategory sc = QSubCategory.subCategory;
		//
		// QCompanyNoHistory cnh = QCompanyNoHistory.companyNoHistory;
		// QItemShopmallMap ism = QItemShopmallMap.itemShopmallMap;
		//
		//
		// JPAQuery<Member> query = new JPAQuery<Member>(entityManager);
		//
		//
		// QueryResults<Tuple> R = query.select(
		// i.id,i.name,b.name,c.name,sc.name,i.price,i.createDt,i.sizeTableYn
		// ).from(i)
		// .innerJoin(i.brand,b)
		// .innerJoin(i.category,c)
		// .innerJoin(i.subCategory,sc)
		// .innerJoin(i.member,m)
		// //.innerJoin(m,cnh.member).on(cnh.id.in(JPAExpressions.select(cnh.id.max()).from(cnh).groupBy(cnh.member.id)))
		//
		// .where(b.useYn.eq("Y")
		// .and(c.useYn.eq("Y"))
		// .and(sc.useYn.eq("Y"))
		// .and(m.useYn.eq("Y")).and(where))
		//
		// .fetchResults();
		//
		// List<Tuple> search_list = R.getResults();
		// List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();
		//
		// for (Tuple row : search_list) {
		// Map<String, Object> search_R = new HashMap<String, Object>();
		//
		// search_R.put("item_id", row.get(i.id));
		// search_R.put("item_name", row.get(i.name));
		// // search_R.put("company_name", row.get(cnh.name));
		// search_R.put("brand_name", row.get(b.name));
		// //search_R.put("shopmall_list", row.get(i.itemShopmallMaps.size()));
		// search_R.put("category_name", row.get(c.name));
		// search_R.put("sub_category_name", row.get(sc.name));
		// search_R.put("price", row.get(i.price));
		// search_R.put("item_change_dt", row.get(i.createDt));
		// search_R.put("item_size_table_yn", row.get(i.sizeTableYn));
		//
		//
		// return_list.add(search_R);
		// }
		// return new PageImpl<Map<String, Object>>(return_list, page, R.getTotal());

	}

	@Override
	@Transactional(readOnly=true)
	public Map<String, Object> client_search(Map<String, Object> param, Pageable page) {
		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) ");

		StringBuffer select_query = new StringBuffer();
		select_query.append("SELECT ");
		select_query.append("    i.id					as item_id, ");
		select_query.append("    i.name					as item_name, ");
		select_query.append("    i.code					as item_code, ");
		select_query.append("    c.name					as category_name, ");
		select_query.append("    sc.name				as sub_category_name, ");
		select_query.append("    i.image				as item_image, ");
		select_query.append("    i.size_measure_image	as size_measure_image, ");
		select_query.append("    st.create_dt			as size_table_create_dt, ");
		select_query.append("    st.id 					as size_table_id, ");
		select_query.append("    st.table_image 					as size_table ");

		StringBuffer where_query = new StringBuffer();
		where_query.append(" FROM ");
		where_query.append("    item i ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    size_table st ON i.id = st.item_id AND st.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    category c ON i.category_id = c.id AND c.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    sub_category sc ON i.sub_category_id = sc.id ");
		where_query.append("        AND sc.use_yn = 'Y'");
		where_query.append(" WHERE ");
		where_query.append("    i.use_yn = 'Y'");

		Long member_id = (Long) param.get("member_seq");
		where_query.append("   AND  i.member_id = " + member_id);
		// param.put("name", name);
		// param.put("category", category);
		// param.put("subcate", subcate);
		// param.put("start_price", start_price);
		// param.put("end_price", end_price);
		// param.put("size_table", size_table);

		String name = (String) param.get("name");
		if (name != null) {
			where_query.append("        AND   i.name like '%" + name + "%' ");
		}

		String category = (String) param.get("category");
		if (category != null) {
			where_query.append("        AND   c.id = " + category + " ");
		}

		String subcate = (String) param.get("subcate");
		if (subcate != null) {
			where_query.append("        AND   sc.id = " + subcate + " ");
		}

		// String company = (String) param.get("company");
		// if (company != null) {
		// where_query.append(" AND m.id = " + company + " ");
		// }
		//
		// String brand = (String) param.get("brand");
		// if (brand != null) {
		// where_query.append(" AND b.id = " + brand + " ");
		// }
		//
		// String shopmall = (String) param.get("shopmall");
		// if (shopmall != null) {
		// where_query.append(" AND s.id = " + shopmall + " ");
		// }
		//
		//
		String size_table = (String) param.get("size_table");
		if (size_table != null && size_table.equals("Y")) {
			where_query.append("        AND   i.size_table_yn = 'Y' ");
		}

		String start_price = (String) param.get("start_price");
		if (start_price != null) {
			where_query.append("        AND   i.price >= " + start_price + " ");
		}

		String end_price = (String) param.get("end_price");
		if (end_price != null) {
			where_query.append("        AND   i.price <= " + end_price + " ");
		}

		String start = (String) param.get("start");
		if (start != null) {
			where_query.append("        AND i.create_dt >= STR_TO_DATE('" + start + "', '%Y-%m-%d %H:%i:%s')");
		}

		String end = (String) param.get("end");
		if (end != null) {
			where_query.append("        AND i.create_dt <= STR_TO_DATE('" + end + "', '%Y-%m-%d %H:%i:%s')");
		}

		StringBuffer sort_query = new StringBuffer();
		sort_query.append("  ORDER BY i.");
		Sort sort = page.getSort();
		String sep = "";
		for (Sort.Order order : sort) {
			sort_query.append(sep);
			sort_query.append(order.getProperty());
			sort_query.append(" ");
			sort_query.append(order.getDirection());
			sep = ", ";
		}

		StringBuffer page_query = new StringBuffer();
		page_query.append("  limit ");
		page_query.append((page.getPageNumber() - 1) * page.getPageSize());
		page_query.append(" , ");
		page_query.append(page.getPageSize());

		Query count_q = entityManager.createNativeQuery(count_query.append(where_query).toString());
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
			//
			// select_query.append(" i.id as item_id, ");
			// select_query.append(" i.name as item_name, ");
			// select_query.append(" i.code as item_code, ");
			// select_query.append(" c.name as category_name, ");
			// select_query.append(" sc.name as sub_category_name, ");
			// select_query.append(" i.image as item_image, ");
			// select_query.append(" i.size_measure_image as size_measure_image, ");
			// select_query.append(" st.create_dt as size_table_create_dt, ");
			// select_query.append(" st.id as size_table_id ");

			search_R.put("item_id", row[0]);
			search_R.put("item_name", row[1]);
			search_R.put("item_code", row[2]);
			search_R.put("category", row[3]);
			search_R.put("sub_cate", row[4]);
			search_R.put("item_image", row[5]);
			search_R.put("size_measure_image", row[6]);
			search_R.put("size_table_create_dt", row[7]);
			search_R.put("size_table_id", row[8]);
			search_R.put("table_image", row[9]);

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
	public String delete(List<ToggleDto> param, Long seq) {
	 
		for(ToggleDto tv : param) {
			Item i = itemRepository.findOne(tv.getId());

			if (!i.getMember().getId().equals(seq))
				continue;

			QSizeTable st = QSizeTable.sizeTable;
			SizeTable this_st = size_tableRepository.findOne(st.item.id.eq(tv.getId()).and(st.useYn.eq("Y")));
			this_st.setUseYn("N");			
		}
		return "Y";
	}

	@Override
	@Transactional
	public String batch_build(List<ToggleDto> param, Long seq) { 
		for(ToggleDto tv : param) {
			Item i = itemRepository.findOne(tv.getId());

			if (!i.getMember().getId().equals(seq))
				continue;

			QSizeTable st = QSizeTable.sizeTable;
			SizeTable this_st = size_tableRepository.findOne(st.item.id.eq(tv.getId()));

			if (this_st == null) {
				this_st = new SizeTable();
				this_st.setCreateDt(new DateTime());
				this_st.setItem(i);
				this_st.setUseYn("Y");
				this_st.setVisibleBasicYn("Y");
				this_st.setVisibleCodeYn("Y");
				this_st.setVisibleColorYn("Y");
				this_st.setVisibleFitInfoYn("Y");
				this_st.setVisibleItemImageYn("Y");
				this_st.setVisibleLaundryInfoYn("Y");
				this_st.setVisibleMeasureHowAYn("Y");
				this_st.setVisibleMeasureHowBYn("Y");
				this_st.setVisibleMeasureTableYn("Y");
				this_st.setVisibleNameYn("Y");
				
			} else {
				if (tv.getValue().equals("N")) {
					this_st.setUseYn("Y");
				} 
			}
 
		} 



		return "Y";
	}

	@Autowired
	private ItemBleachMapRepository itemBleachMapRepository;
	
	@Autowired
	private ItemClothColorMapRepository itemClothColorMapRepository;
	
	@Autowired
	private ItemDrycleaningMapRepository itemDrycleaningMapRepository;
	
	@Autowired
	private ItemDrymethodMapRepository itemDrymethodMapRepository;
	
	@Autowired
	private ItemFitInfoOptionMapRepository itemFitInfoOptionMapRepository;
	
	@Autowired
	private ItemIroningMapRepository  itemIroningMapRepository;
	
	@Autowired
	private ItemLaundryMapRepository itemLaundryMapRepository;
	
	@Autowired
	private ItemMaterialMapRepository itemMaterialMapRepository;
	
	@Autowired
	private ItemSizeOptionMapRepository itemSizeOptionMapRepository;
	
	
	@Autowired
	private ItemScmmSoValueRepository itemScmmSoValueRepository;
	
	
	@Autowired
	private ItemShopmallMapRepository itemShopmallMapRepository;
	
	
	@Autowired
	SubCategoryMeasureMapRepository subCategoryMeasureMapRepository;
	
	@Override
	@Transactional(readOnly=true)
	public Map<String, Object> preview(Long item_id) {

		Map<String, Object> R = new HashMap<String,Object>();
		Item i = itemRepository.findOne(item_id);
		
		R.put("item", i);
		
		QItemBleachMap ibm = QItemBleachMap.itemBleachMap;
		
		Iterable<ItemBleachMap> rimb = itemBleachMapRepository.findAll(ibm.item.id.eq(item_id).and(ibm.useYn.eq("Y"))) ;
		
		R.put("bleach", rimb);
		
		QItemClothColorMap iccm = QItemClothColorMap.itemClothColorMap;
		
		Iterable<ItemClothColorMap> riccm = itemClothColorMapRepository.findAll(iccm.item.id.eq(item_id).and(iccm.useYn.eq("Y"))) ;
		
		R.put("cloth_color", riccm);
		
		QItemDrycleaningMap idcm = QItemDrycleaningMap.itemDrycleaningMap;
		
		Iterable<ItemDrycleaningMap> ridcm = itemDrycleaningMapRepository.findAll(idcm.item.id.eq(item_id).and(idcm.useYn.eq("Y"))) ;
		
		R.put("dry_cleaning", ridcm);
		
		QItemDrymethodMap idmm = QItemDrymethodMap.itemDrymethodMap;
		
		Iterable<ItemDrymethodMap> ridmm = itemDrymethodMapRepository.findAll(idmm.item.id.eq(item_id).and(idmm.useYn.eq("Y"))) ;
		
		R.put("dry_method", ridmm);
		 
		
		
		QItemFitInfoOptionMap ifop = QItemFitInfoOptionMap.itemFitInfoOptionMap;
		
		Iterable<ItemFitInfoOptionMap> rifop = itemFitInfoOptionMapRepository.findAll(ifop.item.id.eq(item_id).and(ifop.useYn.eq("Y"))) ;
		
		R.put("user_select_fit_info_option", rifop);
		
		
		List<Map<String, Object>> fit_infos = new ArrayList<Map<String, Object>>();
		
		for(ItemFitInfoOptionMap m : rifop) {
			
			FitInfoOption this_option = m.getFitInfoOption();
			FitInfo this_info = this_option.getFitInfo();
			
			if(this_info.getUseYn().equals("Y")) {
				
				Map<String, Object> fitinfo_db = new HashMap<String,Object>();
				fitinfo_db.put("fit_info", this_info);
				log.debug("-----------------------------");
				log.debug(this_info.toString());
				//List<FitInfoOption> fio = fitInfoOptionRepository.select_options(this_info);
				List<FitInfoOption> fio = fitInfoOptionRepository.findByFitInfoId(this_info.getId());
				
				for(FitInfoOption a : fio) {
					log.debug(a.toString());
				}
				
				
				this_info.setFitInfoOptions(fio);
				
				fitinfo_db.put("fit_info_option", fio);
				
				fit_infos.add(fitinfo_db);	
			}
			
			
		}
		
		R.put("fit_infos", fit_infos);
		
		
		List<MeasureItem>  mm_list = subCategoryMeasureMapRepository.select_by_sub_cate(i.getSubCategory().getId());
		
 
		R.put("measure_items", mm_list);
		
		
		QItemIroningMap iim = QItemIroningMap.itemIroningMap;
		
		Iterable<ItemIroningMap> riim = itemIroningMapRepository.findAll(iim.item.id.eq(item_id).and(iim.useYn.eq("Y"))) ;
		
		R.put("ironing", riim);
		
		QItemLaundryMap ilm = QItemLaundryMap.itemLaundryMap;
		
		Iterable<ItemLaundryMap> rilm = itemLaundryMapRepository.findAll(ilm.item.id.eq(item_id).and(ilm.useYn.eq("Y"))) ;
		
		R.put("laundry", rilm);
		
		QItemMaterialMap imm = QItemMaterialMap.itemMaterialMap;
		
		Iterable<ItemMaterialMap> rimm = itemMaterialMapRepository.findAll(imm.item.id.eq(item_id).and(imm.useYn.eq("Y"))) ;
		
		R.put("material", rimm);
		
		QItemSizeOptionMap isom = QItemSizeOptionMap.itemSizeOptionMap;
		
		Iterable<ItemSizeOptionMap> risom = itemSizeOptionMapRepository.findAll(isom.item.id.eq(item_id).and(isom.useYn.eq("Y"))) ;
		
		R.put("size_option", risom);
	
		QItemScmmSoValue issv = QItemScmmSoValue.itemScmmSoValue;
		
		Iterable<ItemScmmSoValue> rissv = itemScmmSoValueRepository.findAll(issv.item.id.eq(item_id).and(issv.useYn.eq("Y"))) ;
	
		R.put("sccm_so_value", rissv);
		
		
		QItemShopmallMap ism = QItemShopmallMap.itemShopmallMap;
		
		Iterable<ItemShopmallMap> rism = itemShopmallMapRepository.findAll(ism.item.id.eq(item_id).and(ism.useYn.eq("Y"))) ;
 
		// 중요한 정보는 지우기
		for(ItemShopmallMap ism_item :rism) {
			if(ism_item.getShopmall() != null) {
				ism_item.getShopmall().setAccessToken("");
				ism_item.getShopmall().setOauthCode("");
				ism_item.getShopmall().setOauthID("");
				ism_item.getShopmall().setRefreshToken("");
			}
		}
		
		R.put("shopmall", rism);
		
		
		return  R;
	}

	@Override
	@Transactional
	public String modify(SizeTableDto param) throws CommonException{
		// TODO Auto-generated method stub
		SizeTable update_entity = param.convertToEntity();
		SizeTable old_st = size_tableRepository.findOne(update_entity.getId());
		
		old_st.setVisibleBasicYn(update_entity.getVisibleBasicYn());
		old_st.setVisibleCodeYn(update_entity.getVisibleCodeYn());
		old_st.setVisibleColorYn(update_entity.getVisibleColorYn());
		old_st.setVisibleFitInfoYn(update_entity.getVisibleFitInfoYn());
		old_st.setVisibleItemImageYn(update_entity.getVisibleBasicYn());
		old_st.setVisibleLaundryInfoYn(update_entity.getVisibleLaundryInfoYn());
		old_st.setVisibleMeasureHowAYn(update_entity.getVisibleMeasureHowAYn());
		old_st.setVisibleMeasureHowBYn(update_entity.getVisibleMeasureHowBYn());
		old_st.setVisibleMeasureTableYn(update_entity.getVisibleMeasureTableYn());
		old_st.setVisibleNameYn(update_entity.getVisibleNameYn());
	
		// 수정할때는 항사 ㅇ테이블 이미지가 올라온다.
		// 과거 이미지를 항상 지워줘야 한다.
		
		if(old_st.getTable_image() != null) {
			File old_file = new File(path_sizetable_upload.concat(File.separator).concat(old_st.getTable_image()));
			
			if(old_file.exists())
				old_file.delete();
		}
		
		old_st.setTable_image(update_entity.getTable_image());
		
		
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

		File source = new File(path_temp_upload
				.concat(File.separator).concat(dtf.format(now))
				.concat(File.separator).concat(param.getFile()));
		File dest = new File(path_sizetable_upload.concat(File.separator).concat(update_entity.getTable_image()));

		try {
			FileUtils.copyFile(source, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CommonException("image.upload.failed");
		}

		// 검수용
		CommandLine.Sync_file();
		
		return "Y";
	}
	
	@Override
	@Transactional
	public String create(SizeTableDto param) throws CommonException {
		// TODO Auto-generated method stub
		SizeTable create_entity = param.convertToEntity();
		
		
		Item this_item  = itemRepository.findOne(param.getItem_id());
		
		create_entity.setItem(this_item);
		size_tableRepository.save(create_entity);
		
		this_item.setSizeTableYn("Y");
		
		
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

		File source = new File(path_temp_upload
				.concat(File.separator).concat(dtf.format(now))
				.concat(File.separator).concat(param.getFile()));
		File dest = new File(path_sizetable_upload.concat(File.separator).concat(param.getFile().concat(".png")));

		try {
			FileUtils.copyFile(source, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CommonException("image.upload.failed");
		}
		

		// 검수용
		CommandLine.Sync_file();
		return "Y";
	}

	@Override
	public String update_item_image(SizeTableDto param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SizeTable single_infos(long id) {
		// TODO Auto-generated method stub
		SizeTable a =  size_tableRepository.findOne(id);
		a.setItem(null);
		
		return a;
	}
	
	
	
}