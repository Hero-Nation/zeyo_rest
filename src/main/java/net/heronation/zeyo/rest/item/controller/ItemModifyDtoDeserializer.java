package net.heronation.zeyo.rest.item.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.brand.repository.Brand;
import net.heronation.zeyo.rest.brand.repository.BrandRepository;
import net.heronation.zeyo.rest.category.repository.Category;
import net.heronation.zeyo.rest.category.repository.CategoryRepository;
import net.heronation.zeyo.rest.cloth_color.repository.ClothColor;
import net.heronation.zeyo.rest.cloth_color.repository.ClothColorRepository;
import net.heronation.zeyo.rest.common.dto.FileDto;
import net.heronation.zeyo.rest.fit_info_option.repository.FitInfoOption;
import net.heronation.zeyo.rest.fit_info_option.repository.FitInfoOptionRepository;
import net.heronation.zeyo.rest.item.repository.ItemModifyDto;
import net.heronation.zeyo.rest.item_bleach_map.repository.ItemBleachMap;
import net.heronation.zeyo.rest.item_bleach_map.repository.ItemBleachMapRepository;
import net.heronation.zeyo.rest.item_cloth_color_map.repository.ItemClothColorMap;
import net.heronation.zeyo.rest.item_cloth_color_map.repository.ItemClothColorMapRepository;
import net.heronation.zeyo.rest.item_drycleaning_map.repository.ItemDrycleaningMap;
import net.heronation.zeyo.rest.item_drycleaning_map.repository.ItemDrycleaningMapRepository;
import net.heronation.zeyo.rest.item_drymethod_map.repository.ItemDrymethodMap;
import net.heronation.zeyo.rest.item_drymethod_map.repository.ItemDrymethodMapRepository;
import net.heronation.zeyo.rest.item_fit_info_option_map.repository.ItemFitInfoOptionMap;
import net.heronation.zeyo.rest.item_fit_info_option_map.repository.ItemFitInfoOptionMapRepository;
import net.heronation.zeyo.rest.item_ironing_map.repository.ItemIroningMap;
import net.heronation.zeyo.rest.item_ironing_map.repository.ItemIroningMapRepository;
import net.heronation.zeyo.rest.item_laundry_map.repository.ItemLaundryMap;
import net.heronation.zeyo.rest.item_laundry_map.repository.ItemLaundryMapRepository;
import net.heronation.zeyo.rest.item_material_map.repository.ItemMaterialMap;
import net.heronation.zeyo.rest.item_material_map.repository.ItemMaterialMapRepository;
import net.heronation.zeyo.rest.item_scmm_so_value.repository.ItemScmmSoValue;
import net.heronation.zeyo.rest.item_scmm_so_value.repository.ItemScmmSoValueRepository;
import net.heronation.zeyo.rest.item_shopmall_map.repository.ItemShopmallMap;
import net.heronation.zeyo.rest.item_shopmall_map.repository.ItemShopmallMapRepository;
import net.heronation.zeyo.rest.item_size_option_map.repository.ItemSizeOptionMap;
import net.heronation.zeyo.rest.item_size_option_map.repository.ItemSizeOptionMapRepository;
import net.heronation.zeyo.rest.kindof.repository.Kindof;
import net.heronation.zeyo.rest.kindof.repository.KindofRepository;
import net.heronation.zeyo.rest.madein.repository.Madein;
import net.heronation.zeyo.rest.madein.repository.MadeinRepository;
import net.heronation.zeyo.rest.material.repository.Material;
import net.heronation.zeyo.rest.material.repository.MaterialRepository;
import net.heronation.zeyo.rest.shopmall.repository.Shopmall;
import net.heronation.zeyo.rest.shopmall.repository.ShopmallRepository;
import net.heronation.zeyo.rest.size_option.repository.SizeOption;
import net.heronation.zeyo.rest.size_option.repository.SizeOptionRepository;
import net.heronation.zeyo.rest.sub_category.repository.SubCategory;
import net.heronation.zeyo.rest.sub_category.repository.SubCategoryRepository;
import net.heronation.zeyo.rest.sub_category_measure_map.repository.QSubCategoryMeasureMap;
import net.heronation.zeyo.rest.sub_category_measure_map.repository.SubCategoryMeasureMap;
import net.heronation.zeyo.rest.sub_category_measure_map.repository.SubCategoryMeasureMapRepository;
import net.heronation.zeyo.rest.warranty.repository.Warranty;
import net.heronation.zeyo.rest.warranty.repository.WarrantyRepository;

