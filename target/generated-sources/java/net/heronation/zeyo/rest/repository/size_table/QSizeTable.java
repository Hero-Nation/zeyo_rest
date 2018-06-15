package net.heronation.zeyo.rest.repository.size_table;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSizeTable is a Querydsl query type for SizeTable
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSizeTable extends EntityPathBase<SizeTable> {

    private static final long serialVersionUID = -1663660054L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSizeTable sizeTable = new QSizeTable("sizeTable");

    public final DateTimePath<org.joda.time.DateTime> createDt = createDateTime("createDt", org.joda.time.DateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final net.heronation.zeyo.rest.repository.item.QItem item;

    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = createDateTime("lastModifiedDate", java.time.LocalDateTime.class);

    public final StringPath useYn = createString("useYn");

    public final NumberPath<Long> version = createNumber("version", Long.class);

    public final StringPath visibleBasicYn = createString("visibleBasicYn");

    public final StringPath visibleCodeYn = createString("visibleCodeYn");

    public final StringPath visibleColorYn = createString("visibleColorYn");

    public final StringPath visibleFitInfoYn = createString("visibleFitInfoYn");

    public final StringPath visibleItemImageYn = createString("visibleItemImageYn");

    public final StringPath visibleLaundryInfoYn = createString("visibleLaundryInfoYn");

    public final StringPath visibleMeasureHowAYn = createString("visibleMeasureHowAYn");

    public final StringPath visibleMeasureHowBYn = createString("visibleMeasureHowBYn");

    public final StringPath visibleMeasureTableYn = createString("visibleMeasureTableYn");

    public final StringPath visibleNameYn = createString("visibleNameYn");

    public QSizeTable(String variable) {
        this(SizeTable.class, forVariable(variable), INITS);
    }

    public QSizeTable(Path<? extends SizeTable> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSizeTable(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSizeTable(PathMetadata metadata, PathInits inits) {
        this(SizeTable.class, metadata, inits);
    }

    public QSizeTable(Class<? extends SizeTable> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new net.heronation.zeyo.rest.repository.item.QItem(forProperty("item"), inits.get("item")) : null;
    }

}

