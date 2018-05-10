package net.heronation.zeyo.rest.service.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.MemberRegisterDto;

public interface MemberService {
    Member registry(MemberRegisterDto param);
    
    String send_register_mail();
    
    String find_id_by_email();
    
    String find_id_by_phone();
    
    Page<Member> search(Predicate where,Pageable page); 
}