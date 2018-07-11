package net.heronation.zeyo.rest.integrate.controller.kakao.dto;

import lombok.Data;

@Data
public class KaKaoAccountDto {
	private boolean has_email;
	private boolean is_email_valid;
	private boolean is_email_verified;
	private String email;
	private boolean has_age_range;
	private String age_range;
	private boolean has_birthday;
	private String birthday;
	private boolean has_gender;
	private String gender; 
	
}


//"has_email": true,
//"is_email_valid": true,
//"is_email_verified": true,
//"email": "heronation@naver.com",
//"has_age_range": false,
//"has_birthday": false,
//"has_gender": false