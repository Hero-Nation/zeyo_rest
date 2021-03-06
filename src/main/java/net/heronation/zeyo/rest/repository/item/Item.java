package net.heronation.zeyo.rest.repository.item;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.size_table.SizeTable;
import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.sub_category.SubCategory;
import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.warranty.Warranty;
import net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMap;
import net.heronation.zeyo.rest.repository.item_material_map.ItemMaterialMap;
import net.heronation.zeyo.rest.repository.item_size_option_map.ItemSizeOptionMap;
import net.heronation.zeyo.rest.repository.item_cloth_color_map.ItemClothColorMap;
import net.heronation.zeyo.rest.repository.item_laundry_map.ItemLaundryMap;
import net.heronation.zeyo.rest.repository.item_drycleaning_map.ItemDrycleaningMap;
import net.heronation.zeyo.rest.repository.item_ironing_map.ItemIroningMap;
import net.heronation.zeyo.rest.repository.item_drymethod_map.ItemDrymethodMap;
import net.heronation.zeyo.rest.repository.item_fit_info_option_map.ItemFitInfoOptionMap;
import net.heronation.zeyo.rest.repository.item_bleach_map.ItemBleachMap;

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
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BRAND_ID")
	private Brand brand;
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SUB_CATEGORY_ID")
	private SubCategory subCategory;

	private String imageMode;

	private String image;

	private String sizeMeasureMode;

	private String sizeMeasureImage;

	private String name;

	private String code;

	private int price;

	private String madeinBuilder;
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MADEIN_ID")
	private Madein madein;
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime madeinDate;
	@JsonBackReference
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

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createDt;

	private String useYn;

	@JsonManagedReference
	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
	private List<ItemShopmallMap> itemShopmallMaps = new ArrayList<ItemShopmallMap>();

	@JsonManagedReference
	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
	private List<ItemMaterialMap> itemMaterialMaps = new ArrayList<ItemMaterialMap>();

	@JsonManagedReference
	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
	private List<ItemSizeOptionMap> itemSizeOptionMaps = new ArrayList<ItemSizeOptionMap>();

	@JsonManagedReference
	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
	private List<ItemClothColorMap> itemClothColorMaps = new ArrayList<ItemClothColorMap>();

	@OneToOne(mappedBy = "item")
	private ItemLaundryMap itemLaundryMap;
	@OneToOne(mappedBy = "item")
	private ItemDrycleaningMap itemDrycleaningMap;
	@OneToOne(mappedBy = "item")
	private ItemIroningMap itemIroningMap;
	@OneToOne(mappedBy = "item")
	private ItemDrymethodMap itemDrymethodMap;
	@OneToOne(mappedBy = "item")
	private ItemBleachMap itemBleachMap;

	@JsonManagedReference
	@OneToMany(mappedBy = "item")
	private List<ItemFitInfoOptionMap> itemFitInfoOptionMaps = new ArrayList<ItemFitInfoOptionMap>();

	@JsonManagedReference
	@OneToMany(mappedBy = "item")
	private List<SizeTable> sizeTables = new ArrayList<SizeTable>();

	@Override
	public String toString() {
		return "Item ]";
	}

}