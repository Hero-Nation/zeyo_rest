package net.heronation.zeyo.rest.repository.email_validation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEmailValidation is a Querydsl query type for EmailValidation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmailValidation extends EntityPathBase<EmailValidation> {

    private static final long serialVersionUID = -1180559938L;

    public static final QEmailValidation emailValidation = new QEmailValidation("emailValidation");

    public final DateTimePath<org.joda.time.DateTime> createDt = createDateTime("createDt", org.joda.time.DateTime.class);

    public final StringPath email = createString("email");

    public final StringPath otp = createString("otp");

    public QEmailValidation(String variable) {
        super(EmailValidation.class, forVariable(variable));
    }

    public QEmailValidation(Path<? extends EmailValidation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmailValidation(PathMetadata metadata) {
        super(EmailValidation.class, metadata);
    }

}

