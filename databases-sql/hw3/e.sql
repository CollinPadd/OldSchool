SELECT t.Title
FROM Album t
	INNER JOIN Track p ON t.AlbumId = p.AlbumId
GROUP BY t.Title
order by Sum(p.UnitPrice) desc
limit 1;