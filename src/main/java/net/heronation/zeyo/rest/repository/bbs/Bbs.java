package net.heronation.zeyo.rest.repository.bbs;
 
import javax.persistence.*; 
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.heronation.zeyo.rest.repository.kindof.Kindof;import net.heronation.zeyo.rest.repository.member.Member;
 
@Entity 
@Data
@RequiredArgsConstructor
@Table(name="BBS")
@TableGenerator(name="BBS_ID_GENERATOR",table="JPA_ID_TABLE",pkColumnValue="BBS_ID",allocationSize=1)
@EntityListeners(AuditingEntityListener.class)
public class Bbs{

	
	
 
        @Id 
@GeneratedValue(strategy = GenerationType.TABLE,generator="BBS_ID_GENERATOR")
@Column(name="ID")
private Long id;

 
@ManyToOne(fetch=FetchType.EAGER ) 
@JoinColumn(name="KINDOF_ID")
private Kindof kindof;
 
@ManyToOne(fetch=FetchType.EAGER ) 
@JoinColumn(name="MEMBER_ID")
private Member member;
private String title;




private String bbsContent;




private String replyContent;





@Temporal(TemporalType.TIMESTAMP)
private Date   createDt;




@Temporal(TemporalType.TIMESTAMP)
private Date   replyDt;



private String status;




private String useYn;
    
}