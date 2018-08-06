package net.heronation.zeyo.rest.member.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.company_no_history.controller.CompanyNoHistory;import net.heronation.zeyo.rest.item.controller.Item;
import net.heronation.zeyo.rest.brand.controller.Brand;
import net.heronation.zeyo.rest.shopmall.controller.Shopmall;
import net.heronation.zeyo.rest.company_no_history.controller.CompanyNoHistory;
import net.heronation.zeyo.rest.bbs.controller.Bbs;


@Data
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



private String useYn;



private List<Item> items = new ArrayList<Item>();
 private List<Brand> brands = new ArrayList<Brand>();
 private List<Shopmall> shopmalls = new ArrayList<Shopmall>();
 private List<CompanyNoHistory> companyNoHistorys = new ArrayList<CompanyNoHistory>();
 private List<Bbs> bbss = new ArrayList<Bbs>();
    
}