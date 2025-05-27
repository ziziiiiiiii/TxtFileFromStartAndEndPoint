import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Collections;

public class StartEndPass {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter a starting point and ending point");
        String userInput = keyboard.nextLine();

        String[] userInputSplit = userInput.split(" ");
        ArrayList<Integer> userInputTransformed = new ArrayList<>();

        for (String addString : userInputSplit) {
            userInputTransformed.add(Integer.parseInt(addString));
        }

        File userFile = new File("user-database.txt");
        Scanner fileScanner = null;

        try{
            fileScanner = new Scanner(userFile);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int loopVar = 0;
        ArrayList<User> userList = new ArrayList<>();

        while (fileScanner.hasNextLine()) {
            if (loopVar == 0) {
                fileScanner.nextLine();
                loopVar++;
                continue;
            }
            else {
                String fileLine = fileScanner.nextLine();

                String[] fileLineSplit = fileLine.split("\t");

                userList.add(new User(fileLineSplit[0], fileLineSplit[1]));
                loopVar++;
            }
        }

        for (int index = 1; index < userList.size(); index++) {
                User unsortedValue = userList.get(index);
                int scan = index;

                while (scan > 0 && (userList.get(scan - 1).compareTo(unsortedValue) > -1)) {
                    Collections.swap(userList, scan, scan - 1);
                    scan--;
                }
                userList.set(scan, unsortedValue);
        }


        for (int printLoopVar = userInputTransformed.get(0); printLoopVar < userInputTransformed.get(1); printLoopVar++) {
            User printUser = userList.get(printLoopVar);
            System.out.println(printUser);
        }
    }
}