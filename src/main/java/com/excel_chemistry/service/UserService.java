package com.excel_chemistry.service;

import org.springframework.web.servlet.ModelAndView;
import com.excel_chemistry.model.User;
import jakarta.servlet.http.HttpSession;

public interface UserService {

	ModelAndView registerUser(User user, String dateOfBirth, HttpSession session);
	ModelAndView verifyEmail(int userOtp, HttpSession session);
	ModelAndView resendOtp(HttpSession session);
	ModelAndView login(String email, String password, HttpSession session);
	ModelAndView forgotPassword();
	ModelAndView forgotSendOtp(String email, HttpSession session);
	ModelAndView forgotPasswordVerify(int userOtp, HttpSession session);
	ModelAndView resetPassword(String password, HttpSession session);
	ModelAndView forgotResendOtp(HttpSession session);

}
