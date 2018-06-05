package net.heronation.zeyo.rest.repository.member;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory;
import net.heronation.zeyo.rest.repository.bbs.Bbs;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "MEMBER")
@TableGenerator(name = "MEMBER_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "MEMBER_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)

public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	private String memberId;

	private String name;

	@JsonIgnore
	private String password;

	private String phone;

	private String email;
	
	@JsonIgnore
	private String confirm_no;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COMPANY_NO_HISTORY_ID")
	private CompanyNoHistory companyNoHistory;
	private String manager;

	private String managerEmail;

	private String managerPhone;

	@JsonIgnore
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createDt;

	@JsonIgnore
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime deleteDt;

	@JsonIgnore
	private String adminYn;

	@JsonIgnore
	private String useYn;

	private String email_noti_yn;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<Item> items = new ArrayList<Item>();

	@JsonIgnore
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<Brand> brands = new ArrayList<Brand>();

	@JsonIgnore
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<Shopmall> shopmalls = new ArrayList<Shopmall>();

	@JsonIgnore
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<CompanyNoHistory> companyNoHistorys = new ArrayList<CompanyNoHistory>();

	@JsonIgnore
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<Bbs> bbss = new ArrayList<Bbs>();
	
	
	@Override
	public String toString() {
		return "Member ]";
	}

}