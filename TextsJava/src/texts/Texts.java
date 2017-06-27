package texts;


import java.io.BufferedReader;
import java.io.FileReader;


public class Texts {

	BufferedReader in=null;
	public boolean eot;
	public char[] text;
	private int textLen;
	private int textPos;
	
	// cstr
	public Texts() {
		this.text=new char[10000];
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
		char ch=this.text[textPos];
		textPos++;
		return ch;
	}
	
	public char getCharAtPos(int pos){
		if (pos >=this.textLen) 
			//toDo
			{int i = 10/0;return 0;}
		else return this.text[pos];
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
	
	
	public char read(){
		
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
	
	public void readText(String filename){
		System.out.println("Texts.readText: "+filename);
		this.open(filename);
		while(true){
			char ch=this.read();
			System.out.print(ch);;
			if (this.eot)break;
			text[textLen]=ch;
			textLen++;
		}
		System.out.println();
		
			
		
		
	}//readText

}
