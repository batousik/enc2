package uk.ac.standrews.cs.cs3302.practical1.datastructure;

/**
 * Created by utug on 10/13/15.
 */
public class HNode {
    private HNode leftNode;
    private HNode rightNode;

    public HNode(HNode leftNode, HNode rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public HNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(HNode leftNode) {
        this.leftNode = leftNode;
    }

    public HNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(HNode rightNode) {
        this.rightNode = rightNode;
    }
}
