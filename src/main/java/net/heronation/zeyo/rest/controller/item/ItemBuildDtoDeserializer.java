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
import net.heronation.zeyo.rest.kindof.KindofRepositoryTest;
import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.brand.BrandRepository;
import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.category.CategoryRepository;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColor;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColorRepository;
import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOption;
import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOptionRepository;
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
		Kindof kindof_admin_input = kindofRepository.findOne(1L);
		Kindof kindof_direct_input = kindofRepository.findOne(2L);
		
		Kindof kindof_size_option_char = kindofRepository.findOne(6L);
		Kindof kindof_size_option_num = kindofRepository.findOne(7L);
		Kindof kindof_size_option_direct = kindofRepository.findOne(8L);
		Kindof kindof_size_option_num_bottom = kindofRepository.findOne(9L);
		
		
		
		JsonNode node = jsonParser.getCodec().readTree(jsonParser);

		
		/// BRAND
		Long brand_id = Long.valueOf(node.get("brand").textValue());

		Brand brand = brandRepository.findOne(brand_id);

		/// SHOPMALL

		List<Shopmall> shopmalls = new ArrayList<Shopmall>();

		Iterator<JsonNode> shopmalls_iterator = node.get("shopmalls").elements();

		while (shopmalls_iterator.hasNext()) {
			Long shopmall_index = Long.valueOf(shopmalls_iterator.next().asText());
			Shopmall item = shopmallRepository.findOne(shopmall_index);
			shopmalls.add(item);
		}

		// category
		Long category_id = Long.valueOf(node.get("category").textValue());

		Category category = categoryRepository.findOne(category_id);

		// subCategory
		Long subCategory_id = Long.valueOf(node.get("subCategory").textValue());

		SubCategory subCategory = subCategoryRepository.findOne(subCategory_id);

		String imageMode = node.get("imageMode").textValue();

		String image = node.get("image").textValue();

		String sizeMeasureMode = node.get("sizeMeasureMode").textValue();

		String sizeMeasureImage = node.get("sizeMeasureImage").textValue();

		String name = node.get("name").textValue();

		String code = node.get("code").textValue();

		int price = Integer.parseInt(node.get("price").textValue());

		String madeinBuilder = node.get("madeinBuilder").textValue();

		JsonNode madein_node = node.get("madein");
		Madein madein = null;
		
				String madein_kindof = madein_node.get("kindof").textValue();
				
				// 관리자 1 , 직접입력 2
				if(madein_kindof.equals("2")) {
					
					String madein_input_value = madein_node.get("inputValue").textValue();
					
					
					madein = new Madein();
					madein.setCreateDt(new DateTime());
					madein.setKindof(kindof_direct_input);
					madein.setName(madein_input_value);
					madein.setUseYn("Y");
				}else {
				
					
					Long madein_id =  Long.valueOf(madein_node.get("id").textValue());
					
					madein = madeinRepository.findOne(madein_id);
				}
						

		
		String madeinDate_str = node.get("madeinDate").textValue();

		DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

		DateTime madeinDate = dtf.parseDateTime(madeinDate_str);
		
		
		JsonNode warranty_node = node.get("warranty");
		Warranty warranty = null;
		
				String warranty_kindof = warranty_node.get("kindof").textValue();
				
				// 관리자 1 , 직접입력 2
				if(warranty_kindof.equals("2")) {
					
					String warranty_input_value = warranty_node.get("inputValue").textValue();
					
					
					warranty = new Warranty();
					warranty.setCreateDt(new DateTime());
					warranty.setKindof(kindof_direct_input);
					warranty.setScope(warranty_input_value);
					warranty.setUseYn("Y");
				}else {
				
					
					Long warranty_id =  Long.valueOf(warranty_node.get("id").textValue());
					
					warranty = warrantyRepository.findOne(warranty_id);
				}
						
		
		 

		
		
		List<ItemMaterialMap> materialsmaps = new ArrayList<ItemMaterialMap>();
		
		Iterator<JsonNode> materials_iterator = node.get("materials").elements();
 
		while (materials_iterator.hasNext()) {
			JsonNode materials_node = materials_iterator.next();
			
			String useLocation =materials_node.get("useLocation").asText();
			Long material_id = Long.valueOf(materials_node.get("material").textValue());
			String contain =materials_node.get("contain").asText();
			
			Material db_material = materialRepository.findOne(material_id);
			
			ItemMaterialMap new_material_mapp = new ItemMaterialMap();
			new_material_mapp.setContain(contain);
			new_material_mapp.setMaterial(db_material);
			new_material_mapp.setUseLocatoin(useLocation);
			
			
			materialsmaps.add(new_material_mapp);
		}
		
		// 사이즈 같은 경우는 카테고리 설정에 의존적이다.
		// 상품의 카테고리가  상위일경우와 하위일 경우가 틀려진다.
		// 즉 카테고리에 따라서 기호, 숫자, 하위 숫자 3가지 경우가 나온다. 
		
		
