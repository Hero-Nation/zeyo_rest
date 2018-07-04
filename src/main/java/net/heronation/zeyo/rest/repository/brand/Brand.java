package net.heronation.zeyo.rest.repository.brand;

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
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.member.Member;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "BRAND")
@TableGenerator(name = "BRAND_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "BRAND_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class) 
public class Brand {

	@JsonBackReference
	@OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
	private List<Item> items = new ArrayList<Item>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BRAND_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	private String name;
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createDt;
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime deleteDt;

	private String useYn;
	
	
	@Override
	public String toString() {
		return "Brand ]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Brand other = (Brand) obj;
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