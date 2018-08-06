package net.heronation.zeyo.rest.category.repository;
 
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
import net.heronation.zeyo.rest.sub_category.repository.SubCategory;
import net.heronation.zeyo.rest.size_option.repository.SizeOption;
 
@Entity 
@Data
@RequiredArgsConstructor
@Table(name="CATEGORY")
@TableGenerator(name="CATEGORY_ID_GENERATOR",table="JPA_ID_TABLE",pkColumnValue="CATEGORY_ID",allocationSize=1)
@EntityListeners(AuditingEntityListener.class)
public class Category{

	//private @Version Long version;
	//private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;
 
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(); 
                .append("  Category  Entity  ")
                .append("\n id  =  ").append(id)

.append("\n name  =  ").append(name)

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
		Category other = (Category) obj;
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