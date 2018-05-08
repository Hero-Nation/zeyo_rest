package net.heronation.zeyo.rest.repository.fit_info;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Value;

import net.heronation.zeyo.rest.repository.sub_category_fit_info_map.SubCategoryFitInfoMap;
import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOption; 

@Value
public class FitInfoDto {

	private List<SubCategoryFitInfoMap> subCategoryFitInfoMaps = new ArrayList<SubCategoryFitInfoMap>();
	private Long id;

	private String name;

	private String metaDesc;

	private Date createDt;

	private String useYn;

}