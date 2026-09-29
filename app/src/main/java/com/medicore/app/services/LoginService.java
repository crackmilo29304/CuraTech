package com.medicore.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicore.app.models.User;
import com.medicore.app.repository.UserRepository;
import com.medicore.app.utils.UserSession;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;
    
    public String checkLogin(String documentNumber, String password) {
        User user = userRepository.findByDocumentNumberAndPassword(documentNumber, password);
        if(user == null) {
            //implement exception
            System.out.println("Usuario no encontrado o contraseña incorrecta");
            return null;
        }
        if(user.getRole().equals(UserSession.getRole())){
            return UserSession.getRole();
        }
        else {
            //implement exception
            System.out.println("El rol seleccionado no coincide con el rol del usuario");
            return null;
        }

    }

}
