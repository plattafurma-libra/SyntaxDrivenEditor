package texts;



import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List; 

/* 
 * Texts contains an array textAsCharSeq for storing characters entered by the editor.
 * textAsCharSeq is a private. It is accessed by getters and setters, e.g. for parsing.
 * Texts is part (attribute) of the Shared class by which thread communication is done
 */
public class Texts {

	BufferedReader in=null;
	public boolean eot;
	private List<RichChar> textAsCharSeq;
	//private char[] textAsCharSeq;
	private int textLen;
	private int parsePos;
	static public boolean ok=true;
	static public String grammar="C://users//rols//lexGrammar.txt";
	static public String regex="C://users//rols//Regex.txt";
	// cstr
	public Texts() {
		this.textAsCharSeq=new ArrayList<RichChar>();//new char[1000000];
		textLen=0;
		parsePos=0;
	}
	
	
	/*public char[] getTextAsCharArray() {
		return this.textAsCharSeq;
	}
	
	*/
	
	public void setTextAsCharArray(String str) {
		this.textAsCharSeq = 
			new ArrayList<RichChar>();
				//str.toCharArray();
		for (int i=0;i<str.length();i++) {
			RichChar rChar=new RichChar();
			rChar.ch=str.charAt(i);
			textAsCharSeq.add(rChar);
		}
		this.textLen=str.length();
	}
		
		
	public int getTextLen(){
		return this.textLen;
	}
	
	
	public int getParsePos(){
		return parsePos;
	}
	
	public void setParsePos(int pos){
		this.parsePos=pos;
	}
	
	public void incParsePos(){
		this.parsePos++;
	}
	
	/*public char getTextChar(){
		//todo error if textPos>= textLen
		char ch=this.textAsCharSeq[parsePos];
		parsePos++;
		return ch;
	}*/
	
	public char getTextCharAtPos(int pos){
		RichChar rChar;
		rChar=this.textAsCharSeq.get(pos);
		if (ok) System.out.println("texts.Text.getTextCharAtPos pos: "+pos+ " ch:"+
		rChar.ch);
		//this.textAsCharSeq[pos]);
		if (pos >=this.textLen) 
			//toDo
			{int i = 10/0;return 0;}
		else return //this.textAsCharSeq[pos];
				rChar.ch;
	}
	
	public void setTextCharAtPos(char ch, int pos) {
		if (pos > this.textLen) 
			//toDo
			{int i = 10/0;}
		else {RichChar rChar=new RichChar();
			rChar.ch=ch;
			this.textAsCharSeq.set(pos,rChar);//[pos]=ch;
		}
		
		
	}
		
	public void open(String fileName){
	if (ok) System.out.println("Texts.open: "+fileName);
	this.eot=false;
	try { 
		// EbnfGrammar is found in eclipse 
		in = new BufferedReader(new FileReader(fileName));
		if (ok) System.out.println(fileName.toString()+" opened");
		}
			catch(Exception e){
			if (ok) System.out.println(fileName.toString()+" not found");
		}
		
	}//open
	
		
	public char readCharFromFile(){
		
		int val;	
		try {
			val=in.read();			
			if (val==-1) {this.eot=true;return ' ';}
			else return (char)val;
			}
		catch(Exception e){
			if (ok) System.out.println("read error");
			}
		eot=true;
		return ' ';
	}//read
	
	/*
	public void readTextFromFile(String filename){
		if (ok) System.out.println("Texts.readText: "+filename);
		this.open(filename);
		while(true){
			char ch=this.readCharFromFile();
			if (ok) System.out.print(ch);;
			if (this.eot)break;
			this.textAsCharSeq[textLen]=ch;
			textLen++;
		}
		if (ok) System.out.println();
	}//readText	
	*/
	
	/*******Only for conversion of longint for Syntax Driven Editor Console Writing *****/
		public static char[] convertLongIntToCharArray(long l){
			// to string
			String str=Long.toString(l);
			char[] charArray = str.toCharArray();
			return charArray;
		}

		// only called from RegexReplace.cp
		public static char convertUnicode(String s) {
	        if (s.startsWith("\\u")) {
	            String numberStr = s.substring(2);
	            char c = (char) new BigInteger(numberStr, 16).intValue();
	            return c;//new Character(c).toString();
	        }
	        return 0;
	    } 
		
		


}
