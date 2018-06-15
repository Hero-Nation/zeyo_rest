package net.heronation.zeyo.rest.repository.madein;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMadein is a Querydsl query type for Madein
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMadein extends EntityPathBase<Madein> {

    private static final long serialVersionUID = -386315565L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMadein madein = new QMadein("madein");

    public final DateTimePath<org.joda.time.DateTime> createDt = createDateTime("createDt", org.joda.time.DateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<net.heronation.zeyo.rest.repository.item.Item, net.heronation.zeyo.rest.repository.item.QItem> items = this.<net.heronation.zeyo.rest.repository.item.Item, net.heronation.zeyo.rest.repository.item.QItem>createList("items", net.heronation.zeyo.rest.repository.item.Item.class, net.heronation.zeyo.rest.repository.item.QItem.class, PathInits.DIRECT2);

    public final net.heronation.zeyo.rest.repository.kindof.QKindof kindof;

    public final StringPath name = createString("name");

    public final StringPath useYn = createString("useYn");

    public QMadein(String variable) {
        this(Madein.class, forVariable(variable), INITS);
    }

    public QMadein(Path<? extends Madein> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMadein(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMadein(PathMetadata metadata, PathInits inits) {
        this(Madein.class, metadata, inits);
    }

    public QMadein(Class<? extends Madein> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.kindof = inits.isInitialized("kindof") ? new net.heronation.zeyo.rest.repository.kindof.QKindof(forProperty("kindof")) : null;
    }

}

