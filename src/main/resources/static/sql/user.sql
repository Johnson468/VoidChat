CREATE TABLE `user` (
   `id` int(11) DEFAULT NULL,
   `username` char(15) DEFAULT NULL,
   `created_room_ids` int(11) DEFAULT NULL
 ) ENGINE=InnoDB DEFAULT CHARSET=latin1;