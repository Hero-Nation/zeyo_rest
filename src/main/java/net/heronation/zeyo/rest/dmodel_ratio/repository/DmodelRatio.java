package net.heronation.zeyo.rest.dmodel_ratio.repository;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import net.heronation.zeyo.rest.dmodel.repository.Dmodel;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "DMODEL_RATIO")
@TableGenerator(name = "DMODEL_RATIO_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "DMODEL_RATIO_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)
public class DmodelRatio {

	// private @Version Long version;
	// private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "DMODEL_RATIO_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER )
	@JoinColumn(name = "DMODEL_ID")
	private Dmodel dmodel;

	private String defaultYn;

	private String minValue;

	private String maxValue;

	private String ratioValue;

	private String useYn;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("  DmodelRatio  Entity  ").append("\n id  =  ").append(id)

				.append("\n default_yn  =  ").append(defaultYn)

				.append("\n min_value  =  ").append(minValue)

				.append("\n max_value  =  ").append(maxValue)

				.append("\n ratio_value  =  ").append(ratioValue)

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
		DmodelRatio other = (DmodelRatio) obj;
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