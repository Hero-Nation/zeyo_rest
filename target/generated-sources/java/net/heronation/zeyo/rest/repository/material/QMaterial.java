package net.heronation.zeyo.rest.repository.material;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMaterial is a Querydsl query type for Material
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMaterial extends EntityPathBase<Material> {

    private static final long serialVersionUID = 1530228211L;

    public static final QMaterial material = new QMaterial("material");

    public final DateTimePath<org.joda.time.DateTime> createDt = createDateTime("createDt", org.joda.time.DateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image = createString("image");

    public final ListPath<net.heronation.zeyo.rest.repository.item_material_map.ItemMaterialMap, net.heronation.zeyo.rest.repository.item_material_map.QItemMaterialMap> itemMaterialMaps = this.<net.heronation.zeyo.rest.repository.item_material_map.ItemMaterialMap, net.heronation.zeyo.rest.repository.item_material_map.QItemMaterialMap>createList("itemMaterialMaps", net.heronation.zeyo.rest.repository.item_material_map.ItemMaterialMap.class, net.heronation.zeyo.rest.repository.item_material_map.QItemMaterialMap.class, PathInits.DIRECT2);

    public final StringPath metaDesc = createString("metaDesc");

    public final StringPath name = createString("name");

    public final StringPath useYn = createString("useYn");

    public QMaterial(String variable) {
        super(Material.class, forVariable(variable));
    }

    public QMaterial(Path<? extends Material> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMaterial(PathMetadata metadata) {
        super(Material.class, metadata);
    }

}

