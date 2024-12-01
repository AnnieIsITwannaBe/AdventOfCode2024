package HistorialHysteriaDay1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class Day1FirstTry {
    private static int ACCUMULATED_RESULT = 0;

    private static int CURRENT_SMALLEST_FIRST = 0;

    private static int CURRENT_SMALLEST_SECOND = 0;

    public static void main(String[] args) {
        String firstSequenceFile = "C:\\Users\\A\\Desktop\\IntroductHibernate\\SoftUniExtremeRecap\\AdventOfCode2024\\src\\main\\java\\HistorialHysteriaDay1\\firstSequence";
        String secondSequenceFile = "C:\\Users\\A\\Desktop\\IntroductHibernate\\SoftUniExtremeRecap\\AdventOfCode2024\\src\\main\\java\\HistorialHysteriaDay1\\secondSequence";

        //find smallest in both pairs and subtract them to find the distance between them:
        List<Integer> firstSequence = readSequenceFromFile(firstSequenceFile);

        List<Integer> secondSequence = readSequenceFromFile(secondSequenceFile);

        while (!firstSequence.isEmpty() && !secondSequence.isEmpty()) {
            OptionalInt currentMin1 = findMin(firstSequence);

            if (currentMin1.isPresent()) {
                CURRENT_SMALLEST_FIRST = currentMin1.getAsInt();

                firstSequence.remove(Integer.valueOf(currentMin1.getAsInt()));
            }
            OptionalInt currentMin2 = findMin(firstSequence);

            if (currentMin2.isPresent()) {
                CURRENT_SMALLEST_SECOND = currentMin2.getAsInt();

                secondSequence.remove(Integer.valueOf(currentMin2.getAsInt()));
            }
            // Determine the larger of the two
            int largerValue = Math.max(CURRENT_SMALLEST_FIRST, CURRENT_SMALLEST_SECOND);
            int smallerValue = Math.min(CURRENT_SMALLEST_FIRST, CURRENT_SMALLEST_SECOND);

            ACCUMULATED_RESULT += (largerValue - smallerValue);
        }

        System.out.println(ACCUMULATED_RESULT);
    }
    private static OptionalInt findMin(List<Integer> firstSequence) {
        return firstSequence.stream().mapToInt(Integer::intValue).min().stream().findFirst();
    }

    private static List<Integer> readSequenceFromFile(String fileName) {
        List<Integer> sequence = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Parse the line as an integer and add it to the list
                sequence.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + fileName);

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in file: " + fileName);
        }
        return sequence;
    }

    //what is robust logging?
}
