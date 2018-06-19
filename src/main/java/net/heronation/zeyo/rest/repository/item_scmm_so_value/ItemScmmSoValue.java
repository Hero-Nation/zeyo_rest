package net.heronation.zeyo.rest.repository.item_scmm_so_value;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.sub_category_measure_map.SubCategoryMeasureMap;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;

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
}