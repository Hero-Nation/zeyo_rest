package net.heronation.zeyo.rest.repository.company_no_history;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCompanyNoHistory is a Querydsl query type for CompanyNoHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCompanyNoHistory extends EntityPathBase<CompanyNoHistory> {

    private static final long serialVersionUID = 666554961L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCompanyNoHistory companyNoHistory = new QCompanyNoHistory("companyNoHistory");

    public final StringPath beforeNo = createString("beforeNo");

    public final DateTimePath<org.joda.time.DateTime> changeDt = createDateTime("changeDt", org.joda.time.DateTime.class);

    public final StringPath companyNo = createString("companyNo");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final net.heronation.zeyo.rest.repository.member.QMember member;

    public final ListPath<net.heronation.zeyo.rest.repository.member.Member, net.heronation.zeyo.rest.repository.member.QMember> members = this.<net.heronation.zeyo.rest.repository.member.Member, net.heronation.zeyo.rest.repository.member.QMember>createList("members", net.heronation.zeyo.rest.repository.member.Member.class, net.heronation.zeyo.rest.repository.member.QMember.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public QCompanyNoHistory(String variable) {
        this(CompanyNoHistory.class, forVariable(variable), INITS);
    }

    public QCompanyNoHistory(Path<? extends CompanyNoHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCompanyNoHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCompanyNoHistory(PathMetadata metadata, PathInits inits) {
        this(CompanyNoHistory.class, metadata, inits);
    }

    public QCompanyNoHistory(Class<? extends CompanyNoHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new net.heronation.zeyo.rest.repository.member.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

