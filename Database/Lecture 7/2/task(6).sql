SELECT * FROM employees WHERE salary > ANY (SELECT salary FROM employees WHERE department_id = 10);

SELECT * FROM employees WHERE salary < ALL (SELECT salary FROM employees WHERE department_id = 20);

SELECT * FROM products WHERE price IN (SELECT price FROM products WHERE category = 'Electronics');

SELECT name FROM customers WHERE customer_id IN (SELECT o.customer_id FROM orders o JOIN order_details od ON o.order_id = od.order_id JOIN products p ON od.product_id = p.product_id WHERE p.price > 1000);

SELECT * FROM employees WHERE job_title IN (SELECT job_title FROM employees GROUP BY job_title HAVING COUNT(*) > 1);

SELECT * FROM departments WHERE department_id IN (SELECT department_id FROM employees GROUP BY department_id HAVING COUNT(*) > 1);

SELECT * FROM orders o WHERE o.customer_id IN (SELECT c1.customer_id FROM customers c1 WHERE c1.city IN (SELECT c2.city FROM customers c2 JOIN orders o2 ON c2.customer_id = o2.customer_id));

SELECT * FROM books WHERE author_id IN (SELECT author_id FROM books GROUP BY author_id HAVING COUNT(*) > 1);

SELECT name FROM students WHERE course_id IN (SELECT course_id FROM courses WHERE professor_name = 'Dr. Smith');

SELECT * FROM employees WHERE salary IN (SELECT salary FROM employees WHERE department_id = 30);