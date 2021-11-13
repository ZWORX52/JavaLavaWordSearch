import java.util.*;
import java.io.*;


public class Main {
    static char[][] wordSearch;
    static int sideLength;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("E:\\For Pycharm\\Math Stuff\\small_words.txt"));

        ArrayList<String> words = new ArrayList<>();

        while (in.hasNext()) {
            words.add(in.next());
        }

        System.out.println("words = " + words);

        int wordsCount = words.size();
        int wordsSize = 3;
        sideLength = (int) Math.sqrt(wordsCount * wordsSize * 2) + 1;

        wordSearch = new char[sideLength][sideLength];

        for (char[] row: wordSearch) {
            Arrays.fill(row, ' ');
        }

        for (String word : words) {
            placeWord(word);
        }

        for (char[] row : wordSearch) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void placeWord(String word) {
        Random rand = new Random();
        int row = rand.nextInt(sideLength);
        int col = rand.nextInt(sideLength);
        int dir = rand.nextInt(4);
        while (!attemptToPlace(word, row, col, dir)) {
            row = rand.nextInt(sideLength);
            col = rand.nextInt(sideLength);
            dir = rand.nextInt(4);
        }
    }

    public static boolean attemptToPlace(String word, int row, int col, int direction) {
        char[][] newArray = wordSearch;
        if (direction >= 4 || direction < 0) {
            System.out.println("Direction invalid!");
            throw new IllegalArgumentException();
        }

        int currentRow = row, currentCol = col, wordLength = word.length();

//        0 = North, 1 = East, 2 = South, 3 = West

        switch (direction) {
            case 0 -> {
                for (int i = 0; i < wordLength; i++) {
                    if (currentRow < 0 || currentRow >= sideLength || currentCol < 0 || currentCol >= sideLength) {
                        return false;
                    }

                    if (newArray[currentRow][currentCol] != ' ') {
                        return false;
                    }

                    newArray[currentRow][currentCol] = word.charAt(i);

                    currentRow--;
                }
            }
            case 1 -> {
                for (int i = 0; i < wordLength; i++) {
                    if (currentRow < 0 || currentRow >= sideLength || currentCol < 0 || currentCol >= sideLength) {
                        return false;
                    }

                    if (newArray[currentRow][currentCol] != ' ') {
                        return false;
                    }

                    newArray[currentRow][currentCol] = word.charAt(i);

                    currentCol++;
                }
            }
            case 2 -> {
                for (int i = 0; i < wordLength; i++) {
                    if (currentRow < 0 || currentRow >= sideLength || currentCol < 0 || currentCol >= sideLength) {
                        return false;
                    }

                    if (newArray[currentRow][currentCol] != ' ') {
                        return false;
                    }

                    newArray[currentRow][currentCol] = word.charAt(i);

                    currentRow++;
                }
            }
            case 3 -> {
                for (int i = 0; i < wordLength; i++) {
                    if (currentRow < 0 || currentRow >= sideLength || currentCol < 0 || currentCol >= sideLength) {
                        return false;
                    }

                    if (newArray[currentRow][currentCol] != ' ') {
                        return false;
                    }

                    newArray[currentRow][currentCol] = word.charAt(i);

                    currentCol--;
                }
            }
        }
        wordSearch = newArray;
        return true;
    }
}
