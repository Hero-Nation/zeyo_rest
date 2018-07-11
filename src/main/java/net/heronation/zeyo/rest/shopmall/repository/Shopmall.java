package net.heronation.zeyo.rest.shopmall.repository;

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
import lombok.ToString;
import net.heronation.zeyo.rest.item_shopmall_map.repository.ItemShopmallMap;
import net.heronation.zeyo.rest.member.repository.Member;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "SHOPMALL")
@TableGenerator(name = "SHOPMALL_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "SHOPMALL_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = "member")
public class Shopmall {
	@JsonBackReference
	@OneToMany(mappedBy = "shopmall", fetch = FetchType.LAZY)
	private List<ItemShopmallMap> itemShopmallMaps = new ArrayList<ItemShopmallMap>();

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SHOPMALL_ID_GENERATOR")
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
	
	
	private String storeType;
	private String storeId;
	private String oauthID;
	private String oauthCode;
	private String accessToken;
	private String refreshToken;
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shopmall other = (Shopmall) obj;
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

//	@Override
//	public String toString() {
//		return "Shopmall ]";
//	}
}