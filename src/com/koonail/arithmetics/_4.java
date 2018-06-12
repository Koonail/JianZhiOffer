package com.koonail.arithmetics;

import sun.reflect.generics.tree.Tree;

/**
 * @Author: WEN KANG
 * @Description:
 * @Time : 2018/6/12 17:10
 */
public class _4 {
    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
     */
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
    }

    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        TreeNode result = reConstructBinaryTree(pre,0,pre.length - 1,in,0,in.length - 1);
        return result;
    }

    /**
     * 重构二叉树
     * @param pre 先序遍历
     * @param preStart 先序起始下标
     * @param preEnd 先序终止下标
     * @param in 中序遍历
     * @param inStart 中序起始下标
     * @param inEnd 中序终止下标
     * @return
     */
    private TreeNode reConstructBinaryTree(int[] pre,int preStart,int preEnd,int[] in,int inStart,int inEnd){
        if (preStart > preEnd || inStart > inEnd)
        {
            // 当起始下标大于终止下标时，说明该数组已不存在,则不存在子节点
            return null;
        }
        // 创建子树
        TreeNode result = new TreeNode(pre[preStart]);
        for (int i = inStart;i < inEnd;i++)
        {
            // 从先序遍历中找到根节点，然后通过递归在中序遍历中找左右子树
            if (in[i] == pre[inStart]){
                result.left = reConstructBinaryTree(pre,preStart + 1,i - inStart + preStart,in,inStart,i - 1);
                result.right = reConstructBinaryTree(pre,i - inStart + preStart + 1,preEnd,in,i + 1,inEnd);
            }
            // 匹配上了之后就不用继续比较了
            break;
        }
        return new TreeNode(preStart);
    }
}
