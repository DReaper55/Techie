-- Insert subjects
INSERT INTO subject (name) VALUES ('Math');
INSERT INTO subject (name) VALUES ('Physics');
INSERT INTO subject (name) VALUES ('Computer');

-- Insert students
INSERT INTO student (name) VALUES ('Daniel Uwadi');
INSERT INTO student (name) VALUES ('Jane Smith');

-- Insert scores
INSERT INTO score (score, student_id, subject_id) VALUES (85, 1, 1);
INSERT INTO score (score, student_id, subject_id) VALUES (80, 1, 2);
INSERT INTO score (score, student_id, subject_id) VALUES (90, 1, 3);
INSERT INTO score (score, student_id, subject_id) VALUES (75, 2, 1);
INSERT INTO score (score, student_id, subject_id) VALUES (70, 2, 2);
INSERT INTO score (score, student_id, subject_id) VALUES (80, 2, 3);
