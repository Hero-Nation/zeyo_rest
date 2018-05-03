package net.heronation.zeyo.rest.repository.madein;
 
import javax.persistence.*; 
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.kindof.Kindof;
 
@Entity 
@Data
@RequiredArgsConstructor
@Table(name="MADEIN")
@TableGenerator(name="MADEIN_ID_GENERATOR",table="JPA_ID_TABLE",pkColumnValue="MADEIN_ID",allocationSize=1)
@EntityListeners(AuditingEntityListener.class)
public class Madein{

	private @Version Long version;
	private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;
 
        @OneToMany(mappedBy = "madein" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<Item>  items = new ArrayList<Item>();
 @Id 
@GeneratedValue(strategy = GenerationType.TABLE,generator="MADEIN_ID_GENERATOR")
@Column(name="ID")
private Long id;

 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="KINDOF_ID")
private Kindof kindof;
private String name;





@Temporal(TemporalType.TIMESTAMP)
private Date   createDt;



private String useYn;
    
}