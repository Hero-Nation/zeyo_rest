package net.heronation.zeyo.rest.item_fit_info_option_map.repository;

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

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.heronation.zeyo.rest.fit_info_option.repository.FitInfoOption;
import net.heronation.zeyo.rest.item.repository.Item;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "ITEM_FIT_INFO_OPTION_MAP")
@TableGenerator(name = "ITEM_FIT_INFO_OPTION_MAP_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "ITEM_FIT_INFO_OPTION_MAP_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)

public class ItemFitInfoOptionMap {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ITEM_FIT_INFO_OPTION_MAP_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ITEM_ID")
	private Item item;

	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FIT_INFO_OPTION_ID")
	private FitInfoOption fitInfoOption;
	private String useYn;
	
	
	@Override
	public String toString() {
		return "ItemFitInfoOptionMap ]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemFitInfoOptionMap other = (ItemFitInfoOptionMap) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

}