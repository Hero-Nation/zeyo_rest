package net.heronation.zeyo.rest.integrate.controller.kakao.dto;

import lombok.Data;

@Data
public class ProfileDto {

	private String id;
	private PropertiesDto properties;
	private KaKaoAccountDto kakao_account;
	
}

//
//{
//	  "id":123456789,
//	  "properties":{
//	     "nickname":"홍길동",
//	     "thumbnail_image":"http://xxx.kakao.co.kr/.../aaa.jpg",
//	     "profile_image":"http://xxx.kakao.co.kr/.../bbb.jpg", 
//	  },
//	  "kakao_account": { 
//	    "has_email": true, 
//	    "is_email_valid": true,   
//	    "is_email_verified": true,   
//	    "email": "xxxxxxx@xxxxx.com"
//	    "has_age_range":true,
//	    "age_range":"20~29",
//	    "has_birthday":true,
//	    "birthday":"1130",
//	    "has_gender":true,
//	    "gender":"female"
//	  }
//	}