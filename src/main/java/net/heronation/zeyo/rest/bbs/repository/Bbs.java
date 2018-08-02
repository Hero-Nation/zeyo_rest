package net.heronation.zeyo.rest.bbs.repository;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.heronation.zeyo.rest.kindof.repository.Kindof;
import net.heronation.zeyo.rest.member.repository.Member;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "BBS")
@TableGenerator(name = "BBS_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "BBS_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)

public class Bbs {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BBS_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	@JsonManagedReference(value = "bbss_kindof")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "KINDOF_ID")
	private Kindof kindof;

	@JsonManagedReference(value = "bbss_member")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	private String title;

	private String bbsContent;

	private String replyContent;

	private String attach_file;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createDt;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime replyDt;

	private String status;

	private String useYn;

 

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bbs other = (Bbs) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Transient
	@JsonIgnore
	private UUID hash_id = UUID.randomUUID();

	@Override
	public int hashCode() {
		return hash_id.hashCode();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bbs [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (title != null) {
			builder.append("title=");
			builder.append(title);
			builder.append(", ");
		}
		if (bbsContent != null) {
			builder.append("bbsContent=");
			builder.append(bbsContent);
			builder.append(", ");
		}
		if (replyContent != null) {
			builder.append("replyContent=");
			builder.append(replyContent);
			builder.append(", ");
		}
		if (attach_file != null) {
			builder.append("attach_file=");
			builder.append(attach_file);
			builder.append(", ");
		}
		if (createDt != null) {
			builder.append("createDt=");
			builder.append(createDt);
			builder.append(", ");
		}
		if (replyDt != null) {
			builder.append("replyDt=");
			builder.append(replyDt);
			builder.append(", ");
		}
		if (status != null) {
			builder.append("status=");
			builder.append(status);
			builder.append(", ");
		}
		if (useYn != null) {
			builder.append("useYn=");
			builder.append(useYn);
			builder.append(", ");
		}
		if (hash_id != null) {
			builder.append("hash_id=");
			builder.append(hash_id);
		}
		builder.append("]");
		return builder.toString();
	}

}