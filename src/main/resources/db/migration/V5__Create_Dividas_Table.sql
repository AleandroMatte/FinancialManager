
CREATE TABLE IF NOT EXISTS `dividas` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `data_pagamento` datetime(6) DEFAULT NULL,
  `destino` varchar(255) DEFAULT NULL,
  `paga` bit(1) NOT NULL,
  `valor` double NOT NULL,
  `tipo_dividas_id` bigint DEFAULT NULL,
  `fk_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6kddkoemck6vxwxpvrnvpa6mw` (`tipo_dividas_id`),
  KEY `FKd8fijl8cdbtec4v1lwr8lv6ed` (`fk_user_id`),
  CONSTRAINT `FK6kddkoemck6vxwxpvrnvpa6mw` FOREIGN KEY (`tipo_dividas_id`) REFERENCES `tipo_dividas` (`id`),
  CONSTRAINT `FKd8fijl8cdbtec4v1lwr8lv6ed` FOREIGN KEY (`fk_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
