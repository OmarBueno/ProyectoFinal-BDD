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

CREATE TABLE extras(
id_exs				INTEGER NOT NULL AUTO_INCREMENT,
nombre_exs			VARCHAR(35) NOT NULL,
PRIMARY KEY(id_exs)
)DEFAULT CHARACTER SET utf8;

CREATE TABLE articulos(
id_ats				INTEGER NOT NULL AUTO_INCREMENT,
nombre_ats			VARCHAR(45) NOT NULL,
descripcion_ats		VARCHAR(200) NOT NULL,
precio_ats			DECIMAL(5,2) NOT NULL,
tipo_leche_ats		VARCHAR(20) BINARY,
id_exs				INTEGER,
crema_batida_exs	BOOLEAN,
PRIMARY KEY(id_ats),
FOREIGN KEY(id_exs) REFERENCES extras(id_exs),
CONSTRAINT nombre CHECK (REGEXP_LIKE(nombre_ats,'[A-Z]')),
CONSTRAINT tipo CHECK (REGEXP_LIKE(tipo_leche_ats,'[A-Z]')),
CONSTRAINT verificar_leche CHECK(tipo_leche_ats IN ('ENTERA','LIGHT','DESLACTOSADA','ALMENDRA','SOYA','COCO'))
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