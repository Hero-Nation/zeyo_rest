package net.heronation.zeyo.rest.fit_info_option.repository;

import lombok.Value;
import net.heronation.zeyo.rest.fit_info.repository.FitInfo; 

@Value
public class FitInfoOptionDto {

	private Long id;

	private FitInfo fitInfo;

	private String name;

	private String useYn;

}