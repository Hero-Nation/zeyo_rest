package net.heronation.zeyo.rest.repository.fit_info;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFitInfo is a Querydsl query type for FitInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFitInfo extends EntityPathBase<FitInfo> {

    private static final long serialVersionUID = -658314232L;

    public static final QFitInfo fitInfo = new QFitInfo("fitInfo");

    public final DateTimePath<org.joda.time.DateTime> createDt = createDateTime("createDt", org.joda.time.DateTime.class);

    public final ListPath<net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOption, net.heronation.zeyo.rest.repository.fit_info_option.QFitInfoOption> fitInfoOptions = this.<net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOption, net.heronation.zeyo.rest.repository.fit_info_option.QFitInfoOption>createList("fitInfoOptions", net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOption.class, net.heronation.zeyo.rest.repository.fit_info_option.QFitInfoOption.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath metaDesc = createString("metaDesc");

    public final StringPath name = createString("name");

    public final ListPath<net.heronation.zeyo.rest.repository.sub_category_fit_info_map.SubCategoryFitInfoMap, net.heronation.zeyo.rest.repository.sub_category_fit_info_map.QSubCategoryFitInfoMap> subCategoryFitInfoMaps = this.<net.heronation.zeyo.rest.repository.sub_category_fit_info_map.SubCategoryFitInfoMap, net.heronation.zeyo.rest.repository.sub_category_fit_info_map.QSubCategoryFitInfoMap>createList("subCategoryFitInfoMaps", net.heronation.zeyo.rest.repository.sub_category_fit_info_map.SubCategoryFitInfoMap.class, net.heronation.zeyo.rest.repository.sub_category_fit_info_map.QSubCategoryFitInfoMap.class, PathInits.DIRECT2);

    public final StringPath useYn = createString("useYn");

    public QFitInfo(String variable) {
        super(FitInfo.class, forVariable(variable));
    }

    public QFitInfo(Path<? extends FitInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFitInfo(PathMetadata metadata) {
        super(FitInfo.class, metadata);
    }

}

