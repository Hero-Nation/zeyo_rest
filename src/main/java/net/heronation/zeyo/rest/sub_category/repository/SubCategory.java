package net.heronation.zeyo.rest.sub_category.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.heronation.zeyo.rest.category.repository.Category;
import net.heronation.zeyo.rest.item.repository.Item;
import net.heronation.zeyo.rest.size_option.repository.SizeOption;
import net.heronation.zeyo.rest.sub_category_fit_info_map.repository.SubCategoryFitInfoMap;
import net.heronation.zeyo.rest.sub_category_measure_map.repository.SubCategoryMeasureMap;
@Entity
@Data
@RequiredArgsConstructor
@Table(name = "SUB_CATEGORY")
@TableGenerator(name = "SUB_CATEGORY_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "SUB_CATEGORY_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)
 
public class SubCategory {

	@JsonBackReference
	@OneToMany(mappedBy = "subCategory", fetch = FetchType.LAZY)
	private List<Item> items = new ArrayList<Item>();
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SUB_CATEGORY_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;
	@JsonManagedReference
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
	
	private Long parentId;

	@JsonBackReference
	@OneToMany(mappedBy = "subCategory", fetch = FetchType.LAZY)
	private List<SubCategoryMeasureMap> subCategoryMeasureMaps = new ArrayList<SubCategoryMeasureMap>();

	@JsonBackReference
	@OneToMany(mappedBy = "subCategory", fetch = FetchType.LAZY)
	private List<SubCategoryFitInfoMap> subCategoryFitInfoMaps = new ArrayList<SubCategoryFitInfoMap>();

	@JsonBackReference
	@OneToMany(mappedBy = "subCategory", fetch = FetchType.LAZY)
	private List<SizeOption> sizeOptions = new ArrayList<SizeOption>();

	
	@Override
	public String toString() {
		return "SubCategory ]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubCategory other = (SubCategory) obj;
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