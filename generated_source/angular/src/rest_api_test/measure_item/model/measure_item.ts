import { Rest } from '../../common/model/rest';
import { SubCategoryMeasureMap } from '../../sub_category_measure_map/model/sub_category_measure_map';
 import { DmodelMeasureMap } from '../../dmodel_measure_map/model/dmodel_measure_map';

export class MeasureItem extends Rest{
	subCategoryMeasureMap : SubCategoryMeasureMap[];
id: any;

name : any;

meta_desc : any;

create_dt : any;

use_yn : any;

 dmodelMeasureMap : DmodelMeasureMap[];
}