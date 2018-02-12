MODULE SyntaxTree;

IMPORT RTS,texts;
CONST NameLen*=32;

TYPE TreeNode* = POINTER TO ABSTRACT RECORD	 				
	 				name: ARRAY NameLen OF CHAR;
	 				suc*:TreeNode;(*for NonTerminal and for Terminal*)
	 			 END;
	 			 
NonTerminalTreeNode* = POINTER TO RECORD(TreeNode)
					child*:TreeNode;
				 END;
	 			 
TerminalTreeNode* = POINTER TO RECORD(TreeNode)
							startPos,endPos:INTEGER;
							sharedText:texts.Texts;
						 END;
						 

PROCEDURE (treeNode:TreeNode) nodename*():RTS.NativeString,NEW;


BEGIN
	RETURN MKSTR(treeNode.name$);
	
END nodename;

PROCEDURE (treeNode:TreeNode) nodeName*(VAR name:ARRAY OF CHAR),NEW;
BEGIN
	name:= treeNode.name$;
END nodeName;

PROCEDURE (treeNode:TreeNode) getChild*():TreeNode,NEW;
BEGIN
	IF treeNode IS NonTerminalTreeNode THEN
		RETURN treeNode(NonTerminalTreeNode).child;
	ELSE RETURN NIL;
	END;
END getChild;

PROCEDURE (treeNode:TerminalTreeNode) getStartPos*():INTEGER,NEW;
BEGIN
	RETURN treeNode.startPos;
END getStartPos;

PROCEDURE (treeNode:TerminalTreeNode) getEndPos*():INTEGER,NEW;
BEGIN
	RETURN treeNode.endPos;
END getEndPos;

PROCEDURE (treeNode:TerminalTreeNode) 
	getSharedText*():texts.Texts,NEW;
BEGIN
	RETURN treeNode.sharedText;
END getSharedText;

PROCEDURE (treeNode:TreeNode) isTerminalTreeNode*():BOOLEAN,NEW;
BEGIN
	RETURN treeNode IS TerminalTreeNode;
END isTerminalTreeNode;

PROCEDURE NonTerminalTreeNodeFactory*(name:ARRAY OF CHAR):
NonTerminalTreeNode;

VAR node:NonTerminalTreeNode;
BEGIN
	NEW(node);node.child:=NIL; node.suc:=NIL;node.name:=name$;
	RETURN node;
END NonTerminalTreeNodeFactory;

PROCEDURE TerminalTreeNodeFactory*
(name:ARRAY OF CHAR;sharedText:texts.Texts;start,end:INTEGER):
TerminalTreeNode;

VAR node:TerminalTreeNode;
BEGIN
	NEW(node);node.suc:=NIL;node.name:=name$;
	node.startPos:=start;node.endPos:=end;
	node.sharedText:=sharedText;
	RETURN node;
END TerminalTreeNodeFactory;
 
BEGIN (*Auto-generated*)

END SyntaxTree.