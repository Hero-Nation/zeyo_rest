package net.heronation.zeyo.rest.item_drymethod_map.repository;

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
@Table(name = "ITEM_DRYMETHOD_MAP")
@TableGenerator(name = "ITEM_DRYMETHOD_MAP_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "ITEM_DRYMETHOD_MAP_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)

public class ItemDrymethodMap {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ITEM_DRYMETHOD_MAP_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;
	@JsonManagedReference
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ITEM_ID")
	private Item item;
	private String machineDry;

	private String natureDry;

	private String dryMode;

	private String handDry;

	private String useYn;

 

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
	@JsonIgnore
	private UUID hash_id = UUID.randomUUID();

	@Override
	public int hashCode() {
		return hash_id.hashCode();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ItemDrymethodMap [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (machineDry != null) {
			builder.append("machineDry=");
			builder.append(machineDry);
			builder.append(", ");
		}
		if (natureDry != null) {
			builder.append("natureDry=");
			builder.append(natureDry);
			builder.append(", ");
		}
		if (dryMode != null) {
			builder.append("dryMode=");
			builder.append(dryMode);
			builder.append(", ");
		}
		if (handDry != null) {
			builder.append("handDry=");
			builder.append(handDry);
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