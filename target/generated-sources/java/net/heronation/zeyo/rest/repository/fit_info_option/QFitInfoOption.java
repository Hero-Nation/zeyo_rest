package net.heronation.zeyo.rest.repository.fit_info_option;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFitInfoOption is a Querydsl query type for FitInfoOption
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFitInfoOption extends EntityPathBase<FitInfoOption> {

    private static final long serialVersionUID = 467940451L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFitInfoOption fitInfoOption = new QFitInfoOption("fitInfoOption");

    public final net.heronation.zeyo.rest.repository.fit_info.QFitInfo fitInfo;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath useYn = createString("useYn");

    public QFitInfoOption(String variable) {
        this(FitInfoOption.class, forVariable(variable), INITS);
    }

    public QFitInfoOption(Path<? extends FitInfoOption> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFitInfoOption(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFitInfoOption(PathMetadata metadata, PathInits inits) {
        this(FitInfoOption.class, metadata, inits);
    }

    public QFitInfoOption(Class<? extends FitInfoOption> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fitInfo = inits.isInitialized("fitInfo") ? new net.heronation.zeyo.rest.repository.fit_info.QFitInfo(forProperty("fitInfo")) : null;
    }

}

