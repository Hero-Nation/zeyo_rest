import { Rest } from '../../common/model/rest';
import { ItemSizeOptionMap } from '../../item_size_option_map/model/item_size_option_map';
import { Category } from '../../category/model/category';
import { SubCategory } from '../../sub_category/model/sub_category';
import { Kindof } from '../../kindof/model/kindof';

export class SizeOption extends Rest{
	itemSizeOptionMap : ItemSizeOptionMap[];
id: any;

category: Category;subCategory: SubCategory;kindof: Kindof;name : any;

create_dt : any;

use_yn : any;
}