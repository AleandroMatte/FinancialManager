CREATE TABLE IF NOT EXISTS `recebimentos` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `data_recebimento` datetime(6) DEFAULT NULL,
  `origem` varchar(255) DEFAULT NULL,
  `recebida` bit(1) NOT NULL,
  `valor` double NOT NULL,
  `tipo_recebimento_id` bigint DEFAULT NULL,
  `fk_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb94loe6o7268g8s5xicr51j44` (`tipo_recebimento_id`),
  KEY `FKasivrxy7uxwka2wp600gpt8ld` (`fk_user_id`),
  CONSTRAINT `FKasivrxy7uxwka2wp600gpt8ld` FOREIGN KEY (`fk_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKb94loe6o7268g8s5xicr51j44` FOREIGN KEY (`tipo_recebimento_id`) REFERENCES `tipo_recebimento` (`id`)
) 
