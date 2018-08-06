package net.heronation.zeyo.rest.madein.repository;
 
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

import net.heronation.zeyo.rest.item.repository.Item;
import net.heronation.zeyo.rest.kindof.repository.Kindof;
 
@Entity 
@Data
@RequiredArgsConstructor
@Table(name="MADEIN")
@TableGenerator(name="MADEIN_ID_GENERATOR",table="JPA_ID_TABLE",pkColumnValue="MADEIN_ID",allocationSize=1)
@EntityListeners(AuditingEntityListener.class)
public class Madein{

	//private @Version Long version;
	//private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;
 
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




private String createDt;




private String useYn;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(); 
                .append("  Madein  Entity  ")
                .append("\n id  =  ").append(id)

.append("\n name  =  ").append(name)

.append("\n create_dt  =  ").append(create_dt)

.append("\n use_yn  =  ").append(use_yn)
		return builder.toString();	
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Madein other = (Madein) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Transient
	private UUID hash_id = UUID.randomUUID();
	
	@Override
	public int hashCode() { 
		return hash_id.hashCode();
	}
    
}