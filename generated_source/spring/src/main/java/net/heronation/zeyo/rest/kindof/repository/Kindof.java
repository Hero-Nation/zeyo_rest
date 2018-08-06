package net.heronation.zeyo.rest.kindof.repository;
 
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

import net.heronation.zeyo.rest.item_material_map.repository.ItemMaterialMap;
import net.heronation.zeyo.rest.madein.repository.Madein;
import net.heronation.zeyo.rest.warranty.repository.Warranty;
import net.heronation.zeyo.rest.size_option.repository.SizeOption;
import net.heronation.zeyo.rest.cloth_color.repository.ClothColor;
import net.heronation.zeyo.rest.bbs.repository.Bbs;
 
@Entity 
@Data
@RequiredArgsConstructor
@Table(name="KINDOF")
@TableGenerator(name="KINDOF_ID_GENERATOR",table="JPA_ID_TABLE",pkColumnValue="KINDOF_ID",allocationSize=1)
@EntityListeners(AuditingEntityListener.class)
public class Kindof{

	//private @Version Long version;
	//private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;
 
        @OneToMany(mappedBy = "kindof" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<ItemMaterialMap>  itemMaterialMaps = new ArrayList<ItemMaterialMap>();
 
@OneToMany(mappedBy = "kindof" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<Madein>  madeins = new ArrayList<Madein>();
 
@OneToMany(mappedBy = "kindof" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<Warranty>  warrantys = new ArrayList<Warranty>();
 
@OneToMany(mappedBy = "kindof" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<SizeOption>  sizeOptions = new ArrayList<SizeOption>();
 
@OneToMany(mappedBy = "kindof" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<ClothColor>  clothColors = new ArrayList<ClothColor>();
 
@OneToMany(mappedBy = "kindof" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<Bbs>  bbss = new ArrayList<Bbs>();
 @Id 
@GeneratedValue(strategy = GenerationType.TABLE,generator="KINDOF_ID_GENERATOR")
@Column(name="ID")
private Long id;

private String ktype;




private String kvalue;




private String useYn;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(); 
                .append("  Kindof  Entity  ")
                .append("\n id  =  ").append(id)

.append("\n ktype  =  ").append(ktype)

.append("\n kvalue  =  ").append(kvalue)

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
		Kindof other = (Kindof) obj;
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