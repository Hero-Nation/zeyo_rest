package net.heronation.zeyo.rest.repository.item_size_option_map;

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
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "ITEM_SIZE_OPTION_MAP")
@TableGenerator(name = "ITEM_SIZE_OPTION_MAP_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "ITEM_SIZE_OPTION_MAP_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)

public class ItemSizeOptionMap {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ITEM_SIZE_OPTION_MAP_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ITEM_ID")
	private Item item;

	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SIZE_OPTION_ID")
	private SizeOption sizeOption;
	private String optionValue;

	private String useYn;
	
	@Override
	public String toString() {
		return "ItemSizeOptionMap ]";
	}

}