SELECT e.name AS emp, m.name AS mgr FROM employees e JOIN employees m ON e.manager_id = m.employee_id;

SELECT c.name , e.name  FROM customers c JOIN employees e ON c.salesman_id = e.employee_id;

SELECT o.order_id, od.product_id FROM orders o JOIN order_details od ON o.order_id = od.order_id;

SELECT s.name , i.name  FROM students s JOIN instructors i ON s.instructor_id = i.instructor_id;

SELECT e.salary, d.budget FROM employees e JOIN departments d ON e.department_id = d.department_id;

SELECT p.name AS project, t.name AS task FROM projects p JOIN tasks t ON p.project_id = t.project_id;

SELECT c.start_date, e.exam_date FROM courses c JOIN exams e ON c.course_id = e.course_id;

SELECT p.name AS product, c.name AS category FROM products p JOIN categories c ON p.category_id = c.category_id;

SELECT b.title, p.name AS publisher FROM books b JOIN publishers p ON b.publisher_id = p.publisher_id;

SELECT e.name, d.location FROM employees e JOIN departments d ON e.department_id = d.department_id;