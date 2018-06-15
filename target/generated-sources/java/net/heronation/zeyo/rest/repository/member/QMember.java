package net.heronation.zeyo.rest.repository.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1247988013L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final StringPath adminYn = createString("adminYn");

    public final ListPath<net.heronation.zeyo.rest.repository.bbs.Bbs, net.heronation.zeyo.rest.repository.bbs.QBbs> bbss = this.<net.heronation.zeyo.rest.repository.bbs.Bbs, net.heronation.zeyo.rest.repository.bbs.QBbs>createList("bbss", net.heronation.zeyo.rest.repository.bbs.Bbs.class, net.heronation.zeyo.rest.repository.bbs.QBbs.class, PathInits.DIRECT2);

    public final ListPath<net.heronation.zeyo.rest.repository.brand.Brand, net.heronation.zeyo.rest.repository.brand.QBrand> brands = this.<net.heronation.zeyo.rest.repository.brand.Brand, net.heronation.zeyo.rest.repository.brand.QBrand>createList("brands", net.heronation.zeyo.rest.repository.brand.Brand.class, net.heronation.zeyo.rest.repository.brand.QBrand.class, PathInits.DIRECT2);

    public final net.heronation.zeyo.rest.repository.company_no_history.QCompanyNoHistory companyNoHistory;

    public final ListPath<net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory, net.heronation.zeyo.rest.repository.company_no_history.QCompanyNoHistory> companyNoHistorys = this.<net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory, net.heronation.zeyo.rest.repository.company_no_history.QCompanyNoHistory>createList("companyNoHistorys", net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory.class, net.heronation.zeyo.rest.repository.company_no_history.QCompanyNoHistory.class, PathInits.DIRECT2);

    public final StringPath confirm_no = createString("confirm_no");

    public final DateTimePath<org.joda.time.DateTime> createDt = createDateTime("createDt", org.joda.time.DateTime.class);

    public final DateTimePath<org.joda.time.DateTime> deleteDt = createDateTime("deleteDt", org.joda.time.DateTime.class);

    public final StringPath email = createString("email");

    public final StringPath email_noti_yn = createString("email_noti_yn");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<net.heronation.zeyo.rest.repository.item.Item, net.heronation.zeyo.rest.repository.item.QItem> items = this.<net.heronation.zeyo.rest.repository.item.Item, net.heronation.zeyo.rest.repository.item.QItem>createList("items", net.heronation.zeyo.rest.repository.item.Item.class, net.heronation.zeyo.rest.repository.item.QItem.class, PathInits.DIRECT2);

    public final StringPath manager = createString("manager");

    public final StringPath managerEmail = createString("managerEmail");

    public final StringPath managerPhone = createString("managerPhone");

    public final StringPath memberId = createString("memberId");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final ListPath<net.heronation.zeyo.rest.repository.shopmall.Shopmall, net.heronation.zeyo.rest.repository.shopmall.QShopmall> shopmalls = this.<net.heronation.zeyo.rest.repository.shopmall.Shopmall, net.heronation.zeyo.rest.repository.shopmall.QShopmall>createList("shopmalls", net.heronation.zeyo.rest.repository.shopmall.Shopmall.class, net.heronation.zeyo.rest.repository.shopmall.QShopmall.class, PathInits.DIRECT2);

    public final StringPath useYn = createString("useYn");

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.companyNoHistory = inits.isInitialized("companyNoHistory") ? new net.heronation.zeyo.rest.repository.company_no_history.QCompanyNoHistory(forProperty("companyNoHistory"), inits.get("companyNoHistory")) : null;
    }

}

