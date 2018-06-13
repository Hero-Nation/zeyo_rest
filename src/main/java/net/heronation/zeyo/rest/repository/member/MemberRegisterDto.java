package net.heronation.zeyo.rest.repository.member;

import lombok.Data; 

@Data
public class MemberRegisterDto {
	
	private String member_id;
	private String name;
	private String email;
	private String phone;
	private String password;
	private String manager;
	private String manager_email;
	private String manager_phone;
	private String company_name;
	private String company_no; 
	
	public Member getInitMember() {
		Member a= new Member();
		a.setAdminYn("N");
		a.setEmail(email);
		a.setManager(manager);
		a.setManagerEmail(manager_phone);
		a.setManagerPhone(manager_phone);
		a.setMemberId(member_id);
		a.setName(name);
		a.setPassword(password);
		a.setPhone(phone);
		a.setUseYn("Y");
		
		
		return a;
		
	}
}
