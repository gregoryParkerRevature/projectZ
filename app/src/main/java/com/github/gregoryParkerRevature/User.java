package com.github.gregoryParkerRevature;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.*;

public class User{

    private static String username;
    private static Map<String, Accounts> users = new HashMap<String, Accounts>();
    

    public void user(){
        System.out.println("making a new user");
    }

    public void user(String userNameString, Accounts userName){
        //load users from txt file
        username = userNameString;
        users.put(userNameString, userName);        
    }

    public String getUserName(){
        return username;
    }

    public Accounts getAccountRef(String key){
        return users.get(key);
    }

    public void addAccount(Scanner scan){

        //define objects
        scan = new Scanner(System.in);
        Accounts account = new Accounts();
        account = users.get(username);
        App app = new App();

        //New account name from user input
        System.out.print("\nEnter new account name: ");
        String accountName = scan.next();

        //check if already exists, create new account
        if(account.containsAcct(accountName)==false){
            
            System.out.print("Enter account balance: ");
            try{
                Double balance = scan.nextDouble();
                users.get(username).accounts(accountName, balance);
                System.out.println("\n--->Adding Account");
            }catch (InputMismatchException e){
                app.errorInput();
                app.sleep(2000);
                addAccount(scan);
                
            }
        }else{
            System.out.println("\nAccount name already taken. \nTry another name\n");
            app.sleep(2000);
            addAccount(scan);
        }
    }//end add account

    

    public String getFilePath(){
        return "/Users/gregparker/Desktop/UserData/" + username + ".txt";
    }


}