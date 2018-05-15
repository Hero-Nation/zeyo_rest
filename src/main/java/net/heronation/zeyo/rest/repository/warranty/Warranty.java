package net.heronation.zeyo.rest.repository.warranty;

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
import net.heronation.zeyo.rest.repository.kindof.Kindof;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
@Entity
@Data
@RequiredArgsConstructor
@Table(name = "WARRANTY")
@TableGenerator(name = "WARRANTY_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "WARRANTY_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)

public class Warranty {

	@OneToMany(mappedBy = "warranty", fetch = FetchType.LAZY)
	private List<Item> items = new ArrayList<Item>();
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "WARRANTY_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "KINDOF_ID")
	private Kindof kindof;
	private String scope;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createDt;

	private String useYn;

}