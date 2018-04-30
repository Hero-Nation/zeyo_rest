package net.heronation.zeyo.rest.repository.item_drymethod_map;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Value;

import net.heronation.zeyo.rest.repository.item.Item;


@Value
public class ItemDrymethodMapDto{
 
        private Long id;




private Item item;




private String machineDry;



private String natureDry;



private String dryMode;



private String handDry;



private String useYn;
    
}