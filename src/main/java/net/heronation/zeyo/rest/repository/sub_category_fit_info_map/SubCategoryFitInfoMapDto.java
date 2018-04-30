package net.heronation.zeyo.rest.repository.sub_category_fit_info_map;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Value;

import net.heronation.zeyo.rest.repository.sub_category.SubCategory;import net.heronation.zeyo.rest.repository.fit_info.FitInfo;


@Value
public class SubCategoryFitInfoMapDto{
 
        private Long id;




private SubCategory subCategory;




private FitInfo fitInfo;




private String useYn;
    
}