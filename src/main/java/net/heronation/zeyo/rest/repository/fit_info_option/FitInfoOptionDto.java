package net.heronation.zeyo.rest.repository.fit_info_option;

import lombok.Value;
import net.heronation.zeyo.rest.repository.fit_info.FitInfo; 

@Value
public class FitInfoOptionDto {

	private Long id;

	private FitInfo fitInfo;

	private String name;

	private String useYn;

}