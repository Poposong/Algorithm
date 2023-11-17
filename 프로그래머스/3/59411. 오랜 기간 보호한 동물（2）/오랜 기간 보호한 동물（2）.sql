select b.animal_id, b.name
from animal_ins a
join animal_outs b
on a.animal_id = b.animal_id
order by b.datetime - a.datetime desc limit 2;