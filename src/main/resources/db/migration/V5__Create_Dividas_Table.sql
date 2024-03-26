CREATE TABLE IF NOT EXISTS `dividas` (
  `id` bigint NOT NULL,
  `destino` varchar(255) DEFAULT NULL,
  `paga` bit(1) NOT NULL,
  `valor` double NOT NULL,
  `tipo_dividas_id` bigint DEFAULT NULL,
  `data_pagamento` datetime(6) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6kddkoemck6vxwxpvrnvpa6mw` (`tipo_dividas_id`),
  CONSTRAINT `FK6kddkoemck6vxwxpvrnvpa6mw` FOREIGN KEY (`tipo_dividas_id`) REFERENCES `tipo_dividas` (`id`)
) 

	
