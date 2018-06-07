package net.heronation.zeyo.rest.repository.item_material_map;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.kindof.Kindof;
import net.heronation.zeyo.rest.repository.material.Material;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "ITEM_MATERIAL_MAP")
@TableGenerator(name = "ITEM_MATERIAL_MAP_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "ITEM_MATERIAL_MAP_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)

public class ItemMaterialMap {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ITEM_MATERIAL_MAP_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ITEM_ID")
	private Item item;
 
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MATERIAL_ID")
	private Material material;
	private String contain;

	private String useLocatoin;
	private String useYn;

	@Override
	public String toString() {
		return "ItemMaterialMap ]";
	}
	
}