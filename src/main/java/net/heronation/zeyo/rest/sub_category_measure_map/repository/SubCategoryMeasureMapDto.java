package net.heronation.zeyo.rest.sub_category_measure_map.repository;
 
import lombok.Value;
import net.heronation.zeyo.rest.measure_item.repository.MeasureItem;
import net.heronation.zeyo.rest.sub_category.repository.SubCategory;


@Value
public class SubCategoryMeasureMapDto{
 
        private Long id;




private SubCategory subCategory;




private MeasureItem measureItem;




private String useYn;
    
}