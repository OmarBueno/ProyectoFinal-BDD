-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema cafeteria2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cafeteria2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cafeteria2` DEFAULT CHARACTER SET utf8 ;
USE `cafeteria22` ;

-- -----------------------------------------------------
-- Table `cafeteria2`.`articulos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cafeteria2`.`articulos` (
  `id_ats` INT NOT NULL AUTO_INCREMENT,
  `nombre_ats` VARCHAR(45) NOT NULL,
  `descripcion_ats` VARCHAR(200) NOT NULL,
  `precio_ats` DECIMAL(5,2) NOT NULL,
  PRIMARY KEY (`id_ats`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `cafeteria2`.`clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cafeteria2`.`clientes` (
  `id_cls` INT NOT NULL AUTO_INCREMENT,
  `nombre_cls` VARCHAR(20) NOT NULL,
  `ap_paterno_cls` VARCHAR(20) NOT NULL,
  `ap_materno_cls` VARCHAR(20) NOT NULL,
  `correo_cls` VARCHAR(35) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL,
  `contraseña_cls` VARCHAR(20) NOT NULL,
  `telefono_cls` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id_cls`),
  UNIQUE INDEX `correo_cls` (`correo_cls` ASC) VISIBLE,
  UNIQUE INDEX `telefono_cls` (`telefono_cls` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `cafeteria2`.`pedidos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cafeteria2`.`pedidos` (
  `id_pds` INT NOT NULL AUTO_INCREMENT,
  `direccion_pds` VARCHAR(150) NOT NULL,
  `fecha_hora_pds` DATETIME NOT NULL,
  `estado_pds` VARCHAR(15) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL,
  `total_pds` DECIMAL(6,2) NULL DEFAULT NULL,
  `id_cls` INT NOT NULL,
  PRIMARY KEY (`id_pds`),
  INDEX `id_cls` (`id_cls` ASC) VISIBLE,
  CONSTRAINT `pedidos_ibfk_1`
    FOREIGN KEY (`id_cls`)
    REFERENCES `cafeteria2`.`clientes` (`id_cls`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `cafeteria2`.`pedidos_articulos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cafeteria2`.`pedidos_articulos` (
  `id_pds` INT NOT NULL,
  `id_ats` INT NOT NULL,
  `cantidad_pas` INT NOT NULL,
  `precio_unitario_pas` DECIMAL(5,2) NULL DEFAULT NULL,
  `total_pas` DECIMAL(6,2) NULL DEFAULT NULL,
  PRIMARY KEY (`id_pds`, `id_ats`),
  INDEX `id_ats` (`id_ats` ASC) VISIBLE,
  CONSTRAINT `pedidos_articulos_ibfk_1`
    FOREIGN KEY (`id_pds`)
    REFERENCES `cafeteria2`.`pedidos` (`id_pds`),
  CONSTRAINT `pedidos_articulos_ibfk_2`
    FOREIGN KEY (`id_ats`)
    REFERENCES `cafeteria2`.`articulos` (`id_ats`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

USE `cafeteria2` ;

-- -----------------------------------------------------
-- procedure borrar_articulos
-- -----------------------------------------------------

DELIMITER $$
USE `cafeteria2`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `borrar_articulos`(in id int)
BEGIN
    delete from pedidos_articulos where id_ats=id;
	delete from articulos where id_ats=id;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure borrar_clientes
-- -----------------------------------------------------

DELIMITER $$
USE `cafeteria2`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `borrar_clientes`(in id int)
BEGIN
delete from pedidos_articulos where id_pds in (select id_pds from pedidos where id_cls=id); 
	delete from pedidos where id_cls=id;
	delete from clientes where id_cls=id;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure borrar_pedidos
-- -----------------------------------------------------

DELIMITER $$
USE `cafeteria2`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `borrar_pedidos`(in id int)
BEGIN
	delete from pedidos_articulos where id_pds=id;
	delete from pedidos where id_pds=id;
END$$

DELIMITER ;
USE `cafeteria2`;

DELIMITER $$
USE `cafeteria2`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `cafeteria2`.`articulos_BEFORE_UPDATE`
BEFORE UPDATE ON `cafeteria2`.`articulos`
FOR EACH ROW
BEGIN
set new.nombre_ats=upper(new.nombre_ats);
    set new.descripcion_ats=upper(new.descripcion_ats);
END$$

USE `cafeteria2`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `cafeteria2`.`upper_articulos`
BEFORE INSERT ON `cafeteria2`.`articulos`
FOR EACH ROW
BEGIN
	set new.nombre_ats=upper(new.nombre_ats);
    set new.descripcion_ats=upper(new.descripcion_ats);
END$$

USE `cafeteria2`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `cafeteria2`.`clientes_BEFORE_UPDATE`
BEFORE UPDATE ON `cafeteria2`.`clientes`
FOR EACH ROW
BEGIN
	set new.nombre_cls=upper(new.nombre_cls);
    set new.ap_paterno_cls=upper(new.ap_paterno_cls);
    set new.ap_materno_cls=upper(new.ap_materno_cls);
    set new.correo_cls=upper(new.correo_cls);
    set new.telefono_cls=upper(new.telefono_cls);
END$$

USE `cafeteria2`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `cafeteria2`.`upper_clientes`
BEFORE INSERT ON `cafeteria2`.`clientes`
FOR EACH ROW
BEGIN
	set new.nombre_cls=upper(new.nombre_cls);
    set new.ap_paterno_cls=upper(new.ap_paterno_cls);
    set new.ap_materno_cls=upper(new.ap_materno_cls);
    set new.correo_cls=upper(new.correo_cls);
    set new.telefono_cls=upper(new.telefono_cls);
END$$

USE `cafeteria2`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `cafeteria2`.`pedidos_BEFORE_INSERT`
BEFORE INSERT ON `cafeteria2`.`pedidos`
FOR EACH ROW
BEGIN
	set new.direccion_pds=upper(new.direccion_pds);
END$$

USE `cafeteria2`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `cafeteria2`.`pedidos_BEFORE_UPDATE`
BEFORE UPDATE ON `cafeteria2`.`pedidos`
FOR EACH ROW
BEGIN
	set new.direccion_pds=upper(new.direccion_pds);
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
