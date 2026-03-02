SELECT d.department_name, e.first_name FROM employees e RIGHT OUTER JOIN departments d ON e.department_id = d.department_id;

SELECT c.customer_name, o.order_id FROM orders o RIGHT JOIN customers c ON o.customer_id = c.customer_id;

SELECT c.course_title, s.student_name FROM students s RIGHT JOIN courses c ON s.course_id = c.course_id;

SELECT e.name, p.project_name FROM employees e RIGHT OUTER JOIN projects p ON e.employee_id = p.manager_id;

SELECT t.transaction_id, pm.method_name FROM transactions t RIGHT JOIN payment_methods pm ON t.method_id = pm.method_id;

SELECT b.title, a.author_name FROM books b RIGHT OUTER JOIN authors a ON b.author_id = a.author_id;

SELECT p.product_name, c.category_name FROM products p RIGHT JOIN categories c ON p.category_id = c.category_id;

SELECT s.student_name, dr.room_number FROM students s RIGHT JOIN dorm_rooms dr ON s.dorm_id = dr.dorm_id;