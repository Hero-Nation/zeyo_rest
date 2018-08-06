package net.heronation.zeyo.rest.sub_category_fit_info_map.repository;
 
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

import net.heronation.zeyo.rest.sub_category.repository.SubCategory;import net.heronation.zeyo.rest.fit_info.repository.FitInfo;
 
@Entity 
@Data
@RequiredArgsConstructor
@Table(name="SUB_CATEGORY_FIT_INFO_MAP")
@TableGenerator(name="SUB_CATEGORY_FIT_INFO_MAP_ID_GENERATOR",table="JPA_ID_TABLE",pkColumnValue="SUB_CATEGORY_FIT_INFO_MAP_ID",allocationSize=1)
@EntityListeners(AuditingEntityListener.class)
public class SubCategoryFitInfoMap{

	//private @Version Long version;
	//private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;
 
        @Id 
@GeneratedValue(strategy = GenerationType.TABLE,generator="SUB_CATEGORY_FIT_INFO_MAP_ID_GENERATOR")
@Column(name="ID")
private Long id;

 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="SUB_CATEGORY_ID")
private SubCategory subCategory;
 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="FIT_INFO_ID")
private FitInfo fitInfo;
private String useYn;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(); 
                .append("  SubCategoryFitInfoMap  Entity  ")
                .append("\n id  =  ").append(id)

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
		SubCategoryFitInfoMap other = (SubCategoryFitInfoMap) obj;
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