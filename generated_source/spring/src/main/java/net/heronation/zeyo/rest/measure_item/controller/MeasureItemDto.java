package net.heronation.zeyo.rest.measure_item.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.sub_category_measure_map.controller.SubCategoryMeasureMap;
import net.heronation.zeyo.rest.dmodel_measure_map.controller.DmodelMeasureMap;


@Data
public class MeasureItemDto{
 
        private List<SubCategoryMeasureMap> subCategoryMeasureMaps = new ArrayList<SubCategoryMeasureMap>();
 private Long id;




private String name;



private String metaDesc;



private String createDt;



private String useYn;



private List<DmodelMeasureMap> dmodelMeasureMaps = new ArrayList<DmodelMeasureMap>();
    
}