package net.heronation.zeyo.rest.repository.item;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Value;

import net.heronation.zeyo.rest.repository.member.Member;import net.heronation.zeyo.rest.repository.brand.Brand;import net.heronation.zeyo.rest.repository.category.Category;import net.heronation.zeyo.rest.repository.sub_category.SubCategory;import net.heronation.zeyo.rest.repository.madein.Madein;import net.heronation.zeyo.rest.repository.warranty.Warranty;import net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMap;
import net.heronation.zeyo.rest.repository.item_material_map.ItemMaterialMap;
import net.heronation.zeyo.rest.repository.item_size_option_map.ItemSizeOptionMap;
import net.heronation.zeyo.rest.repository.item_cloth_color_map.ItemClothColorMap;
import net.heronation.zeyo.rest.repository.item_laundry_map.ItemLaundryMap;
import net.heronation.zeyo.rest.repository.item_drycleaning_map.ItemDrycleaningMap;
import net.heronation.zeyo.rest.repository.item_ironing_map.ItemIroningMap;
import net.heronation.zeyo.rest.repository.item_drymethod_map.ItemDrymethodMap;
import net.heronation.zeyo.rest.repository.item_bleach_map.ItemBleachMap;


@Value
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





private Date madeinDate;



private Warranty warranty;




private String laundryYn;



private String drycleaningYn;



private String ironingYn;



private String drymethodYn;



private String bleachYn;



private String sizeLinkYn;



 
private Date createDt;



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