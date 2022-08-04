select max(enrollment)
from (select count(ID) as enrollment
from section 
natural join
takes
where semester= 'Sping'
and year = 2009
group by course_id, sec_id