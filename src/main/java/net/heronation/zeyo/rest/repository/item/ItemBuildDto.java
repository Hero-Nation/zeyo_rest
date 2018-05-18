package net.heronation.zeyo.rest.repository.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.item_bleach_map.ItemBleachMap;
import net.heronation.zeyo.rest.repository.item_cloth_color_map.ItemClothColorMap;
import net.heronation.zeyo.rest.repository.item_drycleaning_map.ItemDrycleaningMap;
import net.heronation.zeyo.rest.repository.item_drymethod_map.ItemDrymethodMap;
import net.heronation.zeyo.rest.repository.item_fit_info_option_map.ItemFitInfoOptionMap;
import net.heronation.zeyo.rest.repository.item_ironing_map.ItemIroningMap;
import net.heronation.zeyo.rest.repository.item_laundry_map.ItemLaundryMap;
import net.heronation.zeyo.rest.repository.item_material_map.ItemMaterialMap;
import net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMap;
import net.heronation.zeyo.rest.repository.item_size_option_map.ItemSizeOptionMap;
import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.material.Material;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.MemberRegisterDto;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
import net.heronation.zeyo.rest.repository.size_table.SizeTable;
import net.heronation.zeyo.rest.repository.sub_category.SubCategory;
import net.heronation.zeyo.rest.repository.warranty.Warranty;
import net.heronation.zeyo.rest.controller.item.ItemBuildDtoDeserializer;
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
    
     
	private ItemLaundryMap itemLaundryMap; 
 	private ItemDrycleaningMap itemDrycleaningMap; 
	private ItemIroningMap itemIroningMap; 
	private ItemDrymethodMap itemDrymethodMap; 
 	private ItemBleachMap itemBleachMap;
//	
// 
    private List<ItemFitInfoOptionMap> itemFitInfoOptionMaps = new ArrayList<ItemFitInfoOptionMap>();
// 
//	private SizeTable sizeTable;
//    
 
//
//	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
//
//
//
//
//	private String laundryYn;
//
//	private String drycleaningYn;
//
//	private String ironingYn;
//
//	private String drymethodYn;
//
//	private String bleachYn;
//
//	private String sizeLinkYn;
//	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
//	private DateTime createDt;
//
//	private String useYn;
}
