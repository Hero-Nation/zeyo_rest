package net.heronation.zeyo.rest.item_drymethod_map.repository;
 
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

import net.heronation.zeyo.rest.item.repository.Item;
 
@Entity 
@Data
@RequiredArgsConstructor
@Table(name="ITEM_DRYMETHOD_MAP")
@TableGenerator(name="ITEM_DRYMETHOD_MAP_ID_GENERATOR",table="JPA_ID_TABLE",pkColumnValue="ITEM_DRYMETHOD_MAP_ID",allocationSize=1)
@EntityListeners(AuditingEntityListener.class)
public class ItemDrymethodMap{

	//private @Version Long version;
	//private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;
 
        @Id 
@GeneratedValue(strategy = GenerationType.TABLE,generator="ITEM_DRYMETHOD_MAP_ID_GENERATOR")
@Column(name="ID")
private Long id;

 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="ITEM_ID")
private Item item;
private String machineDry;




private String natureDry;




private String dryMode;




private String handDry;




private String useYn;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(); 
                .append("  ItemDrymethodMap  Entity  ")
                .append("\n id  =  ").append(id)

.append("\n machine_dry  =  ").append(machine_dry)

.append("\n nature_dry  =  ").append(nature_dry)

.append("\n dry_mode  =  ").append(dry_mode)

.append("\n hand_dry  =  ").append(hand_dry)

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
		ItemDrymethodMap other = (ItemDrymethodMap) obj;
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