package net.heronation.zeyo.rest.repository.item_drycleaning_map;

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

import net.heronation.zeyo.rest.repository.item.Item;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "ITEM_DRYCLEANING_MAP")
@TableGenerator(name = "ITEM_DRYCLEANING_MAP_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "ITEM_DRYCLEANING_MAP_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = {"option"})
public class ItemDrycleaningMap {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ITEM_DRYCLEANING_MAP_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ITEM_ID")
	private Item item;
	private String drycan;

	private String storecan;

	private String detergent;

	private String useYn;

}