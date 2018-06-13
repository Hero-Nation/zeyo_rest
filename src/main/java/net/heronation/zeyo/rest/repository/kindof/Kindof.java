package net.heronation.zeyo.rest.repository.kindof;

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

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.heronation.zeyo.rest.repository.bbs.Bbs;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColor;
import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;
import net.heronation.zeyo.rest.repository.warranty.Warranty;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "KINDOF")
@TableGenerator(name = "KINDOF_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "KINDOF_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)

public class Kindof {

	@JsonBackReference(value="madein_kindof")
	@JsonIgnore
	@OneToMany(mappedBy = "kindof", fetch = FetchType.LAZY)
	private List<Madein> madeins = new ArrayList<Madein>();
	
	
	
	@JsonBackReference(value="warranty_kindof")
	@JsonIgnore
	@OneToMany(mappedBy = "kindof", fetch = FetchType.LAZY)
	private List<Warranty> warrantys = new ArrayList<Warranty>();
	
	
	
	@JsonBackReference(value="sizeOption_kindof")
	@JsonIgnore
	@OneToMany(mappedBy = "kindof", fetch = FetchType.LAZY)
	private List<SizeOption> sizeOptions = new ArrayList<SizeOption>();
	
	
	@JsonBackReference(value="clothColor_kindof")
	@JsonIgnore
	@OneToMany(mappedBy = "kindof", fetch = FetchType.LAZY)
	private List<ClothColor> clothColors = new ArrayList<ClothColor>();
	
	
	
	@JsonBackReference(value="bbss_kindof")
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
	
	
	@Override
	public String toString() {
		return "Kindof ]";
	}

}