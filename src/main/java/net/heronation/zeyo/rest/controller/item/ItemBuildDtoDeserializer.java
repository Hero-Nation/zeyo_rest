package net.heronation.zeyo.rest.controller.item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j; 
import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.brand.BrandRepository;
import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.category.CategoryRepository;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColor;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColorRepository;
import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOption;
import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOptionRepository;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.item.ItemBuildDto;
import net.heronation.zeyo.rest.repository.item.ItemRepository;
import net.heronation.zeyo.rest.repository.item_bleach_map.ItemBleachMap;
import net.heronation.zeyo.rest.repository.item_cloth_color_map.ItemClothColorMap;
import net.heronation.zeyo.rest.repository.item_drycleaning_map.ItemDrycleaningMap;
import net.heronation.zeyo.rest.repository.item_drymethod_map.ItemDrymethodMap;
import net.heronation.zeyo.rest.repository.item_fit_info_option_map.ItemFitInfoOptionMap;
import net.heronation.zeyo.rest.repository.item_ironing_map.ItemIroningMap;
import net.heronation.zeyo.rest.repository.item_laundry_map.ItemLaundryMap;
import net.heronation.zeyo.rest.repository.item_material_map.ItemMaterialMap;
import net.heronation.zeyo.rest.repository.item_size_option_map.ItemSizeOptionMap;
import net.heronation.zeyo.rest.repository.kindof.Kindof;
import net.heronation.zeyo.rest.repository.kindof.KindofRepository;
import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.madein.MadeinRepository;
import net.heronation.zeyo.rest.repository.material.Material;
import net.heronation.zeyo.rest.repository.material.MaterialRepository;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
import net.heronation.zeyo.rest.repository.shopmall.ShopmallRepository;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;
import net.heronation.zeyo.rest.repository.size_option.SizeOptionRepository;
import net.heronation.zeyo.rest.repository.sub_category.SubCategory;
import net.heronation.zeyo.rest.repository.sub_category.SubCategoryRepository;
import net.heronation.zeyo.rest.repository.warranty.Warranty;
import net.heronation.zeyo.rest.repository.warranty.WarrantyRepository;

@Slf4j
public class ItemBuildDtoDeserializer extends JsonDeserializer {

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
	private KindofRepository kindofRepository;
	
	
	
