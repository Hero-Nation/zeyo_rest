import { Rest } from '../../common/model/rest';
import { Member } from '../../member/model/member';
import { Brand } from '../../brand/model/brand';
import { Category } from '../../category/model/category';
import { SubCategory } from '../../sub_category/model/sub_category';
import { Madein } from '../../madein/model/madein';
import { Warranty } from '../../warranty/model/warranty';
 import { ItemShopmallMap } from '../../item_shopmall_map/model/item_shopmall_map';
 import { ItemMaterialMap } from '../../item_material_map/model/item_material_map';
 import { ItemSizeOptionMap } from '../../item_size_option_map/model/item_size_option_map';
 import { ItemClothColorMap } from '../../item_cloth_color_map/model/item_cloth_color_map';
 import { ItemLaundryMap } from '../../item_laundry_map/model/item_laundry_map';
 import { ItemDrycleaningMap } from '../../item_drycleaning_map/model/item_drycleaning_map';
 import { ItemIroningMap } from '../../item_ironing_map/model/item_ironing_map';
 import { ItemDrymethodMap } from '../../item_drymethod_map/model/item_drymethod_map';
 import { ItemBleachMap } from '../../item_bleach_map/model/item_bleach_map';

export class Item extends Rest{
	id: any;

member: Member;brand: Brand;category: Category;subCategory: SubCategory;image_mode : any;

image : any;

size_measure_mode : any;

size_measure_image : any;

name : any;

code : any;

price : any;

madein_builder : any;

madein: Madein;madein_date : any;

warranty: Warranty;laundry_yn : any;

drycleaning_yn : any;

ironing_yn : any;

drymethod_yn : any;

bleach_yn : any;

size_link_yn : any;

create_dt : any;

use_yn : any;

 itemShopmallMap : ItemShopmallMap[];
 itemMaterialMap : ItemMaterialMap[];
 itemSizeOptionMap : ItemSizeOptionMap[];
 itemClothColorMap : ItemClothColorMap[];
 itemLaundryMap : ItemLaundryMap[];
 itemDrycleaningMap : ItemDrycleaningMap[];
 itemIroningMap : ItemIroningMap[];
 itemDrymethodMap : ItemDrymethodMap[];
 itemBleachMap : ItemBleachMap[];
}