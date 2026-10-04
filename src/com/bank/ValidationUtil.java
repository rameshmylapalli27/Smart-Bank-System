package com.bank;

public class ValidationUtil {
    public boolean isValidName(String name) {
        String regexPattern = "^[A-Za-z]+$";
        return name != null && name.matches(regexPattern);
    }

    public boolean isValidEmail(String email) {
        String regexPattern = "^[A-Za-z0-9_.%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email != null && email.matches(regexPattern);
    }
    
    public boolean isValidCardNo(String cardNo) {
        String regexPattern = "^[0-9]{16}$";
        return cardNo != null && cardNo.matches(regexPattern);
    }
    
    public boolean isValidPin(String pin) {
        String regexPattern = "^[0-9]{4}$";
        return pin != null && pin.matches(regexPattern);
    }

    public boolean isValidPassword(String password) {
        String regexPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[@!#$%^&*()_+])(?=.*[0-9]).{8,}$";
        return password != null && password.matches(regexPattern);
    }

    public boolean isValidPhoneNo(String contact) {
        String regexPattern = "^[0-9]{10}$";
        return contact != null && contact.matches(regexPattern);
    }

    public boolean isValidDOB(String DOB) {
        String regexPattern = "^[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}$";
        return DOB != null && DOB.matches(regexPattern);
    }

    public boolean isEmptyOrBlank(String object){
        return !object.isBlank() && !object.trim().isEmpty();
    }
}