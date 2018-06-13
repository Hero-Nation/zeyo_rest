package net.heronation.zeyo.rest.repository.item_cloth_color_map;
 
import lombok.Value;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColor;
import net.heronation.zeyo.rest.repository.item.Item;


@Value
public class ItemClothColorMapDto{
 
        private Long id;




private Item item;




private ClothColor clothColor;




private String useYn;
    
}