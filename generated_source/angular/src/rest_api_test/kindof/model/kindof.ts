import { Rest } from '../../common/model/rest';
import { ItemMaterialMap } from '../../item_material_map/model/item_material_map';
 import { Madein } from '../../madein/model/madein';
 import { Warranty } from '../../warranty/model/warranty';
 import { SizeOption } from '../../size_option/model/size_option';
 import { ClothColor } from '../../cloth_color/model/cloth_color';
 import { Bbs } from '../../bbs/model/bbs';

export class Kindof extends Rest{
	itemMaterialMap : ItemMaterialMap[];
 madein : Madein[];
 warranty : Warranty[];
 sizeOption : SizeOption[];
 clothColor : ClothColor[];
 bbs : Bbs[];
id: any;

ktype : any;

kvalue : any;

use_yn : any;
}