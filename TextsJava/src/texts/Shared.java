package texts;

public class Shared {

		
	private static boolean available=false;
	
	
	private Texts sharedText;// sharedText.textAsCharArray gets (char by char) the chars edited in swtText of
	// the SWT Text Type in the SWT Gui
	
	//private int internalCaretPos=0;
	
	public boolean backTrack=false;
	// parsePos is position of sign processed in EBNF parser;
	// parsePos is compared to caretPos;
	// if caretPos is less than parsePos, backTrack is set to true and parsePos is reset.
	// if caretPos is greater then parsePos, available is true
	//
	
	//public int parsePos=0;//init
	
	public Shared(){
		this.sharedText=new Texts();		
	}
	
	public Texts getSharedText() {
		return this.sharedText;
	}
	
	// ch is put to texts.sharedTexts.textAsCharArray from Editor Gui (s. EditParseGUI where setCharFromSWTText is 
	// called (textAsCharArray is private in Shared; thus, it is only accessible by getters and setters).
	// this might be a similar  interface to draft's json objects and their difference in order to 
	// get new chars edited in draft current block
	
	public void setFromSWTText(String textStr,int caretPos,int len){
		// System.out.println("Shared.charFromSWTText ch "+ch);
		// backtrack for insert
		if (!this.backTrack) {
			if (this.sharedText.getParsePos() > caretPos) {
				this.backTrack=true;
				// parsePos is reset in EBNF parse at the end of backtrack
				System.out.println("Shared.setFromSWTText backTrack true");
			}
		}
		
		//System.out.println("Shared.setFromSWTText parsePos: "+
		//this.sharedText.getParsePos());
		this.sharedText.setTextAsCharArray(textStr);
		//this.internalCaretPos=caretPos;
		System.out.println("Shared.setFromSWTText textStr: "+textStr+
				" parsePos: "+this.sharedText.getParsePos()+ " caretPosition: "+
				caretPos);
		// backTrack is set to false after backtrack in ebnf.parse
		if(this.backTrack) this.available = true;
		else {			
			this.available=caretPos >= this.sharedText.getParsePos();	
		}
		
	}
	
	/***********************************************************************************/
	/* Vogt, Schildkamp */
	/* proposal for draft's jason, s. comment above  */
	
	/******neu s.o.
	public void setCharFromJson(RichChar richChar){
		// 
		System.out.println("Shared.charFromSWTText ch "+richChar.ch);
		this.sharedText.setTextCharAtPos(richChar.ch, sharedText.getTextLen());
		this.sharedText.incTextLen();
		this.available=sharedText.getTextLen()>sharedText.getTextPos();	
		// backtrack if '$'; alternativ bereits in JavaJson setzen, wie
		// oben in setCharFromSWTText vorausgesetzt und in
		// EditParseGUI valueChanged implementiert
		//
		if(richChar.ch=='$') this.backTrack=true;
	}
	
	// End of Json interfacing   
	***********************************************************************************/
	
	public synchronized char getSym(){
		System.out.println("Shared.getSym entry");
		if (sharedText.getTextLen()>sharedText.getParsePos()) available=true;
		try {
			System.out.println("Shared.getSym nach try vor while");
			while (!available) Thread.sleep(100);
			System.out.println("Shared.getSym pos "+
			this.sharedText.getParsePos());
		}
		catch (InterruptedException e){
			
		}
		available=false;
		//
		
		
		char ch= this.sharedText.getTextCharAtPos(this.sharedText.getParsePos());
		this.sharedText.incParsePos();
		// sharedText.text[sharedText.getTextPos()];
		//this.sharedText.incTextPos();
		return ch;
		
	}

	/* ersetzen durch getSym, checken */          
	public synchronized char getCharAtTextPos(int parsePos){
		//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		/*if (parsePos < this.internalCaretPos){
			return this.sharedText.getTextCharAtPos(parsePos);
		} 
		else*/ return this.getSym();
		
	
	}
	
	public static boolean available() {
		return available;
	}
}