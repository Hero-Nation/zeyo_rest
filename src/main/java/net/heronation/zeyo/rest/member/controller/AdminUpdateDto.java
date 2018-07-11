package net.heronation.zeyo.rest.member.controller;

import lombok.Data;

@Data
public class AdminUpdateDto {

	private Long company_id;
	private Long member_id;
	private String company_companyNo;
	private String member_email;
	private String member_manager;
	private String member_manager_phone;
	private String member_phone;
	
	
//	company_companyNo
//	:
//	"1111,11111,11111"
//	member_email
//	:
//	"sekim@heronation.net"
//	member_manager
//	:
//	"안덕곤2"
//	member_manager_phone
//	:
//	"01023422342"
//	member_phone
//	:
//	"01028063412"
	
}
