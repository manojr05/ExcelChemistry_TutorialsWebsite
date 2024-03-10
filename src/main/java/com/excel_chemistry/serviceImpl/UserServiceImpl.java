package com.excel_chemistry.serviceImpl;

import java.sql.Date;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.MergedAnnotation.Adapt;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.excel_chemistry.helper.EmailSender;
import com.excel_chemistry.model.User;
import com.excel_chemistry.repository.UserRepository;
import com.excel_chemistry.service.UserService;
import jakarta.servlet.http.HttpSession;

@Service
@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailSender emailSender;

	@Override
	public ModelAndView registerUser(User user, String dateOfBirth, HttpSession session) {
		ModelAndView andView = new ModelAndView("otpVerification");

		Date dob = Date.valueOf(dateOfBirth);
		user.setDob(dob);

		int otp = new Random().nextInt(100000, 999999);

		session.setAttribute("userObject", user);
		session.setAttribute("otp", otp);

		emailSender.sendOtp(otp, user);

		andView.addObject("alert", "Success: Otp sent to " + user.getEmail());

		return andView;
	}

	@Override
	public ModelAndView verifyEmail(int userOtp, HttpSession session) {
		ModelAndView andView = new ModelAndView();

		if (session.getAttribute("userObject") == null) {
			andView.addObject("alert", "Error: Session Expired");
			andView.setViewName("register");
			return andView;
		}

		User user = (User) session.getAttribute("userObject");
		int otp = (int) session.getAttribute("otp");

		if (otp == userOtp) {
			userRepository.save(user);
			session.removeAttribute("userObject");
			session.removeAttribute("otp");
			andView.addObject("alert", "Success: Verified Successfully");
			andView.setViewName("login");
		} else {
			andView.addObject("alert", "Error: Otp Mismatch");
			andView.setViewName("otpVerification");
		}

		return andView;
	}

	@Override
	public ModelAndView resendOtp(HttpSession session) {
		ModelAndView andView = new ModelAndView("otpVerification");

		if (session.getAttribute("userObject") == null) {
			andView.addObject("alert", "Error: Session Expired");
			andView.setViewName("register");
			return andView;
		}

		User user = (User) session.getAttribute("userObject");
		int newOtp = new Random().nextInt(100000, 999999);

		session.removeAttribute("otp");

		emailSender.sendOtp(newOtp, user);
		session.setAttribute("otp", newOtp);
		andView.addObject("alert", "Success: OTP Resent");

		return andView;
	}

	@Override
	public ModelAndView login(String email, String password, HttpSession session) {
		ModelAndView andView = new ModelAndView();

		User user = userRepository.findByEmail(email);

		if (user == null) {
			andView.addObject("alert", "Invalid Email Address");
			andView.setViewName("login");
			return andView;
		} else {
			if (user.getPassword().equals(password)) {
				session.setAttribute("user", user);
				andView.addObject("alert", "Success: Login Successful");
				andView.setViewName("index");
				return andView;
			} else {
				andView.addObject("alert", "Invalid Password");
				andView.setViewName("login");
			}
		}

		return andView;
	}

	@Override
	public ModelAndView forgotPassword() {
		return new ModelAndView("forgotPassword");
	}

	@Override
	public ModelAndView forgotSendOtp(String email, HttpSession session) {
		ModelAndView andView = new ModelAndView("forgotOtpVerification");
		User user = userRepository.findByEmail(email);

		if (user == null) {
			andView.addObject("alert", "Invalid: No records found");
			andView.setViewName("forgotPassword");
			return andView;
		} else {
			int newOtp = new Random().nextInt(100000, 999999);
			emailSender.sendOtp(newOtp, user);
			session.setAttribute("otp", newOtp);
			session.setAttribute("userObject", user);
			andView.addObject("alert", "Success: OTP sent");
		}

		return andView;
	}

	@Override
	public ModelAndView forgotPasswordVerify(int userOtp, HttpSession session) {
		ModelAndView andView = new ModelAndView();

		if (session.getAttribute("userObject") == null) {
			andView.addObject("alert", "Error: Session Expired");
			andView.setViewName("login");
			return andView;
		}

		int otp = (int) session.getAttribute("otp");

		if (otp == userOtp) {
			session.removeAttribute("otp");
			andView.addObject("alert", "Success: Verified Successfully");
			andView.setViewName("resetPassword");
		} else {
			andView.addObject("alert", "Error: Otp Mismatch");
			andView.setViewName("forgotOtpVerification");
		}
		return andView;
	}

	@Override
	public ModelAndView resetPassword(String password, HttpSession session) {
		ModelAndView andView=new ModelAndView("login");
		User user = (User) session.getAttribute("userObject");
		user.setPassword(password);
		userRepository.save(user);
		andView.addObject("alert", "Password Changed Successfully");
		session.removeAttribute("userObject");
		return andView;
	}

	@Override
	public ModelAndView forgotResendOtp(HttpSession session) {
		ModelAndView andView = new ModelAndView("forgotOtpVerification");

		if (session.getAttribute("userObject") == null) {
			andView.addObject("alert", "Error: Session Expired");
			andView.setViewName("login");
			return andView;
		}

		User user = (User) session.getAttribute("userObject");
		int newOtp = new Random().nextInt(100000, 999999);

		session.removeAttribute("otp");

		emailSender.sendOtp(newOtp, user);
		session.setAttribute("otp", newOtp);
		andView.addObject("alert", "Success: OTP Resent");

		return andView;
	}
}
