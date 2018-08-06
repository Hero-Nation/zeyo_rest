package net.heronation.zeyo.rest.item_cloth_color_map.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.item.controller.Item;import net.heronation.zeyo.rest.cloth_color.controller.ClothColor;


@Data
public class ItemClothColorMapDto{
 
        private Long id;




private Item item;




private ClothColor clothColor;




private String useYn;
    
}