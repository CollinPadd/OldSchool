select Name
from Track
where UnitPrice = ( select max(UnitPrice) from Track)
order by Name Asc