-- minh_esoft.accounts definition

CREATE TABLE `accounts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(100) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_username_idx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;


-- minh_esoft.orders definition

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `orders_category_code` varchar(255) NOT NULL,
  `quantity` bigint(20) NOT NULL DEFAULT '0',
  `orders_status` varchar(255) NOT NULL,
  `orders_service_code` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;


-- minh_esoft.orders_category definition

CREATE TABLE `orders_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- minh_esoft.orders_service definition

CREATE TABLE `orders_service` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- minh_esoft.orders_status definition

CREATE TABLE `orders_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- minh_esoft.users definition

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_UN` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;


INSERT INTO minh_esoft.accounts
(username, password, `role`, status, created_at, created_by, updated_at, updated_by, deleted_at)
VALUES('admin', '$2a$10$KXuF6FvsAgEE9mDRMlhtU.6KnBn82BK/jax9o.Uc1KYg84MguWXW2', 'ADMIN', 'ACTIVE', NULL, NULL, NULL, NULL, NULL);
INSERT INTO minh_esoft.accounts
(username, password, `role`, status, created_at, created_by, updated_at, updated_by, deleted_at)
VALUES('esoft', '$2a$10$ct1iHZGibpw5bPFWuOmVqustkTwcWIOwZ8mOo5tuVpvkLPkgcLaQy', 'CUSTOMER', 'ACTIVE', NULL, NULL, NULL, NULL, NULL);
INSERT INTO minh_esoft.accounts
(username, password, `role`, status, created_at, created_by, updated_at, updated_by, deleted_at)
VALUES('minh', '$2a$10$Th0obMU60zfPT2/i71nMz.ZtWRtvzVkyLYlQcNlqS9lzi1U9Zu7Gm', 'CUSTOMER', 'ACTIVE', NULL, NULL, NULL, NULL, NULL);
