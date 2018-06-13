package net.heronation.zeyo.rest.repository.size_option;

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
import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.item_size_option_map.ItemSizeOptionMap;
import net.heronation.zeyo.rest.repository.kindof.Kindof;
import net.heronation.zeyo.rest.repository.sub_category.SubCategory;
@Entity
@Data
@RequiredArgsConstructor
@Table(name = "SIZE_OPTION")
@TableGenerator(name = "SIZE_OPTION_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "SIZE_OPTION_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)

public class SizeOption {
	@JsonBackReference(value="sizeOption_kindof")
	@OneToMany(mappedBy = "sizeOption", fetch = FetchType.LAZY)
	private List<ItemSizeOptionMap> itemSizeOptionMaps = new ArrayList<ItemSizeOptionMap>();
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SIZE_OPTION_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;

	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SUB_CATEGORY_ID")
	private SubCategory subCategory;

	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "KINDOF_ID")
	private Kindof kindof;
	private String name;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createDt;

	private String useYn;

	
	@Override
	public String toString() {
		return "SizeOption ]";
	}

}