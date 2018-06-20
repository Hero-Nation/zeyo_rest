package net.heronation.zeyo.rest.controller.integrate.cafe24.dto;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import lombok.Data;

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
}
