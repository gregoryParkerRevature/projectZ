package com.github.gregoryParkerRevature;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

//import jdk.internal.org.jline.reader.UserInterruptException;

public class Accounts{

    private String accountName;
    private Double balance;
    private Map<String, Double > accounts = new HashMap<String, Double>();


    public void accounts(){

    }

    public void accounts(String accountName, Double balance){

        accounts.put(accountName, balance);
    }

    public Map<String, Double> getAccounts(){
        return accounts;
    }

    public String getAccountName(String accountName){
        if(accounts.containsKey(accountName))
            return accountName;
        else   
            return "No Account";
    }

    public Double getAccountBalance(String accountName){
        return accounts.get(accountName);
    }

    public void loadUserData(Scanner scan){

        App app = new App();
        System.out.println("\n\nYou have the following accouts: \n");
        app.sleep(1000);

        while(scan.hasNextLine()){

            String line = scan.nextLine();
            System.out.println(line);
            Scanner lineScanner = new Scanner(line);

            while(lineScanner.hasNext()){
                accountName = lineScanner.next();
                balance = lineScanner.nextDouble();
                app.sleep(2000);
                accounts.put(accountName, balance);
            }
        }
        //accounts(accountName, balance);

        
    }

    public void writeData(String filename, Scanner scan) throws IOException{

        App app = new App();
        FileWriter file = null;

        try{
            file = new FileWriter(filename, false);
            System.out.println("\n-->Saving");
            for(String name: accounts.keySet()){
                String key = name.toString();
                Double value = accounts.get(name);
                file.write(key + " " + value + "\n");
            }
            
        }catch (IOException e){
            System.out.println("no file name");
            app.sleep(3000);

        }finally{

            file.flush();
            app.sleep(3000);
            app.userInterface(scan);
        }

    }

    public void format(){
        Double netWorth = 0.0;
        for(String name: accounts.keySet()){
            String key = name.toString();
            Double value = accounts.get(name);
            netWorth+=value;
            System.out.printf("%s:  $%.2f\n", key, value);
        }
        System.out.printf("\nTotal Capital: %.2f", netWorth);
    }

    public Double getNetWorth(){
        Double netWorth = 0.0;
        for(String name: accounts.keySet()){
            netWorth += accounts.get(name);
        }
        return netWorth;
    }

    public void removeAccount(Scanner scan){
        
            App app = new App();
            app.clearScreen();

          if(accounts.size()>=1){  

            //list current account names
            for(String name: accounts.keySet()){
                String key = name.toString();
                System.out.println(key);
            }

            //user input for acct removal
            System.out.print("\nEnter name to remove account: ");
            String acctRemove = scan.next();

            if(acctRemove.equals("cancel")){
                app.userInterface(scan);

            }else if(accounts.containsKey(acctRemove)){

                accounts.remove(acctRemove);
                System.out.println("\n--->Removing Account ");

            }else{
                
                app.errorInput();
                removeAccount(scan);
            }


        }else{
            System.out.println("You currently have no accounts");
            app.sleep(2000);
            app.userInterface(scan);
        }

     }//end remove account

     public boolean containsAcct(String key){
         return accounts.containsKey(key);
     }
       


}//end class
