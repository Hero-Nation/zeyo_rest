package net.heronation.zeyo.rest.fit_info.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.sub_category_fit_info_map.controller.SubCategoryFitInfoMap;
import net.heronation.zeyo.rest.fit_info_option.controller.FitInfoOption;
import net.heronation.zeyo.rest.fit_into_choice.controller.FitIntoChoice;


@Data
public class FitInfoDto{
 
        private List<SubCategoryFitInfoMap> subCategoryFitInfoMaps = new ArrayList<SubCategoryFitInfoMap>();
 private Long id;




private String name;



private String metaDesc;



private String createDt;



private String useYn;



private List<FitInfoOption> fitInfoOptions = new ArrayList<FitInfoOption>();
 private List<FitIntoChoice> fitIntoChoices = new ArrayList<FitIntoChoice>();
    
}