package net.heronation.zeyo.rest.repository.item_ironing_map;
 
import lombok.Value;
import net.heronation.zeyo.rest.repository.item.Item;


@Value
public class ItemIroningMapDto{
 
        private Long id;




private Item item;




private String ironcan;



private String addprotection;



private String startTemp;



private String endTemp;



private String useYn;
    
}