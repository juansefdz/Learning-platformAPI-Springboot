create database learning;
use learning;


INSERT INTO `learning`.`assignment` (`assignment_id`, `assignment_title`, `description`, `due_date`, `lesson_id`) VALUES ('1', 'assignmenttest', 'assignmenttest', '2024-05-05', '1');
INSERT INTO `learning`.`enrrollment` (`enrollment_id`, `enrollment_date`, `course_id`, `user_id`) VALUES ('1', '2024-05-5', '1', '1');
INSERT INTO `learning`.`lesson` (`lesson_id`, `content`, `lesson_title`, `course_id`, `user_id`) VALUES ('1', 'lesson prueba', 'lesson prueba', '1', '1');
INSERT INTO `learning`.`message` (`message_id`, `message_content`, `sent_date`, `course_id`, `receiver_id`, `sender_id`) VALUES ('1', 'ping', '2024-05-05', '1', '1', '2');
INSERT INTO `learning`.`submission` (`submission_id`, `content`, `grade`, `assignment_id`, `user_id`) VALUES ('1', 'submission', '10', '1', '1');
