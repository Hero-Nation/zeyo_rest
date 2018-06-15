package net.heronation.zeyo.rest.repository.sub_category;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubCategory is a Querydsl query type for SubCategory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSubCategory extends EntityPathBase<SubCategory> {

    private static final long serialVersionUID = -1125130360L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubCategory subCategory = new QSubCategory("subCategory");

    public final StringPath bleachYn = createString("bleachYn");

    public final net.heronation.zeyo.rest.repository.category.QCategory category;

    public final StringPath clothImage = createString("clothImage");

    public final DateTimePath<org.joda.time.DateTime> createDt = createDateTime("createDt", org.joda.time.DateTime.class);

    public final StringPath drycleaningYn = createString("drycleaningYn");

    public final StringPath drymethodYn = createString("drymethodYn");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath ironingYn = createString("ironingYn");

    public final StringPath itemImage = createString("itemImage");

    public final ListPath<net.heronation.zeyo.rest.repository.item.Item, net.heronation.zeyo.rest.repository.item.QItem> items = this.<net.heronation.zeyo.rest.repository.item.Item, net.heronation.zeyo.rest.repository.item.QItem>createList("items", net.heronation.zeyo.rest.repository.item.Item.class, net.heronation.zeyo.rest.repository.item.QItem.class, PathInits.DIRECT2);

    public final StringPath laundryYn = createString("laundryYn");

    public final StringPath name = createString("name");

    public final ListPath<net.heronation.zeyo.rest.repository.size_option.SizeOption, net.heronation.zeyo.rest.repository.size_option.QSizeOption> sizeOptions = this.<net.heronation.zeyo.rest.repository.size_option.SizeOption, net.heronation.zeyo.rest.repository.size_option.QSizeOption>createList("sizeOptions", net.heronation.zeyo.rest.repository.size_option.SizeOption.class, net.heronation.zeyo.rest.repository.size_option.QSizeOption.class, PathInits.DIRECT2);

    public final ListPath<net.heronation.zeyo.rest.repository.sub_category_fit_info_map.SubCategoryFitInfoMap, net.heronation.zeyo.rest.repository.sub_category_fit_info_map.QSubCategoryFitInfoMap> subCategoryFitInfoMaps = this.<net.heronation.zeyo.rest.repository.sub_category_fit_info_map.SubCategoryFitInfoMap, net.heronation.zeyo.rest.repository.sub_category_fit_info_map.QSubCategoryFitInfoMap>createList("subCategoryFitInfoMaps", net.heronation.zeyo.rest.repository.sub_category_fit_info_map.SubCategoryFitInfoMap.class, net.heronation.zeyo.rest.repository.sub_category_fit_info_map.QSubCategoryFitInfoMap.class, PathInits.DIRECT2);

    public final ListPath<net.heronation.zeyo.rest.repository.sub_category_measure_map.SubCategoryMeasureMap, net.heronation.zeyo.rest.repository.sub_category_measure_map.QSubCategoryMeasureMap> subCategoryMeasureMaps = this.<net.heronation.zeyo.rest.repository.sub_category_measure_map.SubCategoryMeasureMap, net.heronation.zeyo.rest.repository.sub_category_measure_map.QSubCategoryMeasureMap>createList("subCategoryMeasureMaps", net.heronation.zeyo.rest.repository.sub_category_measure_map.SubCategoryMeasureMap.class, net.heronation.zeyo.rest.repository.sub_category_measure_map.QSubCategoryMeasureMap.class, PathInits.DIRECT2);

    public final StringPath useYn = createString("useYn");

    public QSubCategory(String variable) {
        this(SubCategory.class, forVariable(variable), INITS);
    }

    public QSubCategory(Path<? extends SubCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubCategory(PathMetadata metadata, PathInits inits) {
        this(SubCategory.class, metadata, inits);
    }

    public QSubCategory(Class<? extends SubCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new net.heronation.zeyo.rest.repository.category.QCategory(forProperty("category")) : null;
    }

}

