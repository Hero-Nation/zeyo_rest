package net.heronation.zeyo.rest.company_no_history.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.member.controller.Member;
import net.heronation.zeyo.rest.member.controller.Member;


@Data
public class CompanyNoHistoryDto{
 
        private List<Member> members = new ArrayList<Member>();
 private Long id;




private Member member;




private String name;



private String companyNo;



private String beforeNo;



private String changeDt;
    
}