//		'6', 'size_option', '기호', 'Y'
//		'7', 'size_option', '숫자', 'Y'
//		'8', 'size_option', '직접입력', 'Y'
//		'9', 'size_option', '숫자 - 하위', 'Y'
		
	    List<ItemSizeOptionMap> itemSizeOptionMaps = new ArrayList<ItemSizeOptionMap>();
		
	    Iterator<JsonNode> sizeOptions_iterator = node.get("sizeOptions").elements();
		
	    
		while (sizeOptions_iterator.hasNext()) {
			JsonNode sizeOptions_node = sizeOptions_iterator.next();
			
			String kindof = sizeOptions_node.get("kindof").textValue();
			Long sizeOptions_id =Long.valueOf(sizeOptions_node.get("id").asText());
			String inputValue =sizeOptions_node.get("inputValue").asText();
			
			
			ItemSizeOptionMap new_sizeoptoins_mapp = new ItemSizeOptionMap();
			new_sizeoptoins_mapp.setUseYn("Y");
			SizeOption db_sizeoption = sizeOptionRepository.findOne(sizeOptions_id); 
			new_sizeoptoins_mapp.setSizeOption(db_sizeoption);
			
			// 직접입력일경우
			if(kindof.equals("8")) { 
				new_sizeoptoins_mapp.setOptionValue(inputValue); 
			}else {
				new_sizeoptoins_mapp.setOptionValue(""); 
			}
			
			itemSizeOptionMaps.add(new_sizeoptoins_mapp);
		}
	    
	    // 색상 입력 
		 List<ItemClothColorMap> itemClothColorMaps = new ArrayList<ItemClothColorMap>(); 
		 
		 Iterator<JsonNode> clothColor_iterator = node.get("clothColor").elements();
			
			while (clothColor_iterator.hasNext()) {
				JsonNode clothColor_node = clothColor_iterator.next();
				
				String kindof = clothColor_node.get("kindof").textValue();
				Long clothColor_id =Long.valueOf(clothColor_node.get("id").asText());
				String inputValue =clothColor_node.get("inputValue").asText();
				
				
				ItemClothColorMap new_clothColor_mapp = new ItemClothColorMap();
				new_clothColor_mapp.setUseYn("Y");
				ClothColor db_clothColor = clothColorRepository.findOne(clothColor_id); 
				new_clothColor_mapp.setClothColor(db_clothColor);
				
				// 직접입력일경우
				if(kindof.equals("2")) { 
					new_clothColor_mapp.setOptionValue(inputValue); 
				}else {
					new_clothColor_mapp.setOptionValue(""); 
				}
				
				itemClothColorMaps.add(new_clothColor_mapp);
			}
			
			
			// 세탁방법
			
			
