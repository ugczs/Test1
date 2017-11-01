import java.util.ArrayList;
import java.util.List;


public class Node {
	private String value;
	private Node parentNode;
	private Node left;
	private Node right;
	private List<Byte> index;
	private boolean marked;
	
	public Node(String value) {
		this.value = value;
		this.parentNode = null;
		this.left = null;
		this.right = null;
		this.index = null;
		this.marked = false;
	}
	
	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Node getParentNode() {
		return parentNode;
	}

	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	public List<Byte> getIndex() {
		return index;
	}

	public void setIndex(List<Byte> index) {
		List<Byte> l = new ArrayList<Byte>(index);
		this.index = l;
	}
	
	public void setLeftIndex() {
		List<Byte> l = new ArrayList<Byte>(index);
		l.add((byte) 0);
		this.getLeft().setIndex(l);
	}
	
	public void setRightIndex() {
		List<Byte> l = new ArrayList<Byte>(index);
		l.add((byte) 1);
		this.getRight().setIndex(l);
	}
	
	public void clearChildren() {
		this.left = null;
		this.right = null;
	}
	
	public boolean hasLeft() {
		boolean b = false;
		if(this.left != null) {
			b = true;
		}
		else {
			b = false;
		}
		return b;
	}
	
	public boolean hasRight() {
		boolean b = false;
		if(this.right != null) {
			b = true;
		}
		else {
			b = false;
		}
		return b;
	}

	public boolean isMarked() {
		return marked;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
	}
	
	public boolean hasChildren() {
		boolean b = true;
		if(this.left == null && this.right == null) {
			b = false;
		}
		else {
			b = true;
		}
		return b;
	}
	
}
