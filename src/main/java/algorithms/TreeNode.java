package algorithms;

public class TreeNode {
    private int id;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "id: "+id+", left: "+ (left!=null?left.getId():"")+", right: "+ (right!=null?right.getId():"");
    }
}
