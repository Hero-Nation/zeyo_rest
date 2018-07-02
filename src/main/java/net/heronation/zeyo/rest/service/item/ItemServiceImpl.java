package net.heronation.zeyo.rest.service.item;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.querydsl.core.BooleanBuilder;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.CommonException;
import net.heronation.zeyo.rest.common.util.CommandLine;
import net.heronation.zeyo.rest.common.value.FileDto;
import net.heronation.zeyo.rest.common.value.LIdDto;
import net.heronation.zeyo.rest.common.value.LIdMapIdDto;
import net.heronation.zeyo.rest.common.value.ToggleDto;
import net.heronation.zeyo.rest.constants.CommonConstants;
import net.heronation.zeyo.rest.controller.item.ItemImageUploadDto;
import net.heronation.zeyo.rest.controller.item.ItemSizeMeasureImageUploadDto;
import net.heronation.zeyo.rest.repository.brand.BrandRepository;
import net.heronation.zeyo.rest.repository.category.CategoryRepository;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColor;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColorRepository;
import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOption;
import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOptionRepository;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.item.ItemBuildDto;
import net.heronation.zeyo.rest.repository.item.ItemDto;
import net.heronation.zeyo.rest.repository.item.ItemModifyDto;
import net.heronation.zeyo.rest.repository.item.ItemRepository;
import net.heronation.zeyo.rest.repository.item.QItem;
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
import net.heronation.zeyo.rest.repository.madein.MadeinRepository;
import net.heronation.zeyo.rest.repository.material.Material;
import net.heronation.zeyo.rest.repository.material.MaterialRepository;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.MemberRepository;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
import net.heronation.zeyo.rest.repository.shopmall.ShopmallRepository;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;
import net.heronation.zeyo.rest.repository.size_option.SizeOptionRepository;
import net.heronation.zeyo.rest.repository.size_table.QSizeTable;
import net.heronation.zeyo.rest.repository.size_table.SizeTable;
import net.heronation.zeyo.rest.repository.size_table.SizeTableRepository;
import net.heronation.zeyo.rest.repository.sub_category.SubCategoryRepository;
import net.heronation.zeyo.rest.repository.warranty.Warranty;
import net.heronation.zeyo.rest.repository.warranty.WarrantyRepository;

@Slf4j
@Service
@Transactional
public class ItemServiceImpl implements ItemService {
	
	@Value(value = "${zeyo.path.subcategory.item.image}")
	private String path_subcategory_item_image;

	@Value(value = "${zeyo.path.subcategory.sizemeasure.image}")
	private String path_subcategory_sizemeasure_image;
	
	@Value(value = "${zeyo.path.upload.temp}")
	private String path_temp_upload;

	@Value(value = "${zeyo.path.client.image}")
	private String path_subcategory_client_image;
	
	
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private SizeTableRepository sizeTableRepository;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private ShopmallRepository shopmallRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private MadeinRepository madeinRepository;

	@Autowired
	private WarrantyRepository warrantyRepository;

	@Autowired
	private MaterialRepository materialRepository;

	@Autowired
	private SizeOptionRepository sizeOptionRepository;

	@Autowired
	private ClothColorRepository clothColorRepository;

	@Autowired
	private FitInfoOptionRepository fitInfoOptionRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private ItemRepository itemRepository;

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
	private ItemIroningMapRepository itemIroningMapRepository;

	@Autowired
	private ItemLaundryMapRepository itemLaundryMapRepository;

	@Autowired
	private ItemMaterialMapRepository itemMaterialMapRepository;

	@Autowired
	private ItemShopmallMapRepository itemShopmallMapRepository;

	@Autowired
	private ItemSizeOptionMapRepository itemSizeOptionMapRepository;

	@Autowired
	private ItemScmmSoValueRepository itemScmmSoValueRepository;

	@Autowired
	EntityManager entityManager;

	@Override
	public Map<String, Object> search(Map<String, Object> param, Pageable page) {

		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) from ( ");

		StringBuffer select_query = new StringBuffer();
		select_query.append("SELECT ");
		select_query.append("    i.id				as item_id, ");
		select_query.append("    i.name				as item_name, ");
		select_query.append("    m.name				as member_name, ");
		select_query.append("    b.name				as brand_name, ");
		select_query.append("    s.name				as shopmall_name, ");
		select_query.append("    c.name				as category_name, ");
		select_query.append("    sc.name			as sub_category_name, ");
		select_query.append("    i.price			as price, ");
		select_query.append("    i.create_dt		as item_change_dt, ");
		select_query.append("    i.link_yn			as item_link_yn , ");
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
				"    GROUP BY member_id) pcnh ON cnh.id = pcnh.id where cnh.member_id = m.id ) as company_name   ");

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

		String size_link = (String) param.get("size_link");
		if (size_link != null && size_link.equals("Y")) {
			where_query.append("        AND   i.link_yn = 'Y' ");
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

		Query count_q = entityManager.createNativeQuery(count_query.append(select_query).append(where_query).append(" ) count_table ") .toString());
		
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
			// select_query.append(" c.name as category_name, ");
			// select_query.append(" sc.name as sub_category_name, ");
			// select_query.append(" i.price as price, ");
			// select_query.append(" i.create_dt as item_change_dt, ");
			// select_query.append(" i.link_yn as item_link_yn ");
			// select_query.append(" i.link_yn as company_name ");

			search_R.put("item_id", row[0]);
			search_R.put("item_name", row[1]);
			search_R.put("member_name", row[2]);
			search_R.put("brand_name", row[3]);
			search_R.put("shopmall_name", row[4]);
			search_R.put("category_name", row[5]);
			search_R.put("sub_category_name", row[6]);
			search_R.put("price", row[7]);
			search_R.put("item_change_dt", row[8]);
			search_R.put("item_link_yn", row[9]);
			search_R.put("company_name", row[10]);

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
	public String toggle_link(List<ToggleDto> param, Long seq) {
		// TODO Auto-generated method stub

		for (ToggleDto tv : param) {

			Item i = itemRepository.findOne(tv.getId());

			if (!i.getMember().getId().equals(seq))
				continue;

			String linkYN = i.getLinkYn();
			if (linkYN == null) {

			} else {
				i.setLinkYn(tv.getValue());
			}
		}

		return "Y";
	}

	@Override
	@Transactional
	public String delete(List<ToggleDto> param, Long seq) {
		for (ToggleDto tv : param) {

			Item i = itemRepository.findOne(tv.getId());

			if (!i.getMember().getId().equals(seq))
				continue;

			i.setUseYn("N");

		}

		return "Y";
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> shopmall_list(Long item_id, Pageable page) {

		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) ");

		StringBuffer select_query = new StringBuffer();
		select_query.append("SELECT ");
		select_query.append("    i.name as item_name ");
		select_query.append("   ,s.name as shopmall_name ");
		select_query.append("   ,b.name as brand_name  ");

		StringBuffer where_query = new StringBuffer();
		where_query.append("FROM ");
		where_query.append("    item_shopmall_map ism ");
		where_query.append("        INNER JOIN ");
		where_query.append("    shopmall s ON ism.shopmall_id = s.id ");
		where_query.append("        INNER JOIN ");
		where_query.append("    item i ON ism.item_id = i.id ");
		where_query.append("        INNER JOIN ");
		where_query.append("    brand b ON i.brand_id = b.id ");
		where_query.append("WHERE ");
		where_query.append("    ism.use_yn = 'Y' ");
		where_query.append("        AND s.use_yn = 'Y' ");
		where_query.append("        AND b.use_yn = 'Y' ");
		where_query.append("        AND i.use_yn = 'Y' ");
		where_query.append("        AND i.id = 5");

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
		List<Map<String, Object>> count_list = count_q.getResultList();

		Query q = entityManager
				.createNativeQuery(select_query.append(where_query).append(sort_query).append(page_query).toString());
		List<Object[]> list = q.getResultList();

		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Object[] row : list) {
			Map<String, Object> search_R = new HashMap<String, Object>();

			// select_query.append(" i.name as item_name ");
			// select_query.append(" ,s.name as shopmall_name ");
			// select_query.append(" ,b.name as brand_name ");

			search_R.put("item_name", row[0]);
			search_R.put("shopmall_name", row[1]);
			search_R.put("brand_name", row[2]);

			return_list.add(search_R);
		}

		int totalPages = (count_list.size() / page.getPageSize());
		if (count_list.size() % page.getPageSize() > 0)
			totalPages = totalPages + 1;

		Map<String, Object> R = new HashMap<String, Object>();
		R.put("content", return_list);
		R.put("totalPages", totalPages);
		R.put("totalElements", count_list.size());
		R.put("number", page.getPageNumber());
		R.put("size", return_list.size());

		return R;
	}

