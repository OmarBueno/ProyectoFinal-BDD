SET NAMES 'utf8';
DROP DATABASE IF EXISTS cafeteria;
CREATE DATABASE IF NOT EXISTS cafeteria DEFAULT CHARACTER SET utf8;
USE cafeteria;

CREATE TABLE clientes(
id_cls				INTEGER NOT NULL AUTO_INCREMENT,
nombre_cls			VARCHAR(20) NOT NULL,
ap_paterno_cls		VARCHAR(20) NOT NULL,
ap_materno_cls		VARCHAR(20) NOT NULL,
correo_cls			VARCHAR(35) BINARY UNIQUE NOT NULL,
contraseña_cls		VARCHAR(20) NOT NULL,
telefono_cls		VARCHAR(15) UNIQUE NOT NULL,
PRIMARY KEY(id_cls),
CONSTRAINT rango_correo CHECK (correo_cls LIKE '%@%.%'),
CONSTRAINT apellido_paterno CHECK (REGEXP_LIKE(ap_paterno_cls,'[A-Z]')),
CONSTRAINT apellido_materno CHECK (REGEXP_LIKE(ap_materno_cls,'[A-Z]'))
)DEFAULT CHARACTER SET utf8;

CREATE TABLE pedidos(
id_pds				INTEGER NOT NULL AUTO_INCREMENT,
id_cls				INTEGER NOT NULL,
direccion_pds		VARCHAR(150) NOT NULL,
fecha_hora_pds		DATETIME NOT NULL,
estado_pds			VARCHAR(15) BINARY NOT NULL,
total_pds			DECIMAL(6,2),
PRIMARY KEY(id_pds),
FOREIGN KEY(id_cls) REFERENCES clientes(id_cls),
CONSTRAINT verificar_estado CHECK(estado_pds IN ('CANCELADO','EN PREPARACION','FINALIZADO','NUEVO'))
)DEFAULT CHARACTER SET utf8;


CREATE TABLE articulos(
id_ats				INTEGER NOT NULL AUTO_INCREMENT,
nombre_ats			VARCHAR(45) NOT NULL,
descripcion_ats		VARCHAR(200) NOT NULL,
precio_ats			DECIMAL(5,2) NOT NULL,
PRIMARY KEY(id_ats),
CONSTRAINT nombre CHECK (REGEXP_LIKE(nombre_ats,'[A-Z]'))
)DEFAULT CHARACTER SET utf8;

CREATE TABLE pedidos_articulos(
id_pds					INTEGER	NOT NULL,
id_ats					INTEGER NOT NULL,
cantidad_pas			INTEGER	NOT NULL,
precio_unitario_pas		DECIMAL(5,2),
total_pas				DECIMAL(6,2),
PRIMARY KEY(id_pds,id_ats),
FOREIGN KEY(id_pds) REFERENCES pedidos(id_pds),
FOREIGN KEY(id_ats) REFERENCES articulos(id_ats)
)DEFAULT CHARACTER SET utf8;

DELIMITER $$
USE `cafeteria`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `cafeteria`.`upper_articulos`
BEFORE INSERT ON `cafeteria`.`articulos`
FOR EACH ROW
BEGIN
	set new.nombre_ats=upper(new.nombre_ats);
    set new.descripcion_ats=upper(new.descripcion_ats);
END$$

USE `cafeteria`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `cafeteria`.`upper_extras`
BEFORE INSERT ON `cafeteria`.`extras`
FOR EACH ROW
BEGIN
	set new.nombre_exs=upper(new.nombre_exs);
END$$
USE `cafeteria`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `cafeteria`.`pedidos_BEFORE_INSERT`
BEFORE INSERT ON `cafeteria`.`pedidos`
FOR EACH ROW
BEGIN
	set new.direccion_pds=upper(new.direccion_pds);
END$$

USE `cafeteria`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `cafeteria`.`upper_clientes`
BEFORE INSERT ON `cafeteria`.`clientes`
FOR EACH ROW
BEGIN
	set new.nombre_cls=upper(new.nombre_cls);
    set new.ap_paterno_cls=upper(new.ap_paterno_cls);
    set new.ap_materno_cls=upper(new.ap_materno_cls);
    set new.correo_cls=upper(new.correo_cls);
    set new.telefono_cls=upper(new.telefono_cls);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `borrar_clientes`(in id int)
BEGIN
	delete from pedidos_articulos where id_pds in (select id_pds from pedidos where id_cls=id); 
	delete from pedidos where id_cls=id;
	delete from clientes where id_cls=id;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `borrar_pedidos`(in id int)
BEGIN
	delete from pedidos_articulos where id_pds=id;
	delete from pedidos where id_pds=id;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `borrar_articulos`(in id int)
BEGIN
	CREATE TEMPORARY TABLE IF NOT EXISTS pedidos_temporal AS (select id_pds from pedidos_articulos where id_ats=id);
    delete from pedidos_articulos where id_ats=id;
    delete from pedidos where id_pds in (select * from pedidos_temporal);
	delete from articulos where id_ats=id;
END

DELIMITER ;
