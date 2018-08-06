package net.heronation.zeyo.rest.size_table.repository;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.heronation.zeyo.rest.item.repository.Item;
@Entity
@Data
@RequiredArgsConstructor
@Table(name = "SIZE_TABLE")
@TableGenerator(name = "SIZE_TABLE_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "SIZE_TABLE_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)

public class SizeTable {

//	private @Version Long version;
//	private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SIZE_TABLE_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	@JsonManagedReference
	@OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "ITEM_ID")
	private Item item;
	
	private String visibleNameYn;

	private String visibleCodeYn;

	private String visibleBasicYn;

	private String visibleItemImageYn;

	private String visibleColorYn;

	private String visibleMeasureTableYn;

	private String visibleLaundryInfoYn;

	private String visibleMeasureHowAYn;

	private String visibleMeasureHowBYn;

	private String visibleFitInfoYn;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createDt;

	private String useYn;
	
	private String table_image;
	
 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SizeTable other = (SizeTable) obj;
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
		builder.append("SizeTable [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (visibleNameYn != null) {
			builder.append("visibleNameYn=");
			builder.append(visibleNameYn);
			builder.append(", ");
		}
		if (visibleCodeYn != null) {
			builder.append("visibleCodeYn=");
			builder.append(visibleCodeYn);
			builder.append(", ");
		}
		if (visibleBasicYn != null) {
			builder.append("visibleBasicYn=");
			builder.append(visibleBasicYn);
			builder.append(", ");
		}
		if (visibleItemImageYn != null) {
			builder.append("visibleItemImageYn=");
			builder.append(visibleItemImageYn);
			builder.append(", ");
		}
		if (visibleColorYn != null) {
			builder.append("visibleColorYn=");
			builder.append(visibleColorYn);
			builder.append(", ");
		}
		if (visibleMeasureTableYn != null) {
			builder.append("visibleMeasureTableYn=");
			builder.append(visibleMeasureTableYn);
			builder.append(", ");
		}
		if (visibleLaundryInfoYn != null) {
			builder.append("visibleLaundryInfoYn=");
			builder.append(visibleLaundryInfoYn);
			builder.append(", ");
		}
		if (visibleMeasureHowAYn != null) {
			builder.append("visibleMeasureHowAYn=");
			builder.append(visibleMeasureHowAYn);
			builder.append(", ");
		}
		if (visibleMeasureHowBYn != null) {
			builder.append("visibleMeasureHowBYn=");
			builder.append(visibleMeasureHowBYn);
			builder.append(", ");
		}
		if (visibleFitInfoYn != null) {
			builder.append("visibleFitInfoYn=");
			builder.append(visibleFitInfoYn);
			builder.append(", ");
		}
		if (createDt != null) {
			builder.append("createDt=");
			builder.append(createDt);
			builder.append(", ");
		}
		if (useYn != null) {
			builder.append("useYn=");
			builder.append(useYn);
			builder.append(", ");
		}
		if (table_image != null) {
			builder.append("table_image=");
			builder.append(table_image);
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