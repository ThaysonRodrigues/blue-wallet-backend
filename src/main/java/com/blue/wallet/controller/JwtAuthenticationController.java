package com.blue.wallet.controller;

import com.blue.wallet.controller.uri.JwtAuthenticationURI;
import com.blue.wallet.security.dto.JwtRequest;
import com.blue.wallet.security.dto.JwtResponse;
import com.blue.wallet.service.JwtAuthenticationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(JwtAuthenticationURI.CONTROLLER)
public class JwtAuthenticationController {

	@Autowired
	private JwtAuthenticationService service;

	@PostMapping
	@ApiOperation(value = "EndPoint para autenticar usuário")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		JwtResponse token = service.autentication(authenticationRequest);

		return ResponseEntity.ok(token);
	}
}