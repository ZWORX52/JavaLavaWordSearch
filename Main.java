import java.util.*;
import java.io.*;


public class Main {
    static char[][] wordSearch;
    static int sideLength;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("E:\\For Pycharm\\Math Stuff\\words_output.txt"));

        ArrayList<String> words = new ArrayList<>();

        while (in.hasNext()) {
            words.add(in.next());
        }

//        System.out.println("words = " + words); 
        System.out.println("Word list size: " + words.size());
        System.out.print("Input the percentage of words that should get through: ");
        Scanner cin = new Scanner(System.in);

        long timeAtStart = System.nanoTime();

        float percentage = cin.nextFloat() / 100;
        int wordsCount = words.size();
        int loopUpTo = (int) (wordsCount * percentage);

        ArrayList<String> newWords = new ArrayList<>();
        Random rand = new Random();
        int maxWordLen = 0;

        for (int i = 0; i < loopUpTo; i++) {
            String toAdd = words.remove(rand.nextInt(words.size()));
            maxWordLen = Math.max(maxWordLen, toAdd.length());
            newWords.add(toAdd);
        }

        words = newWords;
        wordsCount = words.size();

        sideLength = (int) Math.sqrt(wordsCount * maxWordLen * 1.5) + 1;

        wordSearch = new char[sideLength][sideLength];

        for (char[] row: wordSearch) {
            Arrays.fill(row, ' ');
        }

        for (String word : words) {
            placeWord(word);
        }

        PrintWriter out = new PrintWriter("search_solved.txt");
        for (String st : words) {
            out.print(st);
            out.print(" ");
        }
        out.println();
        for (char[] row : wordSearch) {
            for (char ch : row) {
                out.print(ch);
                out.print(" ");
            }
            out.println();
        }

        out.close();

//        for (char[] row : wordSearch) {
//            for (char ch: row) {
//                System.out.print(ch);
//                System.out.print(" ");
//            }
//            System.out.println();
//        }

        for (int r = 0; r < sideLength; r++) {
            for (int c = 0; c < sideLength; c++) {
                if (wordSearch[r][c] == ' ') {
                    int charId = rand.nextInt(26) + 97;
                    wordSearch[r][c] = (char) charId;
                }
            }
        }

