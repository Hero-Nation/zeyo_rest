package net.heronation.zeyo.rest.item_size_option_map.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.item.controller.Item;import net.heronation.zeyo.rest.size_option.controller.SizeOption;


@Data
public class ItemSizeOptionMapDto{
 
        private Long id;




private Item item;




private SizeOption sizeOption;




private String optionValue;



private String useYn;
    
}