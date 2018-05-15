package net.heronation.zeyo.rest.repository.madein;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.kindof.Kindof;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "MADEIN")
@TableGenerator(name = "MADEIN_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "MADEIN_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = { "option" })
public class Madein {

	@OneToMany(mappedBy = "madein", fetch = FetchType.LAZY)
	private List<Item> items = new ArrayList<Item>();

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MADEIN_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "KINDOF_ID")
	private Kindof kindof;
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDt;

	private String useYn;

}