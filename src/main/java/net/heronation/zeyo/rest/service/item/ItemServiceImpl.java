package net.heronation.zeyo.rest.service.item;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.CommonException;
import net.heronation.zeyo.rest.common.value.ToggleVO;
import net.heronation.zeyo.rest.repository.brand.BrandRepository;
import net.heronation.zeyo.rest.repository.brand.QBrand;
import net.heronation.zeyo.rest.repository.category.CategoryRepository;
import net.heronation.zeyo.rest.repository.category.QCategory;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColor;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColorRepository;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory;
import net.heronation.zeyo.rest.repository.company_no_history.QCompanyNoHistory;
import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOptionRepository;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.item.ItemBuildDto;
import net.heronation.zeyo.rest.repository.item.ItemDto;
import net.heronation.zeyo.rest.repository.item.ItemModifyDto;
import net.heronation.zeyo.rest.repository.item.ItemRepository;
import net.heronation.zeyo.rest.repository.item.QItem;
import net.heronation.zeyo.rest.repository.item_bleach_map.ItemBleachMap;
import net.heronation.zeyo.rest.repository.item_bleach_map.ItemBleachMapRepository;
import net.heronation.zeyo.rest.repository.item_cloth_color_map.ItemClothColorMap;
import net.heronation.zeyo.rest.repository.item_cloth_color_map.ItemClothColorMapRepository;
import net.heronation.zeyo.rest.repository.item_drycleaning_map.ItemDrycleaningMap;
import net.heronation.zeyo.rest.repository.item_drycleaning_map.ItemDrycleaningMapRepository;
import net.heronation.zeyo.rest.repository.item_drymethod_map.ItemDrymethodMap;
import net.heronation.zeyo.rest.repository.item_drymethod_map.ItemDrymethodMapRepository;
import net.heronation.zeyo.rest.repository.item_fit_info_option_map.ItemFitInfoOptionMap;
import net.heronation.zeyo.rest.repository.item_fit_info_option_map.ItemFitInfoOptionMapRepository;
import net.heronation.zeyo.rest.repository.item_ironing_map.ItemIroningMap;
import net.heronation.zeyo.rest.repository.item_ironing_map.ItemIroningMapRepository;
import net.heronation.zeyo.rest.repository.item_laundry_map.ItemLaundryMap;
import net.heronation.zeyo.rest.repository.item_laundry_map.ItemLaundryMapRepository;
import net.heronation.zeyo.rest.repository.item_material_map.ItemMaterialMap;
import net.heronation.zeyo.rest.repository.item_material_map.ItemMaterialMapRepository;
import net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMap;
import net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMapRepository;
import net.heronation.zeyo.rest.repository.item_shopmall_map.QItemShopmallMap;
import net.heronation.zeyo.rest.repository.item_size_option_map.ItemSizeOptionMap;
import net.heronation.zeyo.rest.repository.item_size_option_map.ItemSizeOptionMapRepository;
import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.madein.MadeinRepository;
import net.heronation.zeyo.rest.repository.material.MaterialRepository;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.MemberRepository;
import net.heronation.zeyo.rest.repository.member.QMember;
import net.heronation.zeyo.rest.repository.shopmall.QShopmall;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
import net.heronation.zeyo.rest.repository.shopmall.ShopmallRepository;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;
import net.heronation.zeyo.rest.repository.size_option.SizeOptionRepository;
import net.heronation.zeyo.rest.repository.size_table.QSizeTable;
import net.heronation.zeyo.rest.repository.size_table.SizeTable;
import net.heronation.zeyo.rest.repository.size_table.SizeTableRepository;
import net.heronation.zeyo.rest.repository.sub_category.QSubCategory;
import net.heronation.zeyo.rest.repository.sub_category.SubCategoryRepository;
import net.heronation.zeyo.rest.repository.warranty.WarrantyRepository;

@Slf4j
@Service
@Transactional
public class ItemServiceImpl implements ItemService {

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
	EntityManager entityManager;

	@Override
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

		String start_price = (String) param.get("start");
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
		List<Map<String, Object>> count_list = count_q.getResultList();

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

