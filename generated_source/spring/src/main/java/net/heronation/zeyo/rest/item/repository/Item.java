package net.heronation.zeyo.rest.item.repository;
 
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

import net.heronation.zeyo.rest.member.repository.Member;import net.heronation.zeyo.rest.brand.repository.Brand;import net.heronation.zeyo.rest.category.repository.Category;import net.heronation.zeyo.rest.sub_category.repository.SubCategory;import net.heronation.zeyo.rest.madein.repository.Madein;import net.heronation.zeyo.rest.warranty.repository.Warranty;import net.heronation.zeyo.rest.item_shopmall_map.repository.ItemShopmallMap;
import net.heronation.zeyo.rest.item_material_map.repository.ItemMaterialMap;
import net.heronation.zeyo.rest.item_size_option_map.repository.ItemSizeOptionMap;
import net.heronation.zeyo.rest.item_cloth_color_map.repository.ItemClothColorMap;
import net.heronation.zeyo.rest.item_laundry_map.repository.ItemLaundryMap;
import net.heronation.zeyo.rest.item_drycleaning_map.repository.ItemDrycleaningMap;
import net.heronation.zeyo.rest.item_ironing_map.repository.ItemIroningMap;
import net.heronation.zeyo.rest.item_drymethod_map.repository.ItemDrymethodMap;
import net.heronation.zeyo.rest.item_bleach_map.repository.ItemBleachMap;
 
@Entity 
@Data
@RequiredArgsConstructor
@Table(name="ITEM")
@TableGenerator(name="ITEM_ID_GENERATOR",table="JPA_ID_TABLE",pkColumnValue="ITEM_ID",allocationSize=1)
@EntityListeners(AuditingEntityListener.class)
public class Item{

	//private @Version Long version;
	//private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;
 
        @Id 
@GeneratedValue(strategy = GenerationType.TABLE,generator="ITEM_ID_GENERATOR")
@Column(name="ID")
private Long id;

 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="MEMBER_ID")
private Member member;
 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="BRAND_ID")
private Brand brand;
 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="CATEGORY_ID")
private Category category;
 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="SUB_CATEGORY_ID")
private SubCategory subCategory;
private String imageMode;




private String image;




private String sizeMeasureMode;




private String sizeMeasureImage;




private String name;




private String code;




private String price;




private String madeinBuilder;




 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="MADEIN_ID")
private Madein madein;
private String madeinDate;




 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="WARRANTY_ID")
private Warranty warranty;
private String laundryYn;




private String drycleaningYn;




private String ironingYn;




private String drymethodYn;




private String bleachYn;




private String sizeLinkYn;




private String createDt;




private String useYn;





@OneToMany(mappedBy = "item" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<ItemShopmallMap>  itemShopmallMaps = new ArrayList<ItemShopmallMap>();
 
@OneToMany(mappedBy = "item" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<ItemMaterialMap>  itemMaterialMaps = new ArrayList<ItemMaterialMap>();
 
@OneToMany(mappedBy = "item" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<ItemSizeOptionMap>  itemSizeOptionMaps = new ArrayList<ItemSizeOptionMap>();
 
@OneToMany(mappedBy = "item" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<ItemClothColorMap>  itemClothColorMaps = new ArrayList<ItemClothColorMap>();
 
@OneToMany(mappedBy = "item" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<ItemLaundryMap>  itemLaundryMaps = new ArrayList<ItemLaundryMap>();
 
@OneToMany(mappedBy = "item" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<ItemDrycleaningMap>  itemDrycleaningMaps = new ArrayList<ItemDrycleaningMap>();
 
@OneToMany(mappedBy = "item" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<ItemIroningMap>  itemIroningMaps = new ArrayList<ItemIroningMap>();
 
@OneToMany(mappedBy = "item" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<ItemDrymethodMap>  itemDrymethodMaps = new ArrayList<ItemDrymethodMap>();
 
@OneToMany(mappedBy = "item" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<ItemBleachMap>  itemBleachMaps = new ArrayList<ItemBleachMap>();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(); 
                .append("  Item  Entity  ")
                .append("\n id  =  ").append(id)

.append("\n image_mode  =  ").append(image_mode)

.append("\n image  =  ").append(image)

.append("\n size_measure_mode  =  ").append(size_measure_mode)

.append("\n size_measure_image  =  ").append(size_measure_image)

.append("\n name  =  ").append(name)

.append("\n code  =  ").append(code)

.append("\n price  =  ").append(price)

.append("\n madein_builder  =  ").append(madein_builder)

.append("\n madein_date  =  ").append(madein_date)

.append("\n laundry_yn  =  ").append(laundry_yn)

.append("\n drycleaning_yn  =  ").append(drycleaning_yn)

.append("\n ironing_yn  =  ").append(ironing_yn)

.append("\n drymethod_yn  =  ").append(drymethod_yn)

.append("\n bleach_yn  =  ").append(bleach_yn)

.append("\n size_link_yn  =  ").append(size_link_yn)

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
		Item other = (Item) obj;
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