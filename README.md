Proyecto base-datos
==========

Tablas utilizadas
	usuario_x
		ID: integer
		Login: varchar2(20)
		Password: char2(64) -- Se guarda como sha-256 hexadecimal
		Llave_sec: char2(40) --se guarda como hexadecimal
		
		script:
			create table usuario_x(Id integer, Login Varchar2(20), Password char(64), Llave_sec char(40), Constraint ux_pk Primary Key(Id));
			create sequence ux_sec start with 1 increment by 1 nomaxvalue nocache order;
			create or replace procedure crear_ux(myId Out integer, myLogin IN Varchar2, myPassword IN Char, mySalt IN Char) As
				Begin
					for uu in (select * from usuario_x) Loop
						if myLogin = uu.Login then
							raise_application_error(-20413, 'Usuario ya existente');
						End If;
					end loop;

					select ux_sec.nextVal into myId from dual;
					insert into usuario_x(Id, Login, Password, Llave_sec) Values(myId, myLogin, myPassword, mySalt);
				End;
				/
				create or replace procedure autenticar_usuariox(sip OUT integer, myLogin IN varchar2, myPassword IN char) As
				    exi boolean := false;
				    pasa boolean := false;
				Begin
				    sip := 0;
				    for uu in (select * from usuario_x) loop
				        if myLogin = uu.Login and not exi then
				            exi := true;
				            if myPassword = uu.Password then
				                sip := 1;
				                pasa := true;
				            else
				                sip := 0;
				                pasa := false;
				            end if;
				        end if;
				    end loop;
				
				    if not exi then
				        dbms_output.put_line('Ese usuario ni existe');
				    else
				        if not pasa then
				            dbms_output.put_line('Pos tas mal');
				        end if;
				    end if;
				End;
				/
	
	paciente_x
		...
		
	reservacion_x
		...
