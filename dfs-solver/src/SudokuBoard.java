
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SudokuBoard {

    List<List<Integer>> board = new ArrayList<>(9);

    public SudokuBoard(List<List<Integer>> board) {
        for (List<Integer> row : board) {
            this.board.add(new ArrayList<>(row));
        }
    }

    public boolean isSolved() {
        for (List<Integer> row : board) {
            for (Integer cell : row) {
                if (cell == 0) {
                    return false;
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            boolean[] rowCheck = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if (rowCheck[board.get(i).get(j) - 1]) {
                    return false;
                }
                rowCheck[board.get(i).get(j) - 1] = true;
            }

            boolean[] colCheck = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if (colCheck[board.get(j).get(i) - 1]) {
                    return false;
                }
                colCheck[board.get(j).get(i) - 1] = true;
            }

            boolean[] squareCheck = new boolean[9];
            for (int j = 0; j < 9; j++) {
                int row = 3 * (i / 3) + j / 3;
                int col = 3 * (i % 3) + j % 3;
                if (squareCheck[board.get(row).get(col) - 1]) {
                    return false;
                }
                squareCheck[board.get(row).get(col) - 1] = true;
            }
        }

        return true;
    }

    public boolean isDeadEnd() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i).get(j) == 0) {
                    // Check if there's any valid k
                    boolean hasValid = false;
                    for (int k = 1; k <= 9; k++) {
                        if (CheckPossiblePositions(k, i, j)) {
                            hasValid = true;
                            break;
                        }
                    }
                    if (!hasValid) {
                        // No valid digit for this empty cell => dead end
                        return true;
                    }
                }
            }
        }
        return false;  // no dead cell found => not a dead end
    }

    public boolean CheckNumber(Integer number) {
        Integer counter = 0;
        List<Integer> rowCheck = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) {
            rowCheck.add(-1);
        }

        for (List<Integer> row : board) {
            for (Integer cell : row) {
                if (Objects.equals(cell, number)) {
                    if (rowCheck.get(counter) != -1) {
                        return false;
                    }

                    rowCheck.add(counter++);
                }
            }
        }

        return counter == 9;
    }

    public boolean CheckPossiblePositions(Integer Number, Integer rowNum, Integer colNum) {
        for (int i = 0; i < 9; i++) {
            if (Objects.equals(board.get(rowNum).get(i), Number) || Objects.equals(board.get(i).get(colNum), Number)) {
                return false;
            } else if (Objects.equals(board.get(3 * (rowNum / 3) + i / 3).get(3 * (colNum / 3) + i % 3), Number)) {
                return false;
            }
        }

        return true;
    }

    public boolean isFull() {
        for (List<Integer> row : board) {
            for (Integer cell : row) {
                if (cell == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean hasNoPossiblePositions(Integer Number) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i).get(j) == 0 && CheckPossiblePositions(Number, i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("╔═╤═╤═╦═╤═╤═╦═╤═╤═╗\n");
        for (List<Integer> row : board) {
            sb.append("║");
            for (int i = 0; i < 9; i++) {
                sb.append(row.get(i) == 0 ? " " : row.get(i));
                if (i % 3 == 2) {
                    sb.append("║");
                } else {
                    sb.append("│");
                }
            }

            sb.append("\n");

            if ((row == board.get(2) || row == board.get(5)) && row != board.get(8)) {
                sb.append("╠═╪═╪═╬═╪═╪═╬═╪═╪═╣\n");
            } else if (row != board.get(8)) {
                sb.append("╟─┼─┼─╫─┼─┼─╫─┼─┼─╢\n");
            }
        }
        sb.append("╚═╧═╧═╩═╧═╧═╩═╧═╧═╝\n");
        return sb.toString();
    }
}
