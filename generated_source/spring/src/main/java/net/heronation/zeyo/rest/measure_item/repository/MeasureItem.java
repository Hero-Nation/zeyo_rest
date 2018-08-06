package net.heronation.zeyo.rest.measure_item.repository;
 
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

import net.heronation.zeyo.rest.sub_category_measure_map.repository.SubCategoryMeasureMap;
import net.heronation.zeyo.rest.dmodel_measure_map.repository.DmodelMeasureMap;
 
@Entity 
@Data
@RequiredArgsConstructor
@Table(name="MEASURE_ITEM")
@TableGenerator(name="MEASURE_ITEM_ID_GENERATOR",table="JPA_ID_TABLE",pkColumnValue="MEASURE_ITEM_ID",allocationSize=1)
@EntityListeners(AuditingEntityListener.class)
public class MeasureItem{

	//private @Version Long version;
	//private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;
 
        @OneToMany(mappedBy = "measureItem" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<SubCategoryMeasureMap>  subCategoryMeasureMaps = new ArrayList<SubCategoryMeasureMap>();
 @Id 
@GeneratedValue(strategy = GenerationType.TABLE,generator="MEASURE_ITEM_ID_GENERATOR")
@Column(name="ID")
private Long id;

private String name;




private String metaDesc;




private String createDt;




private String useYn;





@OneToMany(mappedBy = "measureItem" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<DmodelMeasureMap>  dmodelMeasureMaps = new ArrayList<DmodelMeasureMap>();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(); 
                .append("  MeasureItem  Entity  ")
                .append("\n id  =  ").append(id)

.append("\n name  =  ").append(name)

.append("\n meta_desc  =  ").append(meta_desc)

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
		MeasureItem other = (MeasureItem) obj;
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