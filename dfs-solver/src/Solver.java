
public class Solver {

    Stack<Node> stack = new Stack<>();
    Node root;

    public SudokuBoard DFS() {
        Node currentNode = root;
        if (!currentNode.Board.isSolved()) {
            stack.push(currentNode);
            while (stack.Head != null) {
                currentNode = stack.pop();
                if (currentNode.Board.isSolved()) {
                    return currentNode.Board;
                }
                for (int i = 8; i >= 0; i--) {
                    if (currentNode.Children.get(i) != null) {
                        stack.push(currentNode.Children.get(i));
                    }
                }
            }
        } else {
            return currentNode.Board;
        }

        return null;
    }
}
