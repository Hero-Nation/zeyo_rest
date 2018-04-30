package net.heronation.zeyo.rest.repository.size_option;
 
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

import net.heronation.zeyo.rest.repository.item_size_option_map.ItemSizeOptionMap;
import net.heronation.zeyo.rest.repository.category.Category;import net.heronation.zeyo.rest.repository.sub_category.SubCategory;import net.heronation.zeyo.rest.repository.kindof.Kindof;
 
@Entity 
@Data
@RequiredArgsConstructor
@Table(name="SIZE_OPTION")
@TableGenerator(name="SIZE_OPTION_ID_GENERATOR",table="JPA_ID_TABLE",pkColumnValue="SIZE_OPTION_ID",allocationSize=1)
@EntityListeners(AuditingEntityListener.class)
public class SizeOption{

	private @Version Long version;
	private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;
 
        @OneToMany(mappedBy = "sizeOption" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<ItemSizeOptionMap>  itemSizeOptionMaps = new ArrayList<ItemSizeOptionMap>();
 @Id 
@GeneratedValue(strategy = GenerationType.TABLE,generator="SIZE_OPTION_ID_GENERATOR")
@Column(name="ID")
private Long id;

 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="CATEGORY_ID")
private Category category;
 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="SUB_CATEGORY_ID")
private SubCategory subCategory;
 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="KINDOF_ID")
private Kindof kindof;
private String name;




private String createDt;




private String useYn;
    
}