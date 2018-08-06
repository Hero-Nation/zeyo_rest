package net.heronation.zeyo.rest.bbs.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.kindof.controller.Kindof;import net.heronation.zeyo.rest.member.controller.Member;


@Data
public class BbsDto{
 
        private Long id;




private Kindof kindof;




private Member member;




private String title;



private String bbsContent;



private String replyContent;



private String createDt;



private String replyDt;



private String status;



private String useYn;
    
}