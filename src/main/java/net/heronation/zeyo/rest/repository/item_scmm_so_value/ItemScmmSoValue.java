package net.heronation.zeyo.rest.repository.item_scmm_so_value;

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

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;
import net.heronation.zeyo.rest.repository.sub_category_measure_map.SubCategoryMeasureMap;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
}