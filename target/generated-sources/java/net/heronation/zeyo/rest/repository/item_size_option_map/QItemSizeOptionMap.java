package net.heronation.zeyo.rest.repository.item_size_option_map;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemSizeOptionMap is a Querydsl query type for ItemSizeOptionMap
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QItemSizeOptionMap extends EntityPathBase<ItemSizeOptionMap> {

    private static final long serialVersionUID = -2079988860L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemSizeOptionMap itemSizeOptionMap = new QItemSizeOptionMap("itemSizeOptionMap");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final net.heronation.zeyo.rest.repository.item.QItem item;

    public final StringPath optionValue = createString("optionValue");

    public final net.heronation.zeyo.rest.repository.size_option.QSizeOption sizeOption;

    public final StringPath useYn = createString("useYn");

    public QItemSizeOptionMap(String variable) {
        this(ItemSizeOptionMap.class, forVariable(variable), INITS);
    }

    public QItemSizeOptionMap(Path<? extends ItemSizeOptionMap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemSizeOptionMap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemSizeOptionMap(PathMetadata metadata, PathInits inits) {
        this(ItemSizeOptionMap.class, metadata, inits);
    }

    public QItemSizeOptionMap(Class<? extends ItemSizeOptionMap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new net.heronation.zeyo.rest.repository.item.QItem(forProperty("item"), inits.get("item")) : null;
        this.sizeOption = inits.isInitialized("sizeOption") ? new net.heronation.zeyo.rest.repository.size_option.QSizeOption(forProperty("sizeOption"), inits.get("sizeOption")) : null;
    }

}

