package net.heronation.zeyo.rest.repository.bbs;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBbs is a Querydsl query type for Bbs
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBbs extends EntityPathBase<Bbs> {

    private static final long serialVersionUID = 1554208221L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBbs bbs = new QBbs("bbs");

    public final StringPath attach_file = createString("attach_file");

    public final StringPath bbsContent = createString("bbsContent");

    public final DateTimePath<org.joda.time.DateTime> createDt = createDateTime("createDt", org.joda.time.DateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final net.heronation.zeyo.rest.repository.kindof.QKindof kindof;

    public final net.heronation.zeyo.rest.repository.member.QMember member;

    public final StringPath replyContent = createString("replyContent");

    public final DateTimePath<org.joda.time.DateTime> replyDt = createDateTime("replyDt", org.joda.time.DateTime.class);

    public final StringPath status = createString("status");

    public final StringPath title = createString("title");

    public final StringPath useYn = createString("useYn");

    public QBbs(String variable) {
        this(Bbs.class, forVariable(variable), INITS);
    }

    public QBbs(Path<? extends Bbs> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBbs(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBbs(PathMetadata metadata, PathInits inits) {
        this(Bbs.class, metadata, inits);
    }

    public QBbs(Class<? extends Bbs> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.kindof = inits.isInitialized("kindof") ? new net.heronation.zeyo.rest.repository.kindof.QKindof(forProperty("kindof")) : null;
        this.member = inits.isInitialized("member") ? new net.heronation.zeyo.rest.repository.member.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

