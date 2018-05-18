package net.heronation.zeyo.rest.repository.sub_category;

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

import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.sub_category_measure_map.SubCategoryMeasureMap;
import net.heronation.zeyo.rest.repository.sub_category_fit_info_map.SubCategoryFitInfoMap;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
@Entity
@Data
@RequiredArgsConstructor
@Table(name = "SUB_CATEGORY")
@TableGenerator(name = "SUB_CATEGORY_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "SUB_CATEGORY_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude="items,category,subCategoryMeasureMaps,subCategoryFitInfoMaps") 
public class SubCategory {

	@OneToMany(mappedBy = "subCategory", fetch = FetchType.LAZY)
	private List<Item> items = new ArrayList<Item>();
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SUB_CATEGORY_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;
	private String name;

	private String itemImage;

	private String clothImage;

	private String laundryYn;

	private String drycleaningYn;

	private String ironingYn;

	private String drymethodYn;

	private String bleachYn;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createDt;

	private String useYn;

	@OneToMany(mappedBy = "subCategory", fetch = FetchType.LAZY)
	private List<SubCategoryMeasureMap> subCategoryMeasureMaps = new ArrayList<SubCategoryMeasureMap>();

	@OneToMany(mappedBy = "subCategory", fetch = FetchType.LAZY)
	private List<SubCategoryFitInfoMap> subCategoryFitInfoMaps = new ArrayList<SubCategoryFitInfoMap>();

	@OneToMany(mappedBy = "subCategory", fetch = FetchType.LAZY)
	private List<SizeOption> sizeOptions = new ArrayList<SizeOption>();

	
	@Override
	public String toString() {
		return "SubCategory ]";
	}
	
}