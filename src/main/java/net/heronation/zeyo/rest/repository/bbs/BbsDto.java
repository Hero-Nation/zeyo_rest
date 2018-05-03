package net.heronation.zeyo.rest.repository.bbs;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Value;

import net.heronation.zeyo.rest.repository.kindof.Kindof;import net.heronation.zeyo.rest.repository.member.Member;


@Value
public class BbsDto{
 
        private Long id;




private Kindof kindof;




private Member member;




private String title;



private String bbsContent;



private String replyContent;



 
private Date createDt;



 
private Date replyDt;



private String status;



private String useYn;
    
}