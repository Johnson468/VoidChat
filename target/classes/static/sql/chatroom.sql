CREATE TABLE `chatroom` (
   `room_id` int(11) NOT NULL,
   `max_members` int(11) DEFAULT NULL,
   `curr_members` int(11) DEFAULT NULL,
   PRIMARY KEY (`room_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=latin1;