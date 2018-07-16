package net.heronation.zeyo.rest.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.common.controller.CommonException;
import net.heronation.zeyo.rest.common.dto.FlagDto;
import net.heronation.zeyo.rest.common.dto.ToggleDto;
import net.heronation.zeyo.rest.company_no_history.repository.CompanyNoHistory;
import net.heronation.zeyo.rest.member.controller.AdminUpdateDto;
import net.heronation.zeyo.rest.member.controller.CpNoUpdateDto;
import net.heronation.zeyo.rest.member.controller.EmailUpdateVO;
import net.heronation.zeyo.rest.member.controller.PasswordUpdateDto;
import net.heronation.zeyo.rest.member.repository.Member;
import net.heronation.zeyo.rest.member.repository.MemberRegisterDto;

public interface MemberService {
	Member registry(MemberRegisterDto param);

	String find_id_by_email(String name, String email) throws CommonException;

	String find_id_by_email_confirm(String name, String email, String otp);

	String find_password(String member_id, String member_name, String member_email);

	String find_id_by_phone(String name, String phone);

	String find_id_by_phone_confirm(String name, String phone, String otp);

	Map<String, Object> search(Map<String, Object> where, Pageable page);

	CompanyNoHistory getCompanyInfo(Predicate where);

	Map<String, Object> getUserInfo(Predicate where);

	Map<String, Object> toggle_email_noti(Long member_seq,FlagDto param);

	Map<String, Object> getUserBizInfo(Map<String, Object> param);

	Map<String, Object> my_brand(Map<String, Object> where, Pageable page);

	Map<String, Object> my_shopmall(Map<String, Object> where, Pageable page);

	Map<String, Object> my_item(Map<String, Object> where, Pageable page);

	Page<Map<String, Object>> cn_history(Predicate where, Pageable page);

	Member update_phone(PasswordUpdateDto param, Long member_seq);

	String update_email(EmailUpdateVO param, Long member_seq);
 

	String send_confirm_email(String email) throws CommonException;
	
	String send_register_mail(String email) throws CommonException;
	

	String confirm_otp(String email, String otp) throws CommonException;

	String update_password(String old_pw, String new_pw, Long member_seq);

	CompanyNoHistory update_cp_no(CpNoUpdateDto param, Long member_seq);
	
	String admin_update(AdminUpdateDto param)  throws CommonException;
	
	
	String delete(List<ToggleDto> param,Long seq);

	Member update_mng_name(String mng_name, Long member_seq);

	Member update_mng_phone(String mng_phone, Long member_seq);

	Member findByMemberId(String member_id);

	Map<String, Object> getStat();
}