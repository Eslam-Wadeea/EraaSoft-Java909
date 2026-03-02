SELECT first_name, last_name, department_name FROM employees NATURAL JOIN departments;

SELECT order_id, customer_name FROM orders NATURAL JOIN customers;

SELECT student_name, course_name FROM students  NATURAL JOIN courses;

SELECT project_name, first_name, last_name FROM projects  NATURAL JOIN employees;

SELECT invoice_id, product_name, quantity FROM invoices NATURAL JOIN products;


SELECT book_title, author_name FROM books NATURAL JOIN authors;


SELECT class_time, instructor_name FROM class_schedules NATURAL JOIN instructors;


SELECT supplier_name, product_name FROM suppliers NATURAL JOIN products;


SELECT order_id, shipping_status, shipping_date FROM orders NATURAL JOIN shipments;


SELECT first_name, last_name, job_title FROM employees NATURAL JOIN jobs;
