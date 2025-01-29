## Problem 2

Cousins in binary tree (https://leetcode.com/problems/cousins-in-binary-tree/)

Given the root of a binary tree with unique values and the values of two different nodes of the tree x and y, return true if the nodes corresponding to the values x and y in the tree are cousins, or false otherwise.

Two nodes of a binary tree are cousins if they have the same depth with different parents.

Note that in a binary tree, the root node is at the depth 0, and children of each depth k node are at the depth k + 1.

Input: root = [1,2,3,4], x = 4, y = 3
Output: false

Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
Output: true

// Solution:

We can do this problem with both BFS and DFS

Approach 1 : Using BFS
// In this what we can do is we can go level by level and we can see after poll the curr has left and right if yes then we can check
// if they are equal to x & y if yes then it is the same parent so they are not cousins so we will return fasle. If they are not 
// same we are maintaing 2 boolean variables to see if at that level both x & y exist and after a loop we are checking it.
// So just by maintaing 2 variables we can find weather they are siblings or not.

//Time Complexity : O(n)
//space Complexity : O(n)

class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        if(root==null){
            return false;
        }
        //Variables to keep track if x and y found at same level
        boolean x_found = false;
        boolean y_found = false;
        int size=0;
        //Declared a queue
        Queue<TreeNode> q=new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            size=q.size();
            for(int i=0;i<size;i++){
                TreeNode curr = q.poll();
                //checking if curr has left and right if yes then there values are equal to x & y or not
                if(curr.left!=null && curr.left.val == x && curr.right!=null && curr.right.val==y){
                    return false;
                }
                //checking if curr has left and right if yes then there values are equal to x & y or not
                if(curr.left!=null && curr.left.val == y && curr.right!=null && curr.right.val==x){
                    return false;
                }
                //Adding left node in Queue
                if(curr.left!=null){
                    q.add(curr.left);
                }
                //Adding right node in the queue
                if(curr.right!=null){
                    q.add(curr.right);
                }
                //if curr.vall is x or y then marking corresponding variables as true
                if(curr.val==x){
                   x_found = true; 
                }
                if(curr.val==y){
                   y_found = true; 
                }

            }
            //Checking if x_found & y_found are true that means at same level then true
            if(x_found && y_found){
                return true;
            }
            else{
                x_found=false;
                y_found=false;
            }

        }
        return false;
    }
}

//Code Optimization:
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        if(root==null){
            return false;
        }
        boolean x_found = false;
        boolean y_found = false;
        int size=0;
        Queue<TreeNode> q=new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            size=q.size();
            for(int i=0;i<size;i++){
                TreeNode curr = q.poll();
                if(curr.left!=null && curr.left.val == x && curr.right!=null && curr.right.val==y){
                    return false;
                }
                if(curr.left!=null && curr.left.val == y && curr.right!=null && curr.right.val==x){
                    return false;
                }

                if(curr.left!=null){
                    q.add(curr.left);
                }
                if(curr.right!=null){
                    q.add(curr.right);
                }
                if(curr.val==x){
                   x_found = true; 
                }
                if(curr.val==y){
                   y_found = true; 
                }
                if(x_found && y_found){
                return true;
                }
            }
            //Checking if x_found || y_found are true that means one of x & y found but not the other
            if(x_found || y_found){
                return false;
            }
            
        }
        return false;
    }
}

//Approach 2 : Using DFS
//    In this we are maintaining below variables and then we are assigning values based on x and y found and at the end we are compairing and returning
//         x_level=-1;
//         y_level=-1;
//         x_parent=null;
//         y_parent=null;
// Time Complexity : O(n)
// Space Complexity : O(h)


class Solution {
    int x_level, y_level;
    TreeNode x_parent, y_parent;
    public boolean isCousins(TreeNode root, int x, int y) {
        if(root==null){
            return false;
        }
        x_level=-1;
        y_level=-1;
        x_parent=null;
        y_parent=null;
        dfs(root,null,0,x,y);
        if(x_parent != y_parent && x_level == y_level){
            return true;
        }
        return false;
    }
    private void dfs(TreeNode root,TreeNode parent,int level,int x,int y){
        //Base condition
        if(root==null){
            return;
        }
        //Checking if root's value is equal to x or y
        if(root.val == x){
            x_parent=parent;
            x_level=level;
        }
         if(root.val == y){
            y_parent=parent;
            y_level=level;
        }
        //Left call
        dfs(root.left,root,level+1,x,y);
        //right call
        dfs(root.right,root,level+1,x,y);
    }
}

//In the above code what if we found x and y at 0th and 1st level only then still it will go down to each level
// We can optimize it just by adding a condition in base condition check x_parent!=null && y_parent!=null
// This will help to make less recursive calls but still asymptotically time complexity and space complexity will be same
// Time Complexity : O(n)
// Space Complexity : O(h)
class Solution {
    int x_level, y_level;
    TreeNode x_parent, y_parent;
    public boolean isCousins(TreeNode root, int x, int y) {
        if(root==null){
            return false;
        }
        x_level=-1;
        y_level=-1;
        x_parent=null;
        y_parent=null;

        dfs(root,null,0,x,y);
        if(x_parent != y_parent && x_level == y_level){
            return true;
        }
        return false;

    }
    private void dfs(TreeNode root,TreeNode parent,int level,int x,int y){
        //Changed this condition
        if(root==null || x_parent!=null && y_parent!=null){
            return;
        }
        if(root.val == x){
            x_parent=parent;
            x_level=level;
        }
         if(root.val == y){
            y_parent=parent;
            y_level=level;
        }
        dfs(root.left,root,level+1,x,y);
        dfs(root.right,root,level+1,x,y);
    }
}
