package texts;


import CP.SyntaxTree.*;


public class SyntaxTree {

	public static void walker(INode iNode,SyntaxTree_TreeNode node,
		SyntaxTree_TreeNode mother) {
		
		if(Texts.ok)System.out.println("walker ");
		if (node != null) {
			if(Texts.ok)System.out.println("node != null ");
			// entry 
			iNode.enterNode(node,mother);
			// depth first
			SyntaxTree_TreeNode child=node.getChild();
			walker(iNode,child,node);
			
			
			// bredth second
			walker(iNode,node.suc,mother);
			// exit 
			iNode.exitNode(node,mother);
		}
	}
}
