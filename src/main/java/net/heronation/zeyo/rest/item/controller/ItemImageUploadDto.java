package net.heronation.zeyo.rest.item.controller;

import lombok.Data;
import net.heronation.zeyo.rest.common.dto.FileDto;

@Data
public class ItemImageUploadDto {
	private Long item_id;
	private FileDto[] image;
}
