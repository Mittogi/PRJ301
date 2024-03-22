/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phat.registration;

import java.io.Serializable;

/**
 *
 * @author vopha
 */
public class RegistrationCreateErrors implements Serializable{
    protected String usernameLengthError;
    protected String passwordLengthError;
    protected String fullNameLengthError;
    protected String confirmNotMatch;
    protected String usernameIsExisted;

    public RegistrationCreateErrors() {
    }

    public RegistrationCreateErrors(String usernameLengthError, String passwordLengthError, String fullNameLengthError, String confirmNotMatch, String usernameIsExisted) {
        this.usernameLengthError = usernameLengthError;
        this.passwordLengthError = passwordLengthError;
        this.fullNameLengthError = fullNameLengthError;
        this.confirmNotMatch = confirmNotMatch;
        this.usernameIsExisted = usernameIsExisted;
    }

    public String getConfirmNotMatch() {
        return confirmNotMatch;
    }

    public String getFullNameLengthError() {
        return fullNameLengthError;
    }

    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    public void setConfirmNotMatch(String confirmNotMatch) {
        this.confirmNotMatch = confirmNotMatch;
    }

    public void setFullNameLengthError(String fullNameLengthError) {
        this.fullNameLengthError = fullNameLengthError;
    }

    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }

    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }
    
    
}

