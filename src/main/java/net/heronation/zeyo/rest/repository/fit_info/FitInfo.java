package net.heronation.zeyo.rest.repository.fit_info;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import net.heronation.zeyo.rest.repository.sub_category_fit_info_map.SubCategoryFitInfoMap;
import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOption;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "FIT_INFO")
@TableGenerator(name = "FIT_INFO_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "FIT_INFO_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)

public class FitInfo {
	@JsonBackReference(value="fit_info_map")
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

}