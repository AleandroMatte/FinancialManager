CREATE TABLE IF NOT EXISTS `recebimentos` (
  `id` bigint NOT NULL,
  `origem` varchar(255) DEFAULT NULL,
  `recebida` bit(1) NOT NULL,
  `valor` double NOT NULL,
  `tipo_recebimento_id` bigint DEFAULT NULL,
  `data_recebimento` datetime(6) DEFAULT NULL,
   `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb94loe6o7268g8s5xicr51j44` (`tipo_recebimento_id`),
  CONSTRAINT `FKb94loe6o7268g8s5xicr51j44` FOREIGN KEY (`tipo_recebimento_id`) REFERENCES `tipo_recebimento` (`id`)
) 


