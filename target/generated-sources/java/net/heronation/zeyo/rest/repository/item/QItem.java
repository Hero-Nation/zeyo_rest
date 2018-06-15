package net.heronation.zeyo.rest.repository.item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItem is a Querydsl query type for Item
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QItem extends EntityPathBase<Item> {

    private static final long serialVersionUID = -1566674445L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItem item = new QItem("item");

    public final StringPath bleachYn = createString("bleachYn");

    public final net.heronation.zeyo.rest.repository.brand.QBrand brand;

    public final net.heronation.zeyo.rest.repository.category.QCategory category;

    public final StringPath code = createString("code");

    public final DateTimePath<org.joda.time.DateTime> createDt = createDateTime("createDt", org.joda.time.DateTime.class);

    public final StringPath drycleaningYn = createString("drycleaningYn");

    public final StringPath drymethodYn = createString("drymethodYn");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image = createString("image");

    public final StringPath imageMode = createString("imageMode");

    public final StringPath ironingYn = createString("ironingYn");

    public final net.heronation.zeyo.rest.repository.item_bleach_map.QItemBleachMap itemBleachMap;

    public final ListPath<net.heronation.zeyo.rest.repository.item_cloth_color_map.ItemClothColorMap, net.heronation.zeyo.rest.repository.item_cloth_color_map.QItemClothColorMap> itemClothColorMaps = this.<net.heronation.zeyo.rest.repository.item_cloth_color_map.ItemClothColorMap, net.heronation.zeyo.rest.repository.item_cloth_color_map.QItemClothColorMap>createList("itemClothColorMaps", net.heronation.zeyo.rest.repository.item_cloth_color_map.ItemClothColorMap.class, net.heronation.zeyo.rest.repository.item_cloth_color_map.QItemClothColorMap.class, PathInits.DIRECT2);

    public final net.heronation.zeyo.rest.repository.item_drycleaning_map.QItemDrycleaningMap itemDrycleaningMap;

    public final net.heronation.zeyo.rest.repository.item_drymethod_map.QItemDrymethodMap itemDrymethodMap;

    public final ListPath<net.heronation.zeyo.rest.repository.item_fit_info_option_map.ItemFitInfoOptionMap, net.heronation.zeyo.rest.repository.item_fit_info_option_map.QItemFitInfoOptionMap> itemFitInfoOptionMaps = this.<net.heronation.zeyo.rest.repository.item_fit_info_option_map.ItemFitInfoOptionMap, net.heronation.zeyo.rest.repository.item_fit_info_option_map.QItemFitInfoOptionMap>createList("itemFitInfoOptionMaps", net.heronation.zeyo.rest.repository.item_fit_info_option_map.ItemFitInfoOptionMap.class, net.heronation.zeyo.rest.repository.item_fit_info_option_map.QItemFitInfoOptionMap.class, PathInits.DIRECT2);

    public final net.heronation.zeyo.rest.repository.item_ironing_map.QItemIroningMap itemIroningMap;

    public final net.heronation.zeyo.rest.repository.item_laundry_map.QItemLaundryMap itemLaundryMap;

    public final ListPath<net.heronation.zeyo.rest.repository.item_material_map.ItemMaterialMap, net.heronation.zeyo.rest.repository.item_material_map.QItemMaterialMap> itemMaterialMaps = this.<net.heronation.zeyo.rest.repository.item_material_map.ItemMaterialMap, net.heronation.zeyo.rest.repository.item_material_map.QItemMaterialMap>createList("itemMaterialMaps", net.heronation.zeyo.rest.repository.item_material_map.ItemMaterialMap.class, net.heronation.zeyo.rest.repository.item_material_map.QItemMaterialMap.class, PathInits.DIRECT2);

    public final ListPath<net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMap, net.heronation.zeyo.rest.repository.item_shopmall_map.QItemShopmallMap> itemShopmallMaps = this.<net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMap, net.heronation.zeyo.rest.repository.item_shopmall_map.QItemShopmallMap>createList("itemShopmallMaps", net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMap.class, net.heronation.zeyo.rest.repository.item_shopmall_map.QItemShopmallMap.class, PathInits.DIRECT2);

    public final ListPath<net.heronation.zeyo.rest.repository.item_size_option_map.ItemSizeOptionMap, net.heronation.zeyo.rest.repository.item_size_option_map.QItemSizeOptionMap> itemSizeOptionMaps = this.<net.heronation.zeyo.rest.repository.item_size_option_map.ItemSizeOptionMap, net.heronation.zeyo.rest.repository.item_size_option_map.QItemSizeOptionMap>createList("itemSizeOptionMaps", net.heronation.zeyo.rest.repository.item_size_option_map.ItemSizeOptionMap.class, net.heronation.zeyo.rest.repository.item_size_option_map.QItemSizeOptionMap.class, PathInits.DIRECT2);

    public final StringPath laundryYn = createString("laundryYn");

    public final StringPath linkYn = createString("linkYn");

    public final net.heronation.zeyo.rest.repository.madein.QMadein madein;

    public final StringPath madeinBuilder = createString("madeinBuilder");

    public final DateTimePath<org.joda.time.DateTime> madeinDate = createDateTime("madeinDate", org.joda.time.DateTime.class);

    public final net.heronation.zeyo.rest.repository.member.QMember member;

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath sizeMeasureImage = createString("sizeMeasureImage");

    public final StringPath sizeMeasureMode = createString("sizeMeasureMode");

    public final ListPath<net.heronation.zeyo.rest.repository.size_table.SizeTable, net.heronation.zeyo.rest.repository.size_table.QSizeTable> sizeTables = this.<net.heronation.zeyo.rest.repository.size_table.SizeTable, net.heronation.zeyo.rest.repository.size_table.QSizeTable>createList("sizeTables", net.heronation.zeyo.rest.repository.size_table.SizeTable.class, net.heronation.zeyo.rest.repository.size_table.QSizeTable.class, PathInits.DIRECT2);

    public final StringPath sizeTableYn = createString("sizeTableYn");

    public final net.heronation.zeyo.rest.repository.sub_category.QSubCategory subCategory;

    public final StringPath useYn = createString("useYn");

    public final net.heronation.zeyo.rest.repository.warranty.QWarranty warranty;

    public QItem(String variable) {
        this(Item.class, forVariable(variable), INITS);
    }

    public QItem(Path<? extends Item> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItem(PathMetadata metadata, PathInits inits) {
        this(Item.class, metadata, inits);
    }

    public QItem(Class<? extends Item> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.brand = inits.isInitialized("brand") ? new net.heronation.zeyo.rest.repository.brand.QBrand(forProperty("brand"), inits.get("brand")) : null;
        this.category = inits.isInitialized("category") ? new net.heronation.zeyo.rest.repository.category.QCategory(forProperty("category")) : null;
        this.itemBleachMap = inits.isInitialized("itemBleachMap") ? new net.heronation.zeyo.rest.repository.item_bleach_map.QItemBleachMap(forProperty("itemBleachMap"), inits.get("itemBleachMap")) : null;
        this.itemDrycleaningMap = inits.isInitialized("itemDrycleaningMap") ? new net.heronation.zeyo.rest.repository.item_drycleaning_map.QItemDrycleaningMap(forProperty("itemDrycleaningMap"), inits.get("itemDrycleaningMap")) : null;
        this.itemDrymethodMap = inits.isInitialized("itemDrymethodMap") ? new net.heronation.zeyo.rest.repository.item_drymethod_map.QItemDrymethodMap(forProperty("itemDrymethodMap"), inits.get("itemDrymethodMap")) : null;
        this.itemIroningMap = inits.isInitialized("itemIroningMap") ? new net.heronation.zeyo.rest.repository.item_ironing_map.QItemIroningMap(forProperty("itemIroningMap"), inits.get("itemIroningMap")) : null;
        this.itemLaundryMap = inits.isInitialized("itemLaundryMap") ? new net.heronation.zeyo.rest.repository.item_laundry_map.QItemLaundryMap(forProperty("itemLaundryMap"), inits.get("itemLaundryMap")) : null;
        this.madein = inits.isInitialized("madein") ? new net.heronation.zeyo.rest.repository.madein.QMadein(forProperty("madein"), inits.get("madein")) : null;
        this.member = inits.isInitialized("member") ? new net.heronation.zeyo.rest.repository.member.QMember(forProperty("member"), inits.get("member")) : null;
        this.subCategory = inits.isInitialized("subCategory") ? new net.heronation.zeyo.rest.repository.sub_category.QSubCategory(forProperty("subCategory"), inits.get("subCategory")) : null;
        this.warranty = inits.isInitialized("warranty") ? new net.heronation.zeyo.rest.repository.warranty.QWarranty(forProperty("warranty"), inits.get("warranty")) : null;
    }

}

