package net.heronation.zeyo.rest.repository.item_cloth_color_map;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemClothColorMap is a Querydsl query type for ItemClothColorMap
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QItemClothColorMap extends EntityPathBase<ItemClothColorMap> {

    private static final long serialVersionUID = -620012196L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemClothColorMap itemClothColorMap = new QItemClothColorMap("itemClothColorMap");

    public final net.heronation.zeyo.rest.repository.cloth_color.QClothColor clothColor;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final net.heronation.zeyo.rest.repository.item.QItem item;

    public final StringPath optionValue = createString("optionValue");

    public final StringPath useYn = createString("useYn");

    public QItemClothColorMap(String variable) {
        this(ItemClothColorMap.class, forVariable(variable), INITS);
    }

    public QItemClothColorMap(Path<? extends ItemClothColorMap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemClothColorMap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemClothColorMap(PathMetadata metadata, PathInits inits) {
        this(ItemClothColorMap.class, metadata, inits);
    }

    public QItemClothColorMap(Class<? extends ItemClothColorMap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.clothColor = inits.isInitialized("clothColor") ? new net.heronation.zeyo.rest.repository.cloth_color.QClothColor(forProperty("clothColor"), inits.get("clothColor")) : null;
        this.item = inits.isInitialized("item") ? new net.heronation.zeyo.rest.repository.item.QItem(forProperty("item"), inits.get("item")) : null;
    }

}

