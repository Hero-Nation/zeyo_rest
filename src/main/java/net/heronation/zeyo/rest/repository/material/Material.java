package net.heronation.zeyo.rest.repository.material;

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
import net.heronation.zeyo.rest.repository.item_material_map.ItemMaterialMap;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

}