package net.heronation.zeyo.rest.repository.item_drymethod_map;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemDrymethodMap is a Querydsl query type for ItemDrymethodMap
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QItemDrymethodMap extends EntityPathBase<ItemDrymethodMap> {

    private static final long serialVersionUID = -56623143L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemDrymethodMap itemDrymethodMap = new QItemDrymethodMap("itemDrymethodMap");

    public final StringPath dryMode = createString("dryMode");

    public final StringPath handDry = createString("handDry");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final net.heronation.zeyo.rest.repository.item.QItem item;

    public final StringPath machineDry = createString("machineDry");

    public final StringPath natureDry = createString("natureDry");

    public final StringPath useYn = createString("useYn");

    public QItemDrymethodMap(String variable) {
        this(ItemDrymethodMap.class, forVariable(variable), INITS);
    }

    public QItemDrymethodMap(Path<? extends ItemDrymethodMap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemDrymethodMap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemDrymethodMap(PathMetadata metadata, PathInits inits) {
        this(ItemDrymethodMap.class, metadata, inits);
    }

    public QItemDrymethodMap(Class<? extends ItemDrymethodMap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new net.heronation.zeyo.rest.repository.item.QItem(forProperty("item"), inits.get("item")) : null;
    }

}

