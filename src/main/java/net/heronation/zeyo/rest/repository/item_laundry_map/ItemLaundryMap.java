package net.heronation.zeyo.rest.repository.item_laundry_map;

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

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "ITEM_LAUNDRY_MAP")
@TableGenerator(name = "ITEM_LAUNDRY_MAP_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "ITEM_LAUNDRY_MAP_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)

public class ItemLaundryMap {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ITEM_LAUNDRY_MAP_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ITEM_ID")
	private Item item;
	private String water;

	private String machine;

	private String hand;

	private String waterTemp;

	private String intensity;

	private String detergent;

	private String useYn;
	
	@Override
	public String toString() {
		return "ItemLaundryMap ]";
	}


}