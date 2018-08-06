package net.heronation.zeyo.rest.v2_rule.repository;

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

import net.heronation.zeyo.rest.sub_category.repository.SubCategory;
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

	private String ruleType;

	private String ruleMessage;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "SUB_CATEGORY_ID")
	private SubCategory first_ct;
	private String firstIncludeChild;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "SUB_CATEGORY_ID")
	private SubCategory second_ct;
	private String secondIncludeChild;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createDt;

	private String useYn;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(); 
                .append("  V2Rule  Entity  ")
                .append("\n id  =  ").append(id)

.append("\n title  =  ").append(title)

.append("\n rule_type  =  ").append(rule_type)

.append("\n rule_message  =  ").append(rule_message)

.append("\n first_include_child  =  ").append(first_include_child)

.append("\n second_include_child  =  ").append(second_include_child)

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