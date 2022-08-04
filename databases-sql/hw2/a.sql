select course_id 
from section 
where semester = 'spring' and year = 2009 
union
select course_id 
from section 
where semester = 'fall' and year = 2010