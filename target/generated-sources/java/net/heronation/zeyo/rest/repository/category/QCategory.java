package net.heronation.zeyo.rest.repository.category;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCategory is a Querydsl query type for Category
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCategory extends EntityPathBase<Category> {

    private static final long serialVersionUID = 1597329363L;

    public static final QCategory category = new QCategory("category");

    public final DateTimePath<org.joda.time.DateTime> createDt = createDateTime("createDt", org.joda.time.DateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<net.heronation.zeyo.rest.repository.item.Item, net.heronation.zeyo.rest.repository.item.QItem> items = this.<net.heronation.zeyo.rest.repository.item.Item, net.heronation.zeyo.rest.repository.item.QItem>createList("items", net.heronation.zeyo.rest.repository.item.Item.class, net.heronation.zeyo.rest.repository.item.QItem.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final ListPath<net.heronation.zeyo.rest.repository.size_option.SizeOption, net.heronation.zeyo.rest.repository.size_option.QSizeOption> sizeOptions = this.<net.heronation.zeyo.rest.repository.size_option.SizeOption, net.heronation.zeyo.rest.repository.size_option.QSizeOption>createList("sizeOptions", net.heronation.zeyo.rest.repository.size_option.SizeOption.class, net.heronation.zeyo.rest.repository.size_option.QSizeOption.class, PathInits.DIRECT2);

    public final ListPath<net.heronation.zeyo.rest.repository.sub_category.SubCategory, net.heronation.zeyo.rest.repository.sub_category.QSubCategory> subCategorys = this.<net.heronation.zeyo.rest.repository.sub_category.SubCategory, net.heronation.zeyo.rest.repository.sub_category.QSubCategory>createList("subCategorys", net.heronation.zeyo.rest.repository.sub_category.SubCategory.class, net.heronation.zeyo.rest.repository.sub_category.QSubCategory.class, PathInits.DIRECT2);

    public final StringPath useYn = createString("useYn");

    public QCategory(String variable) {
        super(Category.class, forVariable(variable));
    }

    public QCategory(Path<? extends Category> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategory(PathMetadata metadata) {
        super(Category.class, metadata);
    }

}

