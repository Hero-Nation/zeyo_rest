package net.heronation.zeyo.rest.member.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.heronation.zeyo.rest.bbs.repository.Bbs;
import net.heronation.zeyo.rest.brand.repository.Brand;
import net.heronation.zeyo.rest.company_no_history.repository.CompanyNoHistory;
import net.heronation.zeyo.rest.item.repository.Item;
import net.heronation.zeyo.rest.shopmall.repository.Shopmall;

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
	
	
	
	@JsonManagedReference(value="companyNoHistoryssss_member")
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
	
	@JsonBackReference
	@JsonIgnore
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<Item> items = new ArrayList<Item>();
	
	@JsonBackReference
	@JsonIgnore
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<Brand> brands = new ArrayList<Brand>();
	
	@JsonBackReference
	@JsonIgnore
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<Shopmall> shopmalls = new ArrayList<Shopmall>();
	
	@JsonBackReference(value="companyNoHistory_member")
	@JsonIgnore
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<CompanyNoHistory> companyNoHistorys = new ArrayList<CompanyNoHistory>();
	
	@JsonBackReference(value="bbss_member")
	@JsonIgnore
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<Bbs> bbss = new ArrayList<Bbs>();
	
	
	
	@Override
	public String toString() {
		return "Member ]";
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
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