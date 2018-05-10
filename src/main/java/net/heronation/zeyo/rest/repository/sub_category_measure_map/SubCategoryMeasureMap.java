package net.heronation.zeyo.rest.repository.sub_category_measure_map;

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

import net.heronation.zeyo.rest.repository.sub_category.SubCategory;
import net.heronation.zeyo.rest.repository.measure_item.MeasureItem;

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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SUB_CATEGORY_ID")
	private SubCategory subCategory;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MEASURE_ITEM_ID")
	private MeasureItem measureItem;
	private String useYn;

}