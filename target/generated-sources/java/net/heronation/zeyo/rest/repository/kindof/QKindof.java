package net.heronation.zeyo.rest.repository.kindof;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QKindof is a Querydsl query type for Kindof
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QKindof extends EntityPathBase<Kindof> {

    private static final long serialVersionUID = 1573042611L;

    public static final QKindof kindof = new QKindof("kindof");

    public final ListPath<net.heronation.zeyo.rest.repository.bbs.Bbs, net.heronation.zeyo.rest.repository.bbs.QBbs> bbss = this.<net.heronation.zeyo.rest.repository.bbs.Bbs, net.heronation.zeyo.rest.repository.bbs.QBbs>createList("bbss", net.heronation.zeyo.rest.repository.bbs.Bbs.class, net.heronation.zeyo.rest.repository.bbs.QBbs.class, PathInits.DIRECT2);

    public final ListPath<net.heronation.zeyo.rest.repository.cloth_color.ClothColor, net.heronation.zeyo.rest.repository.cloth_color.QClothColor> clothColors = this.<net.heronation.zeyo.rest.repository.cloth_color.ClothColor, net.heronation.zeyo.rest.repository.cloth_color.QClothColor>createList("clothColors", net.heronation.zeyo.rest.repository.cloth_color.ClothColor.class, net.heronation.zeyo.rest.repository.cloth_color.QClothColor.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath ktype = createString("ktype");

    public final StringPath kvalue = createString("kvalue");

    public final ListPath<net.heronation.zeyo.rest.repository.madein.Madein, net.heronation.zeyo.rest.repository.madein.QMadein> madeins = this.<net.heronation.zeyo.rest.repository.madein.Madein, net.heronation.zeyo.rest.repository.madein.QMadein>createList("madeins", net.heronation.zeyo.rest.repository.madein.Madein.class, net.heronation.zeyo.rest.repository.madein.QMadein.class, PathInits.DIRECT2);

    public final ListPath<net.heronation.zeyo.rest.repository.size_option.SizeOption, net.heronation.zeyo.rest.repository.size_option.QSizeOption> sizeOptions = this.<net.heronation.zeyo.rest.repository.size_option.SizeOption, net.heronation.zeyo.rest.repository.size_option.QSizeOption>createList("sizeOptions", net.heronation.zeyo.rest.repository.size_option.SizeOption.class, net.heronation.zeyo.rest.repository.size_option.QSizeOption.class, PathInits.DIRECT2);

    public final StringPath useYn = createString("useYn");

    public final ListPath<net.heronation.zeyo.rest.repository.warranty.Warranty, net.heronation.zeyo.rest.repository.warranty.QWarranty> warrantys = this.<net.heronation.zeyo.rest.repository.warranty.Warranty, net.heronation.zeyo.rest.repository.warranty.QWarranty>createList("warrantys", net.heronation.zeyo.rest.repository.warranty.Warranty.class, net.heronation.zeyo.rest.repository.warranty.QWarranty.class, PathInits.DIRECT2);

    public QKindof(String variable) {
        super(Kindof.class, forVariable(variable));
    }

    public QKindof(Path<? extends Kindof> path) {
        super(path.getType(), path.getMetadata());
    }

    public QKindof(PathMetadata metadata) {
        super(Kindof.class, metadata);
    }

}

