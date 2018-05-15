package net.heronation.zeyo.rest.repository.material;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.heronation.zeyo.rest.repository.item_material_map.ItemMaterialMap;
import org.joda.time.DateTime;
@Entity
@Data
@RequiredArgsConstructor
@Table(name = "MATERIAL")
@TableGenerator(name = "MATERIAL_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "MATERIAL_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = {"option"})
public class Material {

	@OneToMany(mappedBy = "material", fetch = FetchType.LAZY)
	private List<ItemMaterialMap> itemMaterialMaps = new ArrayList<ItemMaterialMap>();
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MATERIAL_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	private String name;

	private String image;

	private String metaDesc;

	
	private DateTime createDt;

	private String useYn;

}