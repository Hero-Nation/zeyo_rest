package net.heronation.zeyo.rest.kindof.repository;

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
import net.heronation.zeyo.rest.bbs.repository.Bbs;
import net.heronation.zeyo.rest.cloth_color.repository.ClothColor;
import net.heronation.zeyo.rest.madein.repository.Madein;
import net.heronation.zeyo.rest.size_option.repository.SizeOption;
import net.heronation.zeyo.rest.warranty.repository.Warranty;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "KINDOF")
@TableGenerator(name = "KINDOF_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "KINDOF_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)

public class Kindof {

	@JsonBackReference(value = "madein_kindof")
	@JsonIgnore
	@OneToMany(mappedBy = "kindof", fetch = FetchType.LAZY)
	private List<Madein> madeins = new ArrayList<Madein>();

	@JsonBackReference(value = "warranty_kindof")
	@JsonIgnore
	@OneToMany(mappedBy = "kindof", fetch = FetchType.LAZY)
	private List<Warranty> warrantys = new ArrayList<Warranty>();

	@JsonBackReference(value = "sizeOption_kindof")
	@JsonIgnore
	@OneToMany(mappedBy = "kindof", fetch = FetchType.LAZY)
	private List<SizeOption> sizeOptions = new ArrayList<SizeOption>();

	@JsonBackReference(value = "clothColor_kindof")
	@JsonIgnore
	@OneToMany(mappedBy = "kindof", fetch = FetchType.LAZY)
	private List<ClothColor> clothColors = new ArrayList<ClothColor>();

	@JsonBackReference(value = "bbss_kindof")
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kindof other = (Kindof) obj;
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