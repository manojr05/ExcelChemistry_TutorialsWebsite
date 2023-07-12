package com.excel_chemistry.helper;

import com.excel_chemistry.model.User;

public interface EmailSender {
	void sendOtp(int otp, User user);
}
