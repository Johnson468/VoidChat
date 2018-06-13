CREATE TABLE `messages` (
   `room_id` int(11) NOT NULL,
   `contents` varchar(255) DEFAULT NULL,
   `sender` char(20) DEFAULT NULL
 ) ENGINE=InnoDB DEFAULT CHARSET=latin1;