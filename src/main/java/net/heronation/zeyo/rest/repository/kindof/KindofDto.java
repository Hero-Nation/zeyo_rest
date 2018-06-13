package net.heronation.zeyo.rest.repository.kindof;
 
import java.util.ArrayList;
import java.util.List;

import lombok.Value;
import net.heronation.zeyo.rest.repository.bbs.Bbs;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColor;
import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;
import net.heronation.zeyo.rest.repository.warranty.Warranty;


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