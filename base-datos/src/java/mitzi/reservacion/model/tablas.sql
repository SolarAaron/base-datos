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

/CREATE table "CLINICA" (
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
    constraint  "DOCTOR_PK" primary key ("ID_DOCTOR")
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

ALTER TABLE "DOCTOR" ADD CONSTRAINT "DOCTOR_FK" 
FOREIGN KEY ("ID_ESPECIALIDAD")
REFERENCES "ESPECIALIDAD" ("ID_ESPECIALIDAD")

/
ALTER TABLE "DOCTOR" ADD CONSTRAINT "DOCTOR_FK2" 
FOREIGN KEY ("ID_CLINICA")
REFERENCES "CLINICA" ("ID_CLINICA")

/

    "ID_DOCTOR"    NUMBER NOT NULL,
    "NOMBRE"       VARCHAR2(50),
    "APELLIDO"     VARCHAR2(50),
    "ESPECIALIDAD" NUMBER,
    "CLINICA"      NUMBER,
    constraint  "MEDICO_PK" primary key ("ID_DOCTOR")
)
/

CREATE sequence "MEDICO_SEQ" 
/

CREATE trigger "BI_MEDICO"  
  before insert on "MEDICO"              
  for each row 
begin  
  if :NEW."ID_DOCTOR" is null then
    select "MEDICO_SEQ".nextval into :NEW."ID_DOCTOR" from dual;
  end if;
end;
/   

ALTER TABLE "MEDICO" ADD CONSTRAINT "MEDICO_FK" 



int id=res.getInt(1);
         String nombre=res.getString(2);
         String apellido=res.getString(3);
          int especialidad=res.getInt(4);
          int clinica=res.getInt(5);
            Medico me=new Medico(id,nombre,apellido,especialidad,clinica); 
         med.add(me);
       }
       return med;
FOREIGN KEY ("ESPECIALIDAD")
REFERENCES "ESPECIALIDAD" ("ID_ESPECIALIDAD")

/
ALTER TABLE "MEDICO" ADD CONSTRAINT "MEDICO_FK2" 
FOREIGN KEY ("CLINICA")
REFERENCES "CLINICA" ("ID_CLINICA")
ON DELETE SET NULL

/

