-- inside
SELECT A.*
FROM   USER A
WHERE  1 = 1
       AND A.first_name LIKE '%' -- $ lib.replace(@Line,'%',@{firstName})
       AND A.birthday BETWEEN '1990-1-1' AND '2020-12-30'     -- $ lib.replace(@Line,'1990-1-1',@{beginDate},'2020-12-30',@{endDate})
       AND A.score > 10.5 -- $ lib.replace(@Line,10.5,@{score})
-- GROUP  BY A.rank,
--          A.first_name,
--          A.last_name
ORDER  BY
    A.rank,
    A.score