//        for (char[] row : wordSearch) {
//            for (char ch: row) {
//                System.out.print(ch);
//                System.out.print(" ");
//            }
//            System.out.println();
//        }

        out = new PrintWriter("search.txt");
        for (String st : words) {
            out.print(st);
            out.print(" ");
        }
        out.println();
        for (char[] row : wordSearch) {
            for (char ch : row) {
                out.print(ch);
                out.print(" ");
            }
            out.println();
        }

        out.close();

        long timeAtEnd = System.nanoTime();

        System.out.println("time taken is: "  + (timeAtEnd - timeAtStart));
        System.out.println("and in seconds: " + (double) Math.round((double) (timeAtEnd - timeAtStart) / 10000000d) / 100d);
    }

    public static void placeWord(String word) {
        Random rand = new Random();
        int row = rand.nextInt(sideLength);
        int col = rand.nextInt(sideLength);
        int dir = rand.nextInt(8);
        while (!attemptToPlace(word, row, col, dir)) {
            row = rand.nextInt(sideLength);
            col = rand.nextInt(sideLength);
            dir = rand.nextInt(8);
        }
    }

    public static boolean attemptToPlace(String word, int row, int col, int direction) {
        char[][] newArray = new char[sideLength][sideLength];
        for (int r = 0; r < sideLength; r++) {
            System.arraycopy(wordSearch[r], 0, newArray[r], 0, sideLength);
        }

        if (direction >= 8 || direction < 0) {
            System.out.println("Direction invalid!");
            throw new IllegalArgumentException();
        }

        int currentRow = row, currentCol = col, wordLength = word.length();

//        0 = North, 1 = East, 2 = South, 3 = West, 4 = NE, 5 = SE, 6 = SW, 7 = NW

        switch (direction) {
            case 0:
                for (int i = 0; i < wordLength; i++) {
                    if (currentRow < 0 || currentRow >= sideLength || currentCol < 0 || currentCol >= sideLength) {
                        return false;
                    }

                    if (newArray[currentRow][currentCol] != ' ' && newArray[currentRow][currentCol] != word.charAt(i)) {
                        return false;
                    }

                    newArray[currentRow][currentCol] = word.charAt(i);

                    currentRow--;
                }
                break;
            case 1:
                for (int i = 0; i < wordLength; i++) {
                    if (currentRow < 0 || currentRow >= sideLength || currentCol < 0 || currentCol >= sideLength) {
                        return false;
                    }

                    if (newArray[currentRow][currentCol] != ' ' && newArray[currentRow][currentCol] != word.charAt(i)) {
                        return false;
                    }

                    newArray[currentRow][currentCol] = word.charAt(i);

                    currentCol++;
                }
                break;
            case 2:
                for (int i = 0; i < wordLength; i++) {
                    if (currentRow < 0 || currentRow >= sideLength || currentCol < 0 || currentCol >= sideLength) {
                        return false;
                    }

                    if (newArray[currentRow][currentCol] != ' ' && newArray[currentRow][currentCol] != word.charAt(i)) {
                        return false;
                    }

                    newArray[currentRow][currentCol] = word.charAt(i);

                    currentRow++;
                }
                break;
            case 3:
                for (int i = 0; i < wordLength; i++) {
                    if (currentRow < 0 || currentRow >= sideLength || currentCol < 0 || currentCol >= sideLength) {
                        return false;
                    }

                    if (newArray[currentRow][currentCol] != ' ' && newArray[currentRow][currentCol] != word.charAt(i)) {
                        return false;
                    }

                    newArray[currentRow][currentCol] = word.charAt(i);

                    currentCol--;
                }
                break;
            case 4:
                for (int i = 0; i < wordLength; i++) {
                    if (currentRow < 0 || currentRow >= sideLength || currentCol < 0 || currentCol >= sideLength) {
                        return false;
                    }

                    if (newArray[currentRow][currentCol] != ' ' && newArray[currentRow][currentCol] != word.charAt(i)) {
                        return false;
                    }

                    newArray[currentRow][currentCol] = word.charAt(i);

                    currentRow--;
                    currentCol++;
                }
                break;
            case 5:
                for (int i = 0; i < wordLength; i++) {
                    if (currentRow < 0 || currentRow >= sideLength || currentCol < 0 || currentCol >= sideLength) {
                        return false;
                    }

                    if (newArray[currentRow][currentCol] != ' ' && newArray[currentRow][currentCol] != word.charAt(i)) {
                        return false;
                    }

                    newArray[currentRow][currentCol] = word.charAt(i);

                    currentRow++;
                    currentCol++;
                }
                break;
            case 6:
                for (int i = 0; i < wordLength; i++) {
                    if (currentRow < 0 || currentRow >= sideLength || currentCol < 0 || currentCol >= sideLength) {
                        return false;
                    }

                    if (newArray[currentRow][currentCol] != ' ' && newArray[currentRow][currentCol] != word.charAt(i)) {
                        return false;
                    }

                    newArray[currentRow][currentCol] = word.charAt(i);

                    currentRow++;
                    currentCol--;
                }
                break;
            case 7:
                for (int i = 0; i < wordLength; i++) {
                    if (currentRow < 0 || currentRow >= sideLength || currentCol < 0 || currentCol >= sideLength) {
                        return false;
                    }

                    if (newArray[currentRow][currentCol] != ' ' && newArray[currentRow][currentCol] != word.charAt(i)) {
                        return false;
                    }

                    newArray[currentRow][currentCol] = word.charAt(i);

                    currentRow--;
                    currentCol--;
                }
                break;
        }
        for (int r = 0; r < sideLength; r++) {
            System.arraycopy(newArray[r], 0, wordSearch[r], 0, sideLength);
        }
        return true;
    }
}
