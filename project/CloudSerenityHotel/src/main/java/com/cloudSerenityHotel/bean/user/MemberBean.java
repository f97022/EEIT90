package com.cloudSerenityHotel.bean.user;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer userId; //使用者編號
	private String memberName; //會員姓名
	private String gender; //性別
	private LocalDate birthday; //生日
	private String phone; //電話
	private String personalIdNo; //身分證字號
	private String country; //國家
	private String address; //地址
	private String passportNo; //護照號碼
	private LocalDateTime registerDate; //註冊時間
	private LocalDateTime updateTime; //資料更新時間
	
}
