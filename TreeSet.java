public class TreeSet<E extends Comparable<E>>{

	TreeNode<E> root;
	int size;
	String st;
	boolean found;

	public TreeSet(){
		root = null;
		size = 0;
		st = "";
	}

	public int size(){
	 	return size;
	}

	public boolean add(E value)
	{
		if(root == null){
			root = new TreeNode<E>(value);
			size++;
			return true;
		}
		return add(root, value);
	}

	private boolean add(TreeNode<E> currentNode, E value)
	{
		if(value.equals(currentNode.getValue())){
			return false;
		}


		if(value.compareTo(currentNode.getValue()) < 0)
		{
			if(currentNode.getLeft() == null)
			{
				currentNode.setLeft(new TreeNode<E>(value));
				size++;
				return true;
			}
			return add(currentNode.getLeft(), value);
		}
		if(currentNode.getRight() == null)
		{
			currentNode.setRight(new TreeNode<E>(value));
			size++;
			return true;
		}
		return add(currentNode.getRight(), value);
	}

	public String preOrder()
	{
		if(size==0)
			return "[]";
		if(size == 1)
			return "[" + root.getValue() + "]";
		st = "[";
		preOrder(root);
		return st.substring(0, st.length()-2) + "]";
	}

	public void preOrder(TreeNode<E> root)
	{
		if(root!= null)
		{
			st+=root.getValue() + ", ";
			preOrder(root.getLeft());
			preOrder(root.getRight());
		}
	}

	public String inOrder()
	{
		if(size==0)
			return "[]";
		if(size == 1)
			return "[" + root.getValue() + "]";
		st = "[";
		inOrder(root);
		return st.substring(0, st.length()-2) + "]";
	}

	public void inOrder(TreeNode<E> root)
	{
		if(root!= null)
		{
			inOrder(root.getLeft());
			st+=root.getValue() + ", ";
			inOrder(root.getRight());
		}
	}

	public String postOrder()
	{
		if(size==0)
			return "[]";
		if(size == 1)
			return "[" + root.getValue() + "]";
		st = "[";
		postOrder(root);
		return st.substring(0, st.length()-2) + "]";
	}

	public void postOrder(TreeNode<E> root)
	{
		if(root!= null)
		{
			postOrder(root.getLeft());
			postOrder(root.getRight());
			st+=root.getValue() + ", ";
		}
	}

	public boolean remove(E value){
		if(root == null){
			return false;
		}

		if(root.getLeft() == null & root.getRight() == null)
		{
			if(value.equals(root.getValue()))
			{
				root = null;
				size = 0;
				return true;
			}
			return false;
		}

		found = false;
		root = remove(root, value);
		if(found)
		{
			size--;
			return true;
		}
		return false;
	}

	public TreeNode<E> remove(TreeNode<E> temp, E value)
	{
		if(temp == null){
			return temp;
		}

		if(value.compareTo(temp.getValue()) < 0){
			temp.setLeft(remove(temp.getLeft(), value));
		}
		else if(value.compareTo(temp.getValue()) > 0){
			temp.setRight(remove(temp.getRight(), value));
		}
		else {
			found = true;
			if(temp.getLeft() == null){
				return temp.getRight();
			}
			else if(temp.getRight() == null){
				return temp.getLeft();
			}
			else{
				E minValOnRight = minValue(temp.getRight());
				temp.setValue(minValOnRight);
				temp.setRight(remove(temp.getRight(), minValOnRight));
			}
		}
		return temp;
	}

	public E minValue(TreeNode<E> currentNode){
		if(currentNode.getLeft() == null){
			return currentNode.getValue();
		}
		return minValue(currentNode.getLeft());
	}

	public boolean contains(E value){
		found = false;
		if(root != null){
			if(value.compareTo(root.getValue()) < 0){
				contains(root.getLeft(), value);
			}
			else if(value.compareTo(root.getValue()) > 0){
				contains(root.getRight(), value);
			}
		}
		return found;
	}

	public void contains(TreeNode<E> temp, E value){
		try{
			if(value.compareTo(temp.getValue()) < 0){
				contains(temp.getLeft(), value);
			}
			else if(value.compareTo(temp.getValue()) > 0){
				contains(temp.getRight(), value);
			}
			else if(value.compareTo(temp.getValue()) == 0){
				found = true;
			}
		}catch(NullPointerException e){
			found = false;
		}
	}

	public void rotateLeft(){
		TreeNode<E> temp = root;
		if(root != null){
			if(temp.getRight() == null){
				System.out.println("Not rotatable");
			}
			else{
				temp = temp.getRight();
				while(temp.getLeft() != null){
					temp = temp.getLeft();
				}

				TreeNode<E> temp2 = root.getRight();
				root.setRight(null);
				temp.setLeft(root);
				root = temp2;

			}
		}
	}

	public void rotateRight(){
		TreeNode<E> temp = root;
		if(root != null){
			if(temp.getLeft() == null){
				System.out.println("Not rotatable");
			}
			else{
				temp = temp.getLeft();
				while(temp.getRight() != null){
					temp = temp.getRight();
				}

				TreeNode<E> temp2 = root.getLeft();
				root.setLeft(null);
				temp.setRight(root);
				root = temp2;

			}
		}
	}
	public class TreeNode<E extends Comparable<E>>{
		TreeNode<E> left, right;
		E value;
		 public TreeNode(E value){
			 this.value = value;
			 left = null;
			 right = null;
		 }
		 public TreeNode<E> getRight(){
		 	return right;
		 }
		 public TreeNode<E> getLeft(){
			return left;
		 }
		 public void setRight(TreeNode<E> right){
			 this.right = right;
		 }
		 public void setLeft(TreeNode<E> left){
			 this.left = left;
		 }
		 public E getValue(){
			 return value;
		 }
		 public void setValue(E value){
			 this.value = value;
		 }
	}
}
