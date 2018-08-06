import { Rest } from '../../common/model/rest';
import { Item } from '../../item/model/item';
import { ClothColor } from '../../cloth_color/model/cloth_color';

export class ItemClothColorMap extends Rest{
	id: any;

item: Item;clothColor: ClothColor;use_yn : any;
}