//			"A : 제한없음
//			B : 중성세제
//			C : 알카리성세제
//			D: 산성세제"
			
			ItemLaundryMap itemLaundryMap = new ItemLaundryMap();
			
			JsonNode laundry_node = node.get("laundry");
			 
			
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
					 
			
//			"A : 제한없음
//			B : 석유계
//			C : 클로로에틸렌"
			
			ItemDrycleaningMap itemDrycleaningMap = new ItemDrycleaningMap();
			
			JsonNode drycleaning_node = node.get("drycleaning");
			  
			
					String drycleaning_drycan = drycleaning_node.get("drycan").textValue();
					String drycleaning_storecan = drycleaning_node.get("storecan").textValue();
					String drycleaning_detergent = drycleaning_node.get("detergent").textValue();
				 
					itemDrycleaningMap.setDetergent(drycleaning_detergent); 
					itemDrycleaningMap.setStorecan(drycleaning_storecan); 
					itemDrycleaningMap.setDrycan(drycleaning_drycan); 
					itemDrycleaningMap.setUseYn("Y");
			
			// 다림질
					
			ItemIroningMap itemIroningMap = new ItemIroningMap();
			
			JsonNode itemIroning_node = node.get("ironing"); 
			
					String itemIroning_ironcan = itemIroning_node.get("ironcan").textValue();
					String itemIroning_addprotection = itemIroning_node.get("addprotection").textValue();
					String itemIroning_startTemp = itemIroning_node.get("startTemp").textValue();
					String itemIroning_endTemp = itemIroning_node.get("endTemp").textValue();
					 
					itemIroningMap.setIroncan(itemIroning_ironcan); 
					itemIroningMap.setAddprotection(itemIroning_addprotection); 
					itemIroningMap.setStartTemp(itemIroning_startTemp);
					itemIroningMap.setEndTemp(itemIroning_endTemp);
					itemIroningMap.setUseYn("Y");
					
			// 건조방법
					
//			    	"machineDry":"Y", 
//					 "natureDry":"N", 
//					 "dryMode":"B", 
//					 "handDry":"C"
			
					ItemDrymethodMap itemDrymethodMap = new ItemDrymethodMap();
					
					JsonNode itemDrymethod_node = node.get("drymethod"); 
					
							String itemDrymethod_machineDry = itemDrymethod_node.get("machineDry").textValue();
							String itemDrymethod_natureDry = itemDrymethod_node.get("natureDry").textValue();
							String itemDrymethod_dryMode = itemDrymethod_node.get("dryMode").textValue();
							String itemDrymethod_handDry = itemDrymethod_node.get("handDry").textValue();
							 
							itemDrymethodMap.setMachineDry(itemDrymethod_machineDry);
							itemDrymethodMap.setNatureDry(itemDrymethod_natureDry);
							itemDrymethodMap.setDryMode(itemDrymethod_dryMode);
							itemDrymethodMap.setHandDry(itemDrymethod_handDry);
							itemDrymethodMap.setUseYn("Y");
					
					// 표백제 사용법
						
//							private String chlorine
//							private String oxygen;
					ItemBleachMap itemBleachMap = new ItemBleachMap();
					
					JsonNode itemBleach_node = node.get("bleach"); 
							
						String itemBleach_chlorine = itemBleach_node.get("chlorine").textValue();
						String itemBleach_oxygen = itemBleach_node.get("oxygen").textValue();
						
						itemBleachMap.setChlorine(itemBleach_chlorine);
						itemBleachMap.setOxygen(itemBleach_oxygen);
						itemBleachMap.setUseYn("Y");
		
		// fit 정보 관리 				
		List<ItemFitInfoOptionMap> itemFitInfoOptionMaps = new ArrayList<ItemFitInfoOptionMap>();
						
		Iterator<JsonNode> itemFitInfoOption_iterator = node.get("fitInfoOptions").elements();
		
		while (itemFitInfoOption_iterator.hasNext()) {
			JsonNode itemFitInfoOption_node = itemFitInfoOption_iterator.next();
			
			Long fitInfoOptionId =Long.valueOf(itemFitInfoOption_node.get("fitInfoOptionId").asText());
			
			
			ItemFitInfoOptionMap new_FitInfoOption_mapp = new ItemFitInfoOptionMap();
			new_FitInfoOption_mapp.setUseYn("Y");
			
			
			FitInfoOption db_fitinfo = fitInfoOptionRepository.findOne(fitInfoOptionId); 
			new_FitInfoOption_mapp.setFitInfoOption(db_fitinfo);
			
	 
			
			itemFitInfoOptionMaps.add(new_FitInfoOption_mapp);
		}
		
		
		return new ItemBuildDto(brand, shopmalls, category, subCategory, imageMode, image, sizeMeasureMode,
				sizeMeasureImage, name, code, price, madeinBuilder, madein, madeinDate,warranty,materialsmaps,itemSizeOptionMaps,itemClothColorMaps,itemLaundryMap,itemDrycleaningMap,itemIroningMap,itemDrymethodMap,itemBleachMap,itemFitInfoOptionMaps);

	}

}
