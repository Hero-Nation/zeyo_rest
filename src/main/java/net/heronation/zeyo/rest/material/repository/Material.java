package net.heronation.zeyo.rest.material.repository;

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
import net.heronation.zeyo.rest.item_material_map.repository.ItemMaterialMap;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "MATERIAL")
@TableGenerator(name = "MATERIAL_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "MATERIAL_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)

public class Material {
	@JsonBackReference
	@OneToMany(mappedBy = "material", fetch = FetchType.LAZY)
	private List<ItemMaterialMap> itemMaterialMaps = new ArrayList<ItemMaterialMap>();
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MATERIAL_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	private String name;

	private String image;

	private String metaDesc;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createDt;

	private String useYn;

	@Override
	public String toString() {
		return "Material ]";
	}

	 

}