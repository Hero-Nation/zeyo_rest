package net.heronation.zeyo.rest.repository.madein;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.kindof.Kindof;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "MADEIN")
@TableGenerator(name = "MADEIN_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "MADEIN_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class) 
public class Madein {
	
	
	
	@JsonBackReference(value="madein_items")
	@OneToMany(mappedBy = "madein", fetch = FetchType.LAZY)
	private List<Item> items = new ArrayList<Item>();

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MADEIN_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;
	
	
	@JsonManagedReference(value="madein_kindof")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "KINDOF_ID")
	private Kindof kindof;
	
	
	
	private String name;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createDt;

	private String useYn;
	
	@Override
	public String toString() {
		return "Madein ]";
	}

}