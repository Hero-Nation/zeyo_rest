package net.heronation.zeyo.rest.repository.company_no_history;

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

import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.Member;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "COMPANY_NO_HISTORY")
@TableGenerator(name = "COMPANY_NO_HISTORY_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "COMPANY_NO_HISTORY_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)
public class CompanyNoHistory {

	@OneToMany(mappedBy = "companyNoHistory", fetch = FetchType.LAZY)
	private List<Member> members = new ArrayList<Member>();
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMPANY_NO_HISTORY_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	private String name;

	private String companyNo;

	private String beforeNo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date changeDt;

}