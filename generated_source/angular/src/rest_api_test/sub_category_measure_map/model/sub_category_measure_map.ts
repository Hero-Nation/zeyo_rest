import { Rest } from '../../common/model/rest';
import { SubCategory } from '../../sub_category/model/sub_category';
import { MeasureItem } from '../../measure_item/model/measure_item';

export class SubCategoryMeasureMap extends Rest{
	id: any;

subCategory: SubCategory;measureItem: MeasureItem;use_yn : any;
}