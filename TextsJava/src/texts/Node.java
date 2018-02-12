package texts;


import CP.SyntaxTree.SyntaxTree_TreeNode;
import CP.SyntaxTree.SyntaxTree_TerminalTreeNode;
import texts.INode;
import texts.Shared;


public class Node implements INode{
	
	public enum Status {ROOT,ENTER, EXIT};
	
	public static String resultString;
	private StringBuffer sb = new StringBuffer();
	private Status previousOp = Status.ROOT;
		
	public void clearString() {
		sb = new StringBuffer();
		
	}
	
	public String getString(){
		return sb.toString();
	}
	
	
	private String getParsedToken(SyntaxTree_TerminalTreeNode node) {
		String res="";char ch;
		for (int i=0;i<(node.getEndPos()-node.getStartPos());i++)
		{
			ch= node.getSharedText().getTextCharAtPos(i
			+node.getStartPos());
			res=res+ch;
		}
		return res;
	}
	
	public void enterNode(SyntaxTree_TreeNode node,
			SyntaxTree_TreeNode parent) {
		SyntaxTree_TerminalTreeNode tNode=null;
		System.out.println("in EnterNode");
		
		switch(previousOp){
		case ROOT:
			sb = new StringBuffer();
			break;
		case EXIT:
			// previous node is a sibling or a superior node 
			sb.append("\n,");
			break;
		case ENTER:
			// previous node is a parent node
			break;
		}
		
		sb.append("{" + "\n" + "\""+"name"+"\"" +": "+node.nodename()+"),");
		System.out.println("{" + "\n" + "\""+"name"+"\"" +": "+node.nodename()+"),");
		
		if (parent != null){
			
			sb.append("\n" + "\""+"parent"+"\"" +": "+ parent.nodename()+"),");
			System.out.println("\n" + "\""+"parent"+"\"" +": "+ parent.nodename()+"),");
			
		}
		if (node instanceof SyntaxTree_TerminalTreeNode) {
			tNode=(SyntaxTree_TerminalTreeNode)node;
			String parsedToken=getParsedToken(tNode);
			sb.append("\n" + "\""+"match"+"\"" +": "+ parsedToken+"),");
			System.out.println("\n" + "\""+"match"+"\"" +": "+ parsedToken+"),");
		}
		
		sb.append("\n" + "\""+"children"+"\"" +": [");
		System.out.println("\n" + "\""+"children"+"\"" +": [");
		
		previousOp = Status.ENTER;
	};
	
	public void exitNode(SyntaxTree_TreeNode node,
			SyntaxTree_TreeNode mother) {
		System.out.println("in ExitNode");
		
		sb.append("\n" +"]"+"\n" + "}");
		sb.append(",");
		System.out.println("]"+"\n" + "}");
		
		previousOp = Status.EXIT;
		
	};

}

/*
import CP.SyntaxTree.SyntaxTree_TreeNode;
import CP.SyntaxTree.SyntaxTree_TerminalTreeNode;
import texts.INode; 


public class Node implements INode{
	
	public static String resultString;	
		
	public void clearString() {
		resultString="";
	}
	
	public void enterNode(SyntaxTree_TreeNode node,
			SyntaxTree_TreeNode mother) {
		SyntaxTree_TerminalTreeNode tNode=null;
		if (Texts.ok) System.out.println("in EnterNode");
		resultString=resultString+"{" + "\r" + "\""+"name"+"\"" +":";
		String name=node.nodename();
		if (Texts.ok) System.out.println("("+name);
		if (node instanceof SyntaxTree_TerminalTreeNode)
			{tNode=
			(SyntaxTree_TerminalTreeNode)node;
			// write ....
			}
	};
	
	public void exitNode(SyntaxTree_TreeNode node,
			SyntaxTree_TreeNode mother) {
		if (Texts.ok) System.out.println("in ExitNode");
		String name=node.nodename();
		if (Texts.ok) System.out.println(name+")");
	};

}
*/
