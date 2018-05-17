package net.heronation.zeyo.rest.repository.item_fit_info_option_map;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOption;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "ITEM_FIT_INFO_OPTION_MAP")
@TableGenerator(name = "ITEM_FIT_INFO_OPTION_MAP_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "ITEM_FIT_INFO_OPTION_MAP_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = {"option"})
public class ItemFitInfoOptionMap {

	private @Version Long version;
	private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ITEM_FIT_INFO_OPTION_MAP_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ITEM_ID")
	private Item item;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FIT_INFO_OPTION_ID")
	private FitInfoOption fitInfoOption;
	private String useYn;

}