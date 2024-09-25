package com.smarfat.webapp.gym.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {
    private static PasswordUtils instance;
    
    private PasswordUtils(){
    }

    public static PasswordUtils getIntance() {
        if(instance == null){
            instance = new PasswordUtils();
        }
        return instance;
    }
    
    public String encrytedPassword(String pass){
        return BCrypt.hashpw(pass, BCrypt.gensalt());
    
    }
    
    public boolean checkPassword(String pass, String encryptedPass){
        return BCrypt.checkpw(pass, encryptedPass);
    }
}
