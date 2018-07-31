package net.heronation.zeyo.rest.fit_info.repository;

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

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.heronation.zeyo.rest.fit_info_option.repository.FitInfoOption;
import net.heronation.zeyo.rest.sub_category_fit_info_map.repository.SubCategoryFitInfoMap;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "FIT_INFO")
@TableGenerator(name = "FIT_INFO_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "FIT_INFO_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)

public class FitInfo {
	@JsonBackReference(value="sub_cate_fit_info_map")
	@OneToMany(mappedBy = "fitInfo", fetch = FetchType.LAZY)
	private List<SubCategoryFitInfoMap> subCategoryFitInfoMaps = new ArrayList<SubCategoryFitInfoMap>();

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FIT_INFO_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	private String name;

	private String metaDesc;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createDt;

	private String useYn;

	@JsonBackReference
	@OneToMany(mappedBy = "fitInfo", fetch = FetchType.LAZY)
	private List<FitInfoOption> fitInfoOptions = new ArrayList<FitInfoOption>();

	@Override
	public String toString() {
		return "FitInfo ]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FitInfo other = (FitInfo) obj;
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