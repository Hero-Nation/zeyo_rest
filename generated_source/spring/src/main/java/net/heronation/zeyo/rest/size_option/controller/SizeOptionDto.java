package net.heronation.zeyo.rest.size_option.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.item_size_option_map.controller.ItemSizeOptionMap;
import net.heronation.zeyo.rest.category.controller.Category;import net.heronation.zeyo.rest.sub_category.controller.SubCategory;import net.heronation.zeyo.rest.kindof.controller.Kindof;


@Data
public class SizeOptionDto{
 
        private List<ItemSizeOptionMap> itemSizeOptionMaps = new ArrayList<ItemSizeOptionMap>();
 private Long id;




private Category category;




private SubCategory subCategory;




private Kindof kindof;




private String name;



private String createDt;



private String useYn;
    
}