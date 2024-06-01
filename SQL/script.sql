create database learning;
use learning;


INSERT INTO `learning`.`user_entity` (`user_id`, `email`, `full_name`, `password`, `role`, `user_name`) VALUES ('1', 'correo', 'estudent', '123', 'ESTUDENT', 'estudent');
INSERT INTO `learning`.`user_entity` (`user_id`, `email`, `full_name`, `password`, `role`, `user_name`) VALUES ('2', 'corrreo', 'instructor', '123', 'INSTRUCTOR', 'instructor');
INSERT INTO `learning`.`course` (`course_id`, `course_name`, `description`, `fk_instructor_id`) VALUES ('1', 'course', 'course', '1');
INSERT INTO `learning`.`enrrollment` (`enrollment_id`, `enrollment_date`, `fk_course_id`, `fk_user_id`) VALUES ('1', '2024-06-01', '1', '1');
INSERT INTO `learning`.`lesson` (`lesson_id`, `content`, `lesson_title`, `fk_course_id`, `fk_user_id`) VALUES ('1', 'lesson', 'lesson', '1', '1');
INSERT INTO `learning`.`assignment` (`assignment_id`, `assignment_title`, `description`, `due_date`, `fk_lesson_id`) VALUES ('1', 'assignment', 'assignment', '2024-01-06', '1');
INSERT INTO `learning`.`submission` (`submission_id`, `submission_date`, `content`, `grade`, `fk_assignment_id`, `fk_user_id`) VALUES ('1', '2024-06-01', 'submission', '10', '1', '1');
INSERT INTO `learning`.`message` (`message_id`, `message_content`, `sent_date`, `fk_course_id`, `fk_receiver_id`, `fk_sender_id`) VALUES ('1', 'message', '2024-06-01', '1', '1', '2');
INSERT INTO `learning`.`message` (`message_id`, `message_content`, `sent_date`, `fk_course_id`, `fk_receiver_id`, `fk_sender_id`) VALUES ('2', 'message', '2024-06-01', '1', '2', '1');



