package com.cristianvera.springboot.login.models.service;

import com.cristianvera.springboot.login.models.dao.IUsuarioDao;
import com.cristianvera.springboot.login.models.entity.Rol;
import com.cristianvera.springboot.login.models.entity.SuperUsuario;
import com.cristianvera.springboot.login.models.entity.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {

    private final IUsuarioDao usuarioDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public JpaUserDetailsService(IUsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);
        if(usuario == null) {
            logger.error("Error login el usuario y/o contraseña son incorrectos");
            throw  new UsernameNotFoundException("Usuario no existe");
        }

        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (Rol rol: usuario.getRoles()) {
            authorityList.add(new SimpleGrantedAuthority(rol.getRol()));
        }
        return new SuperUsuario(username, usuario.getPassword(), usuario.getEnabled(), true, true, true, authorityList, usuario.getNombre(), usuario.getApellidos(), usuario.getFechaCreacion());
    }
}
