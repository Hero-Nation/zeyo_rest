package net.heronation.zeyo.rest.cloth_color.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.item_cloth_color_map.controller.ItemClothColorMap;
import net.heronation.zeyo.rest.kindof.controller.Kindof;


@Data
public class ClothColorDto{
 
        private List<ItemClothColorMap> itemClothColorMaps = new ArrayList<ItemClothColorMap>();
 private Long id;




private Kindof kindof;




private String name;



private String useYn;
    
}