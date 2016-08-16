package TestRunner;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author kechen
 */
public class ToProveIndependentTrial {
    public static void main(String arg[]){
        System.out.println("This small program will try to prove that failing a coin toss that happens X% of the time "
                + "would not increas the success rate of the next coin toss");
        
        Scanner in = new Scanner(System.in);

        try {
            System.out.println("Enter the chance of success for the coin toss in %: ");
            double successRate = in.nextInt();

            System.out.println("Enter how many times the coin toss you want to fail before counting actual success rate");
            int failThreshold = in.nextInt();

            System.out.println("Enter how many actual coin toss you want (the coin toss after X failures)");
            int actualCoinTossWanted = in.nextInt();

            int currentFailedCount = 0;
            int actualSuccess = 0;

            for (int i = 0; i < actualCoinTossWanted;) {
                if (Math.random() > successRate / 100.0) { //The coin toss failed
                    currentFailedCount++;
                }
                if (currentFailedCount >= failThreshold) {
                    i++;
                    currentFailedCount = 0;
                    if (Math.random() < successRate / 100.0) { //This is the real toss we want to capture. we want to capture success
                        actualSuccess++;
                    }
                }
            }
            
            int alternativeSuccessCount = 0;
            for(int i = 0; i < actualCoinTossWanted; i++){
                if(Math.random() < successRate / 100.0){
                    alternativeSuccessCount++;
                }
            }

            System.out.println("Test completed! we are running the test with a success rate of " + successRate + "%\n"
                    + "We count the success rate of the coin toss after every " + failThreshold + " failures.\n"
                    + "Let's see what's the final success rate after " + actualCoinTossWanted + " trials. \n"
                    + "The final success rate by doing this is " + ((double) actualSuccess / (double) actualCoinTossWanted) * 100 + "%.\n"
                    + "Alternatively, if you just toss the coin without waiting for consective failures, the success rate is " + ((double) alternativeSuccessCount / (double) actualCoinTossWanted) * 100 + "%");

        } catch (InputMismatchException ex) {
            System.out.println("Invalid input. Please provide a valid input!");
        }
    }
}
