package net.heronation.zeyo.rest.repository.shopmall_member_map;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Value;

import net.heronation.zeyo.rest.repository.shopmall.Shopmall;import net.heronation.zeyo.rest.repository.member.Member;


@Value
public class ShopmallMemberMapDto{
 
        private Long id;




private Shopmall shopmall;




private Member member;




private String linkYn;



private String useYn;
    
}