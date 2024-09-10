-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Senicare
-- -----------------------------------------------------
-- 시니케어 프로젝트
-- 

-- -----------------------------------------------------
-- Schema Senicare
--
-- 시니케어 프로젝트
-- 
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Senicare` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `Senicare` ;

-- -----------------------------------------------------
-- Table `Senicare`.`tel_auth_number`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Senicare`.`tel_auth_number` (
  `tel_number` VARCHAR(11) NOT NULL COMMENT '인증전화번호',
  `auth_number` VARCHAR(4) NOT NULL COMMENT '인증번호\n',
  PRIMARY KEY (`tel_number`),
  UNIQUE INDEX `tel_number_UNIQUE` (`tel_number` ASC) VISIBLE)
ENGINE = InnoDB
COMMENT = '전화번호 인증 테이블';


-- -----------------------------------------------------
-- Table `Senicare`.`nurses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Senicare`.`nurses` (
  `user_id` VARCHAR(20) NOT NULL COMMENT '요양사 아이디',
  `password` VARCHAR(255) NOT NULL COMMENT '암호화된 비밀번호',
  `name` VARCHAR(15) NOT NULL COMMENT '요양사 이름',
  `tel_number` VARCHAR(11) NOT NULL COMMENT '요양사 전화번호',
  `join_path` VARCHAR(5) NOT NULL COMMENT '가입 경로',
  `sns_id` VARCHAR(255) NULL COMMENT 'OAuth2 아이디',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `tel_number_UNIQUE` (`tel_number` ASC) VISIBLE,
  CONSTRAINT `tel_auth_number`
    FOREIGN KEY (`tel_number`)
    REFERENCES `Senicare`.`tel_auth_number` (`tel_number`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = '요양사 테이블';


-- -----------------------------------------------------
-- Table `Senicare`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Senicare`.`customer` (
  `custom_number` INT NOT NULL AUTO_INCREMENT COMMENT '고객 번호',
  `profile_image` TEXT NOT NULL COMMENT '고객 이미지',
  `name` VARCHAR(15) NOT NULL COMMENT '고객이름',
  `birh` VARCHAR(6) NOT NULL COMMENT '고객 생년월일(YYMMDD)',
  `charger` VARCHAR(20) NOT NULL COMMENT '담당 요양사',
  `address` TEXT NOT NULL COMMENT '주소',
  `location` VARCHAR(60) NOT NULL COMMENT '지역(시, 도)',
  PRIMARY KEY (`custom_number`),
  UNIQUE INDEX `custom_number_UNIQUE` (`custom_number` ASC) VISIBLE,
  INDEX `customer_charger_idx` (`charger` ASC) VISIBLE,
  CONSTRAINT `customer_charger`
    FOREIGN KEY (`charger`)
    REFERENCES `Senicare`.`nurses` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '고객 테이블';


-- -----------------------------------------------------
-- Table `Senicare`.`tools`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Senicare`.`tools` (
  `tool_number` INT NOT NULL AUTO_INCREMENT COMMENT '용품 번호',
  `name` VARCHAR(255) NOT NULL COMMENT '용품 명',
  `purpose` TEXT NOT NULL COMMENT '용품 용도',
  `count` INT NOT NULL COMMENT '용품 잔여 수량',
  PRIMARY KEY (`tool_number`))
ENGINE = InnoDB
COMMENT = '용품 테이블';


-- -----------------------------------------------------
-- Table `Senicare`.`care_records`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Senicare`.`care_records` (
  `record_number` INT NOT NULL AUTO_INCREMENT COMMENT '관리 기록 번호',
  `record_date` DATE NOT NULL COMMENT '관리 기록 날짜',
  `contents` TEXT NOT NULL COMMENT '관리 내용',
  `used_tool` INT NULL COMMENT '관리에 사용한 용품',
  `count` INT NULL COMMENT '관리시 사용한 용품 개수',
  `charger` VARCHAR(20) NOT NULL COMMENT '관리 담당 요양사',
  `custom_number` INT NOT NULL COMMENT '관리 대상 고객',
  PRIMARY KEY (`record_number`),
  UNIQUE INDEX `record_number_UNIQUE` (`record_number` ASC) VISIBLE,
  INDEX `used_tool_idx` (`used_tool` ASC) VISIBLE,
  INDEX `care_charger_idx` (`charger` ASC) VISIBLE,
  INDEX `record_customer_idx` (`custom_number` ASC) VISIBLE,
  CONSTRAINT `used_tools`
    FOREIGN KEY (`used_tool`)
    REFERENCES `Senicare`.`tools` (`tool_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `record_charger`
    FOREIGN KEY (`charger`)
    REFERENCES `Senicare`.`nurses` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `record_customer`
    FOREIGN KEY (`custom_number`)
    REFERENCES `Senicare`.`customer` (`custom_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '관리 기록 테이블';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

CREATE USER 'senicare'@'%' IDENTIFIED BY '3478';

GRANT all privileges ON senicare.* TO 'senicare'@'%';