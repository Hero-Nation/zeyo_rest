package net.heronation.zeyo.rest.repository.category;
 
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

import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.sub_category.SubCategory;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;
 
@Entity 
@Data
@RequiredArgsConstructor
@Table(name="CATEGORY")
@TableGenerator(name="CATEGORY_ID_GENERATOR",table="JPA_ID_TABLE",pkColumnValue="CATEGORY_ID",allocationSize=1)
@EntityListeners(AuditingEntityListener.class)
public class Category{

	private @Version Long version;
	private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;
 
        @OneToMany(mappedBy = "category" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<Item>  items = new ArrayList<Item>();
 @Id 
@GeneratedValue(strategy = GenerationType.TABLE,generator="CATEGORY_ID_GENERATOR")
@Column(name="ID")
private Long id;

private String name;




private String createDt;




private String useYn;





@OneToMany(mappedBy = "category" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<SubCategory>  subCategorys = new ArrayList<SubCategory>();
 
@OneToMany(mappedBy = "category" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<SizeOption>  sizeOptions = new ArrayList<SizeOption>();
    
}