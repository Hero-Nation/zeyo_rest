package net.heronation.zeyo.rest.item_shopmall_map.repository;
 
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

import net.heronation.zeyo.rest.item.repository.Item;import net.heronation.zeyo.rest.shopmall.repository.Shopmall;
 
@Entity 
@Data
@RequiredArgsConstructor
@Table(name="ITEM_SHOPMALL_MAP")
@TableGenerator(name="ITEM_SHOPMALL_MAP_ID_GENERATOR",table="JPA_ID_TABLE",pkColumnValue="ITEM_SHOPMALL_MAP_ID",allocationSize=1)
@EntityListeners(AuditingEntityListener.class)
public class ItemShopmallMap{

	//private @Version Long version;
	//private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;
 
        @Id 
@GeneratedValue(strategy = GenerationType.TABLE,generator="ITEM_SHOPMALL_MAP_ID_GENERATOR")
@Column(name="ID")
private Long id;

 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="ITEM_ID")
private Item item;
 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="SHOPMALL_ID")
private Shopmall shopmall;
private String useYn;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(); 
                .append("  ItemShopmallMap  Entity  ")
                .append("\n id  =  ").append(id)

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
		ItemShopmallMap other = (ItemShopmallMap) obj;
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