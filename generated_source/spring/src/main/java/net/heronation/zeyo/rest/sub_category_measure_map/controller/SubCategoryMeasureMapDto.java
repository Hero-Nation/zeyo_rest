package net.heronation.zeyo.rest.sub_category_measure_map.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.sub_category.controller.SubCategory;import net.heronation.zeyo.rest.measure_item.controller.MeasureItem;


@Data
public class SubCategoryMeasureMapDto{
 
        private Long id;




private SubCategory subCategory;




private MeasureItem measureItem;




private String useYn;
    
}