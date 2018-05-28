package net.heronation.zeyo.rest.service.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
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
	private  SizeOptionRepository sizeOptionRepository;
	
	
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
	private ItemShopmallMapRepository  itemShopmallMapRepository;
	
	@Autowired
	private ItemSizeOptionMapRepository  itemSizeOptionMapRepository;
	
	
	@Autowired
	EntityManager entityManager;

	@Override
	public Page<Map<String, Object>> search(Predicate where, Pageable page) {
  
		QItem i = QItem.item;
		QBrand b = QBrand.brand;
		QShopmall s = QShopmall.shopmall;
		QCategory c = QCategory.category;
		QMember m = QMember.member;
		QSubCategory sc = QSubCategory.subCategory;
		
		QCompanyNoHistory cnh = QCompanyNoHistory.companyNoHistory;
		QItemShopmallMap ism = QItemShopmallMap.itemShopmallMap;
		
		
		JPAQuery<Member> query = new JPAQuery<Member>(entityManager);
		 
	 
		QueryResults<Tuple> R = query.select( 
				i.id,i.name,b.name,c.name,sc.name,i.price,i.createDt,i.linkYn
		).from(i)
				.innerJoin(i.brand,b)
				.innerJoin(i.category,c)
				.innerJoin(i.subCategory,sc) 
				.innerJoin(i.member,m) 
				//.innerJoin(m,cnh.member).on(cnh.id.in(JPAExpressions.select(cnh.id.max()).from(cnh).groupBy(cnh.member.id)))

				.where(b.useYn.eq("Y")
						.and(c.useYn.eq("Y"))
						.and(sc.useYn.eq("Y"))
						.and(m.useYn.eq("Y")).and(where))
						 
				.fetchResults();

		List<Tuple> search_list = R.getResults();
		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Tuple row : search_list) {
			Map<String, Object> search_R = new HashMap<String, Object>();
 
			search_R.put("item_id", row.get(i.id));
			search_R.put("item_name", row.get(i.name)); 
		//	search_R.put("company_name", row.get(cnh.name));
			search_R.put("brand_name", row.get(b.name));
			//search_R.put("shopmall_list", row.get(i.itemShopmallMaps.size()));
			search_R.put("category_name", row.get(c.name));
			search_R.put("sub_category_name", row.get(sc.name));
			search_R.put("price", row.get(i.price));
			search_R.put("item_change_dt", row.get(i.createDt));
			search_R.put("item_link_yn", row.get(i.linkYn));
				
 
			return_list.add(search_R);
		}
		return new PageImpl<Map<String, Object>>(return_list, page, R.getTotal());
	}

	@Override
	@Transactional
	public String change_connect(String target) {
		// TODO Auto-generated method stub

		String[] targets = target.split(",");

		for (int a = 0; a < targets.length; a++) {

			Item i = itemRepository.findOne(Long.valueOf(targets[a]));
			String linkYN = i.getLinkYn();
			if (linkYN == null) {

			} else {
				if (linkYN.equals("Y")) {
					i.setLinkYn("N");
				} else {
					i.setLinkYn("Y");
				}
			}
		}

		return "Y";
	}

	@Override
	@Transactional
	public String delete(String target) {
		String[] targets = target.split(",");

		for (int a = 0; a < targets.length; a++) {

			itemRepository.delete(Long.valueOf(targets[a]));

		}

		return "Y";
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Map<String,Object>> shopmall_list(Long item_id,Pageable page) {
		 
		StringBuffer  count_query = new StringBuffer();
		count_query.append("SELECT "); 
		count_query.append("    count(*) "); 
		
		
		StringBuffer  select_query = new StringBuffer(); 
		select_query.append("SELECT ");
		select_query.append("    i.name as item_name ");
		select_query.append("   ,s.name as shopmall_name ");
		select_query.append("   ,b.name as brand_name  ");

		
		
		
		StringBuffer  where_query = new StringBuffer(); 
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
 
		
		StringBuffer  sort_query = new StringBuffer();
		sort_query.append("  ORDER BY i.");
		Sort sort = page.getSort();
		String sep = "";
		for(Sort.Order order   : sort) {
			sort_query.append(sep);
			sort_query.append(order.getProperty());
			sort_query.append(" ");
			sort_query.append(order.getDirection());
			 sep = ", ";
		}
		
		StringBuffer page_query  = new StringBuffer(); 
		page_query.append("  limit "); 
		page_query.append((page.getPageNumber() - 1) * page.getPageSize()); 
		page_query.append(" , ");
		page_query.append(page.getPageSize());
		 
		Query count_q =   entityManager.createNativeQuery(count_query.append(where_query).toString());
		List<Map<String,Object>> count_list = count_q.getResultList();
		
		Query q =   entityManager.createNativeQuery(select_query.append(where_query).append(sort_query).append(page_query).toString());
		List<Object[]> list = q.getResultList();

		List<Map<String,Object>> return_list = new ArrayList<Map<String,Object>>();
		
		for (Object[] row : list) {
			Map<String, Object> search_R = new HashMap<String, Object>();
  
//			select_query.append("    i.name as item_name ");
//			select_query.append("   ,s.name as shopmall_name ");
//			select_query.append("   ,b.name as brand_name  ");
			
			search_R.put("item_name", row[0]);
			search_R.put("shopmall_name", row[1]);
			search_R.put("brand_name", row[2]);  
			
			return_list.add(search_R);
		}
		
		return new PageImpl<Map<String,Object>>(return_list, page, count_list.size());
	}

	@Override
	@Transactional
	public String toggle_size_table(Long item_id) {
		Item r = itemRepository.findOne(item_id);
		
		if(r == null) {
			return null;
		}else {
			 String table = r.getSizeTableYn();
			 QSizeTable st = QSizeTable.sizeTable;
			 
			 SizeTable db_st= sizeTableRepository.findOne(st.item.id.eq(item_id));
			 
			 if(table.equals("N")) {
				 
				 if(db_st == null) {
					 
					 SizeTable new_st = new SizeTable();
					 // 정보입력 
					 
					 
					 
					 new_st.setItem(r);
					 sizeTableRepository.save(new_st);
					 
				 }else{
					 
				 }
				 
				 r.setSizeTableYn("Y");
			 }else {
				 r.setSizeTableYn("N");
			 }
		}
		
		
		return "S";
	}

	@Override
	@Transactional
	public Item build(ItemBuildDto ibd,Long member_id) {
		log.debug("build");
		
		Item new_item = new Item();
		Member user = memberRepository.findOne(member_id);

		// 사이즈 테이블 처리 
		if(ibd.getSizeTableYn().equals("Y")) {
			new_item.setSizeTableYn("Y");
		}else {
			
			new_item.setSizeTableYn("N");
		}
		
		
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
		
		
 
		
		List<Shopmall> isms = ibd.getShopmalls();
		if(isms.size()!=0) {
			
			List<ItemShopmallMap> ismms = new ArrayList<ItemShopmallMap>();
			for(Shopmall sm :  isms) {
				ItemShopmallMap ismm = new ItemShopmallMap();
				ismm.setItem(new_item);
				ismm.setShopmall(sm);
				ismm.setUseYn("Y");
				
				ismms.add(ismm);
			}
			
			itemShopmallMapRepository.save(ismms);
		}
		
		
		
		
		if(ibd.getBleachYn().equals("Y")) {
			ItemBleachMap item_bleach  = ibd.getItemBleachMap();
			item_bleach.setItem(new_item);
			
			itemBleachMapRepository.save(item_bleach);	
		}
		
		
		
		List<ItemClothColorMap> icclist = ibd.getItemClothColorMaps();
		
		if(icclist.size()!=0) {
			for(ItemClothColorMap iccitem :  icclist) {
				iccitem.setItem(new_item);
				if(iccitem.getOptionValue().equals("DIRECT")) {
					ClothColor injected_cloth_color = clothColorRepository.save(iccitem.getClothColor());
					
					iccitem.setClothColor(injected_cloth_color); 
				}
			}
			itemClothColorMapRepository.save(icclist);
		}
		
		
		if(ibd.getDrycleaningYn().equals("Y")) {
			ItemDrycleaningMap idcm = ibd.getItemDrycleaningMap();
			idcm.setItem(new_item);
			itemDrycleaningMapRepository.save(idcm);	
		}
		
		
		if(ibd.getDrymethodYn().equals("Y")) {
			ItemDrymethodMap idm = ibd.getItemDrymethodMap();
			idm.setItem(new_item);
			itemDrymethodMapRepository.save(idm);	
		}
		 
		 
		
		List<ItemFitInfoOptionMap> ifiolist = ibd.getItemFitInfoOptionMaps();
		
		if(ifiolist.size()!=0) {
			for(ItemFitInfoOptionMap ifio :  ifiolist) {
				ifio.setItem(new_item);
			}
			itemFitInfoOptionMapRepository.save(ifiolist);	
		}
		
		
		if(ibd.getIroningYn().equals("Y")) {
			ItemIroningMap iim = ibd.getItemIroningMap();
			iim.setItem(new_item);
			itemIroningMapRepository.save(iim);	
		}
		
		
		if(ibd.getLaundryYn().equals("Y")) {
			ItemLaundryMap ilm = ibd.getItemLaundryMap();
			ilm.setItem(new_item);
			itemLaundryMapRepository.save(ilm);	
		}
		
		 
		
		List<ItemSizeOptionMap> isomlist = ibd.getItemSizeOptionMaps();
		
		if(isomlist.size()!=0) {
			for(ItemSizeOptionMap isom :  isomlist) {
				isom.setItem(new_item);
				if(isom.getOptionValue().equals("DIRECT")) {
					SizeOption injected_size_option = sizeOptionRepository.save(isom.getSizeOption());
					isom.setSizeOption(injected_size_option);
				}
			}
			itemSizeOptionMapRepository.save(isomlist);	
		}
		
		List<ItemMaterialMap> imms = ibd.getMaterials();
		 
		if(imms.size()!=0) {
			for(ItemMaterialMap imm :  imms) {
				imm.setItem(new_item); 
			}
			itemMaterialMapRepository.save(imms);
		}
		
		
		return new_item;
	}

	@Override
	public Page<Map<String, Object>> client_search(Map<String, Object> param, Pageable page) {
		
		StringBuffer  count_query = new StringBuffer();
		count_query.append("SELECT "); 
		count_query.append("    count(*) "); 
		
 
		
		
		StringBuffer  select_query = new StringBuffer();  
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


		
		StringBuffer  where_query = new StringBuffer(); 
		
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
		
		
		String member_id = (String)param.get("member_id");
		
		where_query.append("        AND i.member_id = "+member_id+" ");	
		
		
//		param.put("name", name);  
//		param.put("size_link", size_link);
//		param.put("category", category);
//		param.put("sub_category", sub_category);
//		param.put("start_price", start_price);
//		param.put("end_price", end_price);
		
		
		String name = (String)param.get("name");
		if(name != null) {
			where_query.append("        AND i.name like '%"+name+"%' ");	
		}
	
		String size_link = (String)param.get("size_link");
		if(size_link != null && size_link.equals("Y")) {
			where_query.append("        AND i.link_yn = 'Y' ");	
		}
		
		String category = (String)param.get("category");
		if(category != null) {
			where_query.append("        AND i.category_id = "+category+" ");	
		}
		
		String sub_category = (String)param.get("sub_category");
		if(sub_category != null) {
			where_query.append("        AND i.sub_category_id = "+sub_category+" ");	
		}
		
 
		
		String start_price = (String)param.get("start_price");
		if(start_price != null) {
			where_query.append("        AND i.price >= "+start_price+" ");	
		}
		
		String end_price = (String)param.get("end_price");
		if(end_price != null) {
			where_query.append("        AND i.price <= "+end_price+" ");	
		}
		
		String start = (String)param.get("start");
		if(start != null  ) {
			where_query.append("        AND b.create_dt >= STR_TO_DATE('"+start+"', '%Y-%m-%d %H:%i:%s')");	
		}
		
		
		String end = (String)param.get("end");
		if(end != null  ) {
			where_query.append("        AND b.create_dt <= STR_TO_DATE('"+end+"', '%Y-%m-%d %H:%i:%s')");	
		}
		
		 

		
		StringBuffer sort_query  = new StringBuffer(); 
		sort_query.append("  ORDER BY i.");
		Sort sort = page.getSort();
		String sep = "";
		for(Sort.Order order   : sort) {
			sort_query.append(sep);
			sort_query.append(order.getProperty());
			sort_query.append(" ");
			sort_query.append(order.getDirection());
			 sep = ", ";
		}
		
		StringBuffer page_query  = new StringBuffer(); 
		page_query.append("  limit "); 
		page_query.append((page.getPageNumber() - 1) * page.getPageSize()); 
		page_query.append(" , ");
		page_query.append(page.getPageSize());
		 
		Query count_q =   entityManager.createNativeQuery(count_query.append(where_query).toString());
		List<Map<String,Object>> count_list = count_q.getResultList();
		
		Query q =   entityManager.createNativeQuery(select_query.append(where_query).append(sort_query).append(page_query).toString());
		List<Object[]> list = q.getResultList();

		List<Map<String,Object>> return_list = new ArrayList<Map<String,Object>>();
		
		for (Object[] row : list) {
			Map<String, Object> search_R = new HashMap<String, Object>(); 
			
			
//			select_query.append("    i.id					as item_id, ");
//			select_query.append("    i.name					as item_name, ");
//			select_query.append("    i.code					as item_code, ");
//			select_query.append("    c.name					as category_name, ");
//			select_query.append("    sc.name				as sub_category_name, ");
//			select_query.append("    i.price				as item_price, ");
//			select_query.append("    i.create_dt			as item_create_dt, ");
//			select_query.append("    i.link_yn				as item_link_yn, ");
//			select_query.append("    i.size_table_yn		as item_size_table_yn, ");
//			select_query.append("    st.id					as size_table_id ");
			
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
		
		return new PageImpl<Map<String,Object>>(return_list, page, count_list.size());
	}

}