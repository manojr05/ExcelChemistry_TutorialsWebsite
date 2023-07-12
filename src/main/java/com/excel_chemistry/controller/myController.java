package com.excel_chemistry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.excel_chemistry.model.User;
import com.excel_chemistry.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class myController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String indexView() {
		return "index";
	}

	@GetMapping("/register")
	public String registerUser() {
		return "register";
	}

	@GetMapping("/login")
	public String loginUser() {
		return "login";
	}

	@PostMapping("/registerUser")
	public ModelAndView registerUser(@ModelAttribute User user, @RequestParam String date, HttpSession session) {
		return userService.registerUser(user, date, session);
	}

	@PostMapping("/verifyEmail")
	public ModelAndView verifyEmail(@RequestParam int userOtp, HttpSession session) {
		return userService.verifyEmail(userOtp, session);
	}
	
	@GetMapping("/resendOtp")
	public ModelAndView resendOtp(HttpSession session) {
		return userService.resendOtp(session);
	}

	@PostMapping("/login")
	public ModelAndView loginUser(@RequestParam String email, @RequestParam String password, HttpSession session) {
		return userService.login(email, password, session);
	}
	
	@GetMapping("/forgotPassword")
	public ModelAndView forgotPassword() {
		return userService.forgotPassword();
	}
	
	@PostMapping("/forgotSendOtp")
	public ModelAndView forgotSendOtp(@RequestParam String email, HttpSession session) {
		return userService.forgotSendOtp(email, session);
	}
	
	@PostMapping("/forgotOtpVerify")
	public ModelAndView forgotOtpVerify(@RequestParam int userOtp, HttpSession session) {
		return userService.forgotPasswordVerify(userOtp, session);
	}
	
	@PostMapping("/resetPassword")
	public ModelAndView resetPassword(@RequestParam String password, HttpSession session) {
		return userService.resetPassword(password, session);
	}
	
	@GetMapping("/forgotResendOtp")
	public ModelAndView forgotOtpResend(HttpSession session) {
		return userService.forgotResendOtp(session);
	}
}
