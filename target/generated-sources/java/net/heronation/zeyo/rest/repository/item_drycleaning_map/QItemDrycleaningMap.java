package net.heronation.zeyo.rest.repository.item_drycleaning_map;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemDrycleaningMap is a Querydsl query type for ItemDrycleaningMap
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QItemDrycleaningMap extends EntityPathBase<ItemDrycleaningMap> {

    private static final long serialVersionUID = 1020021929L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemDrycleaningMap itemDrycleaningMap = new QItemDrycleaningMap("itemDrycleaningMap");

    public final StringPath detergent = createString("detergent");

    public final StringPath drycan = createString("drycan");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final net.heronation.zeyo.rest.repository.item.QItem item;

    public final StringPath storecan = createString("storecan");

    public final StringPath useYn = createString("useYn");

    public QItemDrycleaningMap(String variable) {
        this(ItemDrycleaningMap.class, forVariable(variable), INITS);
    }

    public QItemDrycleaningMap(Path<? extends ItemDrycleaningMap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemDrycleaningMap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemDrycleaningMap(PathMetadata metadata, PathInits inits) {
        this(ItemDrycleaningMap.class, metadata, inits);
    }

    public QItemDrycleaningMap(Class<? extends ItemDrycleaningMap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new net.heronation.zeyo.rest.repository.item.QItem(forProperty("item"), inits.get("item")) : null;
    }

}

