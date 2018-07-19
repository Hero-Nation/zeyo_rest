package net.heronation.zeyo.rest.item.repository;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.heronation.zeyo.rest.brand.repository.Brand;
import net.heronation.zeyo.rest.category.repository.Category;
import net.heronation.zeyo.rest.item_bleach_map.repository.ItemBleachMap;
import net.heronation.zeyo.rest.item_cloth_color_map.repository.ItemClothColorMap;
import net.heronation.zeyo.rest.item_drycleaning_map.repository.ItemDrycleaningMap;
import net.heronation.zeyo.rest.item_drymethod_map.repository.ItemDrymethodMap;
import net.heronation.zeyo.rest.item_fit_info_option_map.repository.ItemFitInfoOptionMap;
import net.heronation.zeyo.rest.item_ironing_map.repository.ItemIroningMap;
import net.heronation.zeyo.rest.item_laundry_map.repository.ItemLaundryMap;
import net.heronation.zeyo.rest.item_material_map.repository.ItemMaterialMap;
import net.heronation.zeyo.rest.item_shopmall_map.repository.ItemShopmallMap;
import net.heronation.zeyo.rest.item_size_option_map.repository.ItemSizeOptionMap;
import net.heronation.zeyo.rest.madein.repository.Madein;
import net.heronation.zeyo.rest.member.repository.Member;
import net.heronation.zeyo.rest.size_table.repository.SizeTable;
import net.heronation.zeyo.rest.sub_category.repository.SubCategory;
import net.heronation.zeyo.rest.warranty.repository.Warranty;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "ITEM")
@TableGenerator(name = "ITEM_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "ITEM_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)
 
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ITEM_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BRAND_ID")
	private Brand brand;
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SUB_CATEGORY_ID")
	private SubCategory subCategory;

	private String shopProductId;
	
	private String imageMode;

	private String image;

	private String shop_image;
	
	private String sizeMeasureMode;

	private String sizeMeasureImage;

	private String name;

	private String code;

	private int price;

	private String madeinBuilder;
	
	
	@JsonManagedReference(value="madein_items")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MADEIN_ID")
	private Madein madein;
	
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime madeinDate;
	
	@JsonManagedReference(value="warranty_items")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "WARRANTY_ID")
	private Warranty warranty;


	
	private String laundryYn;

	private String drycleaningYn;

	private String ironingYn;

	private String drymethodYn;

	private String bleachYn;

	private String linkYn;

	private String sizeTableYn;
	
	// 자동으로 import시 예외가 발생하여 모든 정보를 다 수집하지 못한경우 
	private String importHasErrorYn;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createDt;

	private String useYn;
	
	@JsonBackReference
	@OneToOne(mappedBy = "item")
	private ItemLaundryMap itemLaundryMap;
	
	@JsonBackReference
	@OneToOne(mappedBy = "item")
	private ItemDrycleaningMap itemDrycleaningMap;
	
	@JsonBackReference
	@OneToOne(mappedBy = "item")
	private ItemIroningMap itemIroningMap;
	
	@JsonBackReference
	@OneToOne(mappedBy = "item")
	private ItemDrymethodMap itemDrymethodMap;
	
	@JsonBackReference
	@OneToOne(mappedBy = "item")
	private ItemBleachMap itemBleachMap;
	
	
	@JsonBackReference
	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
	private List<ItemShopmallMap> itemShopmallMaps = new ArrayList<ItemShopmallMap>();

	@JsonBackReference
	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
	private List<ItemMaterialMap> itemMaterialMaps = new ArrayList<ItemMaterialMap>();

	@JsonBackReference
	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
	private List<ItemSizeOptionMap> itemSizeOptionMaps = new ArrayList<ItemSizeOptionMap>();

	@JsonBackReference
	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
	private List<ItemClothColorMap> itemClothColorMaps = new ArrayList<ItemClothColorMap>();

	@JsonBackReference
	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
	private List<ItemFitInfoOptionMap> itemFitInfoOptionMaps = new ArrayList<ItemFitInfoOptionMap>();

	@JsonBackReference
	@OneToOne(mappedBy = "item" )
	private SizeTable sizeTable;

	@Override
	public String toString() {
		return "Item ]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	 

}