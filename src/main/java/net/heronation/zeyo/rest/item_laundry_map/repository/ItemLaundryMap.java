package net.heronation.zeyo.rest.item_laundry_map.repository;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.heronation.zeyo.rest.item.repository.Item;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "ITEM_LAUNDRY_MAP")
@TableGenerator(name = "ITEM_LAUNDRY_MAP_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "ITEM_LAUNDRY_MAP_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)

public class ItemLaundryMap {

	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ITEM_LAUNDRY_MAP_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	@JsonManagedReference
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ITEM_ID")
	private Item item;

	private String water;

	private String machine;

	private String hand;

	private String waterTemp;

	private String intensity;

	private String detergent;

	private String useYn;

	@Override
	public String toString() {
		return "ItemLaundryMap ]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemLaundryMap other = (ItemLaundryMap) obj;
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