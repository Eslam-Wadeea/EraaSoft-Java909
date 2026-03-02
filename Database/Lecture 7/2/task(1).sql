SELECT e.name , d.name FROM EMPLOYEES e LEFT JOIN DEPARTMENT d ON e.department_id = d.department_id;

SELECT p.product_name, c.category_name FROM products p LEFT JOIN categories c ON p.category_id = c.category_id;

SELECT s.student_name, c.course_id FROM students s LEFT OUTER JOIN courses c ON s.student_id = c.student_id;

SELECT o.order_id, c.customer_name FROM orders o LEFT JOIN customers c ON o.customer_id = c.customer_id;

SELECT d.department_name, m.manager_name FROM departments d LEFT OUTER JOIN managers m ON d.manager_id = m.manager_id;

SELECT b.book_title, a.author_name FROM books b LEFT JOIN authors a ON b.author_id = a.author_id;

SELECT i.invoice_id, p.payment_status FROM invoices i LEFT JOIN payments p ON i.invoice_id = p.invoice_id;

SELECT e.name, pa.project_id FROM employees e LEFT JOIN projects_assigned pa ON e.employee_id = pa.employee_id;