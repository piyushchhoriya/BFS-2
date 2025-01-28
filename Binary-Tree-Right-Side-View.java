## Problem 1

Binary Tree Right Side View (https://leetcode.com/problems/binary-tree-right-side-view/)
Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example 1:

Input: root = [1,2,3,null,5,null,4]

Output: [1,3,4]

//Solution
This problem can be solved by multiple ways
1. BFS
// Explaination: In this we will go level wise and store the last node's value at each level.
// As we know BFS uses a queue so we will initialize a queue and keep a size variable so that we can travel level by level
// Time Complexity : O(n) as we are traversing through each node
// Space Complexity : O(n) asymptotically


class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        //Base condition check
        if(root==null){
            return new ArrayList<>();
        }
        //Declared a list
        List<Integer> list =new ArrayList<>();
        int size=0;
        //Declared a queue
        Queue<TreeNode> q=new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            size=q.size();
            //We are iterating so that we can get the elements for that level only
            for(int i=0;i<size;i++){
                TreeNode curr=q.poll();
                //we are inserting the last element in the list
                if(i==size-1){
                    list.add(curr.val);
                }
                if(curr.left!=null){
                    q.add(curr.left);
                }
                if(curr.right!=null){
                    q.add(curr.right);
                }
                
            }
        }
        //We are returning a list at the end
        return list;
    }
}

2. Using DFS
// What we can do is we can go to the right first instead of going to the left and we will maintain the level with every node
// Also we will check if the size of the list is equal to the level if yes we will insert it. So by doing this we can capture 
// all the right view nodes in the list
// Time Complexity : O(n)
// Space Complexity : o(h) h = height of the tree

class Solution {
    List<Integer> list;
    public List<Integer> rightSideView(TreeNode root) {
        if(root==null){
            return new ArrayList<>();
        }
        list=new ArrayList<>();
        dfs(root,0);
        return list;
    }
    private void dfs(TreeNode root, int level){
        if(root==null){
            return;
        }
        if(list.size()==level){
            list.add(root.val);
        }
        dfs(root.right,level+1);
        dfs(root.left,level+1);
    }
}
// Here we have done the preorder traversal, if we do the inorder then it will give us the wrong answer

// Now what if the interviewer asks us to go to left first and then to right
// What we can do is we can add a left elements in the list and then once we go to right side we can overwrite them based on the level
// Time Complexity : O(n)
// Space Complexity : o(h) h = height of the tree

class Solution {
    List<Integer> list;
    public List<Integer> rightSideView(TreeNode root) {
        if(root==null){
            return new ArrayList<>();
        }
        list=new ArrayList<>();
        dfs(root,0);
        return list;
    }
    private void dfs(TreeNode root, int level){
        if(root==null){
            return;
        }
        if(list.size()==level){
            list.add(root.val);
        }else{
            list.set(level,root.val);
        }
        dfs(root.left,level+1);
        dfs(root.right,level+1);
    }
}