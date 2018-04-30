package net.heronation.zeyo.rest.repository.item_cloth_color_map;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Value;

import net.heronation.zeyo.rest.repository.item.Item;import net.heronation.zeyo.rest.repository.cloth_color.ClothColor;


@Value
public class ItemClothColorMapDto{
 
        private Long id;




private Item item;




private ClothColor clothColor;




private String useYn;
    
}