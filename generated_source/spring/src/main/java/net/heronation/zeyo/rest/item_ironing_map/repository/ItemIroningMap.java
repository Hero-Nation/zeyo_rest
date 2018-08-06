package net.heronation.zeyo.rest.item_ironing_map.repository;
 
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
@Table(name="ITEM_IRONING_MAP")
@TableGenerator(name="ITEM_IRONING_MAP_ID_GENERATOR",table="JPA_ID_TABLE",pkColumnValue="ITEM_IRONING_MAP_ID",allocationSize=1)
@EntityListeners(AuditingEntityListener.class)
public class ItemIroningMap{

	//private @Version Long version;
	//private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;
 
        @Id 
@GeneratedValue(strategy = GenerationType.TABLE,generator="ITEM_IRONING_MAP_ID_GENERATOR")
@Column(name="ID")
private Long id;

 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="ITEM_ID")
private Item item;
private String ironcan;




private String addprotection;




private String startTemp;




private String endTemp;




private String useYn;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(); 
                .append("  ItemIroningMap  Entity  ")
                .append("\n id  =  ").append(id)

.append("\n ironcan  =  ").append(ironcan)

.append("\n addprotection  =  ").append(addprotection)

.append("\n start_temp  =  ").append(start_temp)

.append("\n end_temp  =  ").append(end_temp)

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
		ItemIroningMap other = (ItemIroningMap) obj;
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