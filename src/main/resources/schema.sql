

-- -----------------------------------------------------
-- Schema mykart_data
-- -----------------------------------------------------

USE `mykart_data` ;

-- -----------------------------------------------------
-- Table `mykart_data`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mykart_data`.`user` (
  `user_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NULL DEFAULT NULL,
  `address` VARCHAR(50) NOT NULL,
  `mobile_no` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `email` VARCHAR(30) CHARACTER SET 'utf8' NOT NULL,
  `cart_id` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`user_id`));


-- -----------------------------------------------------
-- Table `mykart_data`.`authenticate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mykart_data`.`authenticate` (
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `user_id` BIGINT(20) UNSIGNED NOT NULL,
  UNIQUE INDEX `username` (`username` ASC) VISIBLE,
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  CONSTRAINT `authenticate_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mykart_data`.`user` (`user_id`));


-- -----------------------------------------------------
-- Table `mykart_data`.`cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mykart_data`.`cart` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `item_id` BIGINT(20) NOT NULL,
  `quantity` VARCHAR(50) NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `cart_id` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`));



