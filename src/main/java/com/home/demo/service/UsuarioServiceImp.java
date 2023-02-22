package com.home.demo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.home.demo.dto.UsuarioDTO;
import com.home.demo.entity.Rol;
import com.home.demo.entity.Usuario;
import com.home.demo.repository.IUsuarioRepositorio;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UsuarioServiceImp implements IUsuarioService {
	private IUsuarioRepositorio repoUser;

	public UsuarioServiceImp(IUsuarioRepositorio repoUser) {
		this.repoUser = repoUser;
	}

	@Override
	public List<Usuario> getAll() {
		return repoUser.findAll();
	}

	@Override
	public Usuario findByID(Integer id) {
		return repoUser.findById(id).orElse(null);
	}

	@Override
	public boolean eliminar(Integer id) {
		Usuario user = findByID(id);
		if (user != null) {
			repoUser.deleteById(id);
			return true;
		} else
			return false;
	}

	@Override
	public Usuario createUpdate(Usuario user) {
		if (user.getId() != null) {
			Usuario u = findByID(user.getId());
			u.setEmail(user.getEmail());
			u.setPassword(user.getPassword());
			u.setRoles(user.getRoles());
			return repoUser.save(u);
		} else {
			log.info("____" + user.toString());
			return repoUser.save(user);
		}
	}

	@Override
	public Usuario createUser(UsuarioDTO user) {
		Usuario newUser = null;
		Set<Rol> roles = null;

		if (user.getId() == null) {
			newUser = new Usuario();
			roles = new HashSet<>();
			if (repoUser.findByEmail(user.getEmail() != null ? user.getEmail() : "notfound") == null) {
				for (Rol rol : user.getRoles()) {
					roles.add(rol);
				}
				newUser.setEmail(user.getEmail());
				newUser.setPassword(user.getPassword());
				newUser.setRoles(roles);
				log.info("____" + newUser.toString());
				return repoUser.save(newUser);
			} else
				return null;
		} else
			return null;
	}

	@Override
	public Usuario updateUser(UsuarioDTO user) {
		Usuario updateUser = null;
		Set<Rol> roles = null;
		log.info("_roles__" + user.getRoles().toString());

		if (user.getId() != null) {
			updateUser = findByID(user.getId());
			if (updateUser != null) {
				roles = new HashSet<>();
				updateUser.setEmail(user.getEmail());
				updateUser.setPassword(user.getPassword());
				for (Rol rol : user.getRoles()) {
					roles.add(rol);
				}
				updateUser.setRoles(roles);
				log.info("___updateUser____" + updateUser.toString());
				return repoUser.save(updateUser);
			}else
				return null;
		} else
			return null;
	}

	@Override
	public Usuario findByEmail(final String email) {
		if (!email.isBlank())
			return repoUser.findByEmail(email);
		else
			return null;
	}
}
