import { Rest } from '../../common/model/rest';
import { Dmodel } from '../../dmodel/model/dmodel';
import { MeasureItem } from '../../measure_item/model/measure_item';

export class DmodelMeasureMap extends Rest{
	id: any;

dmodel: Dmodel;measureItem: MeasureItem;min_value : any;

max_value : any;

use_yn : any;
}