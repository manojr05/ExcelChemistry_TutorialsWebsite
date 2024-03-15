package com.excel_chemistry.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "user")
public class User {
	private String name;
	private String education;
	private String email;
	private long phone;
	private Date dob;
	private String state;
	private String password;
}
