package com.test;

import java.util.List;

public interface ITree {

    public List<TreeNode> getTree();
    public List<TreeNode> getRoot();
    public TreeNode getTreeNode(String nodeId);
}
