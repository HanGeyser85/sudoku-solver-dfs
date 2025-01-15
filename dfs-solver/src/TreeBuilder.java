
import java.util.ArrayList;
import java.util.Collections;

public class TreeBuilder {

    SolutionTree solutionTree;
    Node currentNode;
    static Integer counter = 0;

    public void GenerateSolutionTree(SudokuBoard board) {

        Node root = new Node(new ArrayList<>(Collections.nCopies(9, (Node) null)), null);
        currentNode = root;
        root.name = ++counter;
        root.Board = board;
        solutionTree = new SolutionTree(root);

        if (board.isSolved()) {
            return;
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.board.get(i).get(j) == 0) {
                    for (int k = 1; k <= 9; k++) {
                        if (!board.isSolved() && board.CheckPossiblePositions(k, i, j)) {
                            SudokuBoard newBoard = new SudokuBoard(board.board);
                            newBoard.board.get(i).set(j, k);
                            if (board.isDeadEnd()) {
                                return;
                            }
                            Node child = new Node(new ArrayList<>(Collections.nCopies(9, (Node) null)), currentNode);
                            child.Board = newBoard;
                            child.name = ++counter;
                            currentNode.Children.set(k - 1, child);
                            currentNode = child;

                            GenerateInnerTree(newBoard, counter);

                            currentNode = currentNode.Parent;
                        }
                    }
                }
            }
        }
    }

    public void GenerateInnerTree(SudokuBoard board, Integer counter) {
        if (board.isSolved()) {
            return;
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.board.get(i).get(j) == 0) {
                    for (int k = 1; k <= 9; k++) {
                        if (board.CheckPossiblePositions(k, i, j)) {
                            SudokuBoard newBoard = new SudokuBoard(board.board);
                            newBoard.board.get(i).set(j, k);

                            if (board.isDeadEnd()) {
                                return;
                            }

                            Node child = new Node(new ArrayList<>(Collections.nCopies(9, (Node) null)), currentNode);
                            child.name = ++counter;
                            currentNode.Children.set(k - 1, child);
                            child.Board = newBoard;
                            currentNode = child;

                            GenerateInnerTree(newBoard, counter);

                            currentNode = currentNode.Parent;
                        }
                    }
                    return;
                }
            }
        }
    }

    public SolutionTree getSolutionTree() {
        return solutionTree;
    }
}
