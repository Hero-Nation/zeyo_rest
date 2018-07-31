package net.heronation.zeyo.rest.item_scmm_so_value.repository;

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

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.heronation.zeyo.rest.item.repository.Item;
import net.heronation.zeyo.rest.size_option.repository.SizeOption;
import net.heronation.zeyo.rest.sub_category_measure_map.repository.SubCategoryMeasureMap;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "ITEM_SCMM_SO_VALUE")
@TableGenerator(name = "ITEM_SCMM_SO_VALUE_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "ITEM_SCMM_SO_VALUE_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)
public class ItemScmmSoValue {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ITEM_SCMM_SO_VALUE_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ITEM_ID")
	private Item item;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SUB_CATEGORY_MEASURE_MAP_ID")
	private SubCategoryMeasureMap subCategoryMeasureMap;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SIZE_OPTION_ID")
	private SizeOption sizeOption;
	private String inputValue;

	private String useYn;

	@Override
	public String toString() {
		return "ItemScmmSoValue ]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemScmmSoValue other = (ItemScmmSoValue) obj;
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