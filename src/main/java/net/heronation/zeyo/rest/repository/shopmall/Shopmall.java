package net.heronation.zeyo.rest.repository.shopmall;

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
import net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMap;
import net.heronation.zeyo.rest.repository.member.Member;

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

//	@Override
//	public String toString() {
//		return "Shopmall ]";
//	}
}