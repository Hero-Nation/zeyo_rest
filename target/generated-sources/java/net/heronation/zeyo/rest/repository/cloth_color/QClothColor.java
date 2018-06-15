package net.heronation.zeyo.rest.repository.cloth_color;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClothColor is a Querydsl query type for ClothColor
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QClothColor extends EntityPathBase<ClothColor> {

    private static final long serialVersionUID = 1691358164L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClothColor clothColor = new QClothColor("clothColor");

    public final DateTimePath<org.joda.time.DateTime> createDt = createDateTime("createDt", org.joda.time.DateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<net.heronation.zeyo.rest.repository.item_cloth_color_map.ItemClothColorMap, net.heronation.zeyo.rest.repository.item_cloth_color_map.QItemClothColorMap> itemClothColorMaps = this.<net.heronation.zeyo.rest.repository.item_cloth_color_map.ItemClothColorMap, net.heronation.zeyo.rest.repository.item_cloth_color_map.QItemClothColorMap>createList("itemClothColorMaps", net.heronation.zeyo.rest.repository.item_cloth_color_map.ItemClothColorMap.class, net.heronation.zeyo.rest.repository.item_cloth_color_map.QItemClothColorMap.class, PathInits.DIRECT2);

    public final net.heronation.zeyo.rest.repository.kindof.QKindof kindof;

    public final StringPath name = createString("name");

    public final StringPath useYn = createString("useYn");

    public QClothColor(String variable) {
        this(ClothColor.class, forVariable(variable), INITS);
    }

    public QClothColor(Path<? extends ClothColor> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClothColor(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClothColor(PathMetadata metadata, PathInits inits) {
        this(ClothColor.class, metadata, inits);
    }

    public QClothColor(Class<? extends ClothColor> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.kindof = inits.isInitialized("kindof") ? new net.heronation.zeyo.rest.repository.kindof.QKindof(forProperty("kindof")) : null;
    }

}

