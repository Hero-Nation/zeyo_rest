package net.heronation.zeyo.rest.repository.item_shopmall_map;
 
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

import net.heronation.zeyo.rest.repository.item.Item;import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
 
@Entity 
@Data
@RequiredArgsConstructor
@Table(name="ITEM_SHOPMALL_MAP")
@TableGenerator(name="ITEM_SHOPMALL_MAP_ID_GENERATOR",table="JPA_ID_TABLE",pkColumnValue="ITEM_SHOPMALL_MAP_ID",allocationSize=1)
@EntityListeners(AuditingEntityListener.class)
public class ItemShopmallMap{

	private @Version Long version;
	private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;
 
        @Id 
@GeneratedValue(strategy = GenerationType.TABLE,generator="ITEM_SHOPMALL_MAP_ID_GENERATOR")
@Column(name="ID")
private Long id;

 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="ITEM_ID")
private Item item;
 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="SHOPMALL_ID")
private Shopmall shopmall;
private String useYn;
    
}