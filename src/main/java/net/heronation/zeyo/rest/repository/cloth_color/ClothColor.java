package net.heronation.zeyo.rest.repository.cloth_color;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.heronation.zeyo.rest.repository.item_cloth_color_map.ItemClothColorMap;
import net.heronation.zeyo.rest.repository.kindof.Kindof;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "CLOTH_COLOR")
@TableGenerator(name = "CLOTH_COLOR_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "CLOTH_COLOR_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = { "option" })
public class ClothColor {

	@OneToMany(mappedBy = "clothColor", fetch = FetchType.LAZY)
	private List<ItemClothColorMap> itemClothColorMaps = new ArrayList<ItemClothColorMap>();
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CLOTH_COLOR_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "KINDOF_ID")
	private Kindof kindof;
	private String name;
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createDt;

	private String useYn;

}