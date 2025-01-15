
public class StackNode<T> {

    T Value;
    StackNode<T> Next;

    public StackNode(StackNode<T> Next, T Value) {
        this.Next = Next;
        this.Value = Value;
    }

    public T getValue() {
        return Value;
    }

    public void setValue(T Value) {
        this.Value = Value;
    }

    public StackNode<T> getNext() {
        return Next;
    }

    public void setNext(StackNode<T> Next) {
        this.Next = Next;
    }
}
