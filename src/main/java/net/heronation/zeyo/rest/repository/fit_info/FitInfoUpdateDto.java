package net.heronation.zeyo.rest.repository.fit_info;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import lombok.Data;
import lombok.Value;

import net.heronation.zeyo.rest.repository.sub_category_fit_info_map.SubCategoryFitInfoMap;
import net.heronation.zeyo.rest.common.value.NameVO;
import net.heronation.zeyo.rest.common.value.ToggleVO;
import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOption;

@Data
public class FitInfoUpdateDto {

	private Long id;

	private String name;

	private String metaDesc; 

	private List<ToggleVO> options;
	
	public FitInfo convertToEntity() {
		FitInfo R = new FitInfo();
		R.setId(id);
		R.setName(name);
		R.setMetaDesc(metaDesc);
		
		return R;
	}
}