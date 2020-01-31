package com.ssafy.edu.springjwt.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.edu.service.IUserService;
import com.ssafy.edu.service.UserService;
import com.ssafy.edu.springjwt.model.ERole;
import com.ssafy.edu.springjwt.model.Role;
import com.ssafy.edu.model.User;
import com.ssafy.edu.springjwt.payload.request.LoginRequest;
import com.ssafy.edu.springjwt.payload.request.SignupRequest;
import com.ssafy.edu.springjwt.payload.response.JwtResponse;
import com.ssafy.edu.springjwt.payload.response.MessageResponse;
import com.ssafy.edu.springjwt.security.jwt.JwtUtils;
import com.ssafy.edu.springjwt.security.service.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	IUserService userService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		System.out.println(
				"-----------------------------------1" + loginRequest.getEmail() + "//" + loginRequest.getPwd());
		// db에 있는지 확인
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPwd()));

		// db에 없으면 try catch.....로
		System.out.println(authentication);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication); // 토큰을 만들어서 jwt에 넣는다.
		System.out.println("-----------------------------------3");
		// 지금 로그인된 정보를 userDetails에 넣는다.
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		System.out.println("-----------------------------------4");

		return ResponseEntity
				.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail()));
	}

/////////////////내꺼에 맞게 바꾸기
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		System.out.println("-----------------------------------1");
		if (userService.findUserByEmail(signUpRequest.getEmail()) != null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}
		System.out.println("-----------------------------------2");
		// Create new user's account
		User user = new User(0, signUpRequest.getEmail(), encoder.encode(signUpRequest.getPwd()), null,
				signUpRequest.getName(), 0, 1, signUpRequest.getPhone());

		System.out.println("-----------------------------------3");
		userService.regi(user);
		System.out.println("-----------------------------------4");
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}