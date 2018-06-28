package net.heronation.zeyo.rest.repository.bbs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.RequiredArgsConstructor;
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
	
	private String attach_file;

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