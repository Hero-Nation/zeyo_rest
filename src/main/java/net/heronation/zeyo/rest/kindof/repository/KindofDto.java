package net.heronation.zeyo.rest.kindof.repository;
 
import java.util.ArrayList;
import java.util.List;

import lombok.Value;
import net.heronation.zeyo.rest.bbs.repository.Bbs;
import net.heronation.zeyo.rest.cloth_color.repository.ClothColor;
import net.heronation.zeyo.rest.madein.repository.Madein;
import net.heronation.zeyo.rest.size_option.repository.SizeOption;
import net.heronation.zeyo.rest.warranty.repository.Warranty;


@Value
public class KindofDto{

 private List<Madein> madeins = new ArrayList<Madein>();
 private List<Warranty> warrantys = new ArrayList<Warranty>();
 private List<SizeOption> sizeOptions = new ArrayList<SizeOption>();
 private List<ClothColor> clothColors = new ArrayList<ClothColor>();
 private List<Bbs> bbss = new ArrayList<Bbs>();
 private Long id;




private String ktype;



private String kvalue;



private String useYn;
    
}