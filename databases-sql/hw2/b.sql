select ID, name
from instructor
where salary = (select max(salary) from instructor)