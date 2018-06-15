package net.heronation.zeyo.rest.repository.item_shopmall_map;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemShopmallMap is a Querydsl query type for ItemShopmallMap
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QItemShopmallMap extends EntityPathBase<ItemShopmallMap> {

    private static final long serialVersionUID = 1734193897L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemShopmallMap itemShopmallMap = new QItemShopmallMap("itemShopmallMap");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final net.heronation.zeyo.rest.repository.item.QItem item;

    public final net.heronation.zeyo.rest.repository.shopmall.QShopmall shopmall;

    public final StringPath useYn = createString("useYn");

    public QItemShopmallMap(String variable) {
        this(ItemShopmallMap.class, forVariable(variable), INITS);
    }

    public QItemShopmallMap(Path<? extends ItemShopmallMap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemShopmallMap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemShopmallMap(PathMetadata metadata, PathInits inits) {
        this(ItemShopmallMap.class, metadata, inits);
    }

    public QItemShopmallMap(Class<? extends ItemShopmallMap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new net.heronation.zeyo.rest.repository.item.QItem(forProperty("item"), inits.get("item")) : null;
        this.shopmall = inits.isInitialized("shopmall") ? new net.heronation.zeyo.rest.repository.shopmall.QShopmall(forProperty("shopmall"), inits.get("shopmall")) : null;
    }

}

