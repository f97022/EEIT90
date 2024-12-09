package com.cloudSerenityHotel.bean.user;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer userId; //使用者編號
	private String userName; //使用者名稱
	private String email; //電子信箱
	private String password; //密碼
	private LocalDateTime updateTime; //帳號更新時間
	private String userStatus; //使用者狀態
	private String userIdentity; //使用者身分
	
}
