CREATE TABLE bbs 
  ( 
     id                 BIGINT NOT NULL, 
     bbs_content        VARCHAR(255), 
     create_dt          VARCHAR(255), 
     last_modified_date TINYBLOB, 
     reply_content      VARCHAR(255), 
     reply_dt           VARCHAR(255), 
     status             VARCHAR(255), 
     title              VARCHAR(255), 
     use_yn             VARCHAR(255), 
     version            BIGINT, 
     kindof_id          BIGINT, 
     member_id          BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE brand 
  ( 
     id                 BIGINT NOT NULL, 
     last_modified_date TINYBLOB, 
     name               VARCHAR(255), 
     use_yn             VARCHAR(255), 
     version            BIGINT, 
     member_id          BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE category 
  ( 
     id                 BIGINT NOT NULL, 
     create_dt          VARCHAR(255), 
     last_modified_date TINYBLOB, 
     name               VARCHAR(255), 
     use_yn             VARCHAR(255), 
     version            BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE cloth_color 
  ( 
     id                 BIGINT NOT NULL, 
     last_modified_date TINYBLOB, 
     name               VARCHAR(255), 
     use_yn             VARCHAR(255), 
     version            BIGINT, 
     kindof_id          BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE company_no_history 
  ( 
     id                 BIGINT NOT NULL, 
     before_no          VARCHAR(255), 
     change_dt          VARCHAR(255), 
     company_no         VARCHAR(255), 
     last_modified_date TINYBLOB, 
     name               VARCHAR(255), 
     version            BIGINT, 
     member_id          BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE fit_info 
  ( 
     id                 BIGINT NOT NULL, 
     create_dt          VARCHAR(255), 
     last_modified_date TINYBLOB, 
     meta_desc          VARCHAR(255), 
     name               VARCHAR(255), 
     use_yn             VARCHAR(255), 
     version            BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE fit_info_option 
  ( 
     id                 BIGINT NOT NULL, 
     last_modified_date TINYBLOB, 
     name               VARCHAR(255), 
     use_yn             VARCHAR(255), 
     version            BIGINT, 
     fit_info_id        BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE fit_into_choice 
  ( 
     id                 BIGINT NOT NULL, 
     last_modified_date TINYBLOB, 
     version            BIGINT, 
     fit_info_id        BIGINT, 
     fit_info_option_id BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE item 
  ( 
     id                 BIGINT NOT NULL, 
     bleach_yn          VARCHAR(255), 
     code               VARCHAR(255), 
     create_dt          VARCHAR(255), 
     drycleaning_yn     VARCHAR(255), 
     drymethod_yn       VARCHAR(255), 
     image              VARCHAR(255), 
     image_mode         VARCHAR(255), 
     ironing_yn         VARCHAR(255), 
     last_modified_date TINYBLOB, 
     laundry_yn         VARCHAR(255), 
     madein_builder     VARCHAR(255), 
     madein_date        VARCHAR(255), 
     name               VARCHAR(255), 
     price              VARCHAR(255), 
     size_link_yn       VARCHAR(255), 
     size_measure_image VARCHAR(255), 
     size_measure_mode  VARCHAR(255), 
     use_yn             VARCHAR(255), 
     version            BIGINT, 
     brand_id           BIGINT, 
     category_id        BIGINT, 
     madein_id          BIGINT, 
     member_id          BIGINT, 
     sub_category_id    BIGINT, 
     warranty_id        BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE item_bleach_map 
  ( 
     id                 BIGINT NOT NULL, 
     chlorine           VARCHAR(255), 
     last_modified_date TINYBLOB, 
     oxygen             VARCHAR(255), 
     use_yn             VARCHAR(255), 
     version            BIGINT, 
     item_id            BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE item_cloth_color_map 
  ( 
     id                 BIGINT NOT NULL, 
     last_modified_date TINYBLOB, 
     use_yn             VARCHAR(255), 
     version            BIGINT, 
     cloth_color_id     BIGINT, 
     item_id            BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE item_drycleaning_map 
  ( 
     id                 BIGINT NOT NULL, 
     detergent          VARCHAR(255), 
     drycan             VARCHAR(255), 
     last_modified_date TINYBLOB, 
     storecan           VARCHAR(255), 
     use_yn             VARCHAR(255), 
     version            BIGINT, 
     item_id            BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE item_drymethod_map 
  ( 
     id                 BIGINT NOT NULL, 
     dry_mode           VARCHAR(255), 
     hand_dry           VARCHAR(255), 
     last_modified_date TINYBLOB, 
     machine_dry        VARCHAR(255), 
     nature_dry         VARCHAR(255), 
     use_yn             VARCHAR(255), 
     version            BIGINT, 
     item_id            BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE item_ironing_map 
  ( 
     id                 BIGINT NOT NULL, 
     addprotection      VARCHAR(255), 
     end_temp           VARCHAR(255), 
     ironcan            VARCHAR(255), 
     last_modified_date TINYBLOB, 
     start_temp         VARCHAR(255), 
     use_yn             VARCHAR(255), 
     version            BIGINT, 
     item_id            BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE item_laundry_map 
  ( 
     id                 BIGINT NOT NULL, 
     detergent          VARCHAR(255), 
     hand               VARCHAR(255), 
     intensity          VARCHAR(255), 
     last_modified_date TINYBLOB, 
     machine            VARCHAR(255), 
     use_yn             VARCHAR(255), 
     version            BIGINT, 
     water              VARCHAR(255), 
     water_temp         VARCHAR(255), 
     item_id            BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE item_material_map 
  ( 
     id                 BIGINT NOT NULL, 
     contain            VARCHAR(255), 
     last_modified_date TINYBLOB, 
     use_yn             VARCHAR(255), 
     version            BIGINT, 
     item_id            BIGINT, 
     kindof_id          BIGINT, 
     material_id        BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE item_shopmall_map 
  ( 
     id                 BIGINT NOT NULL, 
     last_modified_date TINYBLOB, 
     use_yn             VARCHAR(255), 
     version            BIGINT, 
     item_id            BIGINT, 
     shopmall_id        BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE item_size_option_map 
  ( 
     id                 BIGINT NOT NULL, 
     last_modified_date TINYBLOB, 
     option_value       VARCHAR(255), 
     use_yn             VARCHAR(255), 
     version            BIGINT, 
     item_id            BIGINT, 
     size_option_id     BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE jpa_id_table 
  ( 
     sequence_name          VARCHAR(255) NOT NULL, 
     sequence_next_hi_value BIGINT, 
     PRIMARY KEY (sequence_name) 
  ); 

CREATE TABLE kindof 
  ( 
     id                 BIGINT NOT NULL, 
     ktype              VARCHAR(255), 
     kvalue             VARCHAR(255), 
     last_modified_date TINYBLOB, 
     use_yn             VARCHAR(255), 
     version            BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE madein 
  ( 
     id                 BIGINT NOT NULL, 
     create_dt          VARCHAR(255), 
     last_modified_date TINYBLOB, 
     name               VARCHAR(255), 
     use_yn             VARCHAR(255), 
     version            BIGINT, 
     kindof_id          BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE material 
  ( 
     id                 BIGINT NOT NULL, 
     create_dt          VARCHAR(255), 
     image              VARCHAR(255), 
     last_modified_date TINYBLOB, 
     meta_desc          VARCHAR(255), 
     name               VARCHAR(255), 
     use_yn             VARCHAR(255), 
     version            BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE measure_item 
  ( 
     id                 BIGINT NOT NULL, 
     create_dt          VARCHAR(255), 
     last_modified_date TINYBLOB, 
     meta_desc          VARCHAR(255), 
     name               VARCHAR(255), 
     use_yn             VARCHAR(255), 
     version            BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE member 
  ( 
     id                    BIGINT NOT NULL, 
     admin_yn              VARCHAR(255), 
     create_dt             VARCHAR(255), 
     delete_dt             VARCHAR(255), 
     email                 VARCHAR(255), 
     last_modified_date    TINYBLOB, 
     manager               VARCHAR(255), 
     manager_email         VARCHAR(255), 
     manager_phone         VARCHAR(255), 
     member_id             VARCHAR(255), 
     name                  VARCHAR(255), 
     password              VARCHAR(255), 
     phone                 VARCHAR(255), 
     use_yn                VARCHAR(255), 
     version               BIGINT, 
     company_no_history_id BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE shopmall 
  ( 
     id                 BIGINT NOT NULL, 
     last_modified_date TINYBLOB, 
     name               VARCHAR(255), 
     use_yn             VARCHAR(255), 
     version            BIGINT, 
     member_id          BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE size_option 
  ( 
     id                 BIGINT NOT NULL, 
     create_dt          VARCHAR(255), 
     last_modified_date TINYBLOB, 
     name               VARCHAR(255), 
     use_yn             VARCHAR(255), 
     version            BIGINT, 
     category_id        BIGINT, 
     kindof_id          BIGINT, 
     sub_category_id    BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE sub_category 
  ( 
     id                 BIGINT NOT NULL, 
     bleach_yn          VARCHAR(255), 
     cloth_image        VARCHAR(255), 
     create_dt          VARCHAR(255), 
     drycleaning_yn     VARCHAR(255), 
     drymethod_yn       VARCHAR(255), 
     ironing_yn         VARCHAR(255), 
     item_image         VARCHAR(255), 
     last_modified_date TINYBLOB, 
     laundry_yn         VARCHAR(255), 
     name               VARCHAR(255), 
     use_yn             VARCHAR(255), 
     version            BIGINT, 
     category_id        BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE sub_category_fit_info_map 
  ( 
     id                 BIGINT NOT NULL, 
     last_modified_date TINYBLOB, 
     use_yn             VARCHAR(255), 
     version            BIGINT, 
     fit_info_id        BIGINT, 
     sub_category_id    BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE sub_category_measure_map 
  ( 
     id                 BIGINT NOT NULL, 
     last_modified_date TINYBLOB, 
     use_yn             VARCHAR(255), 
     version            BIGINT, 
     measure_item_id    BIGINT, 
     sub_category_id    BIGINT, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE warranty 
  ( 
     id                 BIGINT NOT NULL, 
     create_dt          VARCHAR(255), 
     last_modified_date TINYBLOB, 
     scope              VARCHAR(255), 
     use_yn             VARCHAR(255), 
     version            BIGINT, 
     kindof_id          BIGINT, 
     PRIMARY KEY (id) 
  ); 

ALTER TABLE bbs 
  ADD CONSTRAINT fkenr5nejc0bi4oa346asfqcdon FOREIGN KEY (kindof_id) REFERENCES 
  kindof (id); 

ALTER TABLE bbs 
  ADD CONSTRAINT fknaka108edskn5qa9ylk0vpe0x FOREIGN KEY (member_id) REFERENCES 
  member (id); 

ALTER TABLE brand 
  ADD CONSTRAINT fk59a73qwpcnt6h66lpmqjsn73n FOREIGN KEY (member_id) REFERENCES 
  member (id); 

ALTER TABLE cloth_color 
  ADD CONSTRAINT fkr8v9kbek5xgg3ah0le73ikcm0 FOREIGN KEY (kindof_id) REFERENCES 
  kindof (id); 

ALTER TABLE company_no_history 
  ADD CONSTRAINT fk6b2m83qo8rnxd9gijfceuh1gx FOREIGN KEY (member_id) REFERENCES 
  member (id); 

ALTER TABLE fit_info_option 
  ADD CONSTRAINT fkcl17pn9grtkgnbxf3ooxc006b FOREIGN KEY (fit_info_id) 
  REFERENCES fit_info (id); 

ALTER TABLE fit_into_choice 
  ADD CONSTRAINT fksl0yj5q6ps9v6blss8aateoqq FOREIGN KEY (fit_info_id) 
  REFERENCES fit_info (id); 

ALTER TABLE fit_into_choice 
  ADD CONSTRAINT fkp91whe2lbpia2vhok24h7hh0v FOREIGN KEY (fit_info_option_id) 
  REFERENCES fit_info_option (id); 

ALTER TABLE item 
  ADD CONSTRAINT fkhie4w6g67io9k67mf87clka9l FOREIGN KEY (brand_id) REFERENCES 
  brand (id); 

ALTER TABLE item 
  ADD CONSTRAINT fk2n9w8d0dp4bsfra9dcg0046l4 FOREIGN KEY (category_id) 
  REFERENCES category (id); 

ALTER TABLE item 
  ADD CONSTRAINT fkhmjrhli6r10tsuphrt850mb8m FOREIGN KEY (madein_id) REFERENCES 
  madein (id); 

ALTER TABLE item 
  ADD CONSTRAINT fkpuyun1nwd8fupsib8ekn7vrpm FOREIGN KEY (member_id) REFERENCES 
  member (id); 

ALTER TABLE item 
  ADD CONSTRAINT fko33ug6nywet8hkt5mnen9016t FOREIGN KEY (sub_category_id) 
  REFERENCES sub_category (id); 

ALTER TABLE item 
  ADD CONSTRAINT fk3qjh9w6wx8kdmi9i0uev59kk FOREIGN KEY (warranty_id) REFERENCES 
  warranty (id); 

ALTER TABLE item_bleach_map 
  ADD CONSTRAINT fkgwms9troh2ia1omwymfy6pn8j FOREIGN KEY (item_id) REFERENCES 
  item (id); 

ALTER TABLE item_cloth_color_map 
  ADD CONSTRAINT fkref5gfe4e2qrjtvm1km68urnt FOREIGN KEY (cloth_color_id) 
  REFERENCES cloth_color (id); 

ALTER TABLE item_cloth_color_map 
  ADD CONSTRAINT fkfcc0bwvmddwja84cqwyarepuv FOREIGN KEY (item_id) REFERENCES 
  item (id); 

ALTER TABLE item_drycleaning_map 
  ADD CONSTRAINT fk1dsx26x7220iiaeeeiyyqs895 FOREIGN KEY (item_id) REFERENCES 
  item (id); 

ALTER TABLE item_drymethod_map 
  ADD CONSTRAINT fkg3ja160lkq3sv5m31l9ymb8uo FOREIGN KEY (item_id) REFERENCES 
  item (id); 

ALTER TABLE item_ironing_map 
  ADD CONSTRAINT fkpwtd1s97vwgubuji09oxddv98 FOREIGN KEY (item_id) REFERENCES 
  item (id); 

ALTER TABLE item_laundry_map 
  ADD CONSTRAINT fkg4wmy9noxkxyyncw2i37flisr FOREIGN KEY (item_id) REFERENCES 
  item (id); 

ALTER TABLE item_material_map 
  ADD CONSTRAINT fkqn2dhw7g6upbdgpo7d1apewfr FOREIGN KEY (item_id) REFERENCES 
  item (id); 

ALTER TABLE item_material_map 
  ADD CONSTRAINT fkj731wxhseviv40ect7ir6jg17 FOREIGN KEY (kindof_id) REFERENCES 
  kindof (id); 

ALTER TABLE item_material_map 
  ADD CONSTRAINT fkkahmyyniph988jgmbgru7luiv FOREIGN KEY (material_id) 
  REFERENCES material (id); 

ALTER TABLE item_shopmall_map 
  ADD CONSTRAINT fkbrwddijlb6k9ijq3dbxmtkvek FOREIGN KEY (item_id) REFERENCES 
  item (id); 

ALTER TABLE item_shopmall_map 
  ADD CONSTRAINT fkp96tmk8n6j24kaf110uv6qhf9 FOREIGN KEY (shopmall_id) 
  REFERENCES shopmall (id); 

ALTER TABLE item_size_option_map 
  ADD CONSTRAINT fkmda4wwdmcm5madmihlj6fqcjp FOREIGN KEY (item_id) REFERENCES 
  item (id); 

ALTER TABLE item_size_option_map 
  ADD CONSTRAINT fkgbh5hm4w2r1r2oju8baveq4f7 FOREIGN KEY (size_option_id) 
  REFERENCES size_option (id); 

ALTER TABLE madein 
  ADD CONSTRAINT fk27fv081gccndufvhbwnyletu3 FOREIGN KEY (kindof_id) REFERENCES 
  kindof (id); 

ALTER TABLE member 
  ADD CONSTRAINT fkqpp8d1imms4qubswn07a3j532 FOREIGN KEY (company_no_history_id) 
  REFERENCES company_no_history (id); 

ALTER TABLE shopmall 
  ADD CONSTRAINT fk9v32fg1stcoxidcttmtlyn2xf FOREIGN KEY (member_id) REFERENCES 
  member (id); 

ALTER TABLE size_option 
  ADD CONSTRAINT fkqi9ebydupvlxrpp8g8vdh847e FOREIGN KEY (category_id) 
  REFERENCES category (id); 

ALTER TABLE size_option 
  ADD CONSTRAINT fkq70xqw0aysxxlckxqktrcwrr FOREIGN KEY (kindof_id) REFERENCES 
  kindof (id); 

ALTER TABLE size_option 
  ADD CONSTRAINT fkk7k3fvg25k3lfv9y076ulrs2w FOREIGN KEY (sub_category_id) 
  REFERENCES sub_category (id); 

ALTER TABLE sub_category 
  ADD CONSTRAINT fkl65dyy5me2ypoyj8ou1hnt64e FOREIGN KEY (category_id) 
  REFERENCES category (id); 

ALTER TABLE sub_category_fit_info_map 
  ADD CONSTRAINT fksaxh7lembocgbr9a0mvcmc0it FOREIGN KEY (fit_info_id) 
  REFERENCES fit_info (id); 

ALTER TABLE sub_category_fit_info_map 
  ADD CONSTRAINT fkk794pi1kpj5ip8w3059qc0riq FOREIGN KEY (sub_category_id) 
  REFERENCES sub_category (id); 

ALTER TABLE sub_category_measure_map 
  ADD CONSTRAINT fk6a9jysggnoexi3iou12x0xyi8 FOREIGN KEY (measure_item_id) 
  REFERENCES measure_item (id); 

ALTER TABLE sub_category_measure_map 
  ADD CONSTRAINT fk6pddla5y42wkv6a5y9w2u7g5t FOREIGN KEY (sub_category_id) 
  REFERENCES sub_category (id); 

ALTER TABLE warranty 
  ADD CONSTRAINT fk8hfqdbikdbkajxl9vyd3o61fa FOREIGN KEY (kindof_id) REFERENCES 
  kindof (id);