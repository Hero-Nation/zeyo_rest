alter table bbs drop foreign key FKenr5nejc0bi4oa346asfqcdon
alter table bbs drop foreign key FKnaka108edskn5qa9ylk0vpe0x
alter table brand drop foreign key FK59a73qwpcnt6h66lpmqjsn73n
alter table brand_member_map drop foreign key FKb8p4c72m2nkrhvesletfdgj8f
alter table brand_member_map drop foreign key FKrkexskhsadf4lavb3kmsdhpkb
alter table cloth_color drop foreign key FKr8v9kbek5xgg3ah0le73ikcm0
alter table company_no_history drop foreign key FK6b2m83qo8rnxd9gijfceuh1gx
alter table fit_info_option drop foreign key FKcl17pn9grtkgnbxf3ooxc006b
alter table item drop foreign key FKhie4w6g67io9k67mf87clka9l
alter table item drop foreign key FK2n9w8d0dp4bsfra9dcg0046l4
alter table item drop foreign key FKhmjrhli6r10tsuphrt850mb8m
alter table item drop foreign key FKpuyun1nwd8fupsib8ekn7vrpm
alter table item drop foreign key FKo33ug6nywet8hkt5mnen9016t
alter table item drop foreign key FK3qjh9w6wx8kdmi9i0uev59kk
alter table item_bleach_map drop foreign key FKgwms9troh2ia1omwymfy6pn8j
alter table item_cloth_color_map drop foreign key FKref5gfe4e2qrjtvm1km68urnt
alter table item_cloth_color_map drop foreign key FKfcc0bwvmddwja84cqwyarepuv
alter table item_drycleaning_map drop foreign key FK1dsx26x7220iiaeeeiyyqs895
alter table item_drymethod_map drop foreign key FKg3ja160lkq3sv5m31l9ymb8uo
alter table item_fit_info_option_map drop foreign key FKe8x7oa0ixltx9yxs8fo90sdh2
alter table item_fit_info_option_map drop foreign key FKrlpiqvb05hvc708knmmxyw0w4
alter table item_ironing_map drop foreign key FKpwtd1s97vwgubuji09oxddv98
alter table item_laundry_map drop foreign key FKg4wmy9noxkxyyncw2i37flisr
alter table item_material_map drop foreign key FKqn2dhw7g6upbdgpo7d1apewfr
alter table item_material_map drop foreign key FKkahmyyniph988jgmbgru7luiv
alter table item_shopmall_map drop foreign key FKbrwddijlb6k9ijq3dbxmtkvek
alter table item_shopmall_map drop foreign key FKp96tmk8n6j24kaf110uv6qhf9
alter table item_size_option_map drop foreign key FKmda4wwdmcm5madmihlj6fqcjp
alter table item_size_option_map drop foreign key FKgbh5hm4w2r1r2oju8baveq4f7
alter table madein drop foreign key FK27fv081gccndufvhbwnyletu3
alter table member drop foreign key FKqpp8d1imms4qubswn07a3j532
alter table shopmall drop foreign key FK9v32fg1stcoxidcttmtlyn2xf
alter table shopmall_member_map drop foreign key FKvslouykqj1tqiwh1nmtp57eo
alter table shopmall_member_map drop foreign key FK2k36l72akiitu9rmq1wqqd3mx
alter table size_option drop foreign key FKqi9ebydupvlxrpp8g8vdh847e
alter table size_option drop foreign key FKq70xqw0aysxxlckxqktrcwrr
alter table size_option drop foreign key FKk7k3fvg25k3lfv9y076ulrs2w
alter table size_table drop foreign key FK9947teyv7wkgf2mvg256qkamy
alter table sub_category drop foreign key FKl65dyy5me2ypoyj8ou1hnt64e
alter table sub_category_fit_info_map drop foreign key FKsaxh7lembocgbr9a0mvcmc0it
alter table sub_category_fit_info_map drop foreign key FKk794pi1kpj5ip8w3059qc0riq
alter table sub_category_measure_map drop foreign key FK6a9jysggnoexi3iou12x0xyi8
alter table sub_category_measure_map drop foreign key FK6pddla5y42wkv6a5y9w2u7g5t
alter table warranty drop foreign key FK8hfqdbikdbkajxl9vyd3o61fa
drop table if exists bbs
drop table if exists brand
drop table if exists brand_member_map
drop table if exists category
drop table if exists cloth_color
drop table if exists company_no_history
drop table if exists fit_info
drop table if exists fit_info_option
drop table if exists item
drop table if exists item_bleach_map
drop table if exists item_cloth_color_map
drop table if exists item_drycleaning_map
drop table if exists item_drymethod_map
drop table if exists item_fit_info_option_map
drop table if exists item_ironing_map
drop table if exists item_laundry_map
drop table if exists item_material_map
drop table if exists item_shopmall_map
drop table if exists item_size_option_map
drop table if exists jpa_id_table
drop table if exists kindof
drop table if exists madein
drop table if exists material
drop table if exists measure_item
drop table if exists member
drop table if exists shopmall
drop table if exists shopmall_member_map
drop table if exists size_option
drop table if exists size_table
drop table if exists sub_category
drop table if exists sub_category_fit_info_map
drop table if exists sub_category_measure_map
drop table if exists warranty
