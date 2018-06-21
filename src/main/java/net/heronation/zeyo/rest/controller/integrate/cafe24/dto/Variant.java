package net.heronation.zeyo.rest.controller.integrate.cafe24.dto;

import org.joda.time.DateTime;

import lombok.Data;
import net.heronation.zeyo.rest.repository.item.ItemRepository;
import net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMapRepository;
import net.heronation.zeyo.rest.repository.member.MemberRepository;
import net.heronation.zeyo.rest.repository.shopmall.ShopmallRepository;

@Data
public class Variant {
//    "shop_no": 1,
//    "variant_code": "P000000T000C",
//    "option": [
//        {
//            "name": "색상",
//            "value": "블랙"
//        },
//        {
//            "name": "사이즈",
//            "value": "FREE"
//        }
//    ],
//    "custom_variant_code": "",
//    "display": "T",
//    "selling": "T",
//    "additional_amount": "0.00"
	
	
	private long shop_no;
	private String variant_code;
	private VariantOption[] option;
	private String custom_variant_code;
	private String display;
	private String selling;
	private String additional_amount;
	
	
}
