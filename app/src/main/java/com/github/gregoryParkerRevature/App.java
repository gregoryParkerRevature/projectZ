/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.github.gregoryParkerRevature;

import java.io.BufferedReader;
import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.io.File;

public class App {
    
    public String username;

    public static void main(String[] args) {
    
        //load users arraylist before everything 
        App app = new App();
        app.diagnostic();
        
    }


    public void diagnostic(){

        
        clearScreen();
        Scanner scan = new Scanner(System.in);
        
        System.out.print("1: New User\n2: Load User\nInput: ");

        
        int intResponse = scan.nextInt();

       
        //new user or load user
        if(intResponse == 1){
           newUser(scan);
        }
        else if(intResponse == 2 ){
            load(scan);
        }else{
            errorInput();
            diagnostic();
        }

        //return from newUser method
        userInterface(scan);

        scan.close();
        
    }//end diagnostic

    public void userInterface(Scanner scan){

        clearScreen();
        User user = new User();
        Accounts account = user.getAccountRef(user.getUserName());
        System.out.printf("User: %s       Total Capital: %.2f\n", user.getUserName(), account.getNetWorth());
        System.out.println("1: List Accounts \n2: Add Account \n3: Remove Account \n4: Save \n5: Exit");
        System.out.print("Input: ");
        int response = scan.nextInt();

        switch(response){

            case 1: 
                clearScreen();
                user.getAccountRef(user.getUserName()).format();
                System.out.print("\n\nEnter 1 to return: ");
                scan.next();
                userInterface(scan);
                break;

            case 2:
                user.addAccount(scan);
                sleep(2000);
                userInterface(scan);
                break;

            case 3: 
                account.removeAccount(scan);
                sleep(2000);
                userInterface(scan);
                break;

            case 4:

                try{
                    account.writeData(user.getFilePath(), scan);
                }catch (IOException e){
                    System.out.println();
                }

            case 5: 
                System.exit(0);
                break;

            default: 
                errorInput();
                userInterface(scan);

        }
        

    }

    public void newUser(Scanner scan){

        clearScreen();

        System.out.print("Hello! Please enter new username: ");
        String userInput = scan.next();

        //add line to check current users from file/database no overlap!!!
        
        Accounts account = new Accounts();
        User newUser = new User();
        newUser.user(userInput, account);

        System.out.printf("\nWelcome %s!\n", newUser.getUserName());
        sleep(2000);
 }

    public void load(Scanner scan){

        clearScreen();
        System.out.print("Enter account name: ");
        String filePath = scan.next();
        String updatePath = "/Users/gregparker/Desktop/userData/" + filePath + ".txt";

            File file = new File(updatePath);

            try{
                Scanner scanFile = new Scanner(file);
                User newUser = new User();
                Accounts account = new Accounts();
                String fileName = file.getName().substring(0, file.getName().lastIndexOf('.'));
                System.out.printf("\nWelcome back, %s!", fileName);
                sleep(1000);
                newUser.user(fileName, account);
                newUser.getAccountRef(fileName).loadUserData(scanFile);

            }catch (IOException e){
                System.out.println("\n-->Account not found. Try another name");
                sleep(2000);
                load(scan);
            }
        
    }

   
    public void clearScreen() {  

        System.out.print("\033[H\033[2J");  

        System.out.flush();  

    }

    public void sleep(int num){
        try{
            Thread.sleep(num);
        }
        catch(InterruptedException e){

        }
    }

    public void errorInput(){
        System.out.println("Invalid Entry... Reloading"); 
        sleep(2000);
    }



}//end Class