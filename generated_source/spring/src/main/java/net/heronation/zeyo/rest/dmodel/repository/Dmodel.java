package net.heronation.zeyo.rest.dmodel.repository;
 
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

import net.heronation.zeyo.rest.sub_category.repository.SubCategory;
import net.heronation.zeyo.rest.dmodel_measure_map.repository.DmodelMeasureMap;
import net.heronation.zeyo.rest.dmodel_ratio.repository.DmodelRatio;
 
@Entity 
@Data
@RequiredArgsConstructor
@Table(name="DMODEL")
@TableGenerator(name="DMODEL_ID_GENERATOR",table="JPA_ID_TABLE",pkColumnValue="DMODEL_ID",allocationSize=1)
@EntityListeners(AuditingEntityListener.class)
public class Dmodel{

	//private @Version Long version;
	//private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;
 
        @OneToMany(mappedBy = "dmodel" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<SubCategory>  subCategorys = new ArrayList<SubCategory>();
 @Id 
@GeneratedValue(strategy = GenerationType.TABLE,generator="DMODEL_ID_GENERATOR")
@Column(name="ID")
private Long id;

private String title;




private String controller;




private String svgdata;




	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createDt;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime updateDt;

private String useYn;





@OneToMany(mappedBy = "dmodel" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<DmodelMeasureMap>  dmodelMeasureMaps = new ArrayList<DmodelMeasureMap>();
 
@OneToMany(mappedBy = "dmodel" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<DmodelRatio>  dmodelRatios = new ArrayList<DmodelRatio>();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(); 
                .append("  Dmodel  Entity  ")
                .append("\n id  =  ").append(id)

.append("\n title  =  ").append(title)

.append("\n controller  =  ").append(controller)

.append("\n svgdata  =  ").append(svgdata)

.append("\n create_dt  =  ").append(create_dt)


.append("\n update_dt  =  ").append(update_dt)


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
		Dmodel other = (Dmodel) obj;
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