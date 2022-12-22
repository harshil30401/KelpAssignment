package KelpAssignment;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//This will provide the cli. 
public class OptionMenu extends Logic {


    //For receiving the user input.
    Scanner userInput = new Scanner(System.in);


    //This Map Interface stores all the user data until the while loop is running.
    //As a result, all the data will be lost if one interrupts or quits the terminal.
    //In practical scenarios, using a database to store the user data would be preferred.

    Map<String, ArrayList<String>> data = new HashMap<String, ArrayList<String>>();


    // This function validates the command entered by the user.
    public static boolean validateCommand(String input) {

        input = input.toUpperCase();

        String[] words = input.split("\\s+");
        if (words.length == 3) {
            if (words[0].equals("CREATE")) {
                try {
                    Integer.parseInt(words[2]);
                    return false;
                } catch (NumberFormatException e) {
                    return true;
                }
            } else if (words[0].equals("DEPOSIT")) {

                try {
                    Integer.parseInt(words[2]);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            } else if (words[0].equals("WITHDRAW")) {

                try {
                    Integer.parseInt(words[2]);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }

            }
        } else if (words.length == 2) {
            if (words[0].equals("BALANCE")) {
                String pattern = "^ACC\\d{3}$";
                Pattern p = Pattern.compile(pattern);
                Matcher m = p.matcher(words[1]);
                if (m.matches()) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }



    public void getCommand() {
        System.out.println("You may start writing the commands: ");



        //This loop accepts all the commands that you would write in the terminal.
        while (true) {

            String userCommand = userInput.nextLine();

            
            //Convert the user input into uppercase.
            userCommand = userCommand.toUpperCase();
            String[] userCommandWords = userCommand.split("\\s+");


            // Once the command is validated, the respective command is executed. If not, it prompts the user to re-check the command.
            if (validateCommand(userCommand)) {

                if (userCommand.contains("CREATE")) {

                    CREATE(userCommandWords[1], userCommandWords[2]);

                } else if (userCommand.contains("WITHDRAW")) {

                    WITHDRAW(userCommandWords[1], Integer.parseInt(userCommandWords[2]));

                } else if (userCommand.contains("DEPOSIT")) {

                    DEPOSIT(userCommandWords[1], Integer.parseInt(userCommandWords[2]));

                } else {

                    BALANCE(userCommandWords[1]);
                }

            } else {
                System.out.println("Please enter a valid command format!");
            }
        }

    }



    // Requirement #1: (CREATE ACCOUNT)
        //This function creates a new user with account balance = 0.
            //Corner cases:
                //The user cannot have multiple accounts with the same code (Eg. CREATE ACC001 KELP, CREATE ACC001 FVTPL).

    private void CREATE(String code, String name) {

        if (!data.containsKey(code)) {

            ArrayList<String> values = new ArrayList<String>();

            values.add(name);
            values.add("0");

            data.put(code, values);


            //In case you want to view the changes in the 'data' variable, un-comment the line below. 
            // System.out.println(data.toString());

        } else {
            System.out.println("This Account already exists!");
        }

    }



    //Requirement #2: (DEPOSIT AMOUNT)
        //This function adds an amount to the existing balance.
            //Corner cases:
                //User cannot input an amount less than or equal to 0
                //The user cannot deposit more than 100000 for ensuring that the code is easy to understand.
                //The user cannot enter a negative amount.


    public void DEPOSIT(String code, Integer amount) {

        if (data.containsKey(code)) {

            if (!(amount <= 0)) {
                Integer currentAmountD = 0;

                List<String> retrievedValues = data.get(code);
                String x = retrievedValues.get(1);
    
                currentAmountD = Integer.parseInt(x);
    
                Integer result = calcDepositAmount(currentAmountD, amount);
    
                if (result > 1000000) {
                    System.out.println("Account limit is only upto 10 Lakh");
                }else if (result != -1) {
                    retrievedValues.set(1, String.valueOf(result));
                }else{
                    System.out.println("Please enter a valid amount!");
                }
    
                //In case you want to view the changes in the 'data' variable, un-comment the line below. 
                // System.out.println(data.toString());    
            }else{
                System.out.println("Please enter a valid amount!");
            }

        } else {
            System.out.println("This Account does not exist!");
        }

    }



    //Requirement #3: (WITHDRAW)
        // This function reduces an amount from existing balance.
            //Corner Cases:
                //User cannot input an amount less than or equal to 0
                //The user cannot withdraw an amount if the existing amount turns out to be less than 0.
                //The user cannot modify an account that he has not created in the runtime.

    private void WITHDRAW(String code, Integer amount) {

        if (data.containsKey(code)) {

            if (!(amount <= 0)) {
                Integer currentAmountW = 0;

                List<String> retrievedValues = data.get(code);
                String x = retrievedValues.get(1);

                currentAmountW = Integer.parseInt(x);

                Integer result = calcWithdrawAmount(currentAmountW, amount);

                if (result != -1) {
                    retrievedValues.set(1, String.valueOf(result));
                } else {
                    System.out.println("Your balance is too low for Withdrawing!");
                }

                //In case you want to view the changes in the 'data' variable, un-comment the line below. 
                // System.out.println(data.toString());
            }else{
                System.out.println("Please enter a valid amount!");
            }
        } else {
            System.out.println("This Account does not exist!");
        }
    }

    
    // And Lastly,

    //Requirement #4: (BALANCE)
        //This function shows the balance of a respective account.
            //Corner Cases:
                //The user cannot access an account that does not exist.


    private void BALANCE(String code) {

        if ( data.containsKey(code) ){
            List<String> retrievedValues = data.get(code);
            System.out.println();
            System.out.println(retrievedValues.get(0) + " " + retrievedValues.get(1));
            System.out.println();
        }else{
            System.out.println("This Account does not exist!");
        }

        //In case you want to view the changes in the 'data' variable, un-comment the line below. 
        // System.out.println(data.toString());
    }

}
