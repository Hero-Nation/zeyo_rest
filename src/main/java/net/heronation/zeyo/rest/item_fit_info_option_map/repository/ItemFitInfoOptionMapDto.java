package net.heronation.zeyo.rest.item_fit_info_option_map.repository;
 
import lombok.Value;
import net.heronation.zeyo.rest.fit_info_option.repository.FitInfoOption;
import net.heronation.zeyo.rest.item.repository.Item;


@Value
public class ItemFitInfoOptionMapDto{
 
        private Long id;




private Item item;




private FitInfoOption fitInfoOption;




private String useYn;
    
}