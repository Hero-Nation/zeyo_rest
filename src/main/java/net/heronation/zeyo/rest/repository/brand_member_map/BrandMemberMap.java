package net.heronation.zeyo.rest.repository.brand_member_map;

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

import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.member.Member;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "BRAND_MEMBER_MAP")
@TableGenerator(name = "BRAND_MEMBER_MAP_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "BRAND_MEMBER_MAP_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class) 
public class BrandMemberMap {

	private @Version Long version;
	private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BRAND_MEMBER_MAP_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BRAND_ID")
	private Brand brand;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	private String linkYn;

	private String useYn;

	@Override
	public String toString() {
		return "BrandMemberMap ]";
	}

}