CREATE TABLE `data` (
  `id` SMALLINT NOT NULL AUTO_INCREMENT,
  `title` TEXT NULL,
  `img` TEXT NULL,
  `desc` TEXT NULL,
  PRIMARY KEY (`id`)
) COLLATE = 'utf8_general_ci' ENGINE = InnoDB;