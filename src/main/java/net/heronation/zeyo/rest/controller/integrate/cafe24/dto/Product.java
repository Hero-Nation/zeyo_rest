package net.heronation.zeyo.rest.controller.integrate.cafe24.dto;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.item.ItemRepository;
import net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMapRepository;
import net.heronation.zeyo.rest.repository.member.MemberRepository;
import net.heronation.zeyo.rest.repository.shopmall.ShopmallRepository;

@Data
public class Product {
	private Long shop_no;
	private Long product_no;
	private String product_code;
	private String custom_product_code;
	private String product_name;
	private String eng_product_name;
	private String supply_product_name;
	private String model_name;
	private String price;
	private String retail_price;
	private String supply_price;
	private String display;
	private String selling;
	private String product_condition;
	private String summary_description;
	private String product_tag;
	private String margin_rate;
	private String tax_type;
	private Long tax_amount;
	private String price_content;
	private String buy_limit_type;
	private Long buy_unit;
	private Long minimum_quantity;
	private Long maximum_quantity;
	private String mileage_amount;
	private String except_member_mileage;
	private String adult_certification;
	private String detail_image;
	private String list_image;
	private String tiny_image;
	private String small_image;
	private String has_option;
	private String option_type;
	private String manufacturer_code;
	private String trend_code;
	private String brand_code;
	private String supplier_code;
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime made_date;
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime release_date;
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime expiration_date;
	private Long origin_place_code;
	private String origin_place_value;
	private String icon_show_period;
	private String icon;
	private String hscode;
	private String product_weight;
	private String product_material;
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime created_date;
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime updated_date;
	private String english_product_material;
	private String clearance_category_eng;
	private String clearance_category_kor;
	private String cloth_fabric;
	private ListIcon list_icon;
	private String select_one_by_option;
	private String approve_status;
	
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	ItemShopmallMapRepository itemShopmallMapRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	ShopmallRepository shopmallRepository;
	
	
	 
	public Item convertToEntity( ) {
		
//        {
//            "shop_no": 1,
//            "product_no": 47,
//            "product_code": "P00000BU",
//            "custom_product_code": "",
//            "product_name": "베리드반폴NT",
//            "eng_product_name": "",
//            "supply_product_name": "",
//            "model_name": "",
//            "price": "17800.00",
//            "retail_price": "0.00",
//            "supply_price": "17800.00",
//            "display": "T",
//            "selling": "T",
//            "product_condition": "N",
//            "summary_description": "",
//            "product_tag": "",
//            "margin_rate": "10.00",
//            "tax_type": "A",
//            "tax_amount": 10,
//            "price_content": null,
//            "buy_limit_type": "F",
//            "buy_unit": 1,
//            "minimum_quantity": 1,
//            "maximum_quantity": 0,
//            "mileage_amount": null,
//            "except_member_mileage": "F",
//            "adult_certification": "F",
//            "detail_image": "http://heronation.cafe24.com/web/product/big/201703/47_shop1_917681.png",
//            "list_image": "http://heronation.cafe24.com/web/product/medium/201703/47_shop1_917681.png",
//            "tiny_image": "http://heronation.cafe24.com/web/product/tiny/201703/47_shop1_917681.png",
//            "small_image": "http://heronation.cafe24.com/web/product/small/201703/47_shop1_917681.png",
//            "has_option": "T",
//            "option_type": "S",
//            "manufacturer_code": "M0000000",
//            "trend_code": "T0000000",
//            "brand_code": "B0000000",
//            "supplier_code": "S0000000",
//            "made_date": "",
//            "release_date": "",
//            "expiration_date": null,
//            "origin_place_code": 1798,
//            "origin_place_value": "",
//            "icon_show_period": null,
//            "icon": null,
//            "hscode": "",
//            "product_weight": "1.00",
//            "product_material": "",
//            "created_date": "2017-03-08T17:20:17+09:00",
//            "updated_date": "2017-03-09T10:46:22+09:00",
//            "english_product_material": null,
//            "clearance_category_eng": null,
//            "clearance_category_kor": null,
//            "cloth_fabric": null,
//            "list_icon": {
//                "soldout_icon": false,
//                "recommend_icon": false,
//                "new_icon": false
//            },
//            "select_one_by_option": "F",
//            "approve_status": ""
//        }
		

		Item i = new Item();
		i.setShopProductId(product_no+"");
//		i.setBrand(brand);
//		i.setCategory(category);
//		i.setSubCategory(subCategory);
		i.setCode(product_code);
		i.setCreateDt(new DateTime());
		i.setShop_image(detail_image);
		i.setImageMode("D"); 
		i.setName(product_name);
		i.setPrice(Integer.valueOf(price.split("[.]")[0]));
		//i.setSizeMeasureImage(sizeMeasureImage);
		i.setSizeMeasureMode("A");
		
//		i.setDrycleaningYn(drycleaningYn);
//		i.setDrymethodYn(drymethodYn);
//		i.setBleachYn(bleachYn);
//		i.setSizeTableYn(sizeTableYn);
		i.setUseYn("Y");
//		i.setIroningYn(ironingYn);
//		i.setLaundryYn(laundryYn);
//		i.setLinkYn(linkYn);

		return i;
		
	}
}
