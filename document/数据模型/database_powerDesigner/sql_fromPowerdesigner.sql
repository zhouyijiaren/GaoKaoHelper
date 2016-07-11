/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/6/1 10:46:53                            */
/*==============================================================*/


drop table if exists t_application_form;

drop table if exists t_application_major;

drop table if exists t_application_university;

drop table if exists t_area;

drop table if exists t_major;

drop table if exists t_major_area;

drop table if exists t_permission;

drop table if exists t_role;

drop table if exists t_role_permission;

drop table if exists t_state;

drop table if exists t_token;

drop table if exists t_university;

drop table if exists t_university_area;

drop table if exists t_user;

drop table if exists t_user_major_guanzhu;

drop table if exists t_user_role;

drop table if exists t_user_university_guanzhu;

/*==============================================================*/
/* Table: t_application_form                                    */
/*==============================================================*/
create table t_application_form
(
   id                   bigint not null,
   user_id              bigint,
   score                int,
   intro                varchar(255),
   area                 bigint,
   subject_type         int,
   primary key (id)
);

/*==============================================================*/
/* Table: t_application_major                                   */
/*==============================================================*/
create table t_application_major
(
   id                   bigint not null,
   application_university_id bigint,
   major_id             bigint,
   primary key (id)
);

/*==============================================================*/
/* Table: t_application_university                              */
/*==============================================================*/
create table t_application_university
(
   id                   bigint not null,
   form_id              bigint,
   university_id        bigint,
   primary key (id)
);

/*==============================================================*/
/* Table: t_area                                                */
/*==============================================================*/
create table t_area
(
   id                   BIGINT not null auto_increment,
   name                 VARCHAR(50) default '' comment '地区名称',
   pinyin               VARCHAR(100) default '' comment '拼音',
   pid                  BIGINT default 0 comment '父级编号',
   area_code            VARCHAR(6) default NULL,
   zip_code             VARCHAR(6) default NULL comment '邮编',
   left_code            BIGINT default 0 comment '左编码',
   right_code           BIGINT default 0 comment '右编码',
   created_at           TIMESTAMP not null default CURRENT_TIMESTAMP,
   updated_at           TIMESTAMP,
   deleted_at           TIMESTAMP,
   pici                 varchar comment '有多少批次（用逗号分隔）',
   primary key (id)
)
ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '地区';

/*==============================================================*/
/* Table: t_major                                               */
/*==============================================================*/
create table t_major
(
   id                   bigint not null auto_increment,
   name                 varchar(30),
   intro                varchar(255),
   primary key (id)
);

/*==============================================================*/
/* Table: t_major_area                                          */
/*==============================================================*/
create table t_major_area
(
   id                   bigint not null,
   university_area_id   bigint,
   major_id             bigint,
   score_line           varchar(100),
   primary key (id)
);

/*==============================================================*/
/* Table: t_permission                                          */
/*==============================================================*/
create table t_permission
(
   id                   BIGINT not null auto_increment,
   name                 VARCHAR(50) not null comment '名称',
   method               VARCHAR(10) not null comment '方法',
   value                VARCHAR(50) not null comment '值',
   url                  VARCHAR(255) comment 'url地址',
   intro                VARCHAR(255) comment '简介',
   pid                  BIGINT default 0 comment '父级id',
   created_at           TIMESTAMP not null default CURRENT_TIMESTAMP,
   updated_at           TIMESTAMP,
   deleted_at           TIMESTAMP,
   primary key (id)
)
ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '权限';

/*==============================================================*/
/* Table: t_role                                                */
/*==============================================================*/
create table t_role
(
   id                   BIGINT not null auto_increment,
   name                 VARCHAR(50) not null comment '名称',
   value                VARCHAR(50) not null comment '值',
   intro                VARCHAR(255) comment '简介',
   pid                  BIGINT default 0 comment '父级id',
   created_at           TIMESTAMP not null default CURRENT_TIMESTAMP,
   updated_at           TIMESTAMP,
   deleted_at           TIMESTAMP,
   primary key (id)
)
ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '角色';

/*==============================================================*/
/* Table: t_role_permission                                     */
/*==============================================================*/
create table t_role_permission
(
   id                   BIGINT not null,
   role_id              BIGINT not null,
   permission_id        BIGINT not null,
   primary key (id)
)
ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '角色权限';

/*==============================================================*/
/* Table: t_state                                               */
/*==============================================================*/
create table t_state
(
   id                   BIGINT not null auto_increment,
   name                 VARCHAR(45) default NULL comment '状态名称',
   value                INT default 0 comment '状态值',
   intro                TEXT comment '简介',
   type                 VARCHAR(45) default NULL comment '状态类型',
   created_at           TIMESTAMP not null default CURRENT_TIMESTAMP,
   updated_at           TIMESTAMP,
   deleted_at           TIMESTAMP,
   primary key (id)
)
ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '状态';

/*==============================================================*/
/* Table: t_token                                               */
/*==============================================================*/
create table t_token
(
   uuid                 VARCHAR(255) not null comment '用户编码',
   username             VARCHAR(255) not null comment '用户名',
   created_at           TIMESTAMP not null comment '创建时间',
   expiration_at        TIMESTAMP not null comment '结束时间',
   used_to              INT not null comment '0是注册，1是手机验证',
   primary key (uuid)
)
ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '认证';

