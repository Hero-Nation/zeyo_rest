package net.heronation.zeyo.rest.company_no_history.repository;
 
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

import net.heronation.zeyo.rest.member.repository.Member;
import net.heronation.zeyo.rest.member.repository.Member;
 
@Entity 
@Data
@RequiredArgsConstructor
@Table(name="COMPANY_NO_HISTORY")
@TableGenerator(name="COMPANY_NO_HISTORY_ID_GENERATOR",table="JPA_ID_TABLE",pkColumnValue="COMPANY_NO_HISTORY_ID",allocationSize=1)
@EntityListeners(AuditingEntityListener.class)
public class CompanyNoHistory{

	//private @Version Long version;
	//private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;
 
        @OneToMany(mappedBy = "companyNoHistory" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<Member>  members = new ArrayList<Member>();
 @Id 
@GeneratedValue(strategy = GenerationType.TABLE,generator="COMPANY_NO_HISTORY_ID_GENERATOR")
@Column(name="ID")
private Long id;

 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="MEMBER_ID")
private Member member;
private String name;




private String companyNo;




private String beforeNo;




private String changeDt;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(); 
                .append("  CompanyNoHistory  Entity  ")
                .append("\n id  =  ").append(id)

.append("\n name  =  ").append(name)

.append("\n company_no  =  ").append(company_no)

.append("\n before_no  =  ").append(before_no)

.append("\n change_dt  =  ").append(change_dt)
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
		CompanyNoHistory other = (CompanyNoHistory) obj;
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