	private void build_size_table() throws CommonException {

	}

	@Override
	@Transactional
	public String toggle_size_table(List<ToggleDto> param) {

		for (ToggleDto tv : param) {

			Item item = itemRepository.findOne(tv.getId());

			if (item == null) {
				continue;
			} else {

				String table = item.getSizeTableYn();
				QSizeTable st = QSizeTable.sizeTable;

				if (table.equals("N")) {

					SizeTable db_st = sizeTableRepository.findOne(st.item.id.eq(tv.getId()));

					if (db_st == null) {

						SizeTable new_st = new SizeTable();
						// 정보입력

						new_st.setCreateDt(new DateTime());
						new_st.setUseYn("Y");
						new_st.setItem(item);
						new_st.setVisibleBasicYn("Y");
						new_st.setVisibleCodeYn("Y");
						new_st.setVisibleColorYn("Y");
						new_st.setVisibleFitInfoYn("Y");
						new_st.setVisibleItemImageYn("Y");
						new_st.setVisibleLaundryInfoYn("Y");
						new_st.setVisibleMeasureHowAYn("Y");
						new_st.setVisibleMeasureHowBYn("Y");
						new_st.setVisibleMeasureTableYn("Y");
						new_st.setVisibleNameYn("Y");

						sizeTableRepository.save(new_st);

					} else {

						db_st.setUseYn("Y");
						item.setSizeTableYn("Y");

					}

					item.setSizeTableYn("Y");
				} else {
					item.setSizeTableYn("N");
				}
			}

		}

		return "S";
	}

	@Override
	@Transactional
	public Item build(ItemBuildDto ibd, Long member_id) {
		log.debug("build");

		Item new_item = new Item();
		Member user = memberRepository.findOne(member_id);

		new_item.setCode(ibd.getCode());
		new_item.setCreateDt(new DateTime());
		new_item.setBleachYn(ibd.getBleachYn());
		new_item.setDrycleaningYn(ibd.getDrycleaningYn());
		new_item.setDrymethodYn(ibd.getDrymethodYn());
		new_item.setIroningYn(ibd.getIroningYn());
		new_item.setLaundryYn(ibd.getLaundryYn());

		new_item.setImage(ibd.getImage());
		new_item.setImageMode(ibd.getImageMode());
		new_item.setMadeinBuilder(ibd.getMadeinBuilder());
		new_item.setMadeinDate(ibd.getMadeinDate());
		new_item.setName(ibd.getName());
		new_item.setPrice(ibd.getPrice());
		new_item.setSizeMeasureImage(ibd.getSizeMeasureImage());
		new_item.setSizeMeasureMode(ibd.getSizeMeasureMode());

		if (ibd.getBrand() != null)
			brandRepository.save(ibd.getBrand());

		categoryRepository.save(ibd.getCategory());
		madeinRepository.save(ibd.getMadein());
		subCategoryRepository.save(ibd.getSubCategory());
		warrantyRepository.save(ibd.getWarranty());

		new_item.setBrand(ibd.getBrand());
		new_item.setCategory(ibd.getCategory());
		new_item.setMadein(ibd.getMadein());
		new_item.setMember(user);
		new_item.setSubCategory(ibd.getSubCategory());
		new_item.setWarranty(ibd.getWarranty());
		new_item.setLinkYn("N");
		new_item.setUseYn("Y");

		// 사이즈 테이블은 무조건 N으로 설정한다.
		// 화면에서 생성을 누르고 사이즈표가 입력이 되면 그때 Y로 해준다. 
		new_item.setSizeTableYn("N");
		
		
		new_item = itemRepository.save(new_item);


		
		
		List<Shopmall> isms = ibd.getShopmalls();
		if (isms.size() != 0) {

			List<ItemShopmallMap> ismms = new ArrayList<ItemShopmallMap>();
			for (Shopmall sm : isms) {
				ItemShopmallMap ismm = new ItemShopmallMap();
				ismm.setItem(new_item);
				ismm.setShopmall(sm);
				ismm.setUseYn("Y");

				ismms.add(ismm);
			}

			itemShopmallMapRepository.save(ismms);
		}

		if (ibd.getBleachYn().equals("Y")) {
			ItemBleachMap item_bleach = ibd.getItemBleachMap();
			item_bleach.setItem(new_item);

			itemBleachMapRepository.save(item_bleach);
		}

		List<ItemClothColorMap> icclist = ibd.getItemClothColorMaps();

		if (icclist.size() != 0) {
			for (ItemClothColorMap iccitem : icclist) {
				iccitem.setItem(new_item);
				if (iccitem.getOptionValue().equals("DIRECT")) {
					ClothColor injected_cloth_color = clothColorRepository.save(iccitem.getClothColor());

					iccitem.setClothColor(injected_cloth_color);
				}
			}
			itemClothColorMapRepository.save(icclist);
		}

		if (ibd.getDrycleaningYn().equals("Y")) {
			ItemDrycleaningMap idcm = ibd.getItemDrycleaningMap();
			idcm.setItem(new_item);
			itemDrycleaningMapRepository.save(idcm);
		}

		if (ibd.getDrymethodYn().equals("Y")) {
			ItemDrymethodMap idm = ibd.getItemDrymethodMap();
			idm.setItem(new_item);
			itemDrymethodMapRepository.save(idm);
		}

		List<ItemFitInfoOptionMap> ifiolist = ibd.getItemFitInfoOptionMaps();

		if (ifiolist.size() != 0) {
			for (ItemFitInfoOptionMap ifio : ifiolist) {
				ifio.setItem(new_item);
			}
			itemFitInfoOptionMapRepository.save(ifiolist);
		}

		if (ibd.getIroningYn().equals("Y")) {
			ItemIroningMap iim = ibd.getItemIroningMap();
			iim.setItem(new_item);
			itemIroningMapRepository.save(iim);
		}

		if (ibd.getLaundryYn().equals("Y")) {
			ItemLaundryMap ilm = ibd.getItemLaundryMap();
			ilm.setItem(new_item);
			itemLaundryMapRepository.save(ilm);
		}

		Map<String, Object> direct_size_option_store = new HashMap<String, Object>();

		List<ItemSizeOptionMap> isomlist = ibd.getItemSizeOptionMaps();

		if (isomlist.size() != 0) {
			for (ItemSizeOptionMap isom : isomlist) {
				isom.setItem(new_item);
				if (isom.getOptionValue().equals("DIRECT")) {
					SizeOption injected_size_option = sizeOptionRepository.save(isom.getSizeOption());
					isom.setSizeOption(injected_size_option);

					direct_size_option_store.put(injected_size_option.getName(), injected_size_option);
				}
			}
			itemSizeOptionMapRepository.save(isomlist);
		}

		List<ItemMaterialMap> imms = ibd.getMaterials();

		if (imms.size() != 0) {
			for (ItemMaterialMap imm : imms) {
				imm.setItem(new_item);
			}
			itemMaterialMapRepository.save(imms);
		}

		List<ItemScmmSoValue> issv_list = ibd.getItemScmmSoValues();

		if (issv_list.size() != 0) {
			for (ItemScmmSoValue issv : issv_list) {
				issv.setItem(new_item);

				if (issv.getSizeOption().getId() == 0L) {
					issv.setSizeOption((SizeOption) direct_size_option_store.get(issv.getSizeOption().getName()));
				}
			}

			itemScmmSoValueRepository.save(issv_list);
		}

		return new_item;
	}

