
--帳號
CREATE TABLE users (
userid INT IDENTITY(1,1) NOT NULL PRIMARY KEY, --使用者編號(主鍵、外鍵)
user_name NVARCHAR(100) NOT NULL , --使用者姓名
email NVARCHAR(Max) NOT NULL , --電子信箱
password NVARCHAR(100) NOT NULL , --密碼
update_time DATETIME NOT NULL , --帳號更新時間
user_status NVARCHAR(50) NOT NULL , --使用狀態(使用中In_use、已註銷Logged_out)
user_identity NVARCHAR(50) NOT NULL  --使用者身分(管理員admin,會員user)
);

--會員資料
CREATE TABLE members (
userid INT NOT NULL PRIMARY KEY, --使用者編號(主鍵)(連接users)
gender NVARCHAR(10) NOT NULL , --性別(男Male、女Female、其他Other)
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
--SELECT * FROM members;
--TRUNCATE TABLE members;
--SELECT * FROM users u JOIN members m ON u.userid = m.userid;
--SELECT m.userid, u.email,u.password,u.update_time AS acc_update_time, u.user_status,m.member_name,m.gender,m.birthday,m.phone,m.personal_id_no,m.country,m.address,m.passport_no,m.register_date,m.update_time AS data_update_time FROM users u JOIN members m ON u.userid = m.userid WHERE u.userid = 2;
--SELECT * FROM users u JOIN members m ON u.userid = m.userid WHERE m.member_name LIKE '%member%';
--INSERT INTO users(user_name,email,password,user_status,user_identity,update_time) VALUES('test001','test001@mail.com','Test@001','In_use','admin','2024-11-21');
--INSERT INTO users(user_name,email,password,user_status,user_identity,update_time) VALUES('test002','test002@mail.com','Test@002','In_use','admin','2024-11-22');
--INSERT INTO users(user_name,email,password,user_status,user_identity,update_time) VALUES('test003','test003@mail.com','Test@003','In_use','admin','2024-11-23');
--INSERT INTO users(user_name,email,password,user_status,user_identity,update_time) VALUES('test004','test004@mail.com','Test@004','In_use','user','2024-11-24');
--INSERT INTO users(user_name,email,password,user_status,user_identity,update_time) VALUES('test005','test005@mail.com','Test@005','In_use','user','2024-11-25');
--INSERT INTO users(user_name,email,password,user_status,user_identity,update_time) VALUES('test006','test006@mail.com','Test@006','In_use','user','2024-11-26');

--INSERT INTO members(userid,gender,birthday,phone,personal_id_no,country,address,passport_no,register_date,update_time) VALUES(4,'Male','2024-11-24','0900100100','A123456789','TWN','0123456789','','2024-11-24','2024-11-24')
--INSERT INTO members(userid,gender,birthday,phone,personal_id_no,country,address,passport_no,register_date,update_time) VALUES(5,'Male','2024-11-25','0900100100','A123456789','TWN','0123456789','','2024-11-25','2024-11-25')
--INSERT INTO members(userid,gender,birthday,phone,personal_id_no,country,address,passport_no,register_date,update_time) VALUES(6,'Male','2024-11-26','0900100100','A123456789','TWN','0123456789','','2024-11-26','2024-11-26')