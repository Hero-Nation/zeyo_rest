package net.heronation.zeyo.rest.company_no_history.repository;

import java.util.ArrayList;
import java.util.List;

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

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.heronation.zeyo.rest.member.repository.Member;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "COMPANY_NO_HISTORY")
@TableGenerator(name = "COMPANY_NO_HISTORY_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "COMPANY_NO_HISTORY_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)

public class CompanyNoHistory {
	@JsonBackReference(value = "companyNoHistoryssss_member")
	@OneToMany(mappedBy = "companyNoHistory", fetch = FetchType.LAZY)
	private List<Member> members = new ArrayList<Member>();
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMPANY_NO_HISTORY_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	@JsonManagedReference(value = "companyNoHistory_member")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	private String name;

	private String companyNo;

	private String beforeNo;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime changeDt;

	@Override
	public String toString() {
		return "CompanyNoHistory ]";
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

}