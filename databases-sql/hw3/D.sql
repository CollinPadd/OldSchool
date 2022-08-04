SELECT FirstName, Sum(Total) as Spent
FROM Customer,Invoice,InvoiceLine
WHERE Customer.CustomerID = Invoice.CustomerID and Invoice.InvoiceId = InvoiceLine.InvoiceId
GROUP BY Customer.CustomerID
order by Sum(Total) Desc
