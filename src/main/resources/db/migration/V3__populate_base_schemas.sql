
INSERT INTO `branches` (`id`, `code`, `name`) VALUES
	(1, 'GOFACAMP', 'Gofa Camp brach'),
	(2, 'AMBOPIASS', 'Ambo Piassa branch'),
	(3, 'PAMPAM', 'Pomodoro Andarge');


INSERT INTO `cities` (`id`, `code`, `name`) VALUES
	(1, 'ADDISABABA2', 'Addis Ababa23'),
	(2, 'JIMMA', 'Jima');


INSERT INTO `departments` (`id`, `code`, `name`) VALUES
	(1, 'IT', 'Information Technology'),
	(2, 'FINANCE', 'Finance');

INSERT INTO `rights` (`id`, `code`, `description`) VALUES
	(1, 'CREATE_PAYMENT', 'Create payment right'),
	(2, 'VIEW_PAYMENT', 'View payment'),
	(3, 'CANCLE_PAYMENT', 'Cancle Payment'),
	(4, 'EMPLOYEE_REPORT_VIEWER', 'Employee Report Viewer'),
	(5, 'FINANCE_REPORT_VIEWER', 'Finance Report viewer'),
	(6, 'MANAGE_ADMIN_LOOKUP', 'Manage admin Lookups'),
	(7, 'MANAGE_USERS', 'Manage Users'),
	(8, 'MANAGE_ROLES', 'Manage User Roles'),
	(9, 'MANAGE_SYSTEM_SETTNGS', 'View System Settings'),
	(10, 'VIEW_MANAGE_MENU', 'View Manage Menu');

INSERT INTO `roles` (`id`, `code`, `name`, `description`) VALUES
	(1, 'ADMIN', 'Administrator', 'This role is to manage most of the application'),
	(2, 'SUPER_ADMIN', 'Super Admin', 'this role can do everything in the system'),
	(3, 'FINANCE_MANAGER', 'Finance Manager', 'For finance related taks and reports'),
	(4, 'REPORT_VIEWER', 'Report Viewer', 'All report viewr'),
	(5, 'FACILITY_ADMIN', 'Facility Admin', 'Facility administratior. Create, delete ...'),
	(6, 'PAYMENT_OFFICER', 'Payment Officer', 'Roles for the cashiers'),
	(7, 'PURCHASE_MANAGER', 'Manage purchase module', 'Roles that will be given for vice presidents'),
	(22, 'INCIDENT_RESOLVER', 'Incident Resolver', 'ABC'),
	(23, 'INCIDENT MANAGER', 'Incident Manager', 'SDFGSFG'),
	(24, 'ACCOUNT_MNAGER', 'Account Manager', NULL);

INSERT INTO `role_rights` (`id`, `role_id`, `right_id`) VALUES
	(16, 5, 4),
	(17, 4, 4),
	(18, 4, 5),
	(33, 6, 2),
	(52, 23, 1),
	(54, 7, 2),
	(55, 7, 4),
	(66, 22, 3),
	(67, 1, 6),
	(68, 1, 7),
	(69, 1, 10),
	(70, 1, 9),
	(71, 1, 8),
	(72, 24, 2),
	(73, 2, 1),
	(74, 2, 3),
	(75, 2, 2),
	(76, 2, 4),
	(77, 2, 6),
	(78, 2, 8),
	(79, 2, 7),
	(80, 2, 5),
	(81, 2, 9),
	(82, 2, 10);

INSERT INTO `users` (`id`, `email`, `firstname`, `lastname`, `department`, `city`, `branch`, `phone_number`, `cellphone_number`, `address`, `password`, `enabled`) VALUES
	(1, 'aworku@oib.com', 'Adugna', 'Worku', 1, 1, 1, '+25111245854', '0911236589', 'Wollo sefer\nAddis Ababa\nEthiopia', '$2a$10$4Gu/T2V4sr.cO7x3xgQU2Ois10tJCA5kJO0FBezNqhuS95KCen4hW', b'1'),
	(2, '2get@sdf.com', 'Tzera', 'Mamushet', NULL, NULL, NULL, NULL, NULL, NULL, '123', b'1'),
	(3, '3get@sdf.com', 'Natnael', 'Assefa', NULL, NULL, NULL, NULL, NULL, NULL, '123', b'1'),
	(4, '4get@sdf.com', 'Sisay', 'Abebe', 1, 1, 1, '052142', '021542585', NULL, '123', b'1'),
	(5, '5get@sdf.com', 'Getu', 'Gebru', NULL, NULL, NULL, NULL, NULL, NULL, '123', b'1'),
	(6, '6get@sdf.com', 'Yezialem', 'Asrat', NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$qkAoSuUcq77QQIapIKSyL.SnvNerJV.eYIsijN7W0YGMUPjv79CTS', b'1'),
	(7, '7get@sdf.com', 'Geleta', 'Abate', NULL, NULL, NULL, NULL, NULL, NULL, '123', b'1'),
	(8, '8get@sdf.com', 'Abel', 'Tadesse', NULL, NULL, NULL, NULL, NULL, NULL, '123', b'1'),
	(9, 'asdf@asdf.com', 'Firomsa', 'FathersName', 1, 1, 1, '+2513658569', '+251911252487', 'Bistrate Gebreal\nGeba bilo', '123', b'1'),
	(10, 'ASDF@SDFR.JK', 'Bezawit', 'Awoke', NULL, NULL, NULL, 'ASDF', 'ASDF', NULL, '123', b'1'),
	(11, '5ASDF@SDFR.JK', 'Adugna', 'Worku', NULL, NULL, NULL, 'ASDF', 'ASDF', NULL, '123', b'1'),
	(12, 'get@sdf.com', 'sdfg', 'sdfg', NULL, NULL, NULL, NULL, NULL, NULL, '123', b'1'),
	(17, 'g4et@sdf.com', 'asd', 'asdf', NULL, NULL, NULL, NULL, NULL, NULL, '123', b'1'),
	(18, 'asd@sd.com', 'Molla', 'Maru', 1, 1, 1, NULL, NULL, 'asfaasdfsa\nasdf', '123', b'1'),
	(19, 'sdeneke@gmail.com', 'Solomon', 'Deneke', 1, 1, 1, '09100125251`', '415001741', 'Addis Ababa\nKolfe keranyo\nKebele 28, Behind Emperial', '$2a$10$AVotyWocaQWePvU/t6qmYOhtopvvVzlZ8yqfV/U9e4ftspJycZQ2e', b'1');

INSERT INTO `user_role` (`id`, `user_id`, `role_id`) VALUES
	(21, 9, 3),
	(22, 9, 2),
	(23, 9, 6),
	(38, 12, 4),
	(39, 17, 3),
	(46, 4, 2),
	(47, 10, 3),
	(49, 5, 2),
	(50, 8, 3),
	(51, 8, 3),
	(52, 8, 4),
	(53, 8, 6),
	(54, 8, 23),
	(55, 8, 7),
	(56, 8, 2),
	(57, 8, 22),
	(58, 8, 24),
	(65, 1, 4),
	(66, 1, 1),
	(67, 18, 1),
	(68, 18, 3),
	(69, 18, 6),
	(70, 7, 1),
	(71, 7, 4),
	(72, 7, 5),
	(73, 11, 4),
	(74, 11, 1),
	(75, 11, 2),
	(76, 3, 1),
	(77, 19, 1),
	(78, 2, 4),
	(79, 2, 23),
	(80, 6, 4);

