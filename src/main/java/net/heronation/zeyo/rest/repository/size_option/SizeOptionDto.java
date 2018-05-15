package net.heronation.zeyo.rest.repository.size_option;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Value;

import net.heronation.zeyo.rest.repository.item_size_option_map.ItemSizeOptionMap;
import net.heronation.zeyo.rest.repository.category.Category;import net.heronation.zeyo.rest.repository.sub_category.SubCategory;import net.heronation.zeyo.rest.repository.kindof.Kindof;
import org.joda.time.DateTime;

@Value
public class SizeOptionDto{
 
        private List<ItemSizeOptionMap> itemSizeOptionMaps = new ArrayList<ItemSizeOptionMap>();
 private Long id;




private Category category;




private SubCategory subCategory;




private Kindof kindof;




private String name;



 
private DateTime createDt;



private String useYn;
    
}