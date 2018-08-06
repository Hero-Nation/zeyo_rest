package net.heronation.zeyo.rest.v2_rule.repository;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.RequiredArgsConstructor;

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

	private Long firstCt;

	private String firstIncludeChild;

	private Long secondCt;

	private String secondIncludeChild;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createDt;

	private String useYn;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("  V2Rule  Entity  ").append("\n id  =  ").append(id)

				.append("\n title  =  ").append(title)

				.append("\n rule_type  =  ").append(ruleType)

				.append("\n rule_message  =  ").append(ruleMessage)

				.append("\n first_ct  =  ").append(firstCt)

				.append("\n first_include_child  =  ").append(firstIncludeChild)

				.append("\n second_ct  =  ").append(secondCt)

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