package net.heronation.zeyo.rest.item_shopmall_map.repository;

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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.heronation.zeyo.rest.item.repository.Item;
import net.heronation.zeyo.rest.shopmall.repository.Shopmall;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "ITEM_SHOPMALL_MAP")
@TableGenerator(name = "ITEM_SHOPMALL_MAP_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "ITEM_SHOPMALL_MAP_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)

public class ItemShopmallMap {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ITEM_SHOPMALL_MAP_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ITEM_ID")
	private Item item;

	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SHOPMALL_ID")
	private Shopmall shopmall;
	private String useYn;
	@Override
	public String toString() {
		return "ItemShopmallMap ]";
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