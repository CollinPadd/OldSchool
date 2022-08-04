Select e.FirstName
from Employee e
  inner join Employee m
    on e.ReportsTo = m.EmployeeId
   and e.BirthDate < m.BirthDate
order by e.FirstName Asc
   