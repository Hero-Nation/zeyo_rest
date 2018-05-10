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

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "MEMBER")
@TableGenerator(name = "MEMBER_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "MEMBER_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = {"option"})
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	private String memberId;

	private String name;

	private String password;

	private String phone;

	private String email;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COMPANY_NO_HISTORY_ID")
	private CompanyNoHistory companyNoHistory;
	private String manager;

	private String managerEmail;

	private String managerPhone;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date deleteDt;

	private String adminYn;

	private String useYn;

	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<Item> items = new ArrayList<Item>();

	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<Brand> brands = new ArrayList<Brand>();

	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<Shopmall> shopmalls = new ArrayList<Shopmall>();

	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<CompanyNoHistory> companyNoHistorys = new ArrayList<CompanyNoHistory>();

	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<Bbs> bbss = new ArrayList<Bbs>();

}