package net.heronation.zeyo.rest.repository.measure_item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMeasureItem is a Querydsl query type for MeasureItem
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMeasureItem extends EntityPathBase<MeasureItem> {

    private static final long serialVersionUID = 1357896818L;

    public static final QMeasureItem measureItem = new QMeasureItem("measureItem");

    public final DateTimePath<org.joda.time.DateTime> createDt = createDateTime("createDt", org.joda.time.DateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath metaDesc = createString("metaDesc");

    public final StringPath name = createString("name");

    public final ListPath<net.heronation.zeyo.rest.repository.sub_category_measure_map.SubCategoryMeasureMap, net.heronation.zeyo.rest.repository.sub_category_measure_map.QSubCategoryMeasureMap> subCategoryMeasureMaps = this.<net.heronation.zeyo.rest.repository.sub_category_measure_map.SubCategoryMeasureMap, net.heronation.zeyo.rest.repository.sub_category_measure_map.QSubCategoryMeasureMap>createList("subCategoryMeasureMaps", net.heronation.zeyo.rest.repository.sub_category_measure_map.SubCategoryMeasureMap.class, net.heronation.zeyo.rest.repository.sub_category_measure_map.QSubCategoryMeasureMap.class, PathInits.DIRECT2);

    public final StringPath useYn = createString("useYn");

    public QMeasureItem(String variable) {
        super(MeasureItem.class, forVariable(variable));
    }

    public QMeasureItem(Path<? extends MeasureItem> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMeasureItem(PathMetadata metadata) {
        super(MeasureItem.class, metadata);
    }

}

