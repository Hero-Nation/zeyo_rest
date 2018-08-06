import { Rest } from '../../common/model/rest';
import { ItemClothColorMap } from '../../item_cloth_color_map/model/item_cloth_color_map';
import { Kindof } from '../../kindof/model/kindof';

export class ClothColor extends Rest{
	itemClothColorMap : ItemClothColorMap[];
id: any;

kindof: Kindof;name : any;

use_yn : any;
}