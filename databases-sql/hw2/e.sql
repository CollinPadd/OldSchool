select distinct name 
from student natural join course
natural join takes
where course.dept_name='Comp. Sci.'
