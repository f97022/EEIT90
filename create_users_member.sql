
--帳號
CREATE TABLE users (
userid INT IDENTITY(1,1) NOT NULL PRIMARY KEY, --使用者編號(主鍵)
user_name NVARCHAR(100) NOT NULL , --使用者姓名
email NVARCHAR(Max) NOT NULL , --電子信箱
password NVARCHAR(100) NOT NULL , --密碼
update_time DATETIME NOT NULL , --帳號更新時間
user_status NVARCHAR(50) NOT NULL 
CHECK (user_status = 'In_use' OR user_status = 'Logged_out'), --使用者狀態(使用中In_use、已註銷Logged_out)
user_identity NVARCHAR(50) NOT NULL 
CHECK (user_identity = 'admin' OR user_identity = 'user') --使用者身分(管理員admin,會員user)
);

--會員資料
CREATE TABLE members (
userid INT NOT NULL PRIMARY KEY, --使用者編號(主鍵)(連接users)
member_name NVARCHAR(100) NOT NULL , --會員姓名
gender NVARCHAR(10) NOT NULL 
CHECK (gender = 'Male' OR gender = 'Female' OR gender = 'Other'), --性別(男Male、女Female、其他Other)
birthday DATE NOT NULL , --生日
phone NVARCHAR(50) NOT NULL , --電話
personal_id_no NVARCHAR(Max) , --身分證字號(外國人沒有身分證字號)
country NVARCHAR(100) NOT NULL , --國家
address NVARCHAR(Max) NOT NULL , --地址
passport_no NVARCHAR(Max) , --護照號碼(本國人無須護照號碼也可訂房入住)
register_date DATETIME NOT NULL , --註冊時間
update_time DATETIME NOT NULL --資料更新時間
);

--SELECT * FROM users;
--TRUNCATE TABLE users;