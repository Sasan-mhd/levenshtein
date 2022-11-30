import java.util.stream.IntStream;

public class LevenshteinMatrix {

    public static int distance(String string1, String string2) {
        int[][] matrix = new int[string1.length() + 1][string2.length() + 1];

        fillFirstColumnWithIndexOfChars(matrix, string1);
        fillFirstRowWithIndexOfChars(matrix, string2);
        fillOtherCells(matrix, string1, string2);

        return matrix[string1.length()][string2.length()];
    }

    private static void fillFirstColumnWithIndexOfChars(int[][]matrix, String string){
        IntStream.range(0, string.length() + 1)
                .forEach(i -> matrix[i][0] = i);
    }

    private static void fillFirstRowWithIndexOfChars(int[][]matrix, String string){
        IntStream.range(0, string.length() + 1)
                .forEach(i -> matrix[0][i] = i);
    }

    private static void fillOtherCells(int[][]matrix, String string1, String string2){

        for (int i = 1; i <= string1.length(); i++) {
            for (int j = 1; j <= string2.length(); j++) {
                if (string1.charAt(i - 1) == string2.charAt(j - 1)) {
                    matrix[i][j] = Integer.min(
                            matrix[i - 1][j - 1],
                            Integer.min(matrix[i - 1][j] + 1, matrix[i][j - 1] + 1)
                    );
                } else {
                    matrix[i][j] = Integer.min(
                            matrix[i - 1][j] + 1,
                            Integer.min(matrix[i - 1][j - 1] + 1, matrix[i][j - 1] + 1)
                    );
                }
            }
        }
    }


}
