select dept_name, avg(salary) 
from instructor 
group by dept_name 
having avg(salary) > 42000;