package net.heronation.zeyo.rest.repository.sub_category_measure_map;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Value;

import net.heronation.zeyo.rest.repository.sub_category.SubCategory;import net.heronation.zeyo.rest.repository.measure_item.MeasureItem;


@Value
public class SubCategoryMeasureMapDto{
 
        private Long id;




private SubCategory subCategory;




private MeasureItem measureItem;




private String useYn;
    
}