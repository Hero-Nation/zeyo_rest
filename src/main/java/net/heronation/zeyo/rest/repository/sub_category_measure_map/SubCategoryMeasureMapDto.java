package net.heronation.zeyo.rest.repository.sub_category_measure_map;
 
import lombok.Value;
import net.heronation.zeyo.rest.repository.measure_item.MeasureItem;
import net.heronation.zeyo.rest.repository.sub_category.SubCategory;


@Value
public class SubCategoryMeasureMapDto{
 
        private Long id;




private SubCategory subCategory;




private MeasureItem measureItem;




private String useYn;
    
}