@Slf4j
public class ItemModifyDtoDeserializer extends JsonDeserializer {

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
	private ItemMaterialMapRepository itemMaterialMapRepository;

	@Autowired
	private ItemClothColorMapRepository itemClothColorMapRepository;

	@Autowired
	private ItemSizeOptionMapRepository itemSizeOptionMapRepository;

	@Autowired
	private ItemFitInfoOptionMapRepository itemFitInfoOptionMapRepository;

	@Autowired
	private ItemLaundryMapRepository itemLaundryMapRepository;

	@Autowired
	private ItemDrycleaningMapRepository itemDrycleaningMapRepository;

	@Autowired
	private ItemIroningMapRepository itemIroningMapRepository;

	@Autowired
	private ItemDrymethodMapRepository itemDrymethodMapRepository;

	@Autowired
	private ItemBleachMapRepository itemBleachMapRepository;

	@Autowired
	private ItemShopmallMapRepository itemShopmallMapRepository;

	@Autowired
	private KindofRepository kindofRepository;

	@Autowired
	private SubCategoryMeasureMapRepository subCategoryMeasureMapRepository;
	
	@Autowired
	private ItemScmmSoValueRepository itemScmmSoValueRepository;
	
	
	
	

	@Value(value = "${zeyo.config.index.kindof.direct}")
	private String index_kindof_direct;

	@Value(value = "${zeyo.config.index.sizeoption.kindof.direct}")
	private String index_sizeoption_kindof_direct;

