package net.heronation.zeyo.rest.service.member;

import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.MemberRegisterDto;

public interface MemberService {
    Member registry(MemberRegisterDto param);
    
    String send_register_mail();
    
    String find_id_by_email();
    
    String find_id_by_phone();
    
    
}