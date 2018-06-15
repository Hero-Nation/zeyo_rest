package net.heronation.zeyo.rest.repository.shopmall;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShopmall is a Querydsl query type for Shopmall
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QShopmall extends EntityPathBase<Shopmall> {

    private static final long serialVersionUID = -1228489389L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShopmall shopmall = new QShopmall("shopmall");

    public final DateTimePath<org.joda.time.DateTime> createDt = createDateTime("createDt", org.joda.time.DateTime.class);

    public final DateTimePath<org.joda.time.DateTime> deleteDt = createDateTime("deleteDt", org.joda.time.DateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMap, net.heronation.zeyo.rest.repository.item_shopmall_map.QItemShopmallMap> itemShopmallMaps = this.<net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMap, net.heronation.zeyo.rest.repository.item_shopmall_map.QItemShopmallMap>createList("itemShopmallMaps", net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMap.class, net.heronation.zeyo.rest.repository.item_shopmall_map.QItemShopmallMap.class, PathInits.DIRECT2);

    public final net.heronation.zeyo.rest.repository.member.QMember member;

    public final StringPath name = createString("name");

    public final StringPath useYn = createString("useYn");

    public QShopmall(String variable) {
        this(Shopmall.class, forVariable(variable), INITS);
    }

    public QShopmall(Path<? extends Shopmall> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShopmall(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShopmall(PathMetadata metadata, PathInits inits) {
        this(Shopmall.class, metadata, inits);
    }

    public QShopmall(Class<? extends Shopmall> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new net.heronation.zeyo.rest.repository.member.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

