package net.heronation.zeyo.rest.repository.member;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "normal_member", types = { Member.class }) 
interface PNormalMember { 
	
	String getMemberId();
	String getName();
	String getPhone();
	String getEmail();
	
}