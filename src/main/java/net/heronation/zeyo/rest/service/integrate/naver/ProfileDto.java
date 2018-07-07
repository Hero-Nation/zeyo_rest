package net.heronation.zeyo.rest.service.integrate.naver;

import lombok.Data;

@Data
public class ProfileDto {
 
	
	private String resultcode;
	private String message;
	private ProfileResponseDto response;
	
}