CREATE table "RESERVACION" (
    "ID_RESERVACION" NUMBER NOT NULL,
    "USUARIO"        NUMBER,
    "ESPECIALIDAD"   NUMBER,
    "DOCTOR"         NUMBER,
    "CLINICA"        NUMBER,
    "FECHA"          TIMESTAMP WITH TIME ZONE,
    constraint  "RESERVACION_PK" primary key ("ID_RESERVACION")
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

ALTER TABLE "RESERVACION" ADD CONSTRAINT "RESERVACION_FK" 
FOREIGN KEY ("ESPECIALIDAD")
REFERENCES "ESPECIALIDAD" ("ID_ESPECIALIDAD")

/
ALTER TABLE "RESERVACION" ADD CONSTRAINT "RESERVACION_FK2" 
FOREIGN KEY ("DOCTOR")
REFERENCES "MEDICO" ("ID_DOCTOR")

/
ALTER TABLE "RESERVACION" ADD CONSTRAINT "RESERVACION_FK3" 
FOREIGN KEY ("CLINICA")
REFERENCES "CLINICA" ("ID_CLINICA")

/

CREATE table "RESERVACION" (
    "ID_RESERVACION" NUMBER NOT NULL,
    "USUARIO"        NUMBER,
    "ESPECIALIDAD"   NUMBER,
    "DOCTOR"         NUMBER,
    "CLINICA"        NUMBER,
    "FECHA"          TIMESTAMP WITH TIME ZONE,
    constraint  "RESERVACION_PK" primary key ("ID_RESERVACION")
)
/

CREATE sequence "RESERVACION_SEQ" 
STAR WITH 1
INCREMENT BY 1
NOMAXVALUE;
/

CREATE trigger "BI_RESERVACION"  
  before insert on "RESERVACION"              
  for each row 
begin  
  
    select "RESERVACION_SEQ".nextval into :NEW."ID_RESERVACION" from dual;
 
end;
/   

ALTER TABLE "RESERVACION" ADD CONSTRAINT "RESERVACION_FK" 
FOREIGN KEY ("ESPECIALIDAD")
REFERENCES "ESPECIALIDAD" ("ID_ESPECIALIDAD")

/
ALTER TABLE "RESERVACION" ADD CONSTRAINT "RESERVACION_FK2" 
FOREIGN KEY ("DOCTOR")
REFERENCES "MEDICO" ("ID_DOCTOR")

/
ALTER TABLE "RESERVACION" ADD CONSTRAINT "RESERVACION_FK3" 
FOREIGN KEY ("CLINICA")
REFERENCES "CLINICA" ("ID_CLINICA")

/
CREATE table "RESERVACION" (
    "ID_RESERVACION" NUMBER NOT NULL,
    "USUARIO"        NUMBER,
    "ESPECIALIDAD"   NUMBER,
    "DOCTOR"         NUMBER,
    "CLINICA"        NUMBER,
    "FECHA"          TIMESTAMP WITH TIME ZONE,
    constraint  "RESERVACION_PK" primary key ("ID_RESERVACION")
)
/

CREATE sequence "RESERVACION_SEQ" 
STAR WITH 1
INCREMENT BY 1
NOMAXVALUE;
/

CREATE trigger "BI_RESERVACION"  
  before insert on "RESERVACION"              
  for each row 
begin  
  
    select "RESERVACION_SEQ".nextval into :NEW."ID_RESERVACION" from dual;
 
end;
/   

ALTER TABLE "RESERVACION" ADD CONSTRAINT "RESERVACION_FK" 
FOREIGN KEY ("ESPECIALIDAD")
REFERENCES "ESPECIALIDAD" ("ID_ESPECIALIDAD")

/
ALTER TABLE "RESERVACION" ADD CONSTRAINT "RESERVACION_FK2" 
FOREIGN KEY ("DOCTOR")
REFERENCES "MEDICO" ("ID_DOCTOR")

/
ALTER TABLE "RESERVACION" ADD CONSTRAINT "RESERVACION_FK3" 
FOREIGN KEY ("CLINICA")
REFERENCES "CLINICA" ("ID_CLINICA")

/
CREATE table "RESERVACION" (
    "ID_RESERVACION" NUMBER NOT NULL,
    "USUARIO"        NUMBER,
    "ESPECIALIDAD"   NUMBER,
    "DOCTOR"         NUMBER,
    "CLINICA"        NUMBER,
    "FECHA"          TIMESTAMP WITH TIME ZONE,
    constraint  "RESERVACION_PK" primary key ("ID_RESERVACION")
)
/

CREATE sequence "RESERVACION_SEQ" 
STAR WITH 1
INCREMENT BY 1
NOMAXVALUE;
/

CREATE trigger "BI_RESERVACION"  
  before insert on "RESERVACION"              
  for each row 
begin  
  
    select "RESERVACION_SEQ".nextval into :NEW."ID_RESERVACION" from dual;
 
end;
/   

ALTER TABLE "RESERVACION" ADD CONSTRAINT "RESERVACION_FK" 
FOREIGN KEY ("ESPECIALIDAD")
REFERENCES "ESPECIALIDAD" ("ID_ESPECIALIDAD")

/
ALTER TABLE "RESERVACION" ADD CONSTRAINT "RESERVACION_FK2" 
FOREIGN KEY ("DOCTOR")
REFERENCES "MEDICO" ("ID_DOCTOR")

/
ALTER TABLE "RESERVACION" ADD CONSTRAINT "RESERVACION_FK3" 
FOREIGN KEY ("CLINICA")
REFERENCES "CLINICA" ("ID_CLINICA")

/

CREATE OR REPLACE PROCEDURE reservar(id out integer,usuario in integer,especialidad in integer,doctor in integer,clinica in integer,fecha in TIMESTAMP)
AS

valor integer;
BEGIN
insert into reservacion(id_reservacion,usuario,especialidad,doctor,clinica,fecha)values(valor,usuario,especialidad,doctor,clinica,fecha);
END;
/