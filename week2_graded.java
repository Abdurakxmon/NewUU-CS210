
import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        problem1();
        problem2();
        problem3();
        problem4();
        problem5();
        problem6();
        problem7();
        problem8();
        problem9();
        problem10();
    }

    public static void problem1(){
        System.out.print("Cappuccinos $4.5 each: ");
        int cap = input.nextInt();
        input.nextLine();
        System.out.print("Muffins $3.0 each: ");
        int muf = input.nextInt();
        input.nextLine();
        double total = (muf*3+cap*4.5) * 1.08 + 5;
        System.out.printf("Grand Total: $%.2f%n", total);
    }

    public static void problem2(){

        System.out.print("Enter Distance: ");
        int distance = input.nextInt();
        input.nextLine();
        double consumption = 8.5;
        double price = 12500;
        double fuelNeeded = ((double)distance/100)*consumption;
        System.out.printf("Fuel Needed: %.2f Liters%n", fuelNeeded);
        System.out.printf("Total Cost: %.2f UZS%n", fuelNeeded*price);

    }

    public static void problem3(){
        System.out.print("Enter Seconds: ");
        int num = input.nextInt();
        input.nextLine();

        int seconds = num%60;
        num/=60;
        int minutes = num%60;
        num/=60;
        System.out.printf("%d hours, %d minutes, %d seconds%n", num, minutes, seconds);
    }

    public static void problem4(){
        System.out.print("Enter Age: ");
        int age = input.nextInt();
        input.nextLine();
        int price = 10;
        if(age>=0 && age<=12) price=7;
        if (age>=18 && age<=64) price=15;
        System.out.printf("Ticket price: $%d %n", price);
    }

    public static void problem5(){
        System.out.print("Enter Year: ");
        int year = input.nextInt();
        input.nextLine();

        boolean flag=false;
        if((year%4==0 && year%100!=0) | (year%400==0)) flag=true;

        if(flag) System.out.println("Leap Year");
        else System.out.println("Not a Leap Year");
    }

    public static void problem6(){
        System.out.print("Enter Weight: ");
        double weight = input.nextDouble();
        input.nextLine();

        double baseCost = 5;
        if(weight>2) baseCost=10;
        double overLimit = Math.max(weight-10, 0);
        System.out.printf("Shipping Cost: $%.2f %n", baseCost+overLimit*2);
    }

    public static void problem7(){
        int actualPin = 1234;
        System.out.printf("Actual pin: %d%n", actualPin);
        for(int i=0;i<3;i++){
            System.out.print("Enter pin: ");
            int pin = input.nextInt();
            input.nextLine();
            if(pin==actualPin){
                System.out.println("Access Granted");
                return;
            }
            if(i!=2) System.out.println("Try again");
        }
        System.out.println("Account Locked");
    }

    public static void problem8(){
        System.out.print("Enter Population Number: ");
        int num = input.nextInt();
        int target = num*2;
        input.nextLine();
        int growth=0;
        while(growth<5){
            System.out.print("Enter Growth (min 5): ");
            growth = input.nextInt();
            input.nextLine();
        }
        int cnt=1;
        while(num<target){
            num+=num/100*growth;
            System.out.printf("Year %d: %d%n", cnt, num);
            cnt++;
        }
        System.out.printf("It will take %d years to double%n", cnt-1);

    }

    public static void problem9(){

        for(int i=1;i<=50;i++){
            if(i%3==i%5 && i%3==0) System.out.print("FizzBuzz");
            else if (i%3==0) System.out.print("Fizz");
            else if (i%5==0) System.out.print("Buzz");
            else System.out.print(i);
            if(i!=50) System.out.print(", ");
        }
        System.out.println();
    }

    public static void problem10(){
        double depositAmount = 1000;
        int interest=5;
        System.out.printf("Enter Years you want to keep your $%.2f deposit with %d%%: ",depositAmount,interest);
        int year = input.nextInt();
        input.nextLine();
        for(int i=1;i<=year;i++){
            depositAmount+=depositAmount/100*5;
            System.out.printf("Years %d: $%.2f %n",i,depositAmount);
        }
    }

}
