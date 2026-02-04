package com.upb.agripos.service;

import com.upb.agripos.model.User;
import com.upb.agripos.exception.AgriException;

public class AuthService {
    private User currentUser;

    public void login(String username, String password) throws AgriException {
        // Simulasi pengecekan kredensial (Bisa dikembangkan dengan UserDAO)
        if (username.equals("admin") && password.equals("123")) {
            currentUser = new User(username, "ADMIN");
        } else if (username.equals("kasir") && password.equals("123")) {
            currentUser = new User(username, "KASIR");
        } else {
            throw new AgriException("Username atau password salah!"); // Custom Exception
        }
    }

    public User getCurrentUser() { return currentUser; }
    
    public void logout() { currentUser = null; }
}