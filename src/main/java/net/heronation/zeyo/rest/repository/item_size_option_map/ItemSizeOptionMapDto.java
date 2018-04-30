package net.heronation.zeyo.rest.repository.item_size_option_map;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Value;

import net.heronation.zeyo.rest.repository.item.Item;import net.heronation.zeyo.rest.repository.size_option.SizeOption;


@Value
public class ItemSizeOptionMapDto{
 
        private Long id;




private Item item;




private SizeOption sizeOption;




private String optionValue;



private String useYn;
    
}