package net.heronation.zeyo.rest.repository.brand;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBrand is a Querydsl query type for Brand
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBrand extends EntityPathBase<Brand> {

    private static final long serialVersionUID = -2004335163L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBrand brand = new QBrand("brand");

    public final DateTimePath<org.joda.time.DateTime> createDt = createDateTime("createDt", org.joda.time.DateTime.class);

    public final DateTimePath<org.joda.time.DateTime> deleteDt = createDateTime("deleteDt", org.joda.time.DateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<net.heronation.zeyo.rest.repository.item.Item, net.heronation.zeyo.rest.repository.item.QItem> items = this.<net.heronation.zeyo.rest.repository.item.Item, net.heronation.zeyo.rest.repository.item.QItem>createList("items", net.heronation.zeyo.rest.repository.item.Item.class, net.heronation.zeyo.rest.repository.item.QItem.class, PathInits.DIRECT2);

    public final net.heronation.zeyo.rest.repository.member.QMember member;

    public final StringPath name = createString("name");

    public final StringPath useYn = createString("useYn");

    public QBrand(String variable) {
        this(Brand.class, forVariable(variable), INITS);
    }

    public QBrand(Path<? extends Brand> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBrand(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBrand(PathMetadata metadata, PathInits inits) {
        this(Brand.class, metadata, inits);
    }

    public QBrand(Class<? extends Brand> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new net.heronation.zeyo.rest.repository.member.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

