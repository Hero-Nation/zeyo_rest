package net.heronation.zeyo.rest.repository.item_material_map;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemMaterialMap is a Querydsl query type for ItemMaterialMap
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QItemMaterialMap extends EntityPathBase<ItemMaterialMap> {

    private static final long serialVersionUID = 1528222025L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemMaterialMap itemMaterialMap = new QItemMaterialMap("itemMaterialMap");

    public final StringPath contain = createString("contain");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final net.heronation.zeyo.rest.repository.item.QItem item;

    public final net.heronation.zeyo.rest.repository.material.QMaterial material;

    public final StringPath useLocatoin = createString("useLocatoin");

    public final StringPath useYn = createString("useYn");

    public QItemMaterialMap(String variable) {
        this(ItemMaterialMap.class, forVariable(variable), INITS);
    }

    public QItemMaterialMap(Path<? extends ItemMaterialMap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemMaterialMap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemMaterialMap(PathMetadata metadata, PathInits inits) {
        this(ItemMaterialMap.class, metadata, inits);
    }

    public QItemMaterialMap(Class<? extends ItemMaterialMap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new net.heronation.zeyo.rest.repository.item.QItem(forProperty("item"), inits.get("item")) : null;
        this.material = inits.isInitialized("material") ? new net.heronation.zeyo.rest.repository.material.QMaterial(forProperty("material")) : null;
    }

}

