package net.heronation.zeyo.rest.repository.sub_category_fit_info_map;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubCategoryFitInfoMap is a Querydsl query type for SubCategoryFitInfoMap
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSubCategoryFitInfoMap extends EntityPathBase<SubCategoryFitInfoMap> {

    private static final long serialVersionUID = -1584029363L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubCategoryFitInfoMap subCategoryFitInfoMap = new QSubCategoryFitInfoMap("subCategoryFitInfoMap");

    public final net.heronation.zeyo.rest.repository.fit_info.QFitInfo fitInfo;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final net.heronation.zeyo.rest.repository.sub_category.QSubCategory subCategory;

    public final StringPath useYn = createString("useYn");

    public QSubCategoryFitInfoMap(String variable) {
        this(SubCategoryFitInfoMap.class, forVariable(variable), INITS);
    }

    public QSubCategoryFitInfoMap(Path<? extends SubCategoryFitInfoMap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubCategoryFitInfoMap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubCategoryFitInfoMap(PathMetadata metadata, PathInits inits) {
        this(SubCategoryFitInfoMap.class, metadata, inits);
    }

    public QSubCategoryFitInfoMap(Class<? extends SubCategoryFitInfoMap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fitInfo = inits.isInitialized("fitInfo") ? new net.heronation.zeyo.rest.repository.fit_info.QFitInfo(forProperty("fitInfo")) : null;
        this.subCategory = inits.isInitialized("subCategory") ? new net.heronation.zeyo.rest.repository.sub_category.QSubCategory(forProperty("subCategory"), inits.get("subCategory")) : null;
    }

}

