package net.heronation.zeyo.rest.item_drycleaning_map.repository;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.heronation.zeyo.rest.item.repository.Item;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "ITEM_DRYCLEANING_MAP")
@TableGenerator(name = "ITEM_DRYCLEANING_MAP_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "ITEM_DRYCLEANING_MAP_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)

public class ItemDrycleaningMap {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ITEM_DRYCLEANING_MAP_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;
	@JsonManagedReference
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ITEM_ID")
	private Item item;
	private String drycan;

	private String storecan;

	private String detergent;

	private String useYn;

 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemDrycleaningMap other = (ItemDrycleaningMap) obj;
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
		builder.append("ItemDrycleaningMap [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (drycan != null) {
			builder.append("drycan=");
			builder.append(drycan);
			builder.append(", ");
		}
		if (storecan != null) {
			builder.append("storecan=");
			builder.append(storecan);
			builder.append(", ");
		}
		if (detergent != null) {
			builder.append("detergent=");
			builder.append(detergent);
			builder.append(", ");
		}
		if (useYn != null) {
			builder.append("useYn=");
			builder.append(useYn);
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