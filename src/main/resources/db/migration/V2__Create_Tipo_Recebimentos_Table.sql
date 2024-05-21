CREATE TABLE IF NOT EXISTS `tipo_recebimento` (
   `id` bigint NOT NULL,
  `recorrência` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) 




