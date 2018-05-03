package net.heronation.zeyo.rest.repository.material;
 
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

import net.heronation.zeyo.rest.repository.item_material_map.ItemMaterialMap;
 
@Entity 
@Data
@RequiredArgsConstructor
@Table(name="MATERIAL")
@TableGenerator(name="MATERIAL_ID_GENERATOR",table="JPA_ID_TABLE",pkColumnValue="MATERIAL_ID",allocationSize=1)
@EntityListeners(AuditingEntityListener.class)
public class Material{

	private @Version Long version;
	private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;
 
        @OneToMany(mappedBy = "material" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<ItemMaterialMap>  itemMaterialMaps = new ArrayList<ItemMaterialMap>();
 @Id 
@GeneratedValue(strategy = GenerationType.TABLE,generator="MATERIAL_ID_GENERATOR")
@Column(name="ID")
private Long id;

private String name;




private String image;




private String metaDesc;





@Temporal(TemporalType.TIMESTAMP)
private Date   createDt;



private String useYn;
    
}