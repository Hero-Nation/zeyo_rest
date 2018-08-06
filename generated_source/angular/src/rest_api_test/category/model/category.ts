import { Rest } from '../../common/model/rest';
import { Item } from '../../item/model/item';
 import { SubCategory } from '../../sub_category/model/sub_category';
 import { SizeOption } from '../../size_option/model/size_option';

export class Category extends Rest{
	item : Item[];
id: any;

name : any;

create_dt : any;

use_yn : any;

 subCategory : SubCategory[];
 sizeOption : SizeOption[];
}