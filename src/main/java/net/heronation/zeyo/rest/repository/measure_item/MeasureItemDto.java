package net.heronation.zeyo.rest.repository.measure_item;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Value;

import net.heronation.zeyo.rest.repository.sub_category_measure_map.SubCategoryMeasureMap;


@Value
public class MeasureItemDto{
 
        private List<SubCategoryMeasureMap> subCategoryMeasureMaps = new ArrayList<SubCategoryMeasureMap>();
 private Long id;




private String name;



private String metaDesc;



 
private Date createDt;



private String useYn;
    
}