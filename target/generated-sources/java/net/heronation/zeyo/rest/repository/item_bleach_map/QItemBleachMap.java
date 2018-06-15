package net.heronation.zeyo.rest.repository.item_bleach_map;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemBleachMap is a Querydsl query type for ItemBleachMap
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QItemBleachMap extends EntityPathBase<ItemBleachMap> {

    private static final long serialVersionUID = 1127889673L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemBleachMap itemBleachMap = new QItemBleachMap("itemBleachMap");

    public final StringPath chlorine = createString("chlorine");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final net.heronation.zeyo.rest.repository.item.QItem item;

    public final StringPath oxygen = createString("oxygen");

    public final StringPath useYn = createString("useYn");

    public QItemBleachMap(String variable) {
        this(ItemBleachMap.class, forVariable(variable), INITS);
    }

    public QItemBleachMap(Path<? extends ItemBleachMap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemBleachMap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemBleachMap(PathMetadata metadata, PathInits inits) {
        this(ItemBleachMap.class, metadata, inits);
    }

    public QItemBleachMap(Class<? extends ItemBleachMap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new net.heronation.zeyo.rest.repository.item.QItem(forProperty("item"), inits.get("item")) : null;
    }

}

