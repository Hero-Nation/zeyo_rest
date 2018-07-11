package net.heronation.zeyo.rest.integrate.controller.cafe24.dto;

import lombok.Data;

@Data	
public class ScriptCreateRequestDto {
	private String src;
	private String[] display_location;
	private int[] skin_no;
}
