INSERT INTO user (username, password, role, email) VALUES

('patient1', '$2a$04$z8Q1b1Z6G5l8Q8Y8Z8Q8Z.8Q8Z8Q8Z8Q8Z8Q8Z8Q8Z8Q8Z8Q8Z8Q', 'PATIENT', 'patient1@example.com'),

('doctor1', '$2a$04$z8Q1b1Z6G5l8Q8Y8Z8Q8Z.8Q8Z8Q8Z8Q8Z8Q8Z8Q8Z8Q8Z8Q8Z8Q', 'DOCTOR', 'doctor1@example.com'),

('admin1', '$2a$04$z8Q1b1Z6G5l8Q8Y8Z8Q8Z.8Q8Z8Q8Z8Q8Z8Q8Z8Q8Z8Q8Z8Q8Z8Q', 'ADMIN', 'admin1@example.com');





INSERT INTO patient (name, dob, address, user_id) VALUES

('John Doe', '1980-01-01', '123 Main St', 1);





INSERT INTO doctor (name, specialty, user_id) VALUES

('Dr. Smith', 'Cardiology', 2);





INSERT INTO appointment (patient_id, doctor_id, appointment_time, status) VALUES

(1, 1, '2025-08-01 10:00:00', 'SCHEDULED');





INSERT INTO audit_log (action, performed_by, timestamp) VALUES

('System initialized', 'admin', '2025-07-28 22:00:00'); 