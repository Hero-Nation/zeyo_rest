package net.heronation.zeyo.rest.dmodel.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.sub_category.controller.SubCategory;
import net.heronation.zeyo.rest.dmodel_measure_map.controller.DmodelMeasureMap;
import net.heronation.zeyo.rest.dmodel_ratio.controller.DmodelRatio;


@Data
public class DmodelDto{
 
        private List<SubCategory> subCategorys = new ArrayList<SubCategory>();
 private Long id;




private String title;



private String controller;



private String svgdata;



private DateTime  createDt;



private DateTime  updateDt;



private String useYn;



private List<DmodelMeasureMap> dmodelMeasureMaps = new ArrayList<DmodelMeasureMap>();
 private List<DmodelRatio> dmodelRatios = new ArrayList<DmodelRatio>();
    
}