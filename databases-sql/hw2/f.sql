select course_id, sec_id, Count(*) as Enrollment
from section natural join takes
where semester='Spring' and year = '2009'
group by Course_id, Sec_id;