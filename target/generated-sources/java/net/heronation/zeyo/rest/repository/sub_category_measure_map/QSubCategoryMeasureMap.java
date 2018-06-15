package net.heronation.zeyo.rest.repository.sub_category_measure_map;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubCategoryMeasureMap is a Querydsl query type for SubCategoryMeasureMap
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSubCategoryMeasureMap extends EntityPathBase<SubCategoryMeasureMap> {

    private static final long serialVersionUID = -523819774L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubCategoryMeasureMap subCategoryMeasureMap = new QSubCategoryMeasureMap("subCategoryMeasureMap");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final net.heronation.zeyo.rest.repository.measure_item.QMeasureItem measureItem;

    public final net.heronation.zeyo.rest.repository.sub_category.QSubCategory subCategory;

    public final StringPath useYn = createString("useYn");

    public QSubCategoryMeasureMap(String variable) {
        this(SubCategoryMeasureMap.class, forVariable(variable), INITS);
    }

    public QSubCategoryMeasureMap(Path<? extends SubCategoryMeasureMap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubCategoryMeasureMap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubCategoryMeasureMap(PathMetadata metadata, PathInits inits) {
        this(SubCategoryMeasureMap.class, metadata, inits);
    }

    public QSubCategoryMeasureMap(Class<? extends SubCategoryMeasureMap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.measureItem = inits.isInitialized("measureItem") ? new net.heronation.zeyo.rest.repository.measure_item.QMeasureItem(forProperty("measureItem")) : null;
        this.subCategory = inits.isInitialized("subCategory") ? new net.heronation.zeyo.rest.repository.sub_category.QSubCategory(forProperty("subCategory"), inits.get("subCategory")) : null;
    }

}