	@Override
	public Object deserialize(JsonParser jsonParser, DeserializationContext arg1)
			throws IOException, JsonProcessingException {

		log.debug("ItemBuildDtoDeserializer : deserialize");

		
		// kindof table (변경될수 있음)
//		'1', 'input_type', '관리자', 'Y'
//		'2', 'input_type', '직접입력', 'Y'
//		'3', 'bbs_type', '제휴문의', 'Y'
//		'4', 'bbs_type', '1대1문의', 'Y'
//		'5', 'madein_input_type', '관리자', 'Y'
//		'6', 'size_option', '기호', 'Y'
//		'7', 'size_option', '숫자', 'Y'
//		'8', 'size_option', '직접입력', 'Y'
//		'9', 'size_option', '숫자 - 하위', 'Y'

		

		// 공통
//		Kindof kindof_admin_input = kindofRepository.findOne(1L);
		Kindof kindof_direct_input = kindofRepository.findOne(2L);
		
//		Kindof kindof_size_option_char = kindofRepository.findOne(6L);
//		Kindof kindof_size_option_num = kindofRepository.findOne(7L);
		Kindof kindof_size_option_direct = kindofRepository.findOne(8L);
//		Kindof kindof_size_option_num_bottom = kindofRepository.findOne(9L);
		
		
		
		JsonNode node = jsonParser.getCodec().readTree(jsonParser);

		log.debug("ItemBuildDtoDeserializer : BRAND");
		/// BRAND
		Long brand_id = node.get("brand").asLong();

		Brand brand = null;
		if(brand_id == 0) {
			
		}else {
			brand = brandRepository.findOne(brand_id);	
		}
		
		

		/// SHOPMALL
		log.debug("ItemBuildDtoDeserializer : SHOPMALL");
		List<Shopmall> shopmalls = new ArrayList<Shopmall>();

		Iterator<JsonNode> shopmalls_iterator = node.get("shopmalls").elements();

		while (shopmalls_iterator.hasNext()) {
			Long shopmall_index = shopmalls_iterator.next().asLong();
			Shopmall item = shopmallRepository.findOne(shopmall_index);
			shopmalls.add(item);
		}

		// category
		log.debug("ItemBuildDtoDeserializer : Category");
		Long category_id = node.get("category").asLong();

		Category category = categoryRepository.findOne(category_id);

		// subCategory
		log.debug("ItemBuildDtoDeserializer : SubCategory");
		Long subCategory_id = node.get("subCategory").asLong();
				

		SubCategory subCategory = subCategoryRepository.findOne(subCategory_id);

		String imageMode = node.get("imageMode").textValue();

		String image = node.get("image").textValue();

		String sizeMeasureMode = node.get("sizeMeasureMode").textValue();

		String sizeMeasureImage = node.get("sizeMeasureImage").textValue();

		String name = node.get("name").textValue();

		String code = node.get("code").textValue();

		int price = Integer.parseInt(node.get("price").textValue());

		String madeinBuilder = node.get("madeinBuilder").textValue();

		log.debug("ItemBuildDtoDeserializer : Madein");
		JsonNode madein_node = node.get("madein");
		Madein madein = null;
		
				long madein_kindof = madein_node.get("kindof").asLong();
				
				// 관리자 1 , 직접입력 2
				if(madein_kindof == 2) {
					
					String madein_input_value = madein_node.get("inputValue").textValue();
					
					
					madein = new Madein();
					madein.setCreateDt(new DateTime());
					madein.setKindof(kindof_direct_input);
					madein.setName(madein_input_value);
					madein.setUseYn("Y");
				}else {
				
					
					Long madein_id =  madein_node.get("id").asLong();
					
					madein = madeinRepository.findOne(madein_id);
				}
						

			
		String madeinDate_str = node.get("madeinDate").textValue();

		DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

		DateTime madeinDate = dtf.parseDateTime(madeinDate_str);
		
		log.debug("ItemBuildDtoDeserializer : Warranty");
		JsonNode warranty_node = node.get("warranty");
		Warranty warranty = null;
		
				long warranty_kindof = warranty_node.get("kindof").asLong();
				
				// 관리자 1 , 직접입력 2
				if(warranty_kindof == 2) {
					
					String warranty_input_value = warranty_node.get("inputValue").textValue();
					
					
					warranty = new Warranty();
					warranty.setCreateDt(new DateTime());
					warranty.setKindof(kindof_direct_input);
					warranty.setScope(warranty_input_value);
					warranty.setUseYn("Y");
				}else {
				
					
					Long warranty_id =  warranty_node.get("id").asLong();
					
					warranty = warrantyRepository.findOne(warranty_id);
				}
						
		
		 

		
		log.debug("ItemBuildDtoDeserializer : ItemMaterialMap");
		List<ItemMaterialMap> materialsmaps = new ArrayList<ItemMaterialMap>();
		
		Iterator<JsonNode> materials_iterator = node.get("materials").elements();
 
		while (materials_iterator.hasNext()) {
			JsonNode materials_node = materials_iterator.next();
			
			String useLocation =materials_node.get("useLocation").asText();
			if(!useLocation.equals("A")) {
				Long material_id =  materials_node.get("material").asLong();
				String contain =materials_node.get("contain").asText();
				
				Material db_material = materialRepository.findOne(material_id);
				
				ItemMaterialMap new_material_mapp = new ItemMaterialMap();
				new_material_mapp.setContain(contain);
				new_material_mapp.setMaterial(db_material);
				new_material_mapp.setUseLocatoin(useLocation);
				new_material_mapp.setUseYn("Y");
				materialsmaps.add(new_material_mapp);
			}
		}
		
		// 사이즈 같은 경우는 카테고리 설정에 의존적이다.
		// 상품의 카테고리가  상위일경우와 하위일 경우가 틀려진다.
		// 즉 카테고리에 따라서 기호, 숫자, 하위 숫자 3가지 경우가 나온다. 
		
		
//		'6', 'size_option', '기호', 'Y'
//		'7', 'size_option', '숫자', 'Y'
//		'8', 'size_option', '직접입력', 'Y'
//		'9', 'size_option', '숫자 - 하위', 'Y'
		log.debug("ItemBuildDtoDeserializer : ItemSizeOptionMap");
	    List<ItemSizeOptionMap> itemSizeOptionMaps = new ArrayList<ItemSizeOptionMap>();
		
	    Iterator<JsonNode> sizeOptions_iterator = node.get("sizeOptions").elements();
		
	 
	    
		while (sizeOptions_iterator.hasNext()) {
			JsonNode sizeOptions_node = sizeOptions_iterator.next();
			
			Long sizeOptions_id = sizeOptions_node.get("id").asLong();
			String inputValue =sizeOptions_node.get("inputValue").asText();
			
			
			ItemSizeOptionMap new_sizeoptoins_mapp = new ItemSizeOptionMap();
			new_sizeoptoins_mapp.setUseYn("Y");
			
			
			// 직접입력일경우
			if(sizeOptions_id == 0) {  
				
				//size_option 테이블에서 이미 존재하는 옵션의 id를 가지고 와서 한다.
				
				//SizeOption db_direct_optoin = sizeOptionRepository.findOne(direct_id);
				
				
				
				SizeOption direct_input_size_option = new SizeOption();
				direct_input_size_option.setName(inputValue);
				direct_input_size_option.setCreateDt(new DateTime());
				direct_input_size_option.setKindof(kindof_size_option_direct);
				direct_input_size_option.setUseYn("Y"); 
				direct_input_size_option.setCategory(category);
				direct_input_size_option.setSubCategory(subCategory);
				
				new_sizeoptoins_mapp.setSizeOption(direct_input_size_option);
				new_sizeoptoins_mapp.setOptionValue("DIRECT"); 
			}else {
				
				SizeOption db_sizeoption = sizeOptionRepository.findOne(sizeOptions_id); 
				new_sizeoptoins_mapp.setSizeOption(db_sizeoption);
				new_sizeoptoins_mapp.setOptionValue(""); 
			}
			
			itemSizeOptionMaps.add(new_sizeoptoins_mapp);
		}
	    
	    // 색상 입력 
		log.debug("ItemBuildDtoDeserializer : ItemClothColorMap");
		 List<ItemClothColorMap> itemClothColorMaps = new ArrayList<ItemClothColorMap>(); 
		 
		 Iterator<JsonNode> clothColor_iterator = node.get("clothColor").elements();
			
			while (clothColor_iterator.hasNext()) {
				JsonNode clothColor_node = clothColor_iterator.next();
				
				
				 
				Long sizeOptions_id = clothColor_node.get("id").asLong();
				String inputValue =clothColor_node.get("inputValue").asText();
				
				
				ItemClothColorMap new_clothColor_mapp = new ItemClothColorMap();
				new_clothColor_mapp.setUseYn("Y");
				
				
				// 직접입력일경우
				if(sizeOptions_id == 0) {  
					
					//size_option 테이블에서 이미 존재하는 옵션의 id를 가지고 와서 한다.
					
					ClothColor direct_input_color = new ClothColor();
					direct_input_color.setName(inputValue);
					direct_input_color.setCreateDt(new DateTime());
					direct_input_color.setKindof(kindof_direct_input);
					direct_input_color.setUseYn("Y");
					new_clothColor_mapp.setClothColor(direct_input_color);
					new_clothColor_mapp.setOptionValue("DIRECT"); 
				}else {
					
					ClothColor db_sizeoption = clothColorRepository.findOne(sizeOptions_id); 
					new_clothColor_mapp.setClothColor(db_sizeoption);
					new_clothColor_mapp.setOptionValue(""); 
				}
				
		 
				itemClothColorMaps.add(new_clothColor_mapp);
			}
			
			
			// 세탁방법
			
			
//			"A : 제한없음
//			B : 중성세제
//			C : 알카리성세제
//			D: 산성세제"
			log.debug("ItemBuildDtoDeserializer : ItemLaundryMap");
			ItemLaundryMap itemLaundryMap = new ItemLaundryMap();
			
			JsonNode laundry_node = node.get("laundry");
			 
					String laundryYn =  laundry_node.get("useYn").textValue();
					
					if(laundryYn.equals("Y")) {
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
					}
					
	
					 
			
//			"A : 제한없음
//			B : 석유계
//			C : 클로로에틸렌"
			log.debug("ItemBuildDtoDeserializer : ItemDrycleaningMap");
			ItemDrycleaningMap itemDrycleaningMap = new ItemDrycleaningMap();
			
			JsonNode drycleaning_node = node.get("drycleaning");
			
					String drycleaningYn =  drycleaning_node.get("useYn").textValue();
			
					if(drycleaningYn.equals("Y")) {
						String drycleaning_drycan = drycleaning_node.get("drycan").textValue();
						String drycleaning_storecan = drycleaning_node.get("storecan").textValue();
						String drycleaning_detergent = drycleaning_node.get("detergent").textValue();
					 
						itemDrycleaningMap.setDetergent(drycleaning_detergent); 
						itemDrycleaningMap.setStorecan(drycleaning_storecan); 
						itemDrycleaningMap.setDrycan(drycleaning_drycan); 
						itemDrycleaningMap.setUseYn("Y");
					}
			

			
			// 다림질
			log.debug("ItemBuildDtoDeserializer : ItemIroningMap");		
			ItemIroningMap itemIroningMap = new ItemIroningMap();
			
			JsonNode itemIroning_node = node.get("ironing"); 
			
					String ironingYn =  itemIroning_node.get("useYn").textValue();
			
					
					if(ironingYn.equals("Y")) {
						String itemIroning_ironcan = itemIroning_node.get("ironcan").textValue();
						String itemIroning_addprotection = itemIroning_node.get("addprotection").textValue();
						String itemIroning_startTemp = itemIroning_node.get("startTemp").textValue();
						String itemIroning_endTemp = itemIroning_node.get("endTemp").textValue();
						 
						itemIroningMap.setIroncan(itemIroning_ironcan); 
						itemIroningMap.setAddprotection(itemIroning_addprotection); 
						itemIroningMap.setStartTemp(itemIroning_startTemp);
						itemIroningMap.setEndTemp(itemIroning_endTemp);
						itemIroningMap.setUseYn("Y");
					}
					

					
			// 건조방법
					
//			    	"machineDry":"Y", 
//					 "natureDry":"N", 
//					 "dryMode":"B", 
//					 "handDry":"C"
					log.debug("ItemBuildDtoDeserializer : ItemDrymethodMap");
					ItemDrymethodMap itemDrymethodMap = new ItemDrymethodMap();
					
					JsonNode itemDrymethod_node = node.get("drymethod"); 
					
							String drymethodYn =  itemDrymethod_node.get("useYn").textValue();
					
							if(drymethodYn.equals("Y")) {
								String itemDrymethod_machineDry = itemDrymethod_node.get("machineDry").textValue();
								String itemDrymethod_natureDry = itemDrymethod_node.get("natureDry").textValue();
								String itemDrymethod_dryMode = itemDrymethod_node.get("dryMode").textValue();
								String itemDrymethod_handDry = itemDrymethod_node.get("handDry").textValue();
								 
								itemDrymethodMap.setMachineDry(itemDrymethod_machineDry);
								itemDrymethodMap.setNatureDry(itemDrymethod_natureDry);
								itemDrymethodMap.setDryMode(itemDrymethod_dryMode);
								itemDrymethodMap.setHandDry(itemDrymethod_handDry);
								itemDrymethodMap.setUseYn("Y");
							}

					
					// 표백제 사용법
						
//							private String chlorine
//							private String oxygen;
					log.debug("ItemBuildDtoDeserializer : ItemBleachMap");
					ItemBleachMap itemBleachMap = new ItemBleachMap();
					
					JsonNode itemBleach_node = node.get("bleach"); 
							
						String bleachYn =  itemBleach_node.get("useYn").textValue();
						if(bleachYn.equals("Y")) {
							String itemBleach_chlorine = itemBleach_node.get("chlorine").textValue();
							String itemBleach_oxygen = itemBleach_node.get("oxygen").textValue();
							
							itemBleachMap.setChlorine(itemBleach_chlorine);
							itemBleachMap.setOxygen(itemBleach_oxygen);
							itemBleachMap.setUseYn("Y");
			
						}
					

		log.debug("ItemBuildDtoDeserializer : ItemFitInfoOptionMap");
		// fit 정보 관리 				
		List<ItemFitInfoOptionMap> itemFitInfoOptionMaps = new ArrayList<ItemFitInfoOptionMap>();
						
		Iterator<JsonNode> itemFitInfoOption_iterator = node.get("fitInfoOptions").elements();
		
		while (itemFitInfoOption_iterator.hasNext()) {
			JsonNode itemFitInfoOption_node = itemFitInfoOption_iterator.next();
			
			Long fitInfoOptionId = itemFitInfoOption_node.get("fitInfoOptionId").asLong();
			
			
			ItemFitInfoOptionMap new_FitInfoOption_mapp = new ItemFitInfoOptionMap();
			new_FitInfoOption_mapp.setUseYn("Y");
			
			
			FitInfoOption db_fitinfo = fitInfoOptionRepository.findOne(fitInfoOptionId); 
			new_FitInfoOption_mapp.setFitInfoOption(db_fitinfo);
			
	 
			
			itemFitInfoOptionMaps.add(new_FitInfoOption_mapp);
		}
		
		
		String sizeTableYn = node.get("sizeTableYn").textValue();
		
		return new ItemBuildDto(brand, shopmalls, category, subCategory, imageMode, image, sizeMeasureMode,
				sizeMeasureImage, name, code, price, madeinBuilder, madein, madeinDate,warranty,materialsmaps,itemSizeOptionMaps,itemClothColorMaps,laundryYn,itemLaundryMap,drycleaningYn,itemDrycleaningMap,ironingYn,itemIroningMap,drymethodYn,itemDrymethodMap,bleachYn,itemBleachMap,itemFitInfoOptionMaps,sizeTableYn);

	}
	
 
}
