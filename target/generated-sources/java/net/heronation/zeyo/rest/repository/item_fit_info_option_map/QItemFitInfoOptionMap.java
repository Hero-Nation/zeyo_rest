package net.heronation.zeyo.rest.repository.item_fit_info_option_map;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemFitInfoOptionMap is a Querydsl query type for ItemFitInfoOptionMap
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QItemFitInfoOptionMap extends EntityPathBase<ItemFitInfoOptionMap> {

    private static final long serialVersionUID = -1809469435L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemFitInfoOptionMap itemFitInfoOptionMap = new QItemFitInfoOptionMap("itemFitInfoOptionMap");

    public final net.heronation.zeyo.rest.repository.fit_info_option.QFitInfoOption fitInfoOption;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final net.heronation.zeyo.rest.repository.item.QItem item;

    public final StringPath useYn = createString("useYn");

    public QItemFitInfoOptionMap(String variable) {
        this(ItemFitInfoOptionMap.class, forVariable(variable), INITS);
    }

    public QItemFitInfoOptionMap(Path<? extends ItemFitInfoOptionMap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemFitInfoOptionMap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemFitInfoOptionMap(PathMetadata metadata, PathInits inits) {
        this(ItemFitInfoOptionMap.class, metadata, inits);
    }

    public QItemFitInfoOptionMap(Class<? extends ItemFitInfoOptionMap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fitInfoOption = inits.isInitialized("fitInfoOption") ? new net.heronation.zeyo.rest.repository.fit_info_option.QFitInfoOption(forProperty("fitInfoOption"), inits.get("fitInfoOption")) : null;
        this.item = inits.isInitialized("item") ? new net.heronation.zeyo.rest.repository.item.QItem(forProperty("item"), inits.get("item")) : null;
    }

}