	@Override
	public Map<String, Object> client_search(Map<String, Object> param, Pageable page) {

		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) from ( ");

		StringBuffer select_query = new StringBuffer();
		select_query.append("SELECT ");
		select_query.append("    i.id					as item_id, ");
		select_query.append("    i.name					as item_name, ");
		select_query.append("    i.code					as item_code, ");
		select_query.append("    c.name					as category_name, ");
		select_query.append("    sc.name				as sub_category_name, ");
		select_query.append("    i.price				as item_price, ");
		select_query.append("    i.create_dt			as item_create_dt, ");
		select_query.append("    i.link_yn				as item_link_yn, ");
		select_query.append("    i.size_table_yn		as item_size_table_yn, ");
		select_query.append("    st.id					as size_table_id, ");
		select_query.append("    st.table_image					as table_image ");

		
		StringBuffer where_query = new StringBuffer();

		where_query.append("FROM ");
		where_query.append("    item i ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    category c ON i.category_id = c.id  and c.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    sub_category sc ON i.sub_category_id = sc.id and sc.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    size_table st ON i.id = st.item_id  and st.use_yn = 'Y'  ");
		where_query.append("WHERE ");
		where_query.append("    i.use_yn = 'Y' ");
		// where_query.append(" AND c.use_yn = 'Y' ");
		// where_query.append(" AND sc.use_yn = 'Y'");

		String member_id = (String) param.get("member_id");

		where_query.append("        AND i.member_id = " + member_id + " ");

		// param.put("name", name);
		// param.put("size_link", size_link);
		// param.put("category", category);
		// param.put("sub_category", sub_category);
		// param.put("start_price", start_price);
		// param.put("end_price", end_price);

		String name = (String) param.get("name");
		if (name != null) {
			where_query.append("        AND i.name like '%" + name + "%' ");
		}

		String size_link = (String) param.get("size_link");
		if (size_link != null && size_link.equals("Y")) {
			where_query.append("        AND i.link_yn = 'Y' ");
		}

		String category = (String) param.get("category");
		if (category != null) {
			where_query.append("        AND i.category_id = " + category + " ");
		}

		String sub_category = (String) param.get("sub_category");
		if (sub_category != null) {
			where_query.append("        AND i.sub_category_id = " + sub_category + " ");
		}

		String start_price = (String) param.get("start_price");
		if (start_price != null) {
			where_query.append("        AND i.price >= " + start_price + " ");
		}

		String end_price = (String) param.get("end_price");
		if (end_price != null) {
			where_query.append("        AND i.price <= " + end_price + " ");
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

			// select_query.append(" i.id as item_id, ");
			// select_query.append(" i.name as item_name, ");
			// select_query.append(" i.code as item_code, ");
			// select_query.append(" c.name as category_name, ");
			// select_query.append(" sc.name as sub_category_name, ");
			// select_query.append(" i.price as item_price, ");
			// select_query.append(" i.create_dt as item_create_dt, ");
			// select_query.append(" i.link_yn as item_link_yn, ");
			// select_query.append(" i.size_table_yn as item_size_table_yn, ");
			// select_query.append(" st.id as size_table_id ");

			search_R.put("item_id", row[0]);
			search_R.put("item_name", row[1]);
			search_R.put("item_code", row[2]);
			search_R.put("category_name", row[3]);
			search_R.put("sub_category_name", row[4]);
			search_R.put("item_price", row[5]);
			search_R.put("item_create_dt", row[6]);
			search_R.put("item_link_yn", row[7]);
			search_R.put("item_size_table_yn", row[8]);
			search_R.put("size_table_id", row[9]);
			search_R.put("table_image", row[10]);

			
			
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
	@Transactional(readOnly = true)
	public Map<String, Object> getStat() {
		// 상품 정보 관리 현황

		// 제조국
		// 총 등록 수 : 1231
		// 신규 등록 수 : 14
		// 관리자입력 : 5
		// 직접입력 : 5

		// 품질보증기간
		// 총 등록 수 : 1231
		// 신규 등록 수 : 1231
		// 관리자입력 : 1231
		// 직접입력 : 5

		// 소재
		// 총 등록 수 : 1231
		// 신규 등록 수 : 14
		// 관리자입력 : 5
		// 직접입력 : 5

		// 옵션
		// 총 등록 수 : 1231
		// 신규 등록 수 : 14
		// 관리자입력 : 5
		// 직접입력 : 5

		return null;
	}

	@Override
	public Item modify(ItemModifyDto ibd, Long member_id) throws CommonException {
		log.debug("modify 1  ________________________________________________________ ");

		Member user = memberRepository.findOne(member_id);
		Item old_item = itemRepository.findOne(ibd.getItem_id());


//		old_item.setLinkYn("N");
//		old_item.setUseYn("Y");

		//old_item = itemRepository.save(old_item);

		log.debug("modify 2 size_table ________________________________________________________"); 
		// 사이즈 테이블 처리
//		if (ibd.getSizeTableYn().equals("Y")) {
//			old_item.setSizeTableYn("Y");
//
//			if(old_item.getSizeTable() != null) {
//				
//				SizeTable old_st = old_item.getSizeTable();
//				old_st.setUseYn("Y");
//				
//			}else {
//
//				// 사이즈 테이블 생성
//				
//				SizeTable new_st = new SizeTable();
//				new_st.setCreateDt(new DateTime());
//				new_st.setUseYn("Y");
//				new_st.setItem(old_item);
//				new_st.setVisibleBasicYn("Y");
//				new_st.setVisibleCodeYn("Y");
//				new_st.setVisibleColorYn("Y");
//				new_st.setVisibleFitInfoYn("Y");
//				new_st.setVisibleItemImageYn("Y");
//				new_st.setVisibleLaundryInfoYn("Y");
//				new_st.setVisibleMeasureHowAYn("Y");
//				new_st.setVisibleMeasureHowBYn("Y");
//				new_st.setVisibleMeasureTableYn("Y");
//				new_st.setVisibleNameYn("Y");
//
//				sizeTableRepository.save(new_st);
//			}
//			
//
//		} else {
//
//			old_item.setSizeTableYn("N");
//		}

 
 
		log.debug("modify 3 bleach ________________________________________________________");
		// 표백 여부 
		
		// 데이터 베이스에 존재하는가?
		QItemBleachMap qibm = QItemBleachMap.itemBleachMap;
		
		Iterable<ItemBleachMap> db_ibm_list_iter = itemBleachMapRepository.findAll(qibm.item.id.eq(ibd.getItem_id()));
		boolean db_exist = false;  
		List<ItemBleachMap> db_ibm_list = IteratorUtils.toList(db_ibm_list_iter.iterator());    
		if(db_ibm_list.size() > 0) db_exist = true; 

		
		log.debug("modify 3 bleach "+ibd.getBleachYn()+ " db_exist "+db_exist);
		
		if (ibd.getBleachYn().equals("Y")) {
 
			if(db_exist) {
				
				ItemBleachMap db_item_bleach = db_ibm_list.get(0);
				ItemBleachMap user_item_bleach = ibd.getItemBleachMap();
				
				db_item_bleach.setChlorine(user_item_bleach.getChlorine());
				db_item_bleach.setOxygen(user_item_bleach.getOxygen());
				db_item_bleach.setUseYn("Y");
				
			}else {
				ItemBleachMap item_bleach = ibd.getItemBleachMap();
				item_bleach.setItem(old_item);
				itemBleachMapRepository.save(item_bleach);
			}
			
			
		}else{
			if(db_exist) {
				
				ItemBleachMap db_item_bleach = db_ibm_list.get(0); 
				db_item_bleach.setUseYn("N");
				
				//itemBleachMapRepository.save(db_item_bleach);
				
			}
		}
		
		log.debug("modify 4 dry cleaning ________________________________________________________");
		
		
		
		// 데이터 베이스에 존재하는가?
		QItemDrycleaningMap qidcm = QItemDrycleaningMap.itemDrycleaningMap;
		
		Iterable<ItemDrycleaningMap> db_idcm_list_iter = itemDrycleaningMapRepository.findAll(qidcm.item.id.eq(ibd.getItem_id()));
		
		db_exist = false; 
		
		List<ItemDrycleaningMap> db_idc_list = IteratorUtils.toList(db_idcm_list_iter.iterator());   
		if(db_idc_list.size() > 0) db_exist = true; 

		log.debug("modify 4 dry cleaning "+ibd.getBleachYn()+ " db_exist "+db_exist);
		
		if (ibd.getDrycleaningYn().equals("Y")) {
 

			
			
			
			if(db_exist) {
				
				ItemDrycleaningMap db_item_drycleaning = db_idc_list.get(0);
				ItemDrycleaningMap user_item_drycleaning = ibd.getItemDrycleaningMap();
				
				db_item_drycleaning.setDetergent(user_item_drycleaning.getDetergent());
				db_item_drycleaning.setDrycan(user_item_drycleaning.getDrycan());
				db_item_drycleaning.setStorecan(user_item_drycleaning.getStorecan());
				db_item_drycleaning.setUseYn("Y");
				
			}else {
				ItemDrycleaningMap user_item_drycleaning = ibd.getItemDrycleaningMap();
				user_item_drycleaning.setItem(old_item);
				itemDrycleaningMapRepository.save(user_item_drycleaning);
			}
			
			
			
		}else{
			if(db_exist) {
				
				ItemDrycleaningMap db_item_drycleaning = db_idc_list.get(0); 
				db_item_drycleaning.setUseYn("N");
				//itemDrycleaningMapRepository.save(db_item_drycleaning);
				
			}
		}

		log.debug("modify 5 dry method ________________________________________________________");
		
		
		// 데이터 베이스에 존재하는가?
		QItemDrymethodMap qidm = QItemDrymethodMap.itemDrymethodMap;
		
		Iterable<ItemDrymethodMap> db_idm_list_iter = itemDrymethodMapRepository.findAll(qidm.item.id.eq(ibd.getItem_id()));
		 
		db_exist = false; 
		
		List<ItemDrymethodMap> db_idm_list = IteratorUtils.toList(db_idm_list_iter.iterator());   
		if(db_idm_list.size() > 0) db_exist = true;
		
		log.debug("modify 5 dry method "+ibd.getDrymethodYn()+ " db_exist "+db_exist);
		
		if (ibd.getDrymethodYn().equals("Y")) { 
			
			
			
			if(db_exist) {
				
				ItemDrymethodMap db_item_drycleaning = db_idm_list.get(0);
				ItemDrymethodMap user_item_drycleaning = ibd.getItemDrymethodMap();
				
				db_item_drycleaning.setDryMode(user_item_drycleaning.getDryMode());
				db_item_drycleaning.setHandDry(user_item_drycleaning.getHandDry());
				db_item_drycleaning.setMachineDry(user_item_drycleaning.getMachineDry());
				db_item_drycleaning.setNatureDry(user_item_drycleaning.getNatureDry());
				db_item_drycleaning.setUseYn("Y");
				
			}else {
				ItemDrymethodMap user_item_drycleaning = ibd.getItemDrymethodMap();
				user_item_drycleaning.setItem(old_item);
				itemDrymethodMapRepository.save(user_item_drycleaning);
			} 
		}else{
			if(db_exist) {
				
				ItemDrymethodMap db_ItemDrymethodMap = db_idm_list.get(0); 
				db_ItemDrymethodMap.setUseYn("N");
				//itemDrymethodMapRepository.save(db_ItemDrymethodMap);
				
			}
		}
		
		log.debug("modify 6 ironing ________________________________________________________");
		
		// 데이터 베이스에 존재하는가?
		QItemIroningMap qiim = QItemIroningMap.itemIroningMap;
		
		Iterable<ItemIroningMap> db_iim_list_iter = itemIroningMapRepository.findAll(qiim.item.id.eq(ibd.getItem_id()));
		
		List<ItemIroningMap> db_iim_list = IteratorUtils.toList(db_iim_list_iter.iterator());       
		
		db_exist = false;
		
		if(db_iim_list.size() > 0) db_exist = true;

		log.debug("modify 6 ironing "+ibd.getIroningYn()+ " db_exist "+db_exist);
		
		if (ibd.getIroningYn().equals("Y")) {  
			
			if(db_exist) {
				
				ItemIroningMap db_ItemIroning = db_iim_list.get(0);
				ItemIroningMap user_ItemIroning = ibd.getItemIroningMap();
				
				db_ItemIroning.setAddprotection(user_ItemIroning.getAddprotection());
				db_ItemIroning.setEndTemp(user_ItemIroning.getEndTemp());
				db_ItemIroning.setIroncan(user_ItemIroning.getIroncan());
				db_ItemIroning.setStartTemp(user_ItemIroning.getStartTemp());
				db_ItemIroning.setUseYn("Y");
				
			}else {
				
				
				ItemIroningMap user_ItemIroning = ibd.getItemIroningMap();
		 
				
				user_ItemIroning.setItem(old_item);
				itemIroningMapRepository.save(user_ItemIroning);
				
			}
			 
		}else{
			if(db_exist) {
				ItemIroningMap db_ItemIroningMap = db_iim_list.get(0); 
				db_ItemIroningMap.setUseYn("N");
				//itemIroningMapRepository.save(db_ItemIroningMap);
				
			}
		}

		log.debug("modify 7 laundry ________________________________________________________");
		
		// 데이터 베이스에 존재하는가?
		QItemLaundryMap qilm = QItemLaundryMap.itemLaundryMap;
		
		Iterable<ItemLaundryMap> db_ilm_list_iter = itemLaundryMapRepository.findAll(qilm.item.id.eq(ibd.getItem_id()));
		
		db_exist = false;
		
		List<ItemLaundryMap> db_ilm_list = IteratorUtils.toList(db_ilm_list_iter.iterator());   
		
		if(db_ilm_list.size() > 0) db_exist = true; 
		
		log.debug("modify 7 laundry "+ibd.getLaundryYn()+ " db_exist "+db_exist);
		
		
		
		if (ibd.getLaundryYn().equals("Y")) {
			ItemLaundryMap ilm = ibd.getItemLaundryMap();
			ilm.setItem(old_item);
			itemLaundryMapRepository.save(ilm);

			
			
			if(db_exist) {
				
				ItemLaundryMap db_ItemLaundry = db_ilm_list.get(0);
				ItemLaundryMap user_ItemLaundry = ibd.getItemLaundryMap();
				
				db_ItemLaundry.setDetergent(user_ItemLaundry.getDetergent());
				db_ItemLaundry.setHand(user_ItemLaundry.getHand());
				db_ItemLaundry.setIntensity(user_ItemLaundry.getIntensity());
				db_ItemLaundry.setMachine(user_ItemLaundry.getMachine());
				db_ItemLaundry.setWater(user_ItemLaundry.getWater());
				db_ItemLaundry.setWaterTemp(user_ItemLaundry.getWaterTemp());
				db_ItemLaundry.setUseYn("Y");
				
			}else {
				ItemLaundryMap user_ItemLaundry = ibd.getItemLaundryMap();
				user_ItemLaundry.setItem(old_item);
				itemLaundryMapRepository.save(user_ItemLaundry);
			} 
			
		}else{
			if(db_exist) {
				ItemLaundryMap db_ItemLaundryMap = db_ilm_list.get(0); 
				db_ItemLaundryMap.setUseYn("N"); 
				//itemLaundryMapRepository.save(db_ItemLaundryMap);
			}
		}
		
		

		
		
		
		/// 동적 select box 처리 
		
		log.debug("modify 7 Warranty ________________________________________________________");
		
		Warranty warranty = ibd.getWarranty();
		if(warranty.getId() == 0L) {
			warranty = warrantyRepository.save(warranty);
			
		}
		old_item.setWarranty(warranty);
		
		// shopmall 목록 정리 
		
		
		log.debug("modify 8 shopmall ________________________________________________________");
		
		List<ItemShopmallMap> user_shopmall_list = ibd.getShopmalls();
		QItemShopmallMap qism = QItemShopmallMap.itemShopmallMap;
		Iterable<ItemShopmallMap> db_ism_list_iter = itemShopmallMapRepository.findAll(qism.item.id.eq(ibd.getItem_id()));
		List<ItemShopmallMap> db_ism_list = IteratorUtils.toList(db_ism_list_iter.iterator()); 
		
		
		for(ItemShopmallMap sm : user_shopmall_list) {
			
			
			boolean is_shopmall_added = true;
			
			for(ItemShopmallMap db_ism : db_ism_list) {
				

				if (db_ism.getId() == sm.getId()) {
					
					if(db_ism.getUseYn().equals("Y")) {
						is_shopmall_added = false;	
					}else {
						is_shopmall_added = false;
						db_ism.setUseYn("Y");
					}
				}
			}
			
			
			if (is_shopmall_added) { // 다시 선택한 값이 아니면 새로 추가 한다.

					Shopmall new_sm = shopmallRepository.findOne(sm.getShopmall().getId());

					ItemShopmallMap new_ism = new ItemShopmallMap();
					new_ism.setShopmall(new_sm);
					new_ism.setItem(old_item);
					new_ism.setUseYn("Y");

					itemShopmallMapRepository.save(new_ism);					
				
			}
 
		}
		
		for(ItemShopmallMap db_ism : db_ism_list) { 
			
			boolean did_user_delete_this_option = true;
			
			
			for(ItemShopmallMap sm : user_shopmall_list) { 
				if (db_ism.getId() == sm.getId()) {
					
					did_user_delete_this_option = false;
					
					if(db_ism.getUseYn().equals("Y")) {
							
					}else {
						
						db_ism.setUseYn("N");
					}
				}
			}
			
			
			if (did_user_delete_this_option) { // 유저가 삭제한 값이면 삭제한다. 
				db_ism.setUseYn("N");
			}
 
		}
 
		
		
		
		
		
		
		// 색상 

		log.debug("modify 9 cloth color ________________________________________________________");
		
		List<ItemClothColorMap> user_item_color_map_list = ibd.getItemClothColorMaps(); 
		QItemClothColorMap qiccm = QItemClothColorMap.itemClothColorMap;
		Iterable<ItemClothColorMap> db_color_list_iter = itemClothColorMapRepository.findAll(qiccm.item.id.eq(ibd.getItem_id()));
		List<ItemClothColorMap> db_color_list = IteratorUtils.toList(db_color_list_iter.iterator()); 
		
		for(ItemClothColorMap user_data : user_item_color_map_list) {
			
			
			boolean is_this_option_added = true;
			
			for(ItemClothColorMap db_data : db_color_list) {  

				if (db_data.getId() == user_data.getId()) {
					db_data.setOptionValue(user_data.getOptionValue());
					if(db_data.getUseYn().equals("Y")) {
						is_this_option_added = false;	
					}else {
						is_this_option_added = false;
						db_data.setUseYn("Y");
					}
				}
			}
			
			
			if (is_this_option_added) { // 다시 선택한 값이 아니면 새로 추가 한다.

				if(user_data.getClothColor().getId() == 0L) {
					
					ClothColor direct_input = clothColorRepository.save(user_data.getClothColor());
					ItemClothColorMap new_iccm = new ItemClothColorMap();
					new_iccm.setClothColor(direct_input);
					new_iccm.setItem(old_item);
					new_iccm.setUseYn("Y");

					itemClothColorMapRepository.save(new_iccm);	
				}else {
					ClothColor cc = clothColorRepository.findOne(user_data.getClothColor().getId());

					ItemClothColorMap new_iccm = new ItemClothColorMap();
					new_iccm.setClothColor(cc);
					new_iccm.setItem(old_item);
					new_iccm.setUseYn("Y");

					itemClothColorMapRepository.save(new_iccm);	
				}
				
				
				
				
			}
 
		}
		
		for(ItemClothColorMap db_data : db_color_list) {  
		 
			
			boolean did_user_delete_this_option = true;
			
			
			for(ItemClothColorMap user_data : user_item_color_map_list) {
		
				if (db_data.getId() == user_data.getId()) {
					
					db_data.setOptionValue(user_data.getOptionValue());
					
					did_user_delete_this_option = false;
					if(db_data.getUseYn().equals("Y")) {	
					}else {
						
						db_data.setUseYn("N");
					}
				}
			}
			
			
			if (did_user_delete_this_option) { // 유저가 삭제한 값이면 삭제한다. 
				db_data.setUseYn("N");
			}
 
		}
 
		
		
		
		
		// fit 정보 
		

		log.debug("modify 10 FitInfoOption ________________________________________________________");
		
		List<ItemFitInfoOptionMap> user_FitInfo_list = ibd.getItemFitInfoOptionMaps(); 
		QItemFitInfoOptionMap qifiom = QItemFitInfoOptionMap.itemFitInfoOptionMap;
		Iterable<ItemFitInfoOptionMap> db_FitInfo_list_iter = itemFitInfoOptionMapRepository.findAll(qifiom.item.id.eq(ibd.getItem_id()));
		List<ItemFitInfoOptionMap> db_FitInfo_list = IteratorUtils.toList(db_FitInfo_list_iter.iterator()); 
		
		
		for(ItemFitInfoOptionMap user_data : user_FitInfo_list) { 
			boolean is_this_option_added = true;
			
			for(ItemFitInfoOptionMap db_data : db_FitInfo_list) {   
				 

				if (db_data.getId() == user_data.getId()) {
					
					if(db_data.getUseYn().equals("Y")) {
						is_this_option_added = false;	
					}else {
						is_this_option_added = false;
						db_data.setUseYn("Y");
					}
				}
			}
			
			
			if (is_this_option_added) { // 다시 선택한 값이 아니면 새로 추가 한다.

					FitInfoOption fio = fitInfoOptionRepository.findOne(user_data.getFitInfoOption().getId());

					ItemFitInfoOptionMap new_ifiom = new ItemFitInfoOptionMap();
					new_ifiom.setFitInfoOption(fio);
					new_ifiom.setItem(old_item);
					new_ifiom.setUseYn("Y");

					itemFitInfoOptionMapRepository.save(new_ifiom);					
				
			}
 
		}
		
		for(ItemFitInfoOptionMap db_data : db_FitInfo_list) {   
			
			boolean did_user_delete_this_option = true;
			
			
			for(ItemFitInfoOptionMap user_data : user_FitInfo_list) {
				if (db_data.getFitInfoOption() == null)
					continue;

				if (db_data.getId() == user_data.getId()) {
					
					did_user_delete_this_option = false;
					
					if(db_data.getUseYn().equals("Y")) {
							
					}else {
						db_data.setUseYn("N");
					}
				}
			}
			
			
			if (did_user_delete_this_option) { // 유저가 삭제한 값이면 삭제한다. 
				db_data.setUseYn("N");
			}
 
		}
		
		
		// 사이즈 옵션 값 
		
		log.debug("modify 11 SizeOption ________________________________________________________");
		
		List<ItemSizeOptionMap> user_isom_list = ibd.getItemSizeOptionMaps();
		QItemSizeOptionMap qisom = QItemSizeOptionMap.itemSizeOptionMap;
		Iterable<ItemSizeOptionMap> db_isom_list_iter = itemSizeOptionMapRepository.findAll(qisom.item.id.eq(ibd.getItem_id()));
		List<ItemSizeOptionMap> db_isom_list = IteratorUtils.toList(db_isom_list_iter.iterator()); 
		
		
		
		for(ItemSizeOptionMap user_data : user_isom_list) { 
			boolean is_this_option_added = true;
			
			for(ItemSizeOptionMap db_data : db_isom_list) {    
				 

				if (db_data.getId() == user_data.getId()) {
					db_data.setOptionValue(user_data.getOptionValue());
					
					is_this_option_added = false;	
					
					if(db_data.getUseYn().equals("Y")) {
						
					}else {
						db_data.setUseYn("Y");
					}
				}
			}
			
			
			if (is_this_option_added) { // 다시 선택한 값이 아니면 새로 추가 한다.

				if(user_data.getSizeOption().getId() == 0L) {
					
					SizeOption so = sizeOptionRepository.save(user_data.getSizeOption());

					ItemSizeOptionMap new_isom = new ItemSizeOptionMap();
					new_isom.setSizeOption(so);
					new_isom.setItem(old_item);
					new_isom.setUseYn("Y");

					itemSizeOptionMapRepository.save(new_isom);
					
				}else {
					SizeOption so = sizeOptionRepository.findOne(user_data.getSizeOption().getId());

					ItemSizeOptionMap new_isom = new ItemSizeOptionMap();
					new_isom.setSizeOption(so);
					new_isom.setItem(old_item);
					new_isom.setUseYn("Y");

					itemSizeOptionMapRepository.save(new_isom);					
				}
				
					
				
			}
 
		}
		
		for(ItemSizeOptionMap db_data : db_isom_list) {     
			
			boolean did_user_delete_this_option = true;
			
			
			for(ItemSizeOptionMap user_data : user_isom_list) { 

				if (db_data.getId() == user_data.getId()) {
					
					db_data.setOptionValue(user_data.getOptionValue());
					
					did_user_delete_this_option = false;
					
					if(db_data.getUseYn().equals("Y")) {	
					}else {
						
						db_data.setUseYn("N");
					}
				}
			}
			
			
			if (did_user_delete_this_option) { // 유저가 삭제한 값이면 삭제한다. 
				db_data.setUseYn("N");
			}
 
		}
		
		
		
 
		
		// 소재 정보
		
		log.debug("modify 12 Material ________________________________________________________");
		
		List<ItemMaterialMap> user_imm_list = ibd.getMaterials(); 
		QItemMaterialMap qimm = QItemMaterialMap.itemMaterialMap;
		Iterable<ItemMaterialMap> db_imm_list_iter = itemMaterialMapRepository.findAll(qimm.item.id.eq(ibd.getItem_id()));
		List<ItemMaterialMap> db_imm_list = IteratorUtils.toList(db_imm_list_iter.iterator()); 
		
		
		
		for(ItemMaterialMap user_data : user_imm_list) { 
			boolean is_this_option_added = true;
			
			for(ItemMaterialMap db_data : db_imm_list) {      
				 

				if (db_data.getId() == user_data.getId()) {
					
					is_this_option_added = false;	
					
					db_data.setContain(user_data.getContain());
					db_data.setUseLocatoin(user_data.getUseLocatoin());
					
					if(db_data.getUseYn().equals("Y")) {
						
					}else {
						db_data.setUseYn("Y");
					}
				}
			}
			
			
			if (is_this_option_added) { // 다시 선택한 값이 아니면 새로 추가 한다.

					Material m = materialRepository.findOne(user_data.getMaterial().getId());

					ItemMaterialMap new_imm = new ItemMaterialMap();
					new_imm.setContain(user_data.getContain());
					new_imm.setUseLocatoin(user_data.getUseLocatoin());
					new_imm.setMaterial(m);
					new_imm.setItem(old_item);
					new_imm.setUseYn("Y");

					itemMaterialMapRepository.save(new_imm);					
				
			}
 
		}
		
		for(ItemMaterialMap db_data : db_imm_list) {   
		 
			
			boolean did_user_delete_this_option = true;
			
			
			for(ItemMaterialMap user_data : user_imm_list) { 
				if (db_data.getId() == user_data.getId()) {
					
					db_data.setContain(user_data.getContain());
					db_data.setUseLocatoin(user_data.getUseLocatoin());
					
					did_user_delete_this_option = false;	
					if(db_data.getUseYn().equals("Y")) {
						
					}else {
						db_data.setUseYn("N");
					}
				}
			}
			
			
			if (did_user_delete_this_option) { // 유저가 삭제한 값이면 삭제한다. 
				db_data.setUseYn("N");
			}
 
		}
		
		
		log.debug("modify 13 사이즈 수치 입력");
		
		
		List<ItemScmmSoValue> user_miso_value = ibd.getItemScmmSoValues(); 
		QItemScmmSoValue qissv = QItemScmmSoValue.itemScmmSoValue;
		Iterable<ItemScmmSoValue> db_miso_list_iter = itemScmmSoValueRepository.findAll(qissv.item.id.eq(ibd.getItem_id()));
		List<ItemScmmSoValue> db_miso_list = IteratorUtils.toList(db_miso_list_iter.iterator()); 
		
		
		
		for(ItemScmmSoValue user_data : user_miso_value) { 
			boolean is_this_option_added = true;
			
			for(ItemScmmSoValue db_data : db_miso_list) {      
				 

				if (db_data.getId() == user_data.getId()) {
					
					is_this_option_added = false;	
					
					db_data.setInputValue((user_data.getInputValue()));
					db_data.setItem(old_item);
					db_data.setSizeOption(user_data.getSizeOption());
					db_data.setSubCategoryMeasureMap(user_data.getSubCategoryMeasureMap());
					db_data.setUseYn("Y");
					
					
					if(db_data.getUseYn().equals("Y")) {
						
					}else {
						db_data.setUseYn("Y");
					}
				}
			}
			
			
			if (is_this_option_added) { // 다시 선택한 값이 아니면 새로 추가 한다.
 

					ItemScmmSoValue new_issv = new ItemScmmSoValue();
					new_issv.setInputValue((user_data.getInputValue()));
					new_issv.setItem(old_item);
					
					if(user_data.getSizeOption().getId() == 0L) {
						
						
						SizeOption new_so = sizeOptionRepository.save(user_data.getSizeOption());
						
						new_issv.setSizeOption(new_so);
						
					}else {
						new_issv.setSizeOption(user_data.getSizeOption());
					}
					
					
					
					
					
					
					
					new_issv.setSubCategoryMeasureMap(user_data.getSubCategoryMeasureMap());
					new_issv.setUseYn("Y");

					itemScmmSoValueRepository.save(new_issv);					
				
			}
 
		}
		
		for(ItemScmmSoValue db_data : db_miso_list) {   
		 
			
			boolean did_user_delete_this_option = true;
			
			
			for(ItemScmmSoValue user_data : user_miso_value) { 
				if (db_data.getId() == user_data.getId()) {
					
					db_data.setInputValue((user_data.getInputValue()));
					db_data.setItem(old_item);
					db_data.setSizeOption(user_data.getSizeOption());
					db_data.setSubCategoryMeasureMap(user_data.getSubCategoryMeasureMap());
					 
					did_user_delete_this_option = false;	
					if(db_data.getUseYn().equals("Y")) {
						
					}else {
						db_data.setUseYn("N");
					}
				}
			}
			
			
			if (did_user_delete_this_option) { // 유저가 삭제한 값이면 삭제한다. 
				db_data.setUseYn("N");
			}
 
		}
		
		
		
		
		old_item.setCode(ibd.getCode()); 
		old_item.setBleachYn(ibd.getBleachYn());
		old_item.setDrycleaningYn(ibd.getDrycleaningYn());
		old_item.setDrymethodYn(ibd.getDrymethodYn());
		old_item.setIroningYn(ibd.getIroningYn());
		old_item.setLaundryYn(ibd.getLaundryYn());
 
		old_item.setMadeinBuilder(ibd.getMadeinBuilder());
		old_item.setMadeinDate(ibd.getMadeinDate());
		old_item.setName(ibd.getName());
		old_item.setPrice(ibd.getPrice());
 
		old_item.setMadein(ibd.getMadein()); 
		old_item.setBrand(ibd.getBrand());
		old_item.setCategory(ibd.getCategory());
		old_item.setSubCategory(ibd.getSubCategory());
 
		
		

		// 전에 유저가 업로드한 이미지가 있으면 지워준다. 
		if(old_item.getImageMode().equals("C")) {
		
			if(old_item.getImage() != null) {
				File old_file = new File(path_subcategory_client_image.concat(File.separator).concat(user.getMemberId()).concat(File.separator).concat("item").concat(File.separator).concat(old_item.getImage()));
				
				if(old_file.exists())
					old_file.delete();
			}
			
		}	
		
		
		for(FileDto idt : ibd.getImage()) {
			
			// 한번만 돈다고 가정하고 만든다.
			
			String rn = idt.getReal_name();
			String tn = idt.getTemp_name();
			
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

			File source = new File(path_temp_upload.concat(File.separator).concat(dtf.format(now)).concat(File.separator).concat(tn));
			File dest = new File(path_subcategory_client_image.concat(File.separator).concat(user.getMemberId()).concat(File.separator).concat("item").concat(File.separator).concat(rn));

			try {
				FileUtils.copyFile(source, dest);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new CommonException("item.image.upload.failed");
			}
	
		
			old_item.setImage(rn); 
			
			
			
		}
		
		old_item.setImageMode(ibd.getImageMode());

		// 전에 유저가 업로드한 이미지가 있으면 지워준다. 
		if(old_item.getSizeMeasureMode().equals("C")) {
		
			if(old_item.getImage() != null) {
				File old_file = new File(path_subcategory_client_image.concat(File.separator).concat(user.getMemberId()).concat(File.separator).concat("item").concat(File.separator).concat(old_item.getSizeMeasureImage()));
				
				if(old_file.exists())
					old_file.delete();
			}
			
		}	
		
		
		for(FileDto idt : ibd.getSizeMeasureImage()) {
			String rn = idt.getReal_name();
			String tn = idt.getTemp_name();
			
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

			File source = new File(path_temp_upload.concat(File.separator).concat(dtf.format(now)).concat(File.separator).concat(tn));
			File dest = new File(path_subcategory_client_image.concat(File.separator).concat(user.getMemberId()).concat(File.separator).concat("size_measure").concat(File.separator).concat(rn));

			try {
				FileUtils.copyFile(source, dest);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new CommonException("item.image.upload.failed");
			}
			
			
			
			old_item.setSizeMeasureImage(rn); 
		}

		old_item.setSizeMeasureMode(ibd.getSizeMeasureMode());
		// 검수용
		CommandLine.Sync_file();
		return old_item;
	}

	@Override
	public String change_connect(ItemDto param, Long seq) {
		// TODO Auto-generated method stub
		return null;
	}

	public String arrayExcel(List<LIdDto> param, Pageable pageable) throws IOException {

		String today = DateTime.now().toString("yyyy-MM-dd");
		String temp_path = "D:\\TEST_SERVER_ROOT\\temp\\".concat(today).concat("\\");
		String excel_path = UUID.randomUUID().toString().concat(".xls");

		FileUtils.forceMkdir(new File(temp_path));

		QItem i = QItem.item;

		BooleanBuilder builder = new BooleanBuilder();

		for (LIdDto tv : param) {
			builder.or(i.id.eq(tv.getId()));
		}

		Iterable<Item> list = itemRepository.findAll(builder, pageable.getSort());

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("히어로 네이션 상품 목록");
		HSSFRow row = null;
		HSSFCell cell = null;

		// 엑셀 첫번째 줄, 엑셀 파일에 쓰기
		// 헤더만들기
		row = sheet.createRow((short) 0);

		HSSFCell nameCellHeader = row.createCell(0);
		nameCellHeader.setCellValue("이름");

		HSSFCell codeCellHeader = row.createCell(1);
		codeCellHeader.setCellValue("코드");

		HSSFCell priceCellHeader = row.createCell(2);
		priceCellHeader.setCellValue("가격");

		HSSFCell linkCellHeader = row.createCell(3);
		linkCellHeader.setCellValue("연동여부");

		HSSFCell sizeTableCellHeader = row.createCell(4);
		sizeTableCellHeader.setCellValue("사이즈표");

		HSSFCell sizeMeasureModeCellHeader = row.createCell(5);
		sizeMeasureModeCellHeader.setCellValue("측정모드");

		HSSFCell sizeMeasureImageCellHeader = row.createCell(6);
		sizeMeasureImageCellHeader.setCellValue("측정이미지");

		HSSFCell imageCellHeader = row.createCell(7);
		imageCellHeader.setCellValue("제품 이미지");

		HSSFCell imageModeCellHeader = row.createCell(8);
		imageModeCellHeader.setCellValue("제품 모드");

		HSSFCell madeinBuilderCellHeader = row.createCell(9);
		madeinBuilderCellHeader.setCellValue("제조국");

		HSSFCell madeinDateCellHeader = row.createCell(10);
		madeinDateCellHeader.setCellValue("제조일시");

		HSSFCell bleachCellHeader = row.createCell(11);
		bleachCellHeader.setCellValue("표백제 사용법");

		HSSFCell drycleaningCellHeader = row.createCell(12);
		drycleaningCellHeader.setCellValue("드라이클리닝");

		HSSFCell drymethodCellHeader = row.createCell(13);
		drymethodCellHeader.setCellValue("건조방법");

		HSSFCell irongingCellHeader = row.createCell(14);
		irongingCellHeader.setCellValue("다림질 ");

		HSSFCell laundryCellHeader = row.createCell(15);
		laundryCellHeader.setCellValue("세탁 방법");

		int row_index = 1;

		for (Item db_item : list) {

			row = sheet.createRow((short) row_index);
			row_index++;

			HSSFCell nameCell = row.createCell(0);
			nameCell.setCellValue(db_item.getName());

			HSSFCell codeCell = row.createCell(1);
			codeCell.setCellValue(db_item.getCode());

			HSSFCell priceCell = row.createCell(2);
			priceCell.setCellValue(db_item.getPrice());

			HSSFCell linkCell = row.createCell(3);
			linkCell.setCellValue(db_item.getLinkYn());

			HSSFCell sizeTableCell = row.createCell(4);
			sizeTableCell.setCellValue(db_item.getSizeTableYn());

			HSSFCell sizeMeasureModeCell = row.createCell(5);
			sizeMeasureModeCell.setCellValue(db_item.getSizeMeasureMode());

			HSSFCell sizeMeasureImageCell = row.createCell(6);
			sizeMeasureImageCell.setCellValue(db_item.getSizeMeasureImage());

			HSSFCell imageCell = row.createCell(7);
			imageCell.setCellValue(db_item.getImage());

			HSSFCell imageModeCell = row.createCell(8);
			imageModeCell.setCellValue(db_item.getImageMode());

			HSSFCell madeinBuilderCell = row.createCell(9);
			madeinBuilderCell.setCellValue(db_item.getMadeinBuilder());

			HSSFCell madeinDateCell = row.createCell(10);
			DateTime madeinDateTime = db_item.getMadeinDate();
			madeinDateCell.setCellValue(madeinDateTime.toString());

			HSSFCell bleachCell = row.createCell(11);
			bleachCell.setCellValue(db_item.getBleachYn());

			HSSFCell drycleaningCell = row.createCell(12);
			drycleaningCell.setCellValue(db_item.getDrycleaningYn());

			HSSFCell drymethodCell = row.createCell(13);
			drymethodCell.setCellValue(db_item.getDrymethodYn());

			HSSFCell irongingCell = row.createCell(14);
			irongingCell.setCellValue(db_item.getIroningYn());

			HSSFCell laundryCell = row.createCell(15);
			laundryCell.setCellValue(db_item.getLaundryYn());

		}

		// // 엑셀 와이드 설정
		// for (int i = 0; i < 4; i++) {
		// sheet.autoSizeColumn(i, true);
		// if (i == 0) {
		// sheet.setColumnWidth(i, 8000);
		// }
		// if (i == 1 || i == 2) {
		// sheet.setColumnWidth(i, 22000);
		// }
		// if (i == 3) {
		// sheet.setColumnWidth(i, 4000);
		// }
		// }

		// 엑셀 생성 경로 설정 및 자원 종료

		FileOutputStream fileoutputstream = new FileOutputStream(temp_path.concat(excel_path));
		workbook.write(fileoutputstream);
		fileoutputstream.close();

		// 검수용
		CommandLine.Sync_excel();
		
		return "/temp/".concat(today).concat("/").concat(excel_path);

	}

	@Override
	@Transactional
	public String update_item_image(ItemImageUploadDto param,String member_id) throws CommonException {
		Item i = itemRepository.findOne(param.getItem_id());
		
		if(i.getImage() != null) {
			File old_file = new File(path_subcategory_client_image.concat(File.separator).concat(member_id).concat(File.separator).concat("item").concat(File.separator).concat(i.getImage()));
			
			if(old_file.exists())
				old_file.delete();
		}
		
		 
		
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

		
		FileDto[] images = param.getImage();
		
		
		for(FileDto df : images) {
			File source = new File(path_temp_upload
					.concat(File.separator).concat(dtf.format(now))
					.concat(File.separator).concat(df.getTemp_name()));
			File dest = new File(path_subcategory_client_image.concat(File.separator).concat(member_id).concat(File.separator).concat("item").concat(File.separator).concat(df.getReal_name()));

			try {
				FileUtils.copyFile(source, dest);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new CommonException("image.upload.failed");
			}
			i.setImageMode("C");
			i.setImage(df.getReal_name());
		}
		

		// 검수용
		CommandLine.Sync_file();

		return CommonConstants.OK;
	}
	
	@Override
	@Transactional
	public String update_size_measure_image(ItemSizeMeasureImageUploadDto param,String member_id)  throws CommonException{
		Item i = itemRepository.findOne(param.getItem_id());
		
		
		if(i.getSizeMeasureImage() != null) {
			File old_file = new File(path_subcategory_client_image.concat(File.separator).concat(member_id).concat(File.separator).concat("size_measure").concat(File.separator).concat(i.getImage()));
			
			if(old_file.exists())
				old_file.delete();
		}
		 
		
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

		
		FileDto[] images = param.getSizeMeasureImage();
		
		
		for(FileDto df : images) {
			File source = new File(path_temp_upload
					.concat(File.separator).concat(dtf.format(now))
					.concat(File.separator).concat(df.getTemp_name()));
			File dest = new File(path_subcategory_client_image.concat(File.separator).concat(member_id).concat(File.separator).concat("size_measure").concat(File.separator).concat(df.getReal_name()));

			try {
				FileUtils.copyFile(source, dest);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new CommonException("image.upload.failed");
			}
			i.setSizeMeasureMode("C");
			i.setSizeMeasureImage(df.getReal_name());
		}
		

		// 검수용
		CommandLine.Sync_file();

		return CommonConstants.OK;
	}

}