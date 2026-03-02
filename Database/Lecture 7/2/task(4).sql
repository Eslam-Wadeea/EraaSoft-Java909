SELECT name FROM employees WHERE salary > (SELECT AVG(salary) FROM employees);

SELECT customer_name FROM customers WHERE customer_id IN (SELECT customer_id FROM orders GROUP BY customer_id HAVING COUNT(*) = (SELECT MAX(COUNT(*)) FROM orders GROUP BY customer_id));

SELECT product_name FROM products WHERE price > ANY (SELECT price FROM products WHERE category = 'Accessories');

SELECT name FROM employees WHERE department_id = (SELECT department_id FROM employees WHERE name = 'John Smith') AND name <> 'John Smith';

SELECT order_id FROM orders WHERE customer_id IN (SELECT customer_id FROM customers WHERE city = 'New York');

SELECT department_name FROM departments d WHERE NOT EXISTS (SELECT 1 FROM employees e WHERE e.department_id = d.department_id);

SELECT student_name FROM students WHERE student_id NOT IN (SELECT student_id FROM enrollments WHERE student_id IS NOT NULL);

SELECT MAX(salary) FROM employees WHERE salary < (SELECT MAX(salary) FROM employees);

SELECT product_name FROM products WHERE price > (SELECT AVG(price) FROM products);

SELECT c.customer_name FROM customers c WHERE NOT EXISTS (SELECT p.product_id FROM products p WHERE p.category = 'A' MINUS SELECT o.product_id FROM orders o WHERE o.customer_id = c.customer_id);