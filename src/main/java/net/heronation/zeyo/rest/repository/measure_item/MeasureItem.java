package net.heronation.zeyo.rest.repository.measure_item;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.heronation.zeyo.rest.repository.sub_category_measure_map.SubCategoryMeasureMap;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
@Entity
@Data
@RequiredArgsConstructor
@Table(name = "MEASURE_ITEM")
@TableGenerator(name = "MEASURE_ITEM_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "MEASURE_ITEM_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = {"option"})
public class MeasureItem {

	@OneToMany(mappedBy = "measureItem", fetch = FetchType.LAZY)
	private List<SubCategoryMeasureMap> subCategoryMeasureMaps = new ArrayList<SubCategoryMeasureMap>();
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MEASURE_ITEM_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	private String name;

	private String metaDesc;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createDt;

	private String useYn;

}