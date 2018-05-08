package net.heronation.zeyo.rest.repository.item_cloth_color_map;
 
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

import net.heronation.zeyo.rest.repository.item.Item;import net.heronation.zeyo.rest.repository.cloth_color.ClothColor;
 
@Entity 
@Data
@RequiredArgsConstructor
@Table(name="ITEM_CLOTH_COLOR_MAP")
@TableGenerator(name="ITEM_CLOTH_COLOR_MAP_ID_GENERATOR",table="JPA_ID_TABLE",pkColumnValue="ITEM_CLOTH_COLOR_MAP_ID",allocationSize=1)
@EntityListeners(AuditingEntityListener.class)
public class ItemClothColorMap{

	
	
 
        @Id 
@GeneratedValue(strategy = GenerationType.TABLE,generator="ITEM_CLOTH_COLOR_MAP_ID_GENERATOR")
@Column(name="ID")
private Long id;

 
@ManyToOne(fetch=FetchType.EAGER ) 
@JoinColumn(name="ITEM_ID")
private Item item;
 
@ManyToOne(fetch=FetchType.EAGER ) 
@JoinColumn(name="CLOTH_COLOR_ID")
private ClothColor clothColor;
private String useYn;
    
}