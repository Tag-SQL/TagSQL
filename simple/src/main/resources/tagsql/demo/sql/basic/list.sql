-- $ import lib.js
SELECT
 *
FROM demo.user
WHERE 1= 1
AND name = 'value' -- $ lib.replace(@Line,'value',@{name})