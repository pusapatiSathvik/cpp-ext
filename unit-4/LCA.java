import java.util.*;

// Tree node definition
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Main {

    // Method to build tree from level-order array
    public static TreeNode buildTree(String[] values) {
        if (values.length == 0 || values[0].equals("null")) return null;

        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;

        while (i < values.length) {
            TreeNode current = queue.poll();

            // Left child
            if (i < values.length && !values[i].equals("null")) {
                current.left = new TreeNode(Integer.parseInt(values[i]));
                queue.offer(current.left);
            }
            i++;

            // Right child
            if (i < values.length && !values[i].equals("null")) {
                current.right = new TreeNode(Integer.parseInt(values[i]));
                queue.offer(current.right);
            }
            i++;
        }

        return root;
    }

    // Find the node reference for given value
    public static TreeNode findNode(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        TreeNode left = findNode(root.left, val);
        if (left != null) return left;
        return findNode(root.right, val);
    }

    // LCA function
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) return root;
        return (left != null) ? left : right;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Example input: 3 5 1 6 2 0 8 null null 7 4
        System.out.println("Enter tree nodes in level-order (use 'null' for empty):");
        String[] input = scanner.nextLine().split(" ");

        TreeNode root = buildTree(input);

        System.out.print("Enter value of node p: ");
        int pVal = scanner.nextInt();
        System.out.print("Enter value of node q: ");
        int qVal = scanner.nextInt();

        TreeNode p = findNode(root, pVal);
        TreeNode q = findNode(root, qVal);

        if (p == null || q == null) {
            System.out.println("One or both nodes not found in the tree.");
        } else {
            TreeNode lca = lowestCommonAncestor(root, p, q);
            System.out.println("Lowest Common Ancestor: " + lca.val);
        }
    }
}
