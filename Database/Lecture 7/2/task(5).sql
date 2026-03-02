SELECT * FROM employees WHERE salary = (SELECT MAX(salary) FROM employees);

SELECT name FROM employees WHERE department_id = (SELECT department_id FROM employees WHERE name = 'Alice');

SELECT * FROM products WHERE price = (SELECT MIN(price) FROM products);

SELECT department_name FROM departments WHERE department_id = (SELECT department_id FROM employees WHERE salary = (SELECT MAX(salary) FROM employees));

SELECT manager_id FROM employees WHERE hire_date = (SELECT MAX(hire_date) FROM employees);

SELECT name FROM employees WHERE salary = (SELECT AVG(salary) FROM employees);

SELECT * FROM orders WHERE order_date = (SELECT MIN(order_date) FROM orders);

SELECT name, salary FROM employees WHERE salary > (SELECT salary FROM employees WHERE employee_id = 101);

SELECT student_name FROM students WHERE gpa = (SELECT gpa FROM students WHERE student_name = 'John Doe') AND student_name <> 'John Doe';

SELECT book_title FROM books WHERE price = (SELECT MAX(price) FROM books WHERE category = 'Science');