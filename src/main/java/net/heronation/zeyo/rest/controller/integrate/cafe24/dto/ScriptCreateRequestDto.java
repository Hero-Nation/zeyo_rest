package net.heronation.zeyo.rest.controller.integrate.cafe24.dto;

import lombok.Data;

@Data	
public class ScriptCreateRequestDto {
	private String src;
	private String[] display_location;
	private int[] skin_no;
}
