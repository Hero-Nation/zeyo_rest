package net.heronation.zeyo.rest.kindof.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import javax.persistence.Transient;

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

	@Transient
	@JsonIgnore private UUID hash_id = UUID.randomUUID();

	@Override
	public int hashCode() {
		return hash_id.hashCode();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Kindof [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (ktype != null) {
			builder.append("ktype=");
			builder.append(ktype);
			builder.append(", ");
		}
		if (kvalue != null) {
			builder.append("kvalue=");
			builder.append(kvalue);
			builder.append(", ");
		}
		if (useYn != null) {
			builder.append("useYn=");
			builder.append(useYn);
			builder.append(", ");
		}
		if (hash_id != null) {
			builder.append("hash_id=");
			builder.append(hash_id);
		}
		builder.append("]");
		return builder.toString();
	}

}