	@Override
	public Object deserialize(JsonParser jsonParser, DeserializationContext arg1)
			throws IOException, JsonProcessingException {

		log.debug("ItemModifyDtoDeserializer : deserialize");

		// kindof table (변경될수 있음)
		// '1', 'input_type', '관리자', 'Y'
		// '2', 'input_type', '직접입력', 'Y'
		// '3', 'bbs_type', '제휴문의', 'Y'
		// '4', 'bbs_type', '1대1문의', 'Y'
		// '5', 'madein_input_type', '관리자', 'Y'
		// '6', 'size_option', '기호', 'Y'
		// '7', 'size_option', '숫자', 'Y'
		// '8', 'size_option', '직접입력', 'Y'
		// '9', 'size_option', '숫자 - 하위', 'Y'

		// 공통
		// Kindof kindof_admin_input = kindofRepository.findOne(1L);
		Kindof kindof_direct_input = kindofRepository.findOne(Long.valueOf(index_kindof_direct));

		// Kindof kindof_size_option_char = kindofRepository.findOne(6L);
		// Kindof kindof_size_option_num = kindofRepository.findOne(7L);
		Kindof kindof_size_option_direct = kindofRepository.findOne(Long.valueOf(index_sizeoption_kindof_direct));
		// Kindof kindof_size_option_num_bottom = kindofRepository.findOne(9L);

		JsonNode node = jsonParser.getCodec().readTree(jsonParser);

		log.debug("ItemModifyDtoDeserializer : ITEM ID");
		/// BRAND
		Long item_id = node.get("item_id").asLong();

		log.debug("ItemModifyDtoDeserializer : BRAND");
		/// BRAND
		Long brand_id = node.get("brand").asLong();

		Brand brand = null;
		if (brand_id == 0) {

		} else {
			brand = brandRepository.findOne(brand_id);
		}

		// category
		log.debug("ItemModifyDtoDeserializer : Category");
		Long category_id = node.get("category").asLong();

		Category category = categoryRepository.findOne(category_id);

		// subCategory
		log.debug("ItemModifyDtoDeserializer : SubCategory");
		Long subCategory_id = node.get("subCategory").asLong();

		SubCategory subCategory = subCategoryRepository.findOne(subCategory_id);

		String imageMode = node.get("imageMode").textValue();

		Iterator<JsonNode> image_iterator = node.get("image").elements();
		List<FileDto> image = new ArrayList<FileDto>();
		
		while(image_iterator.hasNext()) {
			JsonNode image_data = image_iterator.next();
			String real_name = image_data.get("real_name").textValue();
			String temp_name = image_data.get("temp_name").textValue();
			
			FileDto fd = new FileDto();
			fd.setReal_name(real_name);
			fd.setTemp_name(temp_name);
			
			image.add(fd);
			
		}

		String sizeMeasureMode = node.get("sizeMeasureMode").textValue();
 
		Iterator<JsonNode> sizeMeasureImage_iterator = node.get("sizeMeasureImage").elements();
		
		
		List<FileDto> sizeMeasureImage = new ArrayList<FileDto>();
		
		while(sizeMeasureImage_iterator.hasNext()) {
			JsonNode sizeMeasureImage_data = sizeMeasureImage_iterator.next();
			String real_name = sizeMeasureImage_data.get("real_name").textValue();
			String temp_name = sizeMeasureImage_data.get("temp_name").textValue();
			
			FileDto fd = new FileDto();
			fd.setReal_name(real_name);
			fd.setTemp_name(temp_name);
			
			sizeMeasureImage.add(fd); 
		}

		
		
		String name = node.get("name").textValue();

		String code = node.get("code").textValue();

		int price = Integer.parseInt(node.get("price").textValue());

		String madeinBuilder = node.get("madeinBuilder").textValue();

		log.debug("ItemModifyDtoDeserializer : Madein");
		JsonNode madein_node = node.get("madein");
		Madein madein = null;

		long madein_kindof = madein_node.get("kindof").asLong();

		// 관리자 1 , 직접입력 2
		if (madein_kindof == 2) {

			String madein_input_value = madein_node.get("inputValue").textValue();

			madein = new Madein();
			madein.setCreateDt(new DateTime());
			madein.setKindof(kindof_direct_input);
			madein.setName(madein_input_value);
			madein.setUseYn("Y");
			
			madein = madeinRepository.save(madein);
			
		} else {

			Long madein_id = madein_node.get("id").asLong();

			madein = madeinRepository.findOne(madein_id);
		}

		String madeinDate_str = node.get("madeinDate").textValue();

		DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

		DateTime madeinDate = dtf.parseDateTime(madeinDate_str);

		log.debug("ItemModifyDtoDeserializer : Warranty");
		JsonNode warranty_node = node.get("warranty");
		Warranty warranty = null;

		long warranty_kindof = warranty_node.get("kindof").asLong();

		// 관리자 1 , 직접입력 2
		if (warranty_kindof == 2) {

			
			
			String warranty_input_value = warranty_node.get("inputValue").textValue();

			List<Warranty> db_warranty_list = warrantyRepository.findByName(warranty_input_value);
			
			if(db_warranty_list.size() > 0) { 
				warranty = db_warranty_list.get(0);
			}else {
				warranty = new Warranty();
				warranty.setCreateDt(new DateTime());
				warranty.setKindof(kindof_direct_input);
				warranty.setScope(warranty_input_value);
				warranty.setId(0L);
				warranty.setUseYn("Y");
			}
			
			
		} else {

			Long warranty_id = warranty_node.get("id").asLong();

			warranty = warrantyRepository.findOne(warranty_id);
		}

		/// SHOPMALL
		log.debug("ItemModifyDtoDeserializer : SHOPMALL");
		List<ItemShopmallMap> shopmalls = new ArrayList<ItemShopmallMap>();

		Iterator<JsonNode> shopmalls_iterator = node.get("shopmalls").elements();

		while (shopmalls_iterator.hasNext()) {
			
			JsonNode this_shopmall_map = shopmalls_iterator.next();
			
			Long shopmall_index = this_shopmall_map.get("id").asLong();
			Long shopmall_map_index = this_shopmall_map.get("map_id").asLong();

			Shopmall this_shopmall = shopmallRepository.findOne(shopmall_index);

			if (shopmall_map_index == 0) {

				ItemShopmallMap new_shopmall_mapp = new ItemShopmallMap();
				new_shopmall_mapp.setShopmall(this_shopmall);
				shopmalls.add(new_shopmall_mapp);

			} else {
				ItemShopmallMap old_shopmall_mapp = itemShopmallMapRepository.findOne(shopmall_map_index);
				old_shopmall_mapp.setShopmall(this_shopmall);
				shopmalls.add(old_shopmall_mapp);
			}

		}

		log.debug("ItemModifyDtoDeserializer : ItemMaterialMap");
		List<ItemMaterialMap> materials = new ArrayList<ItemMaterialMap>();

		Iterator<JsonNode> materials_iterator = node.get("materials").elements();

		while (materials_iterator.hasNext()) {
			JsonNode materials_node = materials_iterator.next();

			String useLocation = materials_node.get("useLocation").asText();
			if (!useLocation.equals("A")) {
				Long map_id = materials_node.get("map_id").asLong();
				Long material_id = materials_node.get("material").asLong();
				String contain = materials_node.get("contain").asText();
				Material db_material = materialRepository.findOne(material_id);

				if (map_id == 0) {

					ItemMaterialMap new_material_mapp = new ItemMaterialMap();
					new_material_mapp.setContain(contain);
					new_material_mapp.setMaterial(db_material);
					new_material_mapp.setUseLocatoin(useLocation);
					new_material_mapp.setUseYn("Y");
					materials.add(new_material_mapp);

				} else {

					ItemMaterialMap old_material_mapp = itemMaterialMapRepository.findOne(map_id);
					old_material_mapp.setContain(contain);
					old_material_mapp.setMaterial(db_material);
					old_material_mapp.setUseLocatoin(useLocation);
					old_material_mapp.setUseYn("Y");

					materials.add(old_material_mapp);

				}

			}
		}

		// 색상 입력
		log.debug("ItemModifyDtoDeserializer : ItemClothColorMap");
		List<ItemClothColorMap> itemClothColorMaps = new ArrayList<ItemClothColorMap>();

		Iterator<JsonNode> clothColor_iterator = node.get("clothColor").elements();

		while (clothColor_iterator.hasNext()) {
			JsonNode clothColor_node = clothColor_iterator.next();

			Long map_id = clothColor_node.get("map_id").asLong();
			Long cloth_color_id = clothColor_node.get("id").asLong();
			String inputValue = clothColor_node.get("inputValue").asText();

			// 직접입력일경우
			if (map_id == 0) {

				// size_option 테이블에서 이미 존재하는 옵션의 id를 가지고 와서 한다.
				ItemClothColorMap new_clothColor_mapp = new ItemClothColorMap();
				new_clothColor_mapp.setUseYn("Y");

				ClothColor direct_input_color = new ClothColor();
				direct_input_color.setName(inputValue);
				direct_input_color.setCreateDt(new DateTime());
				direct_input_color.setKindof(kindof_direct_input);
				direct_input_color.setUseYn("Y");
				direct_input_color.setId(0L);
				
				new_clothColor_mapp.setClothColor(direct_input_color);
				new_clothColor_mapp.setOptionValue("DIRECT");

				itemClothColorMaps.add(new_clothColor_mapp);
			} else {

				ItemClothColorMap new_clothColor_mapp = itemClothColorMapRepository.findOne(map_id);

				ClothColor db_sizeoption = clothColorRepository.findOne(cloth_color_id);
				new_clothColor_mapp.setClothColor(db_sizeoption);
				new_clothColor_mapp.setOptionValue("");

				itemClothColorMaps.add(new_clothColor_mapp);
			}

		}

		// 사이즈 같은 경우는 카테고리 설정에 의존적이다.
		// 상품의 카테고리가 상위일경우와 하위일 경우가 틀려진다.
		// 즉 카테고리에 따라서 기호, 숫자, 하위 숫자 3가지 경우가 나온다.

		// '6', 'size_option', '기호', 'Y'
		// '7', 'size_option', '숫자', 'Y'
		// '8', 'size_option', '직접입력', 'Y'
		// '9', 'size_option', '숫자 - 하위', 'Y'
		log.debug("ItemModifyDtoDeserializer : ItemSizeOptionMap");
		List<ItemSizeOptionMap> itemSizeOptionMaps = new ArrayList<ItemSizeOptionMap>();

		Iterator<JsonNode> sizeOptions_iterator = node.get("sizeOptions").elements();

		Map<String, Object> direct_size_option_store = new HashMap<String, Object>();

		while (sizeOptions_iterator.hasNext()) {
			JsonNode sizeOptions_node = sizeOptions_iterator.next();

			Long map_id = sizeOptions_node.get("map_id").asLong();
			
			Long sizeOptions_id = sizeOptions_node.get("id").asLong();
			String inputValue = sizeOptions_node.get("inputValue").asText();

			// 직접입력일경우
			if (map_id == 0) {

				// size_option 테이블에서 이미 존재하는 옵션의 id를 가지고 와서 한다.

				// SizeOption db_direct_optoin = sizeOptionRepository.findOne(direct_id);

				ItemSizeOptionMap new_sizeoptoins_mapp = new ItemSizeOptionMap();
				new_sizeoptoins_mapp.setUseYn("Y");

				SizeOption direct_input_size_option = new SizeOption();
				direct_input_size_option.setName(inputValue);
				direct_input_size_option.setCreateDt(new DateTime());
				direct_input_size_option.setKindof(kindof_size_option_direct);
				direct_input_size_option.setUseYn("Y");
				direct_input_size_option.setCategory(category);
				direct_input_size_option.setSubCategory(subCategory);
				direct_input_size_option.setId(0L);

				new_sizeoptoins_mapp.setSizeOption(direct_input_size_option);
				new_sizeoptoins_mapp.setOptionValue("DIRECT");

				direct_size_option_store.put(inputValue, direct_input_size_option);

				itemSizeOptionMaps.add(new_sizeoptoins_mapp);

			} else {

				ItemSizeOptionMap old_sizeoptoins_mapp = itemSizeOptionMapRepository.findOne(map_id);

				SizeOption db_sizeoption = sizeOptionRepository.findOne(sizeOptions_id);
				old_sizeoptoins_mapp.setSizeOption(db_sizeoption);
				// old_sizeoptoins_mapp.setOptionValue("");

				itemSizeOptionMaps.add(old_sizeoptoins_mapp);
			}

		}

		log.debug("ItemModifyDtoDeserializer : ItemFitInfoOptionMap");
		// fit 정보 관리
		List<ItemFitInfoOptionMap> itemFitInfoOptionMaps = new ArrayList<ItemFitInfoOptionMap>();

		Iterator<JsonNode> itemFitInfoOption_iterator = node.get("fitInfoOptions").elements();

		while (itemFitInfoOption_iterator.hasNext()) {
			JsonNode itemFitInfoOption_node = itemFitInfoOption_iterator.next();

			Long fitInfoOptionId = itemFitInfoOption_node.get("fitInfoOptionId").asLong();

			Long map_id = itemFitInfoOption_node.get("map_id").asLong();

			if (map_id == 0) {

				ItemFitInfoOptionMap new_FitInfoOption_mapp = new ItemFitInfoOptionMap();
				new_FitInfoOption_mapp.setUseYn("Y");

				FitInfoOption db_fitinfo = fitInfoOptionRepository.findOne(fitInfoOptionId);
				new_FitInfoOption_mapp.setFitInfoOption(db_fitinfo);

				itemFitInfoOptionMaps.add(new_FitInfoOption_mapp);

			} else {

				ItemFitInfoOptionMap old_FitInfoOption_mapp = itemFitInfoOptionMapRepository.findOne(map_id);

				FitInfoOption db_fitinfo = fitInfoOptionRepository.findOne(fitInfoOptionId);
				old_FitInfoOption_mapp.setFitInfoOption(db_fitinfo);

				itemFitInfoOptionMaps.add(old_FitInfoOption_mapp);
			}

		}

		// 세탁방법

		// "A : 제한없음
		// B : 중성세제
		// C : 알카리성세제
		// D: 산성세제"
		log.debug("ItemModifyDtoDeserializer : ItemLaundryMap");

		JsonNode laundry_node = node.get("laundry");
		Long laundry_map_id = laundry_node.get("map_id").asLong();

		ItemLaundryMap itemLaundryMap = new ItemLaundryMap();

		if (laundry_map_id == 0) {

		} else {
			itemLaundryMap = itemLaundryMapRepository.findOne(laundry_map_id);
		}

		String laundryYn = laundry_node.get("useYn").textValue();

		if (laundryYn.equals("Y")) {
			String laundry_detergent = laundry_node.get("detergent").textValue();
			String laundry_hand = laundry_node.get("hand").textValue();
			String laundry_intensity = laundry_node.get("intensity").textValue();
			String laundry_machine = laundry_node.get("machine").textValue();
			String laundry_water = laundry_node.get("water").textValue();
			String laundry_waterTemp = laundry_node.get("waterTemp").textValue();

			itemLaundryMap.setDetergent(laundry_detergent);
			itemLaundryMap.setHand(laundry_hand);
			itemLaundryMap.setIntensity(laundry_intensity);
			itemLaundryMap.setMachine(laundry_machine);
			itemLaundryMap.setUseYn("Y");
			itemLaundryMap.setWater(laundry_water);
			itemLaundryMap.setWaterTemp(laundry_waterTemp);
		} else {
			itemLaundryMap.setUseYn("N");
		}

		// "A : 제한없음
		// B : 석유계
		// C : 클로로에틸렌"
		log.debug("ItemModifyDtoDeserializer : ItemDrycleaningMap");

		JsonNode drycleaning_node = node.get("drycleaning");
		Long drycleaning_map_id = drycleaning_node.get("map_id").asLong();

		ItemDrycleaningMap itemDrycleaningMap = new ItemDrycleaningMap();

		if (drycleaning_map_id == 0) {

		} else {
			itemDrycleaningMap = itemDrycleaningMapRepository.findOne(drycleaning_map_id);
		}

		String drycleaningYn = drycleaning_node.get("useYn").textValue();

		if (drycleaningYn.equals("Y")) {
			String drycleaning_drycan = drycleaning_node.get("drycan").textValue();
			String drycleaning_storecan = drycleaning_node.get("storecan").textValue();
			String drycleaning_detergent = drycleaning_node.get("detergent").textValue();

			itemDrycleaningMap.setDetergent(drycleaning_detergent);
			itemDrycleaningMap.setStorecan(drycleaning_storecan);
			itemDrycleaningMap.setDrycan(drycleaning_drycan);
			itemDrycleaningMap.setUseYn("Y");
		} else {
			itemDrycleaningMap.setUseYn("N");
		}

		// 다림질
		log.debug("ItemModifyDtoDeserializer : ItemIroningMap");

		JsonNode itemIroning_node = node.get("ironing");

		Long itemIroning_map_id = itemIroning_node.get("map_id").asLong();

		ItemIroningMap itemIroningMap = new ItemIroningMap();

		if (drycleaning_map_id == 0) {

		} else {
			itemIroningMap = itemIroningMapRepository.findOne(itemIroning_map_id);
			
			if(itemIroningMap == null)
				itemIroningMap = new ItemIroningMap();
				itemIroningMap.setId(0L);
		}

		String ironingYn = itemIroning_node.get("useYn").textValue();

		if (ironingYn.equals("Y")) {
			String itemIroning_ironcan = itemIroning_node.get("ironcan").textValue();
			String itemIroning_addprotection = itemIroning_node.get("addprotection").textValue();
			String itemIroning_startTemp = itemIroning_node.get("startTemp").textValue();
			String itemIroning_endTemp = itemIroning_node.get("endTemp").textValue();

			itemIroningMap.setIroncan(itemIroning_ironcan);
			itemIroningMap.setAddprotection(itemIroning_addprotection);
			itemIroningMap.setStartTemp(itemIroning_startTemp);
			itemIroningMap.setEndTemp(itemIroning_endTemp);
			itemIroningMap.setUseYn("Y");
		} else {
			itemIroningMap.setUseYn("N");
		}

		// 건조방법

		// "machineDry":"Y",
		// "natureDry":"N",
		// "dryMode":"B",
		// "handDry":"C"
		log.debug("ItemModifyDtoDeserializer : ItemDrymethodMap");

		JsonNode itemDrymethod_node = node.get("drymethod");

		Long itemDrymethod_map_id = itemDrymethod_node.get("map_id").asLong();

		ItemDrymethodMap itemDrymethodMap = new ItemDrymethodMap();

		if (itemDrymethod_map_id == 0) {

		} else {
			itemDrymethodMap = itemDrymethodMapRepository.findOne(itemDrymethod_map_id);
		}

		String drymethodYn = itemDrymethod_node.get("useYn").textValue();

		if (drymethodYn.equals("Y")) {
			String itemDrymethod_machineDry = itemDrymethod_node.get("machineDry").textValue();
			String itemDrymethod_natureDry = itemDrymethod_node.get("natureDry").textValue();
			String itemDrymethod_dryMode = itemDrymethod_node.get("dryMode").textValue();
			String itemDrymethod_handDry = itemDrymethod_node.get("handDry").textValue();

			itemDrymethodMap.setMachineDry(itemDrymethod_machineDry);
			itemDrymethodMap.setNatureDry(itemDrymethod_natureDry);
			itemDrymethodMap.setDryMode(itemDrymethod_dryMode);
			itemDrymethodMap.setHandDry(itemDrymethod_handDry);
			itemDrymethodMap.setUseYn("Y");
		} else {
			itemDrymethodMap.setUseYn("N");
		}
		// 표백제 사용법

		// private String chlorine
		// private String oxygen;
		log.debug("ItemModifyDtoDeserializer : ItemBleachMap");

		JsonNode itemBleach_node = node.get("bleach");

		Long bleach_map_id = itemBleach_node.get("map_id").asLong();

		ItemBleachMap itemBleachMap = new ItemBleachMap();

		if (bleach_map_id == 0) {

		} else {
			itemBleachMap = itemBleachMapRepository.findOne(bleach_map_id);
			
			if(itemBleachMap == null) itemBleachMap = new ItemBleachMap();
		}

		String bleachYn = itemBleach_node.get("useYn").textValue();

		if (bleachYn.equals("Y")) {
			String itemBleach_chlorine = itemBleach_node.get("chlorine").textValue();
			String itemBleach_oxygen = itemBleach_node.get("oxygen").textValue();

			itemBleachMap.setChlorine(itemBleach_chlorine);
			itemBleachMap.setOxygen(itemBleach_oxygen);
			itemBleachMap.setUseYn("Y");

		} else {
			itemBleachMap.setUseYn("N");
		}

		log.debug("ItemModifyDtoDeserializer : mi_so_value");
		// fit 정보 관리

		List<ItemScmmSoValue> itemScmmSoValues = new ArrayList<ItemScmmSoValue>();

		Iterator<JsonNode> mi_so_value_iterator = node.get("mi_so_value").elements();

		while (mi_so_value_iterator.hasNext()) {

			
			JsonNode mi_so_value_node = mi_so_value_iterator.next();
			
 
			Long issv_id = mi_so_value_node.get("map_id").asLong();
			
			
			
			 // 버그 여기서 올라오는 값은 sub_category_measure_map id가 아니라 measure_item id값이다.
			JsonNode measureItem_sub_node = mi_so_value_node.get("measureItem");

			Long measure_item_id = measureItem_sub_node.get("id").asLong();
			
			


			JsonNode sizeOption_sub_node = mi_so_value_node.get("sizeOption");

			Long sizeOption_id = sizeOption_sub_node.get("id").asLong();

			String sizeOption_inputValue = sizeOption_sub_node.get("inputValue").asText();

			SizeOption this_so = new SizeOption();

			if (sizeOption_id == 0) { // 사이즈 옵션이 직접입력인 경우 앞에서 입력한 객체를 사용한다.
				this_so = (SizeOption) direct_size_option_store.get(sizeOption_inputValue);
				this_so.setId(0L);
			} else {
				this_so = sizeOptionRepository.findOne(sizeOption_id);
			}

			String mi_so_value_input_value = mi_so_value_node.get("input_value").textValue();
 
			QSubCategoryMeasureMap qscmm = QSubCategoryMeasureMap.subCategoryMeasureMap;
			SubCategoryMeasureMap this_scmm = subCategoryMeasureMapRepository.findOne(qscmm.measureItem.id.eq(measure_item_id).and(qscmm.subCategory.id.eq(subCategory_id)));
			

			if(issv_id ==0) {
				
				
				ItemScmmSoValue this_issv = new ItemScmmSoValue();
				this_issv.setInputValue(mi_so_value_input_value);
				this_issv.setSizeOption(this_so);
				this_issv.setSubCategoryMeasureMap(this_scmm);
				this_issv.setUseYn("Y");
				this_issv.setId(0L);
				itemScmmSoValues.add(this_issv);
			}else {
				
	
				ItemScmmSoValue this_issv = itemScmmSoValueRepository.findOne(issv_id);
				this_issv.setInputValue(mi_so_value_input_value);
				this_issv.setSizeOption(this_so);
				this_issv.setSubCategoryMeasureMap(this_scmm);
				itemScmmSoValues.add(this_issv);
			}
			
			


			
		} 

		return new ItemModifyDto(item_id, brand, category, subCategory, imageMode, image, sizeMeasureMode,
				sizeMeasureImage, name, code, price, madeinBuilder, madein, madeinDate, warranty, shopmalls, materials,
				itemSizeOptionMaps, itemClothColorMaps, itemScmmSoValues, laundryYn, itemLaundryMap, drycleaningYn,
				itemDrycleaningMap, ironingYn, itemIroningMap, drymethodYn, itemDrymethodMap, bleachYn, itemBleachMap,
				itemFitInfoOptionMaps);

	}

}