	@Override
	@Transactional
	public String toggle_link(List<ToggleVO> param, Long seq) {
		// TODO Auto-generated method stub

		for (ToggleVO tv : param) {

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
	public String delete(List<ToggleVO> param, Long seq) {
		for (ToggleVO tv : param) {

			Item i = itemRepository.findOne(tv.getId());

			if (!i.getMember().getId().equals(seq))
				continue;

			i.setUseYn("N");

		}

		return "Y";
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Map<String, Object>> shopmall_list(Long item_id, Pageable page) {

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

		return new PageImpl<Map<String, Object>>(return_list, page, count_list.size());
	}

	private void build_size_table() throws CommonException {

	}

	@Override
	@Transactional
	public String toggle_size_table(List<ToggleVO> param) {

		for (ToggleVO tv : param) {

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

		new_item = itemRepository.save(new_item);

		// 사이즈 테이블 처리
		if (ibd.getSizeTableYn().equals("Y")) {
			new_item.setSizeTableYn("Y");

			// 사이즈 테이블 생성

			SizeTable new_st = new SizeTable();
			// 정보입력

			new_st.setCreateDt(new DateTime());
			new_st.setUseYn("Y");
			new_st.setItem(new_item);
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

			new_item.setSizeTableYn("N");
		}

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

		List<ItemSizeOptionMap> isomlist = ibd.getItemSizeOptionMaps();

		if (isomlist.size() != 0) {
			for (ItemSizeOptionMap isom : isomlist) {
				isom.setItem(new_item);
				if (isom.getOptionValue().equals("DIRECT")) {
					SizeOption injected_size_option = sizeOptionRepository.save(isom.getSizeOption());
					isom.setSizeOption(injected_size_option);
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

		return new_item;
	}

	@Override
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
		select_query.append("    i.price				as item_price, ");
		select_query.append("    i.create_dt			as item_create_dt, ");
		select_query.append("    i.link_yn				as item_link_yn, ");
		select_query.append("    i.size_table_yn		as item_size_table_yn, ");
		select_query.append("    st.id					as size_table_id ");

		StringBuffer where_query = new StringBuffer();

		where_query.append("FROM ");
		where_query.append("    item i ");
		where_query.append("        INNER JOIN ");
		where_query.append("    category c ON i.category_id = c.id ");
		where_query.append("        INNER JOIN ");
		where_query.append("    sub_category sc ON i.sub_category_id = sc.id ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    size_table st ON i.id = st.item_id ");
		where_query.append("WHERE ");
		where_query.append("    i.use_yn = 'Y' ");
		where_query.append("    AND c.use_yn = 'Y' ");
		where_query.append("	AND sc.use_yn = 'Y'");

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
			where_query.append("        AND b.create_dt >= STR_TO_DATE('" + start + "', '%Y-%m-%d %H:%i:%s')");
		}

		String end = (String) param.get("end");
		if (end != null) {
			where_query.append("        AND b.create_dt <= STR_TO_DATE('" + end + "', '%Y-%m-%d %H:%i:%s')");
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
		List<Map<String, Object>> count_list = count_q.getResultList();

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
	public Item modify(ItemModifyDto ibd, Long member_id) {
		log.debug("build");

		Member user = memberRepository.findOne(member_id);
		Item old_item = itemRepository.findOne(ibd.getItem_id());

		old_item.setCode(ibd.getCode());
		old_item.setCreateDt(new DateTime());
		old_item.setBleachYn(ibd.getBleachYn());
		old_item.setDrycleaningYn(ibd.getDrycleaningYn());
		old_item.setDrymethodYn(ibd.getDrymethodYn());
		old_item.setIroningYn(ibd.getIroningYn());
		old_item.setLaundryYn(ibd.getLaundryYn());

		old_item.setImage(ibd.getImage());
		old_item.setImageMode(ibd.getImageMode());
		old_item.setMadeinBuilder(ibd.getMadeinBuilder());
		old_item.setMadeinDate(ibd.getMadeinDate());
		old_item.setName(ibd.getName());
		old_item.setPrice(ibd.getPrice());
		old_item.setSizeMeasureImage(ibd.getSizeMeasureImage());
		old_item.setSizeMeasureMode(ibd.getSizeMeasureMode());

		if (ibd.getBrand() != null)
			brandRepository.save(ibd.getBrand());

		categoryRepository.save(ibd.getCategory());
		madeinRepository.save(ibd.getMadein());
		subCategoryRepository.save(ibd.getSubCategory());
		warrantyRepository.save(ibd.getWarranty());

		old_item.setBrand(ibd.getBrand());
		old_item.setCategory(ibd.getCategory());
		old_item.setMadein(ibd.getMadein());
		old_item.setMember(user);
		old_item.setSubCategory(ibd.getSubCategory());
		old_item.setWarranty(ibd.getWarranty());
		old_item.setLinkYn("N");
		old_item.setUseYn("Y");

		old_item = itemRepository.save(old_item);

		// 사이즈 테이블 처리
		if (ibd.getSizeTableYn().equals("Y")) {
			old_item.setSizeTableYn("Y");

			// 사이즈 테이블 생성

			SizeTable new_st = new SizeTable();
			// 정보입력

			new_st.setCreateDt(new DateTime());
			new_st.setUseYn("Y");
			new_st.setItem(old_item);
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

			old_item.setSizeTableYn("N");
		}

		List<Shopmall> isms = ibd.getShopmalls();
		if (isms.size() != 0) {

			List<ItemShopmallMap> ismms = new ArrayList<ItemShopmallMap>();
			for (Shopmall sm : isms) {
				ItemShopmallMap ismm = new ItemShopmallMap();
				ismm.setItem(old_item);
				ismm.setShopmall(sm);
				ismm.setUseYn("Y");

				ismms.add(ismm);
			}

			itemShopmallMapRepository.save(ismms);
		}

		if (ibd.getBleachYn().equals("Y")) {
			ItemBleachMap item_bleach = ibd.getItemBleachMap();
			item_bleach.setItem(old_item);

			itemBleachMapRepository.save(item_bleach);
		}

		List<ItemClothColorMap> icclist = ibd.getItemClothColorMaps();

		if (icclist.size() != 0) {
			for (ItemClothColorMap iccitem : icclist) {
				iccitem.setItem(old_item);
				if (iccitem.getOptionValue().equals("DIRECT")) {
					ClothColor injected_cloth_color = clothColorRepository.save(iccitem.getClothColor());

					iccitem.setClothColor(injected_cloth_color);
				}
			}
			itemClothColorMapRepository.save(icclist);
		}

		if (ibd.getDrycleaningYn().equals("Y")) {
			ItemDrycleaningMap idcm = ibd.getItemDrycleaningMap();
			idcm.setItem(old_item);
			itemDrycleaningMapRepository.save(idcm);
		}

		if (ibd.getDrymethodYn().equals("Y")) {
			ItemDrymethodMap idm = ibd.getItemDrymethodMap();
			idm.setItem(old_item);
			itemDrymethodMapRepository.save(idm);
		}

		List<ItemFitInfoOptionMap> ifiolist = ibd.getItemFitInfoOptionMaps();

		if (ifiolist.size() != 0) {
			for (ItemFitInfoOptionMap ifio : ifiolist) {
				ifio.setItem(old_item);
			}
			itemFitInfoOptionMapRepository.save(ifiolist);
		}

		if (ibd.getIroningYn().equals("Y")) {
			ItemIroningMap iim = ibd.getItemIroningMap();
			iim.setItem(old_item);
			itemIroningMapRepository.save(iim);
		}

		if (ibd.getLaundryYn().equals("Y")) {
			ItemLaundryMap ilm = ibd.getItemLaundryMap();
			ilm.setItem(old_item);
			itemLaundryMapRepository.save(ilm);
		}

		List<ItemSizeOptionMap> isomlist = ibd.getItemSizeOptionMaps();

		if (isomlist.size() != 0) {
			for (ItemSizeOptionMap isom : isomlist) {
				isom.setItem(old_item);
				if (isom.getOptionValue().equals("DIRECT")) {
					SizeOption injected_size_option = sizeOptionRepository.save(isom.getSizeOption());
					isom.setSizeOption(injected_size_option);
				}
			}
			itemSizeOptionMapRepository.save(isomlist);
		}

		List<ItemMaterialMap> imms = ibd.getMaterials();

		if (imms.size() != 0) {
			for (ItemMaterialMap imm : imms) {
				imm.setItem(old_item);
			}
			itemMaterialMapRepository.save(imms);
		}

		return old_item;
	}

	@Override
	public String change_connect(ItemDto param, Long seq) {
		// TODO Auto-generated method stub
		return null;
	}

	public String arrayExcel(List<ToggleVO> param, Pageable pageable) throws IOException {

		String today = DateTime.now().toString("yyyy-MM-dd");
		String temp_path = "D:\\TEST_SERVER_ROOT\\temp\\".concat(today).concat("\\");
		String excel_path = UUID.randomUUID().toString().concat(".xls");

		
		FileUtils.forceMkdir(new File(temp_path));
		
		QItem i = QItem.item;

		BooleanBuilder builder = new BooleanBuilder();

		for (ToggleVO tv : param) {
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

			row = sheet.createRow((short)row_index);
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

		return "/temp/".concat(today).concat("/").concat(excel_path);

	}

}