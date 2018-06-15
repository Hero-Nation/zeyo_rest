package net.heronation.zeyo.rest.repository.size_option;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSizeOption is a Querydsl query type for SizeOption
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSizeOption extends EntityPathBase<SizeOption> {

    private static final long serialVersionUID = -571205204L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSizeOption sizeOption = new QSizeOption("sizeOption");

    public final net.heronation.zeyo.rest.repository.category.QCategory category;

    public final DateTimePath<org.joda.time.DateTime> createDt = createDateTime("createDt", org.joda.time.DateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<net.heronation.zeyo.rest.repository.item_size_option_map.ItemSizeOptionMap, net.heronation.zeyo.rest.repository.item_size_option_map.QItemSizeOptionMap> itemSizeOptionMaps = this.<net.heronation.zeyo.rest.repository.item_size_option_map.ItemSizeOptionMap, net.heronation.zeyo.rest.repository.item_size_option_map.QItemSizeOptionMap>createList("itemSizeOptionMaps", net.heronation.zeyo.rest.repository.item_size_option_map.ItemSizeOptionMap.class, net.heronation.zeyo.rest.repository.item_size_option_map.QItemSizeOptionMap.class, PathInits.DIRECT2);

    public final net.heronation.zeyo.rest.repository.kindof.QKindof kindof;

    public final StringPath name = createString("name");

    public final net.heronation.zeyo.rest.repository.sub_category.QSubCategory subCategory;

    public final StringPath useYn = createString("useYn");

    public QSizeOption(String variable) {
        this(SizeOption.class, forVariable(variable), INITS);
    }

    public QSizeOption(Path<? extends SizeOption> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSizeOption(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSizeOption(PathMetadata metadata, PathInits inits) {
        this(SizeOption.class, metadata, inits);
    }

    public QSizeOption(Class<? extends SizeOption> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new net.heronation.zeyo.rest.repository.category.QCategory(forProperty("category")) : null;
        this.kindof = inits.isInitialized("kindof") ? new net.heronation.zeyo.rest.repository.kindof.QKindof(forProperty("kindof")) : null;
        this.subCategory = inits.isInitialized("subCategory") ? new net.heronation.zeyo.rest.repository.sub_category.QSubCategory(forProperty("subCategory"), inits.get("subCategory")) : null;
    }

}

