package net.heronation.zeyo.rest.repository.fit_info;

import java.util.List;

import lombok.Data;
import net.heronation.zeyo.rest.common.value.NameVO;

@Data
public class FitInfoDto {


	private String name;

	private String metaDesc; 

	private List<NameVO> options;
	
	public FitInfo convertToEntity() {
		FitInfo R = new FitInfo();
		R.setName(name);
		R.setMetaDesc(metaDesc);
		
		return R;
	}
}