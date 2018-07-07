package net.heronation.zeyo.rest.controller.item;

import lombok.Data;
import net.heronation.zeyo.rest.common.value.FileDto;

@Data
public class ItemSizeMeasureImageUploadDto {
	private Long item_id;
	private FileDto[] sizeMeasureImage
;
}
