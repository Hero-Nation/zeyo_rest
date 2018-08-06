package net.heronation.zeyo.rest.sub_category.repository;
 
import javax.persistence.*; 
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.heronation.zeyo.rest.item.repository.Item;
import net.heronation.zeyo.rest.category.repository.Category;import net.heronation.zeyo.rest.dmodel.repository.Dmodel;import net.heronation.zeyo.rest.sub_category_measure_map.repository.SubCategoryMeasureMap;
import net.heronation.zeyo.rest.sub_category_fit_info_map.repository.SubCategoryFitInfoMap;
import net.heronation.zeyo.rest.size_option.repository.SizeOption;
import net.heronation.zeyo.rest.v2_rule.repository.V2Rule;
import net.heronation.zeyo.rest.v2_rule.repository.V2Rule;
 
@Entity 
@Data
@RequiredArgsConstructor
@Table(name="SUB_CATEGORY")
@TableGenerator(name="SUB_CATEGORY_ID_GENERATOR",table="JPA_ID_TABLE",pkColumnValue="SUB_CATEGORY_ID",allocationSize=1)
@EntityListeners(AuditingEntityListener.class)
public class SubCategory{

	//private @Version Long version;
	//private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;
 
        @OneToMany(mappedBy = "subCategory" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<Item>  items = new ArrayList<Item>();
 @Id 
@GeneratedValue(strategy = GenerationType.TABLE,generator="SUB_CATEGORY_ID_GENERATOR")
@Column(name="ID")
private Long id;

 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="CATEGORY_ID")
private Category category;
 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="DMODEL_ID")
private Dmodel dmodel;
private String name;




private String itemImage;




private String clothImage;




private String laundryYn;




private String drycleaningYn;




private String ironingYn;




private String drymethodYn;




private String bleachYn;




private String createDt;




private String useYn;





@OneToMany(mappedBy = "subCategory" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<SubCategoryMeasureMap>  subCategoryMeasureMaps = new ArrayList<SubCategoryMeasureMap>();
 
@OneToMany(mappedBy = "subCategory" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<SubCategoryFitInfoMap>  subCategoryFitInfoMaps = new ArrayList<SubCategoryFitInfoMap>();
 
@OneToMany(mappedBy = "subCategory" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<SizeOption>  sizeOptions = new ArrayList<SizeOption>();
 
@OneToMany(mappedBy = "subCategory" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<V2Rule>  v2Rules = new ArrayList<V2Rule>();
 
@OneToMany(mappedBy = "subCategory" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<V2Rule>  v2Rules = new ArrayList<V2Rule>();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(); 
                .append("  SubCategory  Entity  ")
                .append("\n id  =  ").append(id)

.append("\n name  =  ").append(name)

.append("\n item_image  =  ").append(item_image)

.append("\n cloth_image  =  ").append(cloth_image)

.append("\n laundry_yn  =  ").append(laundry_yn)

.append("\n drycleaning_yn  =  ").append(drycleaning_yn)

.append("\n ironing_yn  =  ").append(ironing_yn)

.append("\n drymethod_yn  =  ").append(drymethod_yn)

.append("\n bleach_yn  =  ").append(bleach_yn)

.append("\n create_dt  =  ").append(create_dt)

.append("\n use_yn  =  ").append(use_yn)
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
		SubCategory other = (SubCategory) obj;
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