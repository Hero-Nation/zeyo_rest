package net.heronation.zeyo.rest.integrate.service.naver;

import lombok.Data;

@Data
public class ProfileDto {
 
	
	private String resultcode;
	private String message;
	private ProfileResponseDto response;
	
}
