package net.heronation.zeyo.rest.repository.sub_category_fit_info_map;
 
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

import net.heronation.zeyo.rest.repository.sub_category.SubCategory;import net.heronation.zeyo.rest.repository.fit_info.FitInfo;
 
@Entity 
@Data
@RequiredArgsConstructor
@Table(name="SUB_CATEGORY_FIT_INFO_MAP")
@TableGenerator(name="SUB_CATEGORY_FIT_INFO_MAP_ID_GENERATOR",table="JPA_ID_TABLE",pkColumnValue="SUB_CATEGORY_FIT_INFO_MAP_ID",allocationSize=1)
@EntityListeners(AuditingEntityListener.class)
public class SubCategoryFitInfoMap{

	
	
 
        @Id 
@GeneratedValue(strategy = GenerationType.TABLE,generator="SUB_CATEGORY_FIT_INFO_MAP_ID_GENERATOR")
@Column(name="ID")
private Long id;

 
@ManyToOne(fetch=FetchType.EAGER ) 
@JoinColumn(name="SUB_CATEGORY_ID")
private SubCategory subCategory;
 
@ManyToOne(fetch=FetchType.EAGER ) 
@JoinColumn(name="FIT_INFO_ID")
private FitInfo fitInfo;
private String useYn;
    
}