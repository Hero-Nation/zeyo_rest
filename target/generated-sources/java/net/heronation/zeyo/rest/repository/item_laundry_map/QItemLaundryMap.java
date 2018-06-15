package net.heronation.zeyo.rest.repository.item_laundry_map;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemLaundryMap is a Querydsl query type for ItemLaundryMap
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QItemLaundryMap extends EntityPathBase<ItemLaundryMap> {

    private static final long serialVersionUID = 257726839L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemLaundryMap itemLaundryMap = new QItemLaundryMap("itemLaundryMap");

    public final StringPath detergent = createString("detergent");

    public final StringPath hand = createString("hand");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath intensity = createString("intensity");

    public final net.heronation.zeyo.rest.repository.item.QItem item;

    public final StringPath machine = createString("machine");

    public final StringPath useYn = createString("useYn");

    public final StringPath water = createString("water");

    public final StringPath waterTemp = createString("waterTemp");

    public QItemLaundryMap(String variable) {
        this(ItemLaundryMap.class, forVariable(variable), INITS);
    }

    public QItemLaundryMap(Path<? extends ItemLaundryMap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemLaundryMap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemLaundryMap(PathMetadata metadata, PathInits inits) {
        this(ItemLaundryMap.class, metadata, inits);
    }

    public QItemLaundryMap(Class<? extends ItemLaundryMap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new net.heronation.zeyo.rest.repository.item.QItem(forProperty("item"), inits.get("item")) : null;
    }

}

