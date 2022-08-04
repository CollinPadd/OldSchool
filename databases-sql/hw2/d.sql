select D.dept_name, max(D.salary) 
from instructor D group by dept_name;