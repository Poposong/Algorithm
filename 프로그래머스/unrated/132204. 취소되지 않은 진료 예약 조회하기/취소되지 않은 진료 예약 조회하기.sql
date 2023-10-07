-- 코드를 입력하세요
SELECT A.apnt_no, p.pt_name, a.pt_no, A.mcdp_cd, d.dr_name ,a.apnt_ymd
from APPOINTMENT A
join DOCTOR D on D.DR_ID = A.MDDR_ID
join PATIENT P on P.PT_NO = A.PT_NO


and A.APNT_YMD like '%2022-04-13%'
and a.APNT_CNCL_YN = 'N'
and A.mcdp_cd = 'CS'

order by A.apnt_ymd;