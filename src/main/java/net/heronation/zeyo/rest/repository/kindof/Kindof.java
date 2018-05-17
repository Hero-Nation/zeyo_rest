package net.heronation.zeyo.rest.repository.kindof;

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

import net.heronation.zeyo.rest.repository.item_material_map.ItemMaterialMap;
import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.warranty.Warranty;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColor;
import net.heronation.zeyo.rest.repository.bbs.Bbs;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "KINDOF")
@TableGenerator(name = "KINDOF_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "KINDOF_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)

public class Kindof {

	@JsonIgnore
	@OneToMany(mappedBy = "kindof", fetch = FetchType.LAZY)
	private List<ItemMaterialMap> itemMaterialMaps = new ArrayList<ItemMaterialMap>();

	@JsonIgnore
	@OneToMany(mappedBy = "kindof", fetch = FetchType.LAZY)
	private List<Madein> madeins = new ArrayList<Madein>();

	@JsonIgnore
	@OneToMany(mappedBy = "kindof", fetch = FetchType.LAZY)
	private List<Warranty> warrantys = new ArrayList<Warranty>();

	@JsonIgnore
	@OneToMany(mappedBy = "kindof", fetch = FetchType.LAZY)
	private List<SizeOption> sizeOptions = new ArrayList<SizeOption>();

	@JsonIgnore
	@OneToMany(mappedBy = "kindof", fetch = FetchType.LAZY)
	private List<ClothColor> clothColors = new ArrayList<ClothColor>();

	@JsonIgnore
	@OneToMany(mappedBy = "kindof", fetch = FetchType.LAZY)
	private List<Bbs> bbss = new ArrayList<Bbs>();
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "KINDOF_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	private String ktype;

	private String kvalue;
	@JsonIgnore
	private String useYn;

}