package net.heronation.zeyo.rest.item_size_option_map.repository;
 
import lombok.Value;
import net.heronation.zeyo.rest.item.repository.Item;
import net.heronation.zeyo.rest.size_option.repository.SizeOption;


@Value
public class ItemSizeOptionMapDto{
 
        private Long id;




private Item item;




private SizeOption sizeOption;




private String optionValue;



private String useYn;
    
}