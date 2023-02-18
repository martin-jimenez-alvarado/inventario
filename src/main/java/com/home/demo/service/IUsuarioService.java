package com.home.demo.service;

import java.util.List;
import com.home.demo.entity.Usuario;

public interface IUsuarioService {
	public List<Usuario> getAll();
	public Usuario findByID(Integer id);
	public boolean eliminar(Integer id);
	public Usuario createUpdate(Usuario user);

}
