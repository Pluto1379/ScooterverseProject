import java.sql.Date;
import java.util.Arrays;
import java.util.Scanner;

public class ScooterRental {
    public static void main(String[] args){
        sqlFunctions sql = new sqlFunctions();
        Scanner in = new Scanner(System.in);
        Customer customer = null;
        String output = "";
        while (!output.equals("Exit")){
            if (customer != null){

            } else {
               System.out.print("Please Enter Your Command: ");
               output = in.nextLine();
               String[] parseOutput = output.split(" ");
               String command = parseOutput[0];
               
               if (command.equals("login")){
                   if(parseOutput.length != 3){
                       System.out.println("The Information Is Not Sufficient.");
                       continue;
                   }
                   String username = parseOutput[1];
                   String password = parseOutput[2];
                   sql.login(username, password);
                }
               
               if (command.equals("addScooter")){
                   if (parseOutput.length != 4) {
                       System.out.println("The Information Is Not Sufficient.");
                       continue;
                   }
                   String scooterType = parseOutput[1];
                   String brand = parseOutput[2];
                   String model = parseOutput[3];
                   sql.addScooter(scooterType,brand, model);
                   System.out.println(Arrays.toString(parseOutput));
               }

               if (command.equals("deleteScooter")){
                   if(parseOutput.length != 2){
                       System.out.println("The Information Is Not Sufficient.");
                       continue;
                   }
                   String scooterIDString = parseOutput[1];
                   int scooterID = Integer.parseInt(scooterIDString);
                   sql.deleteScooter(scooterID);
                   System.out.println(Arrays.toString(parseOutput));
               }

               if (command.equals("updateFee")){
                   if(parseOutput.length != 3){
                       System.out.println("The Information Is Not Sufficient.");
                       continue;
                   }
                   String scooterType = parseOutput[1];
                   String feeString = parseOutput[2];
                   int fee = Integer.parseInt(feeString);
                   sql.updateFee(scooterType, fee);
                   System.out.println(Arrays.toString(parseOutput));
                }

                if (command.equals("deleteCustomer")){
                    if(parseOutput.length != 2){
                        System.out.println("The Information Is Not Sufficient.");
                        continue;
                    }
                    String username = parseOutput[1];
                    sql.deleteCustomer(username);
                    System.out.println(Arrays.toString(parseOutput));
                }

                if (command.equals("returnScooter")){
                    if(parseOutput.length != 3){
                        System.out.println("The Information Is Not Sufficient.");
                        continue;
                    }
                    String username = parseOutput[1];
                    String scooterID = parseOutput[2];
                    int id = Integer.parseInt(scooterID);
                    sql.returnScooter(username, id);
                    System.out.println(Arrays.toString(parseOutput));
                }

                if (command.equals("getFee")){
                    if(parseOutput.length != 2){
                        System.out.println("The Information Is Not Sufficient.");
                        continue;
                    }
                    String scooterType = parseOutput[1];
                    sql.getFee(scooterType);
                    System.out.println(Arrays.toString(parseOutput));
                }

                if (command.equals("rentScooter")){
                    if(parseOutput.length != 3){
                        System.out.println("The Information Is Not Sufficient.");
                        continue;
                    }
                    String username = parseOutput[1];
                    String scooterId = parseOutput[2];
                    int id = Integer.parseInt(scooterId);
                    System.out.print("How Long Would You Like To Rent The Scooter? ");
                    int time = in.nextInt();
                    in.nextLine();
                    sql.rentScooter(username, id, time);
                    System.out.println(Arrays.toString(parseOutput));
                }
              
                if (command.equals("createAccount")) {
					if (parseOutput.length != 7) {
						System.out.println("The Information Is Not Sufficient.");
						continue;
					}
					String fn = parseOutput[1];
					String ln = parseOutput[2];
					String ln = parseOutput[3];
					String username = parseOutput[4];
					String password = parseOutput[5];
					String admin = parseOutput[6];
					sql.createAccount(fn, ln, ln, username, password, Boolean.valueOf(admin));
				}
                if (command.equals("getMostExpensiveScooter")) {
					if (parseOutput.length != 1) {
						System.out.println("The Information Is Not Sufficient.");
						continue;
					}
					sql.getMostExpensiveScooter();
				}
                if (command.equals("getScooterRentedFromInventory")) {
					if (parseOutput.length != 2) {
						System.out.println("The Information Is Not Sufficient.");
						continue;
					}
					String inventoryId = parseOutput[1];
					sql.getScooterRentedFromInventory(inventoryId);
				}
                if (command.equals("getCustomerDataAsIns")) {
					if (parseOutput.length != 2) {
						System.out.println("The Information Is Not Sufficient.");
						continue;
					}
					String username = parseOutput[1];
					sql.getCustomerDataAsIns(username);
				}
                if (command.equals("insertScooter")) {
					if (parseOutput.length != 3) {
						System.out.println("The Information Is Not Sufficient.");
						continue;
					}
					String scooterType = parseOutput[1];
					String fee = parseOutput[2];
					sql.insertScooter(scooterType, Integer.valueOf(fee));
				}             

                if (command.equals("getReturnInfo")){
                    if(parseOutput.length != 2){
                        System.out.println("The Information Is Not Sufficient.");
                        continue;
                    }
                    String scooterID = parseOutput[1];
                    int sID = Integer.parseInt(scooterID);
                    sql.getReturnInfo(sID);
                    System.out.println(Arrays.toString(parseOutput));
                }

                if (command.equals("changeReturnDate")){
                    if(parseOutput.length != 3){
                        System.out.println("The Information Is Not Sufficient.");
                        continue;
                    }
                    String username = parseOutput[1];
                    String scooterId = parseOutput[2];
                    int id = Integer.parseInt(scooterId);
                    System.out.print("How Long Would You Like To Extend? ");
                    int time = in.nextInt();
                    in.nextLine();
                    sql.changeReturnDate(username, id, time);
                    System.out.println(Arrays.toString(parseOutput));
                }

                if (command.equals("getScooters")){
                    if (parseOutput.length != 2) {
                        System.out.println("The Information Is Not Sufficient.");
                        continue;
                    }
                    String inventoryID = parseOutput[1];
                    sql.getScooters(inventoryID);
                    System.out.println(Arrays.toString(parseOutput));
                }

                if (command.equals("addInventory")){
                    if (parseOutput.length != 3) {
                        System.out.println("The Information Is Not Sufficient.");
                        continue;
                    }
                    String inventoryID = parseOutput[1];
                    String scooterID = parseOutput[2];
                    int id = Integer.parseInt(scooterID);
                    sql.addInventory(inventoryID, id);
                    System.out.println(Arrays.toString(parseOutput));
                }
                if (command.equals("log")){
                    if (parseOutput.length != 1) {
                        System.out.println("The Information Is Not Sufficient.");
                        continue;
                    }
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Enter The Date: ");
                    String date = sc.nextLine();
                    sql.log(date);
                }
            }
        }
    }
}
