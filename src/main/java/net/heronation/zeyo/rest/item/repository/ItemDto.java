package net.heronation.zeyo.rest.item.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import lombok.Data;
import net.heronation.zeyo.rest.brand.repository.Brand;
import net.heronation.zeyo.rest.category.repository.Category;
import net.heronation.zeyo.rest.item_bleach_map.repository.ItemBleachMap;
import net.heronation.zeyo.rest.item_cloth_color_map.repository.ItemClothColorMap;
import net.heronation.zeyo.rest.item_drycleaning_map.repository.ItemDrycleaningMap;
import net.heronation.zeyo.rest.item_drymethod_map.repository.ItemDrymethodMap;
import net.heronation.zeyo.rest.item_ironing_map.repository.ItemIroningMap;
import net.heronation.zeyo.rest.item_laundry_map.repository.ItemLaundryMap;
import net.heronation.zeyo.rest.item_material_map.repository.ItemMaterialMap;
import net.heronation.zeyo.rest.item_shopmall_map.repository.ItemShopmallMap;
import net.heronation.zeyo.rest.item_size_option_map.repository.ItemSizeOptionMap;
import net.heronation.zeyo.rest.madein.repository.Madein;
import net.heronation.zeyo.rest.member.repository.Member;
import net.heronation.zeyo.rest.sub_category.repository.SubCategory;
import net.heronation.zeyo.rest.warranty.repository.Warranty;

@Data
public class ItemDto {

	private Long id;

	private Member member;

	private Brand brand;

	private Category category;

	private SubCategory subCategory;

	private String imageMode;

	private String image;

	private String sizeMeasureMode;

	private String sizeMeasureImage;

	private String name;

	private String code;

	private String price;

	private String madeinBuilder;

	private Madein madein;
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime madeinDate;

	private Warranty warranty;

	private String laundryYn;

	private String drycleaningYn;

	private String ironingYn;

	private String drymethodYn;

	private String bleachYn;

	private String sizeLinkYn;
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createDt;

	private String useYn;

	private String target;
	
	private List<ItemShopmallMap> itemShopmallMaps = new ArrayList<ItemShopmallMap>();
	private List<ItemMaterialMap> itemMaterialMaps = new ArrayList<ItemMaterialMap>();
	private List<ItemSizeOptionMap> itemSizeOptionMaps = new ArrayList<ItemSizeOptionMap>();
	private List<ItemClothColorMap> itemClothColorMaps = new ArrayList<ItemClothColorMap>();
	private List<ItemLaundryMap> itemLaundryMaps = new ArrayList<ItemLaundryMap>();
	private List<ItemDrycleaningMap> itemDrycleaningMaps = new ArrayList<ItemDrycleaningMap>();
	private List<ItemIroningMap> itemIroningMaps = new ArrayList<ItemIroningMap>();
	private List<ItemDrymethodMap> itemDrymethodMaps = new ArrayList<ItemDrymethodMap>();
	private List<ItemBleachMap> itemBleachMaps = new ArrayList<ItemBleachMap>();

}