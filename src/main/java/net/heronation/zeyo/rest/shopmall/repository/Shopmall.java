package net.heronation.zeyo.rest.shopmall.repository;

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

	@Transient
	@JsonIgnore
	private UUID hash_id = UUID.randomUUID();

	@Override
	public int hashCode() {
		return hash_id.hashCode();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Shopmall [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (createDt != null) {
			builder.append("createDt=");
			builder.append(createDt);
			builder.append(", ");
		}
		if (deleteDt != null) {
			builder.append("deleteDt=");
			builder.append(deleteDt);
			builder.append(", ");
		}
		if (useYn != null) {
			builder.append("useYn=");
			builder.append(useYn);
			builder.append(", ");
		}
		if (storeType != null) {
			builder.append("storeType=");
			builder.append(storeType);
			builder.append(", ");
		}
		if (storeId != null) {
			builder.append("storeId=");
			builder.append(storeId);
			builder.append(", ");
		}
		if (oauthID != null) {
			builder.append("oauthID=");
			builder.append(oauthID);
			builder.append(", ");
		}
		if (oauthCode != null) {
			builder.append("oauthCode=");
			builder.append(oauthCode);
			builder.append(", ");
		}
		if (accessToken != null) {
			builder.append("accessToken=");
			builder.append(accessToken);
			builder.append(", ");
		}
		if (refreshToken != null) {
			builder.append("refreshToken=");
			builder.append(refreshToken);
			builder.append(", ");
		}
		if (hash_id != null) {
			builder.append("hash_id=");
			builder.append(hash_id);
		}
		builder.append("]");
		return builder.toString();
	}
 
}