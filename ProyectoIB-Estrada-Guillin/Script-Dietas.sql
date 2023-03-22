/*==============================================================*/
/* Table: COMIDA                                                */
/*==============================================================*/
create table COMIDA
(
   ID_COMIDA            int not null,
   ID_DIETA             int,
   DESCRIPCION_COMIDA   varchar(500) not null,
   HORARIO_COMIDA       varchar(50) not null,
   primary key (ID_COMIDA)
);

/*==============================================================*/
/* Table: DIETA                                                 */
/*==============================================================*/
create table DIETA
(
   ID_DIETA             int not null,
   TIPO_DIETA           varchar(100) not null,
   primary key (ID_DIETA)
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO
(
   ID_USUARIO           int not null,
   ID_DIETA             int,
   EMAIL_USUARIO        varchar(100) not null,
   NOMBRE_USUARIO       varchar(100) not null,
   FOTO_USUARIO         longblob,
   PASSWORD_USUARIO     varchar(100) not null,
   ALTURA_USUARIO       float not null,
   PESOACT_USUARIO      float not null,
   PESOOBJ_USUARIO      float not null,
   primary key (ID_USUARIO)
);

alter table COMIDA add constraint FK_RELATIONSHIP_2 foreign key (ID_DIETA)
      references DIETA (ID_DIETA) on delete restrict on update restrict;

alter table USUARIO add constraint FK_RELATIONSHIP_1 foreign key (ID_DIETA)
      references DIETA (ID_DIETA) on delete restrict on update restrict;

