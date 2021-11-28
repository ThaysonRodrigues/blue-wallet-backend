package com.blue.wallet.controller;

import com.blue.wallet.controller.uri.JwtAuthenticationURI;
import com.blue.wallet.security.JwtTokenUtil;
import com.blue.wallet.security.dto.JwtRequest;
import com.blue.wallet.security.dto.JwtResponse;
import com.blue.wallet.service.JwtUserDetailsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"https://blue-wallet-app.herokuapp.com:59158"})
@RequestMapping(JwtAuthenticationURI.CONTROLLER)
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@PostMapping
	@ApiOperation(value = "EndPoint para autenticar usuário")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getEmail(), authenticationRequest.getSenha());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

		final Integer idUsuario = userDetailsService.getIdUsuarioByEmail(authenticationRequest.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails, idUsuario);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}