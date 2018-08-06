import { Rest } from '../../common/model/rest';
import { ItemMaterialMap } from '../../item_material_map/model/item_material_map';

export class Material extends Rest{
	itemMaterialMap : ItemMaterialMap[];
id: any;

name : any;

image : any;

meta_desc : any;

create_dt : any;

use_yn : any;
}