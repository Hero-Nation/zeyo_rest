package net.heronation.zeyo.rest.dmodel.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.heronation.zeyo.rest.dmodel_measure_map.repository.DmodelMeasureMap;
import net.heronation.zeyo.rest.dmodel_ratio.repository.DmodelRatio;
import net.heronation.zeyo.rest.sub_category.repository.SubCategory;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "DMODEL")
@TableGenerator(name = "DMODEL_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "DMODEL_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)
public class Dmodel {

	// private @Version Long version;
	// private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;

	@JsonBackReference
	@OneToMany(mappedBy = "dmodel", fetch = FetchType.LAZY)
	private List<SubCategory> subCategorys = new ArrayList<SubCategory>();
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "DMODEL_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	private String title;

	private String controller;

	private String svgdata;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createDt;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime updateDt;

	private String useYn;

	@JsonBackReference
	@OneToMany(mappedBy = "dmodel", fetch = FetchType.LAZY )
	private List<DmodelMeasureMap> dmodelMeasureMaps = new ArrayList<DmodelMeasureMap>();

	@JsonBackReference
	@OneToMany(mappedBy = "dmodel", fetch = FetchType.LAZY )
	private List<DmodelRatio> dmodelRatios = new ArrayList<DmodelRatio>();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("  Dmodel  Entity  ").append("\n id  =  ").append(id)

				.append("\n title  =  ").append(title)

				.append("\n controller  =  ").append(controller)

				.append("\n svgdata  =  ").append(svgdata)

				.append("\n create_dt  =  ").append(createDt)

				.append("\n update_dt  =  ").append(updateDt)

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
		Dmodel other = (Dmodel) obj;
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