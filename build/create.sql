create table bbs (id bigint not null, bbs_content varchar(255), create_dt datetime, reply_content varchar(255), reply_dt datetime, status varchar(255), title varchar(255), use_yn varchar(255), kindof_id bigint, member_id bigint, primary key (id))
create table brand (id bigint not null, create_dt datetime, delete_dt datetime, name varchar(255), use_yn varchar(255), member_id bigint, primary key (id))
create table category (id bigint not null, create_dt datetime, name varchar(255), parent_id bigint, use_yn varchar(255), primary key (id))
create table cloth_color (id bigint not null, create_dt datetime, name varchar(255), use_yn varchar(255), kindof_id bigint, primary key (id))
create table company_no_history (id bigint not null, before_no varchar(255), change_dt datetime, company_no varchar(255), name varchar(255), member_id bigint, primary key (id))
create table email_validation (email varchar(255) not null, create_dt datetime, otp varchar(255), primary key (email))
create table fit_info (id bigint not null, create_dt datetime, meta_desc varchar(255), name varchar(255), use_yn varchar(255), primary key (id))
create table fit_info_option (id bigint not null, name varchar(255), use_yn varchar(255), fit_info_id bigint, primary key (id))
create table item (id bigint not null, bleach_yn varchar(255), code varchar(255), create_dt datetime, drycleaning_yn varchar(255), drymethod_yn varchar(255), image varchar(255), image_mode varchar(255), ironing_yn varchar(255), laundry_yn varchar(255), link_yn varchar(255), madein_builder varchar(255), madein_date datetime, name varchar(255), price integer not null, shop_product_id varchar(255), size_measure_image varchar(255), size_measure_mode varchar(255), size_table_yn varchar(255), use_yn varchar(255), brand_id bigint, category_id bigint, madein_id bigint, member_id bigint, sub_category_id bigint, warranty_id bigint, primary key (id))
create table item_bleach_map (id bigint not null, chlorine varchar(255), oxygen varchar(255), use_yn varchar(255), item_id bigint, primary key (id))
create table item_cloth_color_map (id bigint not null, option_value varchar(255), use_yn varchar(255), cloth_color_id bigint, item_id bigint, primary key (id))
create table item_drycleaning_map (id bigint not null, detergent varchar(255), drycan varchar(255), storecan varchar(255), use_yn varchar(255), item_id bigint, primary key (id))
create table item_drymethod_map (id bigint not null, dry_mode varchar(255), hand_dry varchar(255), machine_dry varchar(255), nature_dry varchar(255), use_yn varchar(255), item_id bigint, primary key (id))
create table item_fit_info_option_map (id bigint not null, use_yn varchar(255), fit_info_option_id bigint, item_id bigint, primary key (id))
create table item_ironing_map (id bigint not null, addprotection varchar(255), end_temp varchar(255), ironcan varchar(255), start_temp varchar(255), use_yn varchar(255), item_id bigint, primary key (id))
create table item_laundry_map (id bigint not null, detergent varchar(255), hand varchar(255), intensity varchar(255), machine varchar(255), use_yn varchar(255), water varchar(255), water_temp varchar(255), item_id bigint, primary key (id))
create table item_material_map (id bigint not null, contain varchar(255), use_locatoin varchar(255), use_yn varchar(255), item_id bigint, material_id bigint, primary key (id))
create table item_scmm_so_value (id bigint not null, input_value varchar(255), use_yn varchar(255), item_id bigint, size_option_id bigint, sub_category_measure_map_id bigint, primary key (id))
create table item_shopmall_map (id bigint not null, use_yn varchar(255), item_id bigint, shopmall_id bigint, primary key (id))
create table item_size_option_map (id bigint not null, option_value varchar(255), use_yn varchar(255), item_id bigint, size_option_id bigint, primary key (id))
create table jpa_id_table (sequence_name varchar(255) not null, sequence_next_hi_value bigint, primary key (sequence_name))
create table kindof (id bigint not null, ktype varchar(255), kvalue varchar(255), use_yn varchar(255), primary key (id))
create table madein (id bigint not null, create_dt datetime, name varchar(255), use_yn varchar(255), kindof_id bigint, primary key (id))
create table material (id bigint not null, create_dt datetime, image varchar(255), meta_desc varchar(255), name varchar(255), use_yn varchar(255), primary key (id))
create table measure_item (id bigint not null, create_dt datetime, meta_desc varchar(255), name varchar(255), use_yn varchar(255), primary key (id))
create table member (id bigint not null, admin_yn varchar(255), confirm_no varchar(255), create_dt datetime, delete_dt datetime, email varchar(255), email_noti_yn varchar(255), manager varchar(255), manager_email varchar(255), manager_phone varchar(255), member_id varchar(255), name varchar(255), password varchar(255), phone varchar(255), use_yn varchar(255), company_no_history_id bigint, primary key (id))
create table shopmall (id bigint not null, access_token varchar(255), create_dt datetime, delete_dt datetime, name varchar(255), oauth_code varchar(255), oauthid varchar(255), refresh_token varchar(255), store_id varchar(255), store_type varchar(255), use_yn varchar(255), member_id bigint, primary key (id))
create table size_option (id bigint not null, create_dt datetime, name varchar(255), use_yn varchar(255), category_id bigint, kindof_id bigint, sub_category_id bigint, primary key (id))
create table size_table (id bigint not null, create_dt datetime, last_modified_date tinyblob, use_yn varchar(255), version bigint, visible_basic_yn varchar(255), visible_code_yn varchar(255), visible_color_yn varchar(255), visible_fit_info_yn varchar(255), visible_item_image_yn varchar(255), visible_laundry_info_yn varchar(255), visible_measure_howayn varchar(255), visible_measure_howbyn varchar(255), visible_measure_table_yn varchar(255), visible_name_yn varchar(255), item_id bigint, primary key (id))
create table sub_category (id bigint not null, bleach_yn varchar(255), cloth_image varchar(255), create_dt datetime, drycleaning_yn varchar(255), drymethod_yn varchar(255), ironing_yn varchar(255), item_image varchar(255), laundry_yn varchar(255), name varchar(255), use_yn varchar(255), category_id bigint, primary key (id))
create table sub_category_fit_info_map (id bigint not null, use_yn varchar(255), fit_info_id bigint, sub_category_id bigint, primary key (id))
create table sub_category_measure_map (id bigint not null, use_yn varchar(255), measure_item_id bigint, sub_category_id bigint, primary key (id))
create table warranty (id bigint not null, create_dt datetime, scope varchar(255), use_yn varchar(255), kindof_id bigint, primary key (id))
alter table bbs add constraint FKenr5nejc0bi4oa346asfqcdon foreign key (kindof_id) references kindof (id)
alter table bbs add constraint FKnaka108edskn5qa9ylk0vpe0x foreign key (member_id) references member (id)
alter table brand add constraint FK59a73qwpcnt6h66lpmqjsn73n foreign key (member_id) references member (id)
alter table cloth_color add constraint FKr8v9kbek5xgg3ah0le73ikcm0 foreign key (kindof_id) references kindof (id)
alter table company_no_history add constraint FK6b2m83qo8rnxd9gijfceuh1gx foreign key (member_id) references member (id)
alter table fit_info_option add constraint FKcl17pn9grtkgnbxf3ooxc006b foreign key (fit_info_id) references fit_info (id)
alter table item add constraint FKhie4w6g67io9k67mf87clka9l foreign key (brand_id) references brand (id)
alter table item add constraint FK2n9w8d0dp4bsfra9dcg0046l4 foreign key (category_id) references category (id)
alter table item add constraint FKhmjrhli6r10tsuphrt850mb8m foreign key (madein_id) references madein (id)
alter table item add constraint FKpuyun1nwd8fupsib8ekn7vrpm foreign key (member_id) references member (id)
alter table item add constraint FKo33ug6nywet8hkt5mnen9016t foreign key (sub_category_id) references sub_category (id)
alter table item add constraint FK3qjh9w6wx8kdmi9i0uev59kk foreign key (warranty_id) references warranty (id)
alter table item_bleach_map add constraint FKgwms9troh2ia1omwymfy6pn8j foreign key (item_id) references item (id)
alter table item_cloth_color_map add constraint FKref5gfe4e2qrjtvm1km68urnt foreign key (cloth_color_id) references cloth_color (id)
alter table item_cloth_color_map add constraint FKfcc0bwvmddwja84cqwyarepuv foreign key (item_id) references item (id)
alter table item_drycleaning_map add constraint FK1dsx26x7220iiaeeeiyyqs895 foreign key (item_id) references item (id)
alter table item_drymethod_map add constraint FKg3ja160lkq3sv5m31l9ymb8uo foreign key (item_id) references item (id)
alter table item_fit_info_option_map add constraint FKe8x7oa0ixltx9yxs8fo90sdh2 foreign key (fit_info_option_id) references fit_info_option (id)
alter table item_fit_info_option_map add constraint FKrlpiqvb05hvc708knmmxyw0w4 foreign key (item_id) references item (id)
alter table item_ironing_map add constraint FKpwtd1s97vwgubuji09oxddv98 foreign key (item_id) references item (id)
alter table item_laundry_map add constraint FKg4wmy9noxkxyyncw2i37flisr foreign key (item_id) references item (id)
alter table item_material_map add constraint FKqn2dhw7g6upbdgpo7d1apewfr foreign key (item_id) references item (id)
alter table item_material_map add constraint FKkahmyyniph988jgmbgru7luiv foreign key (material_id) references material (id)
alter table item_scmm_so_value add constraint FKobtikn1n9atr45m156fffoqh4 foreign key (item_id) references item (id)
alter table item_scmm_so_value add constraint FKosap26muju3jyj856ueyueyy foreign key (size_option_id) references size_option (id)
alter table item_scmm_so_value add constraint FKjhnbr19hqwkkvwljn3ltbbqt6 foreign key (sub_category_measure_map_id) references sub_category_measure_map (id)
alter table item_shopmall_map add constraint FKbrwddijlb6k9ijq3dbxmtkvek foreign key (item_id) references item (id)
alter table item_shopmall_map add constraint FKp96tmk8n6j24kaf110uv6qhf9 foreign key (shopmall_id) references shopmall (id)
alter table item_size_option_map add constraint FKmda4wwdmcm5madmihlj6fqcjp foreign key (item_id) references item (id)
alter table item_size_option_map add constraint FKgbh5hm4w2r1r2oju8baveq4f7 foreign key (size_option_id) references size_option (id)
alter table madein add constraint FK27fv081gccndufvhbwnyletu3 foreign key (kindof_id) references kindof (id)
alter table member add constraint FKqpp8d1imms4qubswn07a3j532 foreign key (company_no_history_id) references company_no_history (id)
alter table shopmall add constraint FK9v32fg1stcoxidcttmtlyn2xf foreign key (member_id) references member (id)
alter table size_option add constraint FKqi9ebydupvlxrpp8g8vdh847e foreign key (category_id) references category (id)
alter table size_option add constraint FKq70xqw0aysxxlckxqktrcwrr foreign key (kindof_id) references kindof (id)
alter table size_option add constraint FKk7k3fvg25k3lfv9y076ulrs2w foreign key (sub_category_id) references sub_category (id)
alter table size_table add constraint FK9947teyv7wkgf2mvg256qkamy foreign key (item_id) references item (id)
alter table sub_category add constraint FKl65dyy5me2ypoyj8ou1hnt64e foreign key (category_id) references category (id)
alter table sub_category_fit_info_map add constraint FKsaxh7lembocgbr9a0mvcmc0it foreign key (fit_info_id) references fit_info (id)
alter table sub_category_fit_info_map add constraint FKk794pi1kpj5ip8w3059qc0riq foreign key (sub_category_id) references sub_category (id)
alter table sub_category_measure_map add constraint FK6a9jysggnoexi3iou12x0xyi8 foreign key (measure_item_id) references measure_item (id)
alter table sub_category_measure_map add constraint FK6pddla5y42wkv6a5y9w2u7g5t foreign key (sub_category_id) references sub_category (id)
alter table warranty add constraint FK8hfqdbikdbkajxl9vyd3o61fa foreign key (kindof_id) references kindof (id)
