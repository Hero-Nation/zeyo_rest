package net.heronation.zeyo.rest.repository.category;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;
import net.heronation.zeyo.rest.repository.sub_category.SubCategory;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "CATEGORY")
@TableGenerator(name = "CATEGORY_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "CATEGORY_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class) 
public class Category {

	@JsonBackReference
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<Item> items = new ArrayList<Item>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CATEGORY_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	private String name;
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createDt;

	private String useYn;
	
	private Long parent_id;
	
	@JsonBackReference
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<SubCategory> subCategorys = new ArrayList<SubCategory>();
	
	@JsonBackReference
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<SizeOption> sizeOptions = new ArrayList<SizeOption>();

	@Override
	public String toString() {
		return "Category ]";
	}
	
 

}