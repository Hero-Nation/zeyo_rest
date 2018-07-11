package net.heronation.zeyo.rest.member.repository;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import lombok.Data;
import net.heronation.zeyo.rest.bbs.repository.Bbs;
import net.heronation.zeyo.rest.brand.repository.Brand;
import net.heronation.zeyo.rest.company_no_history.repository.CompanyNoHistory;
import net.heronation.zeyo.rest.item.repository.Item;
import net.heronation.zeyo.rest.shopmall.repository.Shopmall;

@Data
public class MemberDto {

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

	private DateTime createDt;

	private DateTime deleteDt;

	private String adminYn;

	private String useYn;

	private String cp_no;
	
	private String confirm_no;
	
	private String old_pw;
	
	private String new_pw; 
	
	private String mng_phone;
	
	private String mng_name;
	
	private String flag;
	 
	
	private List<Item> items = new ArrayList<Item>();
	private List<Brand> brands = new ArrayList<Brand>();
	private List<Shopmall> shopmalls = new ArrayList<Shopmall>();
	private List<CompanyNoHistory> companyNoHistorys = new ArrayList<CompanyNoHistory>();
	private List<Bbs> bbss = new ArrayList<Bbs>();

}