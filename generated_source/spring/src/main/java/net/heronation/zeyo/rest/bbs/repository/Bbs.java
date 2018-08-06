package net.heronation.zeyo.rest.bbs.repository;
 
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

import net.heronation.zeyo.rest.kindof.repository.Kindof;import net.heronation.zeyo.rest.member.repository.Member;
 
@Entity 
@Data
@RequiredArgsConstructor
@Table(name="BBS")
@TableGenerator(name="BBS_ID_GENERATOR",table="JPA_ID_TABLE",pkColumnValue="BBS_ID",allocationSize=1)
@EntityListeners(AuditingEntityListener.class)
public class Bbs{

	//private @Version Long version;
	//private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;
 
        @Id 
@GeneratedValue(strategy = GenerationType.TABLE,generator="BBS_ID_GENERATOR")
@Column(name="ID")
private Long id;

 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="KINDOF_ID")
private Kindof kindof;
 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="MEMBER_ID")
private Member member;
private String title;




private String bbsContent;




private String replyContent;




private String createDt;




private String replyDt;




private String status;




private String useYn;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(); 
                .append("  Bbs  Entity  ")
                .append("\n id  =  ").append(id)

.append("\n title  =  ").append(title)

.append("\n bbs_content  =  ").append(bbs_content)

.append("\n reply_content  =  ").append(reply_content)

.append("\n create_dt  =  ").append(create_dt)

.append("\n reply_dt  =  ").append(reply_dt)

.append("\n status  =  ").append(status)

.append("\n use_yn  =  ").append(use_yn)
		return builder.toString();	
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bbs other = (Bbs) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Transient
	private UUID hash_id = UUID.randomUUID();
	
	@Override
	public int hashCode() { 
		return hash_id.hashCode();
	}
    
}