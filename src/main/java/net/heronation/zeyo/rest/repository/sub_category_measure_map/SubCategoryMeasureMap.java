package net.heronation.zeyo.rest.repository.sub_category_measure_map;

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

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.heronation.zeyo.rest.repository.measure_item.MeasureItem;
import net.heronation.zeyo.rest.repository.sub_category.SubCategory;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "SUB_CATEGORY_MEASURE_MAP")
@TableGenerator(name = "SUB_CATEGORY_MEASURE_MAP_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "SUB_CATEGORY_MEASURE_MAP_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)

public class SubCategoryMeasureMap {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SUB_CATEGORY_MEASURE_MAP_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SUB_CATEGORY_ID")
	private SubCategory subCategory;
	
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MEASURE_ITEM_ID")
	private MeasureItem measureItem;
	
	
	private String useYn;

	@Override
	public String toString() {
		return "SubCategoryMeasureMap ]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubCategoryMeasureMap other = (SubCategoryMeasureMap) obj;
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