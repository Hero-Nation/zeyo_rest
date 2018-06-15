package net.heronation.zeyo.rest.repository.item_ironing_map;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemIroningMap is a Querydsl query type for ItemIroningMap
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QItemIroningMap extends EntityPathBase<ItemIroningMap> {

    private static final long serialVersionUID = -1568420547L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemIroningMap itemIroningMap = new QItemIroningMap("itemIroningMap");

    public final StringPath addprotection = createString("addprotection");

    public final StringPath endTemp = createString("endTemp");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath ironcan = createString("ironcan");

    public final net.heronation.zeyo.rest.repository.item.QItem item;

    public final StringPath startTemp = createString("startTemp");

    public final StringPath useYn = createString("useYn");

    public QItemIroningMap(String variable) {
        this(ItemIroningMap.class, forVariable(variable), INITS);
    }

    public QItemIroningMap(Path<? extends ItemIroningMap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemIroningMap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemIroningMap(PathMetadata metadata, PathInits inits) {
        this(ItemIroningMap.class, metadata, inits);
    }

    public QItemIroningMap(Class<? extends ItemIroningMap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new net.heronation.zeyo.rest.repository.item.QItem(forProperty("item"), inits.get("item")) : null;
    }

}

