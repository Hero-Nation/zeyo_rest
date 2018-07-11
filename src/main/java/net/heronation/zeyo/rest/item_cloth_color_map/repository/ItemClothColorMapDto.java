package net.heronation.zeyo.rest.item_cloth_color_map.repository;
 
import lombok.Value;
import net.heronation.zeyo.rest.cloth_color.repository.ClothColor;
import net.heronation.zeyo.rest.item.repository.Item;


@Value
public class ItemClothColorMapDto{
 
        private Long id;




private Item item;




private ClothColor clothColor;




private String useYn;
    
}