package net.heronation.zeyo.rest.material.repository;
 
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
 
@Entity 
@Data
@RequiredArgsConstructor
@Table(name="MATERIAL")
@TableGenerator(name="MATERIAL_ID_GENERATOR",table="JPA_ID_TABLE",pkColumnValue="MATERIAL_ID",allocationSize=1)
@EntityListeners(AuditingEntityListener.class)
public class Material{

	//private @Version Long version;
	//private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;
 
        @OneToMany(mappedBy = "material" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<ItemMaterialMap>  itemMaterialMaps = new ArrayList<ItemMaterialMap>();
 @Id 
@GeneratedValue(strategy = GenerationType.TABLE,generator="MATERIAL_ID_GENERATOR")
@Column(name="ID")
private Long id;

private String name;




private String image;




private String metaDesc;




private String createDt;




private String useYn;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(); 
                .append("  Material  Entity  ")
                .append("\n id  =  ").append(id)

.append("\n name  =  ").append(name)

.append("\n image  =  ").append(image)

.append("\n meta_desc  =  ").append(meta_desc)

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
		Material other = (Material) obj;
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