CREATE DATABASE  IF NOT EXISTS `celix_prueba` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `celix_prueba`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: celix_prueba
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `almacen`
--

DROP TABLE IF EXISTS `almacen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `almacen` (
  `id_almacen` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(200) NOT NULL,
  PRIMARY KEY (`id_almacen`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `almacen`
--

LOCK TABLES `almacen` WRITE;
/*!40000 ALTER TABLE `almacen` DISABLE KEYS */;
/*!40000 ALTER TABLE `almacen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_celular`
--

DROP TABLE IF EXISTS `detalle_celular`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_celular` (
  `id_detalle_celulares` int NOT NULL AUTO_INCREMENT,
  `id_inventario` int NOT NULL,
  `imei_uno` varchar(20) NOT NULL,
  `imei_dos` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_detalle_celulares`),
  KEY `detallecelular_inventario_idx` (`id_inventario`),
  CONSTRAINT `detallecelular_inventario` FOREIGN KEY (`id_inventario`) REFERENCES `inventario` (`id_inventario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_celular`
--

LOCK TABLES `detalle_celular` WRITE;
/*!40000 ALTER TABLE `detalle_celular` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_celular` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_chip`
--

DROP TABLE IF EXISTS `detalle_chip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_chip` (
  `id_detalle_chips` int NOT NULL AUTO_INCREMENT,
  `id_inventario` int NOT NULL,
  `numero_telefonico` varchar(20) NOT NULL,
  `iccid` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_detalle_chips`),
  KEY `detallechip_inventario_idx` (`id_inventario`),
  CONSTRAINT `detallechip_inventario` FOREIGN KEY (`id_inventario`) REFERENCES `inventario` (`id_inventario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_chip`
--

LOCK TABLES `detalle_chip` WRITE;
/*!40000 ALTER TABLE `detalle_chip` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_chip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_nota`
--

DROP TABLE IF EXISTS `detalle_nota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_nota` (
  `id_detalle_nota` int NOT NULL AUTO_INCREMENT,
  `id_nota` int NOT NULL,
  `id_producto` int NOT NULL,
  `cantidad` int NOT NULL,
  PRIMARY KEY (`id_detalle_nota`),
  KEY `detallenota_nota_idx` (`id_nota`),
  CONSTRAINT `detallenota_nota` FOREIGN KEY (`id_nota`) REFERENCES `nota` (`id_nota`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_nota`
--

LOCK TABLES `detalle_nota` WRITE;
/*!40000 ALTER TABLE `detalle_nota` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_nota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estatus_producto`
--

DROP TABLE IF EXISTS `estatus_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estatus_producto` (
  `id_estatus_producto` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(200) NOT NULL,
  PRIMARY KEY (`id_estatus_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estatus_producto`
--

LOCK TABLES `estatus_producto` WRITE;
/*!40000 ALTER TABLE `estatus_producto` DISABLE KEYS */;
INSERT INTO `estatus_producto` VALUES (4,'En garantia');
/*!40000 ALTER TABLE `estatus_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventario`
--

DROP TABLE IF EXISTS `inventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventario` (
  `id_inventario` int NOT NULL AUTO_INCREMENT,
  `id_producto` int NOT NULL,
  `id_estatus_producto` int NOT NULL,
  `factura` varchar(50) DEFAULT NULL,
  `tipo_compra` varchar(1) NOT NULL,
  `id_almacen` int NOT NULL,
  PRIMARY KEY (`id_inventario`),
  KEY `inventario_producto_idx` (`id_producto`),
  KEY `inventario_estatusproducto_idx` (`id_estatus_producto`),
  KEY `inventario_almacen_idx` (`id_almacen`),
  CONSTRAINT `inventario_almacen` FOREIGN KEY (`id_almacen`) REFERENCES `almacen` (`id_almacen`),
  CONSTRAINT `inventario_estatusproducto` FOREIGN KEY (`id_estatus_producto`) REFERENCES `estatus_producto` (`id_estatus_producto`),
  CONSTRAINT `inventario_producto` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventario`
--

LOCK TABLES `inventario` WRITE;
/*!40000 ALTER TABLE `inventario` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marca`
--

DROP TABLE IF EXISTS `marca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marca` (
  `id_marca` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(200) NOT NULL,
  PRIMARY KEY (`id_marca`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marca`
--

LOCK TABLES `marca` WRITE;
/*!40000 ALTER TABLE `marca` DISABLE KEYS */;
/*!40000 ALTER TABLE `marca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nota`
--

DROP TABLE IF EXISTS `nota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nota` (
  `id_nota` int NOT NULL AUTO_INCREMENT,
  `fecha` datetime NOT NULL,
  `id_usuario` int NOT NULL,
  `folio` varchar(5) NOT NULL,
  `serie` varchar(1) NOT NULL,
  PRIMARY KEY (`id_nota`),
  KEY `nota_usuario_idx` (`id_usuario`),
  CONSTRAINT `nota_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nota`
--

LOCK TABLES `nota` WRITE;
/*!40000 ALTER TABLE `nota` DISABLE KEYS */;
/*!40000 ALTER TABLE `nota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id_producto` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(200) NOT NULL,
  `id_tipo_producto` int NOT NULL,
  `precio_compra` decimal(7,2) NOT NULL,
  `precio_venta` decimal(7,2) NOT NULL,
  `codigo_de_barras_tienda` varchar(50) DEFAULT NULL,
  `codigo_de_barras_marca` varchar(50) DEFAULT NULL,
  `id_proveedor` int NOT NULL,
  `id_marca` int NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `fecha_agrego` datetime DEFAULT NULL,
  `usuario_agrego` int DEFAULT NULL,
  `fecha_modifico` datetime DEFAULT NULL,
  `usuario_modifico` int DEFAULT NULL,
  PRIMARY KEY (`id_producto`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`),
  KEY `producto_tipoproducto_idx` (`id_tipo_producto`),
  KEY `producto_proveedor_idx` (`id_proveedor`),
  KEY `producto_marca_idx` (`id_marca`),
  CONSTRAINT `producto_marca` FOREIGN KEY (`id_marca`) REFERENCES `marca` (`id_marca`),
  CONSTRAINT `producto_proveedor` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`),
  CONSTRAINT `producto_tipoproducto` FOREIGN KEY (`id_tipo_producto`) REFERENCES `tipo_producto` (`id_tipo_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedor` (
  `id_proveedor` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) NOT NULL,
  `direccion` varchar(200) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `correo_electronico` varchar(100) DEFAULT NULL,
  `sitio_web` varchar(100) DEFAULT NULL,
  `rfc` varchar(50) NOT NULL,
  `razon_social` varchar(200) NOT NULL,
  PRIMARY KEY (`id_proveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (1,'Celular Milenium','Av. Juarez Norte No.11 Colonia Centro Tizayuca Hidalgo C.P 43800','7751277889','celular.milenium@gmail.com','www.cellularmilenium.com','ERT343ERT5454P4','Celulares de Pachuca S.A de C.V'),(2,'DAR','Av.Tuipanes No.65 Colonia El Rosarito Pachuca Hidalgo','7712345678','dar@gmail.com','www.dar.com.mx','454FGHJHJ354435','Celulares Chafitas S.A de C.V');
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tienda`
--

DROP TABLE IF EXISTS `tienda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tienda` (
  `id_tienda` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `direccion` varchar(400) NOT NULL,
  `telefono_fijo` varchar(20) DEFAULT NULL,
  `telefono_celular` varchar(20) DEFAULT NULL,
  `correo_electronico` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_tienda`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tienda`
--

LOCK TABLES `tienda` WRITE;
/*!40000 ALTER TABLE `tienda` DISABLE KEYS */;
/*!40000 ALTER TABLE `tienda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_producto`
--

DROP TABLE IF EXISTS `tipo_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_producto` (
  `id_tipo_producto` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(200) NOT NULL,
  PRIMARY KEY (`id_tipo_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_producto`
--

LOCK TABLES `tipo_producto` WRITE;
/*!40000 ALTER TABLE `tipo_producto` DISABLE KEYS */;
INSERT INTO `tipo_producto` VALUES (1,'Cargador'),(2,'Mica');
/*!40000 ALTER TABLE `tipo_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_usuario`
--

DROP TABLE IF EXISTS `tipo_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_usuario` (
  `id_tipo_usuario` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(200) NOT NULL,
  PRIMARY KEY (`id_tipo_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_usuario`
--

LOCK TABLES `tipo_usuario` WRITE;
/*!40000 ALTER TABLE `tipo_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_documento`
--

DROP TABLE IF EXISTS `tipo_documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_documento` (
  `id_tipo_documento` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(200) NOT NULL,
  PRIMARY KEY (`id_tipo_documento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_documento`
--

LOCK TABLES `tipo_documento` WRITE;
/*!40000 ALTER TABLE `tipo_documento` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_documento` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellido_paterno` varchar(50) NOT NULL,
  `apellido_materno` varchar(50) NOT NULL,
  `user` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `direccion` varchar(200) NOT NULL,
  `id_tipo_usuario` int NOT NULL,
  `id_tienda` int NOT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `usuario_tipousuario_idx` (`id_tipo_usuario`),
  KEY `usuario_tienda_idx` (`id_tienda`),
  CONSTRAINT `usuario_tienda` FOREIGN KEY (`id_tienda`) REFERENCES `tienda` (`id_tienda`),
  CONSTRAINT `usuario_tipousuario` FOREIGN KEY (`id_tipo_usuario`) REFERENCES `tipo_usuario` (`id_tipo_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-20 13:53:36


ALTER TABLE `celix_prueba`.`producto` 
ADD COLUMN `precio_compra_iva` DECIMAL(7,2) NOT NULL AFTER `usuario_modifico`;

ALTER TABLE `celix_prueba`.`inventario` 
CHANGE COLUMN `factura` `numero_documento` VARCHAR(50) NULL DEFAULT NULL ,
CHANGE COLUMN `tipo_compra` `tipo_documento` INT NOT NULL ,
ADD INDEX `inventario_tipo_documento_idx` (`tipo_documento` ASC) VISIBLE;
;
ALTER TABLE `celix_prueba`.`inventario` 
ADD CONSTRAINT `inventario_tipo_documento`
  FOREIGN KEY (`tipo_documento`)
  REFERENCES `celix_prueba`.`tipo_documento` (`id_tipo_documento`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
  
  ALTER TABLE `celix_prueba`.`inventario` 
ADD COLUMN `precio_compra` DECIMAL(7,2) NOT NULL AFTER `id_almacen`,
ADD COLUMN `precio_compra_iva` DECIMAL(7,2) NOT NULL AFTER `precio_compra`,
ADD COLUMN `id_proveedor` INT NOT NULL AFTER `precio_compra_iva`,
ADD COLUMN `fecha_compra` DATETIME NOT NULL AFTER `id_proveedor`;

ALTER TABLE `celix_prueba`.`inventario` 
DROP FOREIGN KEY `inventario_producto`,
DROP FOREIGN KEY `inventario_estatusproducto`,
DROP FOREIGN KEY `inventario_almacen`;
ALTER TABLE `celix_prueba`.`inventario` 
DROP COLUMN `precio_compra_iva`,
DROP COLUMN `precio_compra`,
DROP COLUMN `id_almacen`,
DROP COLUMN `id_estatus_producto`,
DROP COLUMN `id_producto`,
DROP INDEX `inventario_almacen_idx` ,
DROP INDEX `inventario_estatusproducto_idx` ,
DROP INDEX `inventario_producto_idx` ;
;

CREATE TABLE `celix_prueba`.`detalle_inventario_producto` (
  `id_detalle_inventario_producto` INT NOT NULL AUTO_INCREMENT,
  `id_inventario` INT NOT NULL,
  `id_producto` INT NOT NULL,
  `id_estatus_producto` INT NOT NULL,
  `id_almacen` INT NOT NULL,
  `precio_compra` DECIMAL(7,2) NOT NULL,
  `precio_compra_iva` DECIMAL(7,2) NOT NULL,
  PRIMARY KEY (`id_detalle_inventario_producto`),
  INDEX `inventario_fk_idx` (`id_inventario` ASC) VISIBLE,
  INDEX `producto_fk_idx` (`id_producto` ASC) VISIBLE,
  INDEX `estatus_producto_fk_idx` (`id_estatus_producto` ASC) VISIBLE,
  INDEX `almacen_fk_idx` (`id_almacen` ASC) VISIBLE,
  CONSTRAINT `inventario_fk`
    FOREIGN KEY (`id_inventario`)
    REFERENCES `celix_prueba`.`inventario` (`id_inventario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `producto_fk`
    FOREIGN KEY (`id_producto`)
    REFERENCES `celix_prueba`.`producto` (`id_producto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `estatus_producto_fk`
    FOREIGN KEY (`id_estatus_producto`)
    REFERENCES `celix_prueba`.`estatus_producto` (`id_estatus_producto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `almacen_fk`
    FOREIGN KEY (`id_almacen`)
    REFERENCES `celix_prueba`.`almacen` (`id_almacen`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    ALTER TABLE `celix_prueba`.`detalle_celular` 
RENAME TO  `celix_prueba`.`detalle_inventario_celular` ;

ALTER TABLE `celix_prueba`.`detalle_chip` 
RENAME TO  `celix_prueba`.`detalle_inventario_chip` ;
