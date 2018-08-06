import { Rest } from '../../common/model/rest';
import { SubCategory } from '../../sub_category/model/sub_category';
 import { DmodelMeasureMap } from '../../dmodel_measure_map/model/dmodel_measure_map';
 import { DmodelRatio } from '../../dmodel_ratio/model/dmodel_ratio';

export class Dmodel extends Rest{
	subCategory : SubCategory[];
id: any;

title : any;

controller : any;

svgdata : any;

 
create_dt : any; 
update_dt : any;use_yn : any;

 dmodelMeasureMap : DmodelMeasureMap[];
 dmodelRatio : DmodelRatio[];
}