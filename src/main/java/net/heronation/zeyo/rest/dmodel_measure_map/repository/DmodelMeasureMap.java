package net.heronation.zeyo.rest.dmodel_measure_map.repository;

import java.util.UUID;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.heronation.zeyo.rest.dmodel.repository.Dmodel;
import net.heronation.zeyo.rest.measure_item.repository.MeasureItem;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "DMODEL_MEASURE_MAP")
@TableGenerator(name = "DMODEL_MEASURE_MAP_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "DMODEL_MEASURE_MAP_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)
public class DmodelMeasureMap {

	// private @Version Long version;
	// private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "DMODEL_MEASURE_MAP_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DMODEL_ID")
	private Dmodel dmodel;

	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MEASURE_ITEM_ID")
	private MeasureItem measureItem;
	private String minValue;

	private String maxValue;

	private String useYn;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("  DmodelMeasureMap  Entity  ").append("\n id  =  ").append(id)

				.append("\n min_value  =  ").append(minValue)

				.append("\n max_value  =  ").append(maxValue)

				.append("\n use_yn  =  ").append(useYn);
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
		DmodelMeasureMap other = (DmodelMeasureMap) obj;
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

}