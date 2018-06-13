package net.heronation.zeyo.rest.repository.sub_category_fit_info_map;
 
import lombok.Value;
import net.heronation.zeyo.rest.repository.fit_info.FitInfo;
import net.heronation.zeyo.rest.repository.sub_category.SubCategory;


@Value
public class SubCategoryFitInfoMapDto{
 
        private Long id;




private SubCategory subCategory;




private FitInfo fitInfo;




private String useYn;
    
}