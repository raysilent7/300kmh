package br.com.desafioicarros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioICarrosApplication {

    public static void main (String[] args) throws Exception {
        SpringApplication.run(DesafioICarrosApplication.class, args);

        String[] sixPoints = {"*--", "---", "**-", "***"};
        String[] twelvePoints = {"***", "***", "***", "***"};
        String[] fourPoints = {"---", "*--", "---", "***"};

        String[] twoCreepyHours = {"04:04", "14:35", "15:51", "00:18"};
        String[] threeCreepyHours = {"04:04", "14:35", "15:51", "22:22"};
        String[] fourCreepyHours = {"04:04", "11:55", "15:51", "00:00"};
        String[] noCreepyHours = {"04:36", "11:42", "15:28", "00:50"};

        int[] thirdWins = {2, 1, 4};
        int[] thirdTies = {3, 4, 1};
        int[] firstWins = {3, 2, 0, 1};
        int[] firstTies = {2, 3, 5, 4};

        fizzingBuzzing();

        System.out.println(angryMarvinBirds(sixPoints));
        System.out.println(angryMarvinBirds(twelvePoints));
        System.out.println(angryMarvinBirds(fourPoints));

        System.out.println(creepyHours(twoCreepyHours));
        System.out.println(creepyHours(threeCreepyHours));
        System.out.println(creepyHours(fourCreepyHours));
        System.out.println(creepyHours(noCreepyHours));

        System.out.println(weAreTheChampions(thirdWins, thirdTies));
        System.out.println(weAreTheChampions(firstWins, firstTies));


    }

    public static void fizzingBuzzing () {
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FizzBuzz");
            }
            else if (i % 3 == 0) {
                System.out.println("Fizz");
            }
            else if (i % 5 == 0) {
                System.out.println("Buzz");
            }
            else {
                System.out.println(i);
            }
        }
    }

    public static long angryMarvinBirds (String[] results) throws Exception {
        long count = 0;

        for (String result : results) {
            switch (result) {
                case "---" : count += 0; break;
                case "*--" : count += 1; break;
                case "**-" : count += 2; break;
                case "***" : count += 3; break;
                default : throw new Exception("dados invalidos");
            }
        }

        return count;
    }

    public static int creepyHours (String[] hours) throws Exception {
        int count = 0;

        for (String hour : hours) {
            if (hour == null || "".equals(hour)) {
                throw new Exception("dados invalidos");
            }

            char[] hourDigits = hour.toCharArray();
            if (validatePatterns(hourDigits)) {
                count++;
            }
        }

        return count;
    }

    public static int weAreTheChampions (int[] wins, int[] ties) {
        int championPoints = 0;

        for (int i = 0; i < wins.length; i++) {
            int temp = wins[i] * 3 + ties[i];

            if (temp > championPoints) {
                championPoints = temp;
            }
        }

        return championPoints;
    }

    private static boolean validatePatterns (char[] hourDigits) {
        return hourDigits[0] == hourDigits[3] && hourDigits[1] == hourDigits[4]
                || hourDigits[0] == hourDigits[1] && hourDigits[3] == hourDigits[4]
                || hourDigits[0] == hourDigits[4] && hourDigits[1] == hourDigits[3];
    }
}
