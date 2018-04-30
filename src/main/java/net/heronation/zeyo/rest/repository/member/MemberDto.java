package net.heronation.zeyo.rest.repository.member;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Value;

import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory;import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory;
import net.heronation.zeyo.rest.repository.bbs.Bbs;


@Value
public class MemberDto{
 
        private Long id;




private String memberId;



private String name;



private String password;



private String phone;



private String email;



private CompanyNoHistory companyNoHistory;




private String manager;



private String managerEmail;



private String managerPhone;



private String createDt;



private String deleteDt;



private String adminYn;



private String useYn;



private List<Item> items = new ArrayList<Item>();
 private List<Brand> brands = new ArrayList<Brand>();
 private List<Shopmall> shopmalls = new ArrayList<Shopmall>();
 private List<CompanyNoHistory> companyNoHistorys = new ArrayList<CompanyNoHistory>();
 private List<Bbs> bbss = new ArrayList<Bbs>();
    
}