package net.heronation.zeyo.rest.repository.shopmall_member_map;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
import net.heronation.zeyo.rest.repository.member.Member;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "SHOPMALL_MEMBER_MAP")
@TableGenerator(name = "SHOPMALL_MEMBER_MAP_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "SHOPMALL_MEMBER_MAP_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)
public class ShopmallMemberMap {

	private @Version Long version;
	private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SHOPMALL_MEMBER_MAP_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SHOPMALL_ID")
	private Shopmall shopmall;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	private String linkYn;

	private String useYn;

}