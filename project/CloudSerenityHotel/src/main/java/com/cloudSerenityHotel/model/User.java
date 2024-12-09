package com.cloudSerenityHotel.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid")
	private Integer userId; //使用者編號
	
	@Column(name = "user_name")
	private String userName; //使用者名稱
	
	@Column(name = "email")
	private String email; //電子信箱
	
	@Column(name = "password")
	private String password; //密碼
	
	@Column(name = "update_time")
	private LocalDateTime accountUpdateTime; //帳號更新時間
	
	@Column(name = "user_status")
	private String userStatus; //使用者狀態
	
	@Column(name = "user_identity")
	private String userIdentity; //使用者身分
	
	 @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
	private Member member;

}
