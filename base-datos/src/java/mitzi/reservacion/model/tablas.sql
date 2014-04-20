Create table area(Id integer, nombre varchar2(30), descripcion varchar2(150), Constraint area_pk Primary Key(id))
/

Create sequence area_seq start with 1 increment by 1 nomaxvalue order nocache
/

create trigger bi_area before insert on area for each row
begin
    if :new.id is null then
        select area_seq.nextval into :new.id from dual;
    end if;
end;
/

CREATE table "ESPECIALIDAD" (
    "ID_ESPECIALIDAD" NUMBER,
    "ID_AREA"         NUMBER,
    "NOMBRE"          VARCHAR2(50),
    "DESCRIPCION"     VARCHAR2(50),
    constraint  "ESPECIALIDAD_PK" primary key ("ID_ESPECIALIDAD")
)
/

CREATE sequence "ESPECIALIDAD_SEQ"
/

CREATE trigger "BI_ESPECIALIDAD"

  before insert on "ESPECIALIDAD"
  for each row
begin
  if :NEW."ID_ESPECIALIDAD" is null then
    select "ESPECIALIDAD_SEQ".nextval into :NEW."ID_ESPECIALIDAD" from dual;
  end if;
end;
/

ALTER TABLE "ESPECIALIDAD" ADD CONSTRAINT "ESPECIALIDAD_FK"
FOREIGN KEY ("ID_AREA")
REFERENCES "AREA" ("ID")

/

CREATE table "CLINICA" (
    "ID_CLINICA" NUMBER NOT NULL,
    "NOMBRE"     VARCHAR2(50),
    "DIRECCION"  VARCHAR2(50),
    "TELEFONO"   VARCHAR2(50),
    constraint  "CLINICA_PK" primary key ("ID_CLINICA")
)
/

CREATE sequence "clinica"
/

CREATE trigger "BI_CLINICA"
  before insert on "CLINICA"
  for each row
begin
  if :NEW."ID_CLINICA" is null then
    select "clinica".nextval into :NEW."ID_CLINICA" from dual;
  end if;
end;
/


CREATE table "DOCTOR" (
    "ID_DOCTOR"       NUMBER NOT NULL,
    "NOMBRE"          VARCHAR2(50),
    "APELLIDO"        VARCHAR2(50),
    "ID_ESPECIALIDAD" NUMBER,
    "ID_CLINICA"      NUMBER,
    constraint  "DOCTOR_PK" primary key ("ID_DOCTOR"),
    constraint  doctor_especialidad_fk FOREIGN KEY ("ID_ESPECIALIDAD") REFERENCES "ESPECIALIDAD"("ID_ESPECIALIDAD"),
    constraint  doctor_clinica_fk FOREIGN KEY ("ID_CLINICA") REFERENCES "CLINICA"("ID_CLINICA") On delete set null
)
/

CREATE sequence "DOCTOR_SEQ"
/

CREATE trigger "BI_DOCTOR"
  before insert on "DOCTOR"
  for each row
begin
  if :NEW."ID_DOCTOR" is null then
    select "DOCTOR_SEQ".nextval into :NEW."ID_DOCTOR" from dual;
  end if;
end;
/


CREATE table "RESERVACION" (
    "ID_RESERVACION" NUMBER NOT NULL,
    "USUARIO"        NUMBER,
    "ESPECIALIDAD"   NUMBER,
    "DOCTOR"         NUMBER,
    "CLINICA"        NUMBER,
    "FECHA"          TIMESTAMP WITH TIME ZONE,
    constraint "RESERVACION_PK" primary key ("ID_RESERVACION"),
    CONSTRAINT "RESERVACION_ESPECIALIDAD_FK" FOREIGN KEY ("ESPECIALIDAD") REFERENCES "ESPECIALIDAD" ("ID_ESPECIALIDAD"),
    CONSTRAINT "RESERVACION_DOCTOR_FK" FOREIGN KEY ("DOCTOR") REFERENCES "DOCTOR" ("ID_DOCTOR"),
    CONSTRAINT "RESERVACION_CLINICA_FK" FOREIGN KEY ("CLINICA") REFERENCES "CLINICA" ("ID_CLINICA")
)
/

CREATE sequence "RESERVACION_SEQ"
/

CREATE trigger "BI_RESERVACION"
  before insert on "RESERVACION"
  for each row
begin
  if :NEW."ID_RESERVACION" is null then
    select "RESERVACION_SEQ".nextval into :NEW."ID_RESERVACION" from dual;
  end if;
end;
/

CREATE OR REPLACE PROCEDURE reservar(id out integer,usuario in integer,especialidad in integer,doctor in integer,clinica in integer,fecha in TIMESTAMP)
AS
BEGIN
select reservacion_seq.nextval into id from dual;
insert into reservacion(id_reservacion,usuario,especialidad,doctor,clinica,fecha)values(id,usuario,especialidad,doctor,clinica,fecha);
END;
/