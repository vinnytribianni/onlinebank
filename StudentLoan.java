import java.util.Scanner; 
import java.util.ArrayList;
import java.time.Month;

public class StudentLoan {
    public static void main(String[] args) {
        
        double loanAmount = 0.0;
        double loanTerm = 0.0;
        double interestRate = .06;
        double startMonth = 0.0;
        double startYear = 0.0;
        int startDay = 1;
        double balance = loanAmount;
        double paymentMonth = startMonth;
        double paymentYear = startYear;
        double totalInterest = 0.0;
        double monthlyPayment = 0.0;
        double payment = 0.0;
        double monthlyPrincipal = 0.0;
        double monthlyInterest = 0.0;
        boolean loop = true;
        boolean paymentcompleted = false;
        
        while (loop == true) {
            // Menu
            System.out.println("");
            System.out.println("-------------------Welcome to Vincent's Bank Student Loan Manager------------------");
            System.out.println("");
            Scanner menu = new Scanner(System.in);
            System.out.println("(1) Create a new student loan");
            System.out.println("(2) Open current student loan");
            System.out.println("(3) Exit program");
            int selection = menu.nextInt();
            if (selection == 3) {
                break;
            }
            else if (selection == 1) {  
                System.out.println("");
                System.out.println("-----New Student Loan Form-----");
                System.out.println("");
                Scanner loanInfo = new Scanner(System.in);
            
                // Form to collect loan data from user

                // Question 1: Loan Amount
                System.out.println("Enter loan amount ($): ");
                loanAmount = loanInfo.nextDouble();
                
                // Question 2: Loan Term
                System.out.println("Enter loan term (in months): ");
                loanTerm = loanInfo.nextInt();
                while (loanTerm > 361) {
                    System.out.println("Maximum loan term: 30 years/360 months. Try again.");
                    System.out.println("Enter loan term (in months)");
                    loanTerm = loanInfo.nextInt();
                }

                // Question 4: Start Month
                System.out.println("Enter start month number: ");
                startMonth = loanInfo.nextInt();
                while (startMonth > 12) {
                    System.out.println("Must enter a valid month number. Try again.");
                    System.out.println("Enter start month number: ");
                    startMonth = loanInfo.nextInt();
                }


                // Question 5: Start Year
                System.out.println("Enter start year: ");
                startYear = loanInfo.nextInt();
                
                System.out.println("");
                System.out.println("Student Loan Created. Go to open current loan to view amortization schedule. Exiting...");
            }
            else if (selection == 2) {
                      
                balance = loanAmount;
                paymentMonth = startMonth;
                paymentYear = startYear;
                totalInterest = 0.0;
                monthlyPayment = 0.0;

                // Schedule Labels
                System.out.println("");
                System.out.println("----------------------------------------Current Student Loan Schedule----------------------------------------");
                System.out.println("");
                ArrayList<String> scheduleHeaders = new ArrayList<String>();
                scheduleHeaders.add("Payment Month");
                scheduleHeaders.add("Payment Year");
                scheduleHeaders.add("Monthly Payment");
                scheduleHeaders.add("Monthly Principal");
                scheduleHeaders.add("Monthly Interest");
                scheduleHeaders.add("Total Interest");
                scheduleHeaders.add("Current Balance");
                System.out.println(scheduleHeaders);
        
                for(int i=1;i<=loanTerm;i++){  
                    
                    
                    // Calculating Payment Date
                    paymentMonth += 1;
                    if (paymentMonth > 12) {
                        paymentYear += 1;
                        paymentMonth = 1;
                    }
                    String PaymentMonth = String.format("%02.0f", paymentMonth)+"";
            
                    // Calculating Payment Year
                    String PaymentYear = String.format("%.0f", paymentYear)+"";
            
                    // Calculating Monthly Payment
                    monthlyPayment = loanAmount * ((interestRate/loanTerm)/(1-(Math.pow(1+(interestRate/12),(-1*loanTerm)))));
                    String MonthlyPayment = String.format("%.2f", monthlyPayment)+"";    
            
                    // Calculating Monthly Interest
                    monthlyInterest = (balance * interestRate) / 12;
                    String MonthlyInterest = String.format("%.2f", monthlyInterest)+"";
                
                    //Calculating Total Interest
                    totalInterest = totalInterest + monthlyInterest;
                    String TotalInterest = String.format("%.2f", totalInterest)+"";
            
                    // Calculating Monthly Principal
                    monthlyPrincipal = monthlyPayment - monthlyInterest;
                    String MonthlyPrincipal = String.format("%.2f", monthlyPrincipal)+"";
            
                    // Calculating Balance
                    balance = balance - monthlyPrincipal;
                    String Balance = String.format("%.2f", balance)+"";
                    
                    //Completed Payment Boolean

                    // Printing Schedule
                    ArrayList<String> scheduleData = new ArrayList<String>();
                    scheduleData.add("......"+ PaymentMonth + "......");
                    scheduleData.add("...." + PaymentYear + "....");
                    scheduleData.add("...$" + MonthlyPayment + "...");
                    scheduleData.add("....$" + MonthlyPrincipal + "....");
                    scheduleData.add("....$" + MonthlyInterest + "....");
                    scheduleData.add("....$" + TotalInterest + "....");                        
                    scheduleData.add("....$" + Balance + "....");
                    System.out.println(scheduleData);
                }
            }  
            else {
                System.out.println("");
                System.out.println("Not a valid input. Restarting...");
            }
        }
    }
}