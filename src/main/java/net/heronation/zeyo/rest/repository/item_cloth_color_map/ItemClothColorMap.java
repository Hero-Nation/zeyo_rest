package net.heronation.zeyo.rest.repository.item_cloth_color_map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColor;
import net.heronation.zeyo.rest.repository.item.Item;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "ITEM_CLOTH_COLOR_MAP")
@TableGenerator(name = "ITEM_CLOTH_COLOR_MAP_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "ITEM_CLOTH_COLOR_MAP_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)

public class ItemClothColorMap {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ITEM_CLOTH_COLOR_MAP_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ITEM_ID")
	private Item item;

	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CLOTH_COLOR_ID")
	private ClothColor clothColor;
	private String useYn;
	private String optionValue;
	
	
	@Override
	public String toString() {
		return "ItemClothColorMap ]";
	}


}