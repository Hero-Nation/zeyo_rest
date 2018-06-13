package net.heronation.zeyo.rest.repository.bbs;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import net.heronation.zeyo.rest.repository.kindof.Kindof;
import net.heronation.zeyo.rest.repository.member.Member;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "BBS")
@TableGenerator(name = "BBS_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "BBS_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)

public class Bbs {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BBS_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;
	
	
	@JsonManagedReference(value="bbss_kindof")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "KINDOF_ID")
	private Kindof kindof;
	
	
	
	@JsonManagedReference(value="bbss_member")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	
	
	
	private String title;

	private String bbsContent;

	private String replyContent;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createDt;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime replyDt;

	private String status;

	private String useYn;

	@Override
	public String toString() {
		return "Bbs ]";
	}

}