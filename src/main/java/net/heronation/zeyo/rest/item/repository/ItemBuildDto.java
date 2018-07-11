package net.heronation.zeyo.rest.item.repository;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.heronation.zeyo.rest.brand.repository.Brand;
import net.heronation.zeyo.rest.category.repository.Category;
import net.heronation.zeyo.rest.item.controller.ItemBuildDtoDeserializer;
import net.heronation.zeyo.rest.item_bleach_map.repository.ItemBleachMap;
import net.heronation.zeyo.rest.item_cloth_color_map.repository.ItemClothColorMap;
import net.heronation.zeyo.rest.item_drycleaning_map.repository.ItemDrycleaningMap;
import net.heronation.zeyo.rest.item_drymethod_map.repository.ItemDrymethodMap;
import net.heronation.zeyo.rest.item_fit_info_option_map.repository.ItemFitInfoOptionMap;
import net.heronation.zeyo.rest.item_ironing_map.repository.ItemIroningMap;
import net.heronation.zeyo.rest.item_laundry_map.repository.ItemLaundryMap;
import net.heronation.zeyo.rest.item_material_map.repository.ItemMaterialMap;
import net.heronation.zeyo.rest.item_scmm_so_value.repository.ItemScmmSoValue;
import net.heronation.zeyo.rest.item_size_option_map.repository.ItemSizeOptionMap;
import net.heronation.zeyo.rest.madein.repository.Madein;
import net.heronation.zeyo.rest.shopmall.repository.Shopmall;
import net.heronation.zeyo.rest.sub_category.repository.SubCategory;
import net.heronation.zeyo.rest.warranty.repository.Warranty;
@Data
@AllArgsConstructor
@JsonDeserialize(using = ItemBuildDtoDeserializer.class)
public class ItemBuildDto { 	
	private Brand brand;

	
 	private List<Shopmall> shopmalls = new ArrayList<Shopmall>();
	
	
	private Category category;

	private SubCategory subCategory;
 
	
	private String imageMode;

	private String image;

	private String sizeMeasureMode;

	private String sizeMeasureImage;

	private String name;

	private String code;

	private int price; 
	
	private String madeinBuilder;
	
	private Madein madein;
	
	
	private DateTime madeinDate;
 
	private Warranty warranty;
 
 	private List<ItemMaterialMap> materials = new ArrayList<ItemMaterialMap>();	
	private List<ItemSizeOptionMap> itemSizeOptionMaps = new ArrayList<ItemSizeOptionMap>();
    private List<ItemClothColorMap> itemClothColorMaps = new ArrayList<ItemClothColorMap>();
    private List<ItemScmmSoValue> itemScmmSoValues = new ArrayList<ItemScmmSoValue>();
    
    
    
    private String laundryYn; 
	private ItemLaundryMap itemLaundryMap; 
	private String drycleaningYn;
 	private ItemDrycleaningMap itemDrycleaningMap; 
 	private String ironingYn;
	private ItemIroningMap itemIroningMap; 
	private String drymethodYn;
	private ItemDrymethodMap itemDrymethodMap; 
	private String bleachYn;
 	private ItemBleachMap itemBleachMap; 
 	
    private List<ItemFitInfoOptionMap> itemFitInfoOptionMaps = new ArrayList<ItemFitInfoOptionMap>();
 
    private String sizeTableYn;
}
