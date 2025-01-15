
public class Stack<T> {

    StackNode<T> Head;

    public Stack() {
        Head = null;
    }

    public void push(T Value) {
        Head = new StackNode<>(Head, Value);
    }

    public T pop() {
        if (Head == null) {
            return null;
        }
        T Value = Head.getValue();
        Head = Head.getNext();
        return Value;
    }
}
