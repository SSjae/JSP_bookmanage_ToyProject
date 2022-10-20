create database bookmanage;

use bookmanage;

CREATE TABLE `admin` (
  `id` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `users` (
  `id` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `name` char(8) NOT NULL,
  `phone` char(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `books` (
  `isbn` varchar(10) NOT NULL,
  `title` varchar(50) NOT NULL,
  `author` varchar(30) NOT NULL,
  `publish` varchar(50) NOT NULL,
  `kind` char(10) NOT NULL,
  `explains` varchar(300) NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`isbn`)
);

CREATE TABLE `loans` (
  `l_num` char(4) NOT NULL,
  `l_u_id` varchar(20) NOT NULL,
  `l_b_isbn` varchar(10) NOT NULL,
  `l_date` date NOT NULL,
  PRIMARY KEY (`l_num`),
  KEY `u_id_idx` (`l_u_id`),
  KEY `b_isbn_idx` (`l_b_isbn`),
  CONSTRAINT `l_b_isbn` FOREIGN KEY (`l_b_isbn`) REFERENCES `books` (`isbn`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `l_u_id` FOREIGN KEY (`l_u_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE `returns` (
  `r_num` char(4) NOT NULL,
  `r_l_num` char(4) NOT NULL,
  `r_u_id` varchar(20) NOT NULL,
  `r_b_isbn` varchar(10) NOT NULL,
  `r_date` date NOT NULL,
  PRIMARY KEY (`r_num`),
  KEY `l_num_idx` (`r_l_num`),
  KEY `u_id_idx` (`r_u_id`),
  KEY `b_isbn_idx` (`r_b_isbn`),
  CONSTRAINT `r_b_isbn` FOREIGN KEY (`r_b_isbn`) REFERENCES `books` (`isbn`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `r_l_num` FOREIGN KEY (`r_l_num`) REFERENCES `loans` (`l_num`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `r_u_id` FOREIGN KEY (`r_u_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `purchases` (
  `p_num` char(4) NOT NULL,
  `p_u_id` varchar(20) NOT NULL,
  `p_b_isbn` varchar(10) NOT NULL,
  `p_date` date NOT NULL,
  `method` char(10) NOT NULL,
  `address` varchar(100) NOT NULL,
  PRIMARY KEY (`p_num`),
  KEY `p_u_id_idx` (`p_u_id`),
  KEY `p_b_isbn_idx` (`p_b_isbn`),
  CONSTRAINT `p_b_isbn` FOREIGN KEY (`p_b_isbn`) REFERENCES `books` (`isbn`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `p_u_id` FOREIGN KEY (`p_u_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);
