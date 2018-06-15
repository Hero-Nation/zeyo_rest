package net.heronation.zeyo.rest.repository.warranty;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWarranty is a Querydsl query type for Warranty
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QWarranty extends EntityPathBase<Warranty> {

    private static final long serialVersionUID = 384757139L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWarranty warranty = new QWarranty("warranty");

    public final DateTimePath<org.joda.time.DateTime> createDt = createDateTime("createDt", org.joda.time.DateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<net.heronation.zeyo.rest.repository.item.Item, net.heronation.zeyo.rest.repository.item.QItem> items = this.<net.heronation.zeyo.rest.repository.item.Item, net.heronation.zeyo.rest.repository.item.QItem>createList("items", net.heronation.zeyo.rest.repository.item.Item.class, net.heronation.zeyo.rest.repository.item.QItem.class, PathInits.DIRECT2);

    public final net.heronation.zeyo.rest.repository.kindof.QKindof kindof;

    public final StringPath scope = createString("scope");

    public final StringPath useYn = createString("useYn");

    public QWarranty(String variable) {
        this(Warranty.class, forVariable(variable), INITS);
    }

    public QWarranty(Path<? extends Warranty> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWarranty(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWarranty(PathMetadata metadata, PathInits inits) {
        this(Warranty.class, metadata, inits);
    }

    public QWarranty(Class<? extends Warranty> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.kindof = inits.isInitialized("kindof") ? new net.heronation.zeyo.rest.repository.kindof.QKindof(forProperty("kindof")) : null;
    }

}

