package net.heronation.zeyo.rest.fit_info_option.repository;
 
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

import net.heronation.zeyo.rest.fit_info.repository.FitInfo;import net.heronation.zeyo.rest.fit_into_choice.repository.FitIntoChoice;
 
@Entity 
@Data
@RequiredArgsConstructor
@Table(name="FIT_INFO_OPTION")
@TableGenerator(name="FIT_INFO_OPTION_ID_GENERATOR",table="JPA_ID_TABLE",pkColumnValue="FIT_INFO_OPTION_ID",allocationSize=1)
@EntityListeners(AuditingEntityListener.class)
public class FitInfoOption{

	//private @Version Long version;
	//private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;
 
        @Id 
@GeneratedValue(strategy = GenerationType.TABLE,generator="FIT_INFO_OPTION_ID_GENERATOR")
@Column(name="ID")
private Long id;

 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="FIT_INFO_ID")
private FitInfo fitInfo;
private String name;




private String useYn;





@OneToMany(mappedBy = "fitInfoOption" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
private List<FitIntoChoice>  fitIntoChoices = new ArrayList<FitIntoChoice>();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(); 
                .append("  FitInfoOption  Entity  ")
                .append("\n id  =  ").append(id)

.append("\n name  =  ").append(name)

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
		FitInfoOption other = (FitInfoOption) obj;
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