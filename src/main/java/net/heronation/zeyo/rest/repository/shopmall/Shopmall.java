package net.heronation.zeyo.rest.repository.shopmall;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMap;
import net.heronation.zeyo.rest.repository.member.Member;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

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

	@Override
	public String toString() {
		return "Shopmall ]";
	}
}