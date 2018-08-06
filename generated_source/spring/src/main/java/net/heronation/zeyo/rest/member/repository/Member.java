package net.heronation.zeyo.rest.member.repository;
 
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

import net.heronation.zeyo.rest.company_no_history.repository.CompanyNoHistory;import net.heronation.zeyo.rest.item.repository.Item;
import net.heronation.zeyo.rest.brand.repository.Brand;
import net.heronation.zeyo.rest.shopmall.repository.Shopmall;
import net.heronation.zeyo.rest.company_no_history.repository.CompanyNoHistory;
import net.heronation.zeyo.rest.bbs.repository.Bbs;
 
@Entity 
@Data
@RequiredArgsConstructor
@Table(name="MEMBER")
@TableGenerator(name="MEMBER_ID_GENERATOR",table="JPA_ID_TABLE",pkColumnValue="MEMBER_ID",allocationSize=1)
@EntityListeners(AuditingEntityListener.class)
public class Member{

	//private @Version Long version;
	//private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;
 
        @Id 
@GeneratedValue(strategy = GenerationType.TABLE,generator="MEMBER_ID_GENERATOR")
@Column(name="ID")
private Long id;

private String memberId;




private String name;




private String password;




private String phone;




private String email;




 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="COMPANY_NO_HISTORY_ID")
private CompanyNoHistory companyNoHistory;
private String manager;




private String managerEmail;




private String managerPhone;




private String createDt;




private String deleteDt;




private String useYn;





@OneToMany(mappedBy = "member" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<Item>  items = new ArrayList<Item>();
 
@OneToMany(mappedBy = "member" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<Brand>  brands = new ArrayList<Brand>();
 
@OneToMany(mappedBy = "member" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<Shopmall>  shopmalls = new ArrayList<Shopmall>();
 
@OneToMany(mappedBy = "member" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<CompanyNoHistory>  companyNoHistorys = new ArrayList<CompanyNoHistory>();
 
@OneToMany(mappedBy = "member" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<Bbs>  bbss = new ArrayList<Bbs>();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(); 
                .append("  Member  Entity  ")
                .append("\n id  =  ").append(id)

.append("\n member_id  =  ").append(member_id)

.append("\n name  =  ").append(name)

.append("\n password  =  ").append(password)

.append("\n phone  =  ").append(phone)

.append("\n email  =  ").append(email)

.append("\n manager  =  ").append(manager)

.append("\n manager_email  =  ").append(manager_email)

.append("\n manager_phone  =  ").append(manager_phone)

.append("\n create_dt  =  ").append(create_dt)

.append("\n delete_dt  =  ").append(delete_dt)

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