package auctionsys;

import java.util.Scanner;

public class User {
    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role){
        if(username.isBlank()||password.isBlank()){
            throw new IllegalArgumentException("Neither the username or the password can be blank");
        }
        if(!role.equalsIgnoreCase("user")&&!role.equalsIgnoreCase("admin")){
            throw new IllegalArgumentException("The user's role cannot be "+role+", it has to be either user or admin");
        }
        this.password=password;
        this.role=role;
        this.username=username;
    }

    //Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        if(username.isBlank()){
            throw new IllegalArgumentException("The username cannot be blank");
        }
        this.username=username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws IllegalAccessException {
        System.out.println("Accessing a secured method, password required: ");
        Scanner scanner = new Scanner(System.in);
        String enteredPass = scanner.next();
        if(enteredPass.equals(this.password)){
            System.out.println("Correct password, password has been updated.");
            this.password = password;
        }else{
            throw new IllegalAccessException("Incorrect password");
        }
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if(!role.equalsIgnoreCase("user")&&!role.equalsIgnoreCase("admin")){
            throw new IllegalArgumentException("The user's role cannot be "+role+", it has to be either user or admin");
        }
        this.role = role;
    }

    @Override
    public String toString() {
        return username+","+password+","+role;
    }
}