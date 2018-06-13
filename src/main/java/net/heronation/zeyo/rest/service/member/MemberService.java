package net.heronation.zeyo.rest.service.member;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.common.controller.CommonException;
import net.heronation.zeyo.rest.controller.member.EmailUpdateVO;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.MemberDto;
import net.heronation.zeyo.rest.repository.member.MemberRegisterDto;

public interface MemberService {
	Member registry(MemberRegisterDto param);

	String find_id_by_email(String name, String email);

	String find_id_by_email_confirm(String name, String email, String otp);

	String find_password(String member_id, String member_name, String member_email);

	String find_id_by_phone(String name, String phone);

	String find_id_by_phone_confirm(String name, String phone, String otp);

	Map<String, Object> search(Map<String, Object> where, Pageable page);

	CompanyNoHistory getCompanyInfo(Predicate where);

	Map<String, Object> getUserInfo(Predicate where);

	Map<String, Object> toggle_email_noti(Long member_seq,MemberDto param);

	Map<String, Long> getUserBizInfo(Predicate where);

	Page<Map<String, Object>> my_brand(Predicate where, Pageable page);

	Page<Map<String, Object>> my_shopmall(Predicate where, Pageable page);

	Page<Map<String, Object>> my_item(Predicate where, Pageable page);

	Page<Map<String, Object>> cn_history(Predicate where, Pageable page);

	Member update_phone(MemberDto param, Long member_seq);

	String update_email(EmailUpdateVO param, Long member_seq);
 

	String send_confirm_email(String email) throws CommonException;
	
	String send_register_mail(String email) throws CommonException;
	

	String confirm_otp(String email, String otp) throws CommonException;

	String update_password(String old_pw, String new_pw, Long member_seq);

	CompanyNoHistory update_cp_no(MemberDto param, Long member_seq);

	Member update_mng_name(String mng_name, Long member_seq);

	Member update_mng_phone(String mng_phone, Long member_seq);

	Member findByMemberId(String member_id);

	Map<String, Object> getStat();
}