package net.heronation.zeyo.rest.repository.fit_info;

import java.util.List;

import lombok.Data;
import net.heronation.zeyo.rest.common.dto.NameDto;

@Data
public class FitInfoDto {


	private String name;

	private String metaDesc; 

	private List<NameDto> options;
	
	public FitInfo convertToEntity() {
		FitInfo R = new FitInfo();
		R.setName(name);
		R.setMetaDesc(metaDesc);
		
		return R;
	}
}