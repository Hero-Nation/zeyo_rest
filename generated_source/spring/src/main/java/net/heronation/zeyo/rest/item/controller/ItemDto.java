package net.heronation.zeyo.rest.item.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.member.controller.Member;import net.heronation.zeyo.rest.brand.controller.Brand;import net.heronation.zeyo.rest.category.controller.Category;import net.heronation.zeyo.rest.sub_category.controller.SubCategory;import net.heronation.zeyo.rest.madein.controller.Madein;import net.heronation.zeyo.rest.warranty.controller.Warranty;import net.heronation.zeyo.rest.item_shopmall_map.controller.ItemShopmallMap;
import net.heronation.zeyo.rest.item_material_map.controller.ItemMaterialMap;
import net.heronation.zeyo.rest.item_size_option_map.controller.ItemSizeOptionMap;
import net.heronation.zeyo.rest.item_cloth_color_map.controller.ItemClothColorMap;
import net.heronation.zeyo.rest.item_laundry_map.controller.ItemLaundryMap;
import net.heronation.zeyo.rest.item_drycleaning_map.controller.ItemDrycleaningMap;
import net.heronation.zeyo.rest.item_ironing_map.controller.ItemIroningMap;
import net.heronation.zeyo.rest.item_drymethod_map.controller.ItemDrymethodMap;
import net.heronation.zeyo.rest.item_bleach_map.controller.ItemBleachMap;


@Data
public class ItemDto{
 
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




private String madeinDate;



private Warranty warranty;




private String laundryYn;



private String drycleaningYn;



private String ironingYn;



private String drymethodYn;



private String bleachYn;



private String sizeLinkYn;



private String createDt;



private String useYn;



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