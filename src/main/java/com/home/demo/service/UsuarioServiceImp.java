package com.home.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.home.demo.entity.Usuario;
import com.home.demo.repository.IUsuarioRepositorio;


@Service
public class UsuarioServiceImp implements IUsuarioService{
	private IUsuarioRepositorio repoUser;
	
	private UsuarioServiceImp(IUsuarioRepositorio repoUser) {
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
		if(user != null) {
			repoUser.deleteById(id);
			return true;
		}
		else
			return false;
	}

	@Override
	public Usuario createUpdate(Usuario user) {
		if(user.getId() != null) {
			Usuario u = findByID(user.getId());
			u.setEmail(user.getEmail());
			u.setPassword(user.getPassword());
			u.setRoles(user.getRoles());
			return repoUser.save(u);
		}else {
			return repoUser.save(user);
		}
	}

}
