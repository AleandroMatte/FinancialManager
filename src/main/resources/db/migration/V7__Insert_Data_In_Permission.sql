INSERT INTO `permission` (`id`,`description`) VALUES
	(1,'ADMIN'),
	(2,'MANAGER'),
	(3,'COMMON_USER');

update permission_seq set next_val=4;