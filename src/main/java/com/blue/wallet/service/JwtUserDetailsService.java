package com.blue.wallet.service;

import com.blue.wallet.domain.UsuarioORM;
import com.blue.wallet.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UsuarioORM> user = userDao.findByEmail(username);

		if (!user.isPresent()) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getSenha(),
				new ArrayList<>());
	}

	public Integer getIdUsuarioByEmail(String email) {
		Optional<UsuarioORM> user = userDao.findByEmail(email);

		return user.get().getId();
	}
}