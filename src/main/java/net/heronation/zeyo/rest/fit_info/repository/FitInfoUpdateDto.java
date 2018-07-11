package net.heronation.zeyo.rest.fit_info.repository;

import java.util.List;

import lombok.Data;
import net.heronation.zeyo.rest.common.dto.ToggleDto;

@Data
public class FitInfoUpdateDto {

	private Long id;

	private String name;

	private String metaDesc; 

	private List<ToggleDto> options;
	
	public FitInfo convertToEntity() {
		FitInfo R = new FitInfo();
		R.setId(id);
		R.setName(name);
		R.setMetaDesc(metaDesc);
		
		return R; 
	}
}