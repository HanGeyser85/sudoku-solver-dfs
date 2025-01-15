
import java.util.ArrayList;
import java.util.List;

public class Node {

    Integer name;
    Node Parent;
    List<Node> Children = new ArrayList<>(9);
    SudokuBoard Board;

    public Node(List<Node> Children, Node Parent) {
        this.Children = Children;
        this.Parent = Parent;
    }

    private void buildTreeString(Node node, StringBuilder sb, String prefix, boolean isLast) {
        sb.append(prefix);
        sb.append(isLast ? "└─ " : "├─ ");
        sb.append("Node ").append(node.name).append("\n");

        List<Node> realChildren = new ArrayList<>();
        for (Node child : node.Children) {
            if (child != null) {
                realChildren.add(child);
            }
        }

        String childPrefix = prefix + (isLast ? "   " : "│  ");
        for (int i = 0; i < realChildren.size(); i++) {
            boolean childIsLast = (i == realChildren.size() - 1);
            buildTreeString(realChildren.get(i), sb, childPrefix, childIsLast);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildTreeString(this, sb, "", true);
        return sb.toString();
    }
}