/*==============================================================*/
/* Table: t_university                                          */
/*==============================================================*/
create table t_university
(
   id                   bigint not null auto_increment,
   name                 varchar(30),
   intro                varchar(255),
   pici                 int comment '0 未知批次
            1 提前批
            2 本科一批
            3 本科二批
            4 本科三批
            5 专科批次',
   university_code      int comment '院校编号',
   area                 varchar(8),
   primary key (id)
);

/*==============================================================*/
/* Table: t_university_area                                     */
/*==============================================================*/
create table t_university_area
(
   id                   bigint not null,
   university_id        char(10),
   area_id              char(10),
   pici                 char(10),
   score_line           char(10),
   primary key (id)
);

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   id                   BIGINT not null,
   username             VARCHAR(50) not null comment '登录名',
   providername         VARCHAR(50) not null comment '提供者',
   email                VARCHAR(200) comment '邮箱',
   mobile               VARCHAR(50) comment '手机',
   password             VARCHAR(200) not null comment '密码',
   avatar_url           VARCHAR(255) comment '头像',
   first_name           VARCHAR(10) comment '名字',
   last_name            VARCHAR(10) comment '姓氏',
   full_name            VARCHAR(20) comment '全名',
   created_at           TIMESTAMP not null default CURRENT_TIMESTAMP,
   updated_at           TIMESTAMP,
   deleted_at           TIMESTAMP,
   area_id              bigint,
   score_now            int comment '现在自己输入的分数',
   score_real           int comment '从高考查分中获得分数',
   subject_type         int comment '科目类型  文科还是理科',
   primary key (id)
)
ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '用户';

/*==============================================================*/
/* Table: t_user_major_guanzhu                                  */
/*==============================================================*/
create table t_user_major_guanzhu
(
   id                   bigint not null,
   user_id              bigint,
   major_id             bigint,
   primary key (id)
);

/*==============================================================*/
/* Table: t_user_role                                           */
/*==============================================================*/
create table t_user_role
(
   id                   BIGINT not null,
   user_id              BIGINT not null,
   role_id              BIGINT not null,
   primary key (id)
)
ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '用户角色';

/*==============================================================*/
/* Table: t_user_university_guanzhu                             */
/*==============================================================*/
create table t_user_university_guanzhu
(
   id                   bigint not null,
   user_id              bigint,
   university_id        bigint,
   primary key (id)
);

alter table t_application_form add constraint FK_Reference_10 foreign key (user_id)
      references t_user (id) on delete restrict on update restrict;

alter table t_application_form add constraint FK_Reference_15 foreign key (area)
      references t_area (id) on delete restrict on update restrict;

alter table t_application_major add constraint FK_Reference_13 foreign key (application_university_id)
      references t_application_university (id) on delete restrict on update restrict;

alter table t_application_major add constraint FK_Reference_14 foreign key (major_id)
      references t_major (id) on delete restrict on update restrict;

alter table t_application_university add constraint FK_Reference_11 foreign key (form_id)
      references t_application_form (id) on delete restrict on update restrict;

alter table t_application_university add constraint FK_Reference_12 foreign key (university_id)
      references t_university (id) on delete restrict on update restrict;

alter table t_major_area add constraint FK_Reference_18 foreign key (university_area_id)
      references t_university_area (id) on delete restrict on update restrict;

alter table t_major_area add constraint FK_Reference_19 foreign key (major_id)
      references t_major (id) on delete restrict on update restrict;

alter table t_role_permission add constraint FK_Reference_3 foreign key (role_id)
      references t_role (id) on delete restrict on update restrict;

alter table t_role_permission add constraint FK_Reference_4 foreign key (permission_id)
      references t_permission (id) on delete restrict on update restrict;

alter table t_university_area add constraint FK_Reference_16 foreign key (area_id)
      references t_area (id) on delete restrict on update restrict;

alter table t_university_area add constraint FK_Reference_17 foreign key (university_id)
      references t_university (id) on delete restrict on update restrict;

alter table t_user add constraint FK_Reference_5 foreign key (area_id)
      references t_area (id) on delete restrict on update restrict;

alter table t_user_major_guanzhu add constraint FK_Reference_8 foreign key (user_id)
      references t_user (id) on delete restrict on update restrict;

alter table t_user_major_guanzhu add constraint FK_Reference_9 foreign key (major_id)
      references t_major (id) on delete restrict on update restrict;

alter table t_user_role add constraint FK_Reference_1 foreign key (user_id)
      references t_user (id) on delete restrict on update restrict;

alter table t_user_role add constraint FK_Reference_2 foreign key (role_id)
      references t_role (id) on delete restrict on update restrict;

alter table t_user_university_guanzhu add constraint FK_Reference_6 foreign key (user_id)
      references t_user (id) on delete restrict on update restrict;

alter table t_user_university_guanzhu add constraint FK_Reference_7 foreign key (university_id)
      references t_university (id) on delete restrict on update restrict;

