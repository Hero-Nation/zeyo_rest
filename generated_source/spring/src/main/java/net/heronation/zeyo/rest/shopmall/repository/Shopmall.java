package net.heronation.zeyo.rest.shopmall.repository;
 
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

import net.heronation.zeyo.rest.item_shopmall_map.repository.ItemShopmallMap;
import net.heronation.zeyo.rest.member.repository.Member;
 
@Entity 
@Data
@RequiredArgsConstructor
@Table(name="SHOPMALL")
@TableGenerator(name="SHOPMALL_ID_GENERATOR",table="JPA_ID_TABLE",pkColumnValue="SHOPMALL_ID",allocationSize=1)
@EntityListeners(AuditingEntityListener.class)
public class Shopmall{

	//private @Version Long version;
	//private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;
 
        @OneToMany(mappedBy = "shopmall" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<ItemShopmallMap>  itemShopmallMaps = new ArrayList<ItemShopmallMap>();
 @Id 
@GeneratedValue(strategy = GenerationType.TABLE,generator="SHOPMALL_ID_GENERATOR")
@Column(name="ID")
private Long id;

 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="MEMBER_ID")
private Member member;
private String name;




private String useYn;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(); 
                .append("  Shopmall  Entity  ")
                .append("\n id  =  ").append(id)

.append("\n name  =  ").append(name)

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
		Shopmall other = (Shopmall) obj;
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