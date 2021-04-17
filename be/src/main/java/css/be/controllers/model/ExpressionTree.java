package css.be.controllers.model;

public class ExpressionTree {

    private Node root;
    private ExpressionTree left;
    private ExpressionTree right;
    private ExpressionTree parent;

    public ExpressionTree(Node root, ExpressionTree left, ExpressionTree right, ExpressionTree parent) {
        this.root = root;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void setLeft(ExpressionTree left) {
        this.left = left;
    }

    public void setRight(ExpressionTree right) {
        this.right = right;
    }

    public void setParent(ExpressionTree parent) {
        this.parent = parent;
    }

    public Node getRoot() {
        return root;
    }

    public ExpressionTree getLeft() {
        return left;
    }

    public ExpressionTree getRight() {
        return right;
    }

    public ExpressionTree getParent() {
        return parent;
    }

    @Override
    public String toString() {
        String result = "";
        result += this.getRoot().getValue();
        if (this.getLeft() != null)
            result += "\n\tLeft:" + this.getLeft().toString();
        if (this.getRight() != null)
            result += "\n\tRight:" +  this.getRight().toString();
        return result;
    }
}
