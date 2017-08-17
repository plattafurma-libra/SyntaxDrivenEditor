package texts;


import java.io.BufferedReader;
import java.io.FileReader;

/* 
 * Texts contains an array textAsCharArray for storing characters entered by the editor.
 * textAsCharArray is a private. It is accessed by getters and setters, e.g. for parsing.
 * Texts is part (attribute) of the Shared class by which htread communication is done
 */
public class Texts {

	BufferedReader in=null;
	public boolean eot;
	private char[] textAsCharArray;
	private int textLen;
	private int textPos;
	
	// cstr
	public Texts() {
		this.textAsCharArray=new char[10000];
		textLen=0;
		textPos=0;
	}
	
	public void incTextLen() {
		this.textLen++;
	}
	
	public int getTextLen(){
		return this.textLen;
	}
	
	public int getTextPos(){
		return textPos;
	}
	
	public void setTextPos(int pos){
		this.textPos=pos;
	}
	
	public void incTextPos(){
		this.textPos++;
	}
	
	public char getTextChar(){
		//todo error if textPos>= textLen
		char ch=this.textAsCharArray[textPos];
		textPos++;
		return ch;
	}
	
	public char getTextCharAtPos(int pos){
		if (pos >=this.textLen) 
			//toDo
			{int i = 10/0;return 0;}
		else return this.textAsCharArray[pos];
	}
	
	public void setTextCharAtPos(char ch, int pos) {
		if (pos > this.textLen) 
			//toDo
			{int i = 10/0;}
		else this.textAsCharArray[pos]=ch;
		
		
	}
		
	public void open(String fileName){
	System.out.println("Texts.open: "+fileName);
	this.eot=false;
	try { 
		// EbnfGrammar is found in eclipse 
		in = new BufferedReader(new FileReader(fileName));
		System.out.println(fileName.toString()+" opened");
		}
			catch(Exception e){
			System.out.println(fileName.toString()+" not found");
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
			System.out.println("read error");
			}
		eot=true;
		return ' ';
	}//read
	
	public void readTextFromFile(String filename){
		System.out.println("Texts.readText: "+filename);
		this.open(filename);
		while(true){
			char ch=this.readCharFromFile();
			System.out.print(ch);;
			if (this.eot)break;
			this.textAsCharArray[textLen]=ch;
			textLen++;
		}
		System.out.println();
	}//readText	
	
	/*******Only for conversion of longint for Syntax Driven Editor Console Writing *****/
		public static char[] convertLongIntToCharArray(long l){
			// to string
			String str=Long.toString(l);
			char[] charArray = str.toCharArray();
			return charArray;
		}
		
		


}
