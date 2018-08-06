import { Rest } from '../../common/model/rest';
import { Item } from '../../item/model/item';
import { Kindof } from '../../kindof/model/kindof';
import { Material } from '../../material/model/material';

export class ItemMaterialMap extends Rest{
	id: any;

item: Item;kindof: Kindof;material: Material;contain : any;

use_yn : any;
}