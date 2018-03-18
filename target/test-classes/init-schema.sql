DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `u_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `u_name` varchar(64) NOT NULL DEFAULT '',
  `u_password` varchar(128) NOT NULL DEFAULT '',
  `head_url` varchar(256) NOT NULL DEFAULT '',
  `salt` varchar(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `u_name` (`u_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `n_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(128) NOT NULL DEFAULT '',
  `link` varchar(256) NOT NULL DEFAULT '',
  `image` varchar(256) NOT NULL DEFAULT '',
  `like_count` int NOT NULL,
  `comment_count` int NOT NULL,
  `n_date` datetime NOT NULL,
  `u_id` int(11) NOT NULL,
  PRIMARY KEY (`n_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `login_ticket`;
CREATE TABLE `login_ticket` (
  `l_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NOT NULL,
  `ticket` varchar(45) NOT NULL,
  `expired` datetime NOT NULL,
  `status` int NULL DEFAULT 0,
  PRIMARY KEY (`l_id`),
  UNIQUE INDEX `ticket_UNIQUE` (`ticket` ASC));
  
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
`c_id` INT NOT NULL AUTO_INCREMENT,
`c_content` TEXT NOT NULL,
`u_id` INT NOT NULL,
`entity_id` INT NOT NULL,
`entity_type` INT NOT NULL,
`c_date` DATETIME NOT NULL,
`status` INT NOT NULL DEFAULT 0,
PRIMARY KEY (`c_id`),
INDEX `entity_index` (`entity_id` ASC, `entity_type` ASC)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET sql_mode = '';
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `m_id` INT NOT NULL AUTO_INCREMENT,
  `from_id` INT NULL,
  `to_id` INT NULL,
  `m_content` TEXT NULL,
  `m_date` DATETIME NULL,
  `has_read` INT NULL,
  `conversation_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`m_id`),
  INDEX `conversation_index` (`conversation_id` ASC),
  INDEX `m_date` (`m_date` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
SET sql_mode = '';