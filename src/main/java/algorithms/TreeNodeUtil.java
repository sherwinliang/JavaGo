package algorithms;

import java.util.Stack;

public class TreeNodeUtil {
    /* @description: create a certain depth node tree
     * @author: Sherwin Liang
     * @timestamp: 2021/6/14 22:46
     * @param: depth depth of the tree
     * @return: TreeNode the root node
    */
    public static TreeNode create(int depth){
        int tempId = (int)Math.random()*(2<<depth);
        TreeNode root = new TreeNode(tempId);
        TreeNode temp = root;
        Stack<TreeNode> stack = new Stack<>();
        int line = 0;
        for(;;){
            if(line==depth)
                break;
            temp = stack.pop();
            if(temp.getLeft()==null){
                tempId = (int)Math.random()*(2<<depth);
                temp.setLeft(new TreeNode(tempId));
                stack.push(temp);
                line++;
            }
        }
        return root;
    }
}
