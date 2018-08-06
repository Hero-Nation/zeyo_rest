package net.heronation.zeyo.rest.v2_rule.repository;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore; 

import net.heronation.zeyo.rest.sub_category.repository.SubCategory; 

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "V2_RULE")
@TableGenerator(name = "V2_RULE_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "V2_RULE_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)
public class V2Rule {

	// private @Version Long version;
	// private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "V2_RULE_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	private String title;

	private String ruleType;

	private String ruleMessage;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "FIRST_CT")
	private SubCategory firstCt;
	private String firstIncludeChild;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "SECOND_CT")
	private SubCategory secondCt;
	private String secondIncludeChild;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createDt;

	private String useYn;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(); 
		builder.append("  V2Rule  Entity  ")
                .append("\n id  =  ").append(id)

				.append("\n title  =  ").append(title)
				
				.append("\n rule_type  =  ").append(ruleType)
				
				.append("\n rule_message  =  ").append(ruleMessage)
				
				.append("\n first_include_child  =  ").append(firstIncludeChild)
				
				.append("\n second_include_child  =  ").append(secondIncludeChild)
				
				.append("\n create_dt  =  ").append(createDt)
				
				
				.append("\n use_yn  =  ").append(useYn);
		
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
		V2Rule other = (V2Rule) obj;
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