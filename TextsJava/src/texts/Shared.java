package texts;

public class Shared {

	
	
	private boolean available=false;
	private Texts sharedText;// sharedText.textAsCharArray gets (char by char) the chars edited in swtText of
	// the SWT Text Type in the SWT Gui
	
	public boolean backTrack=false;
	
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
	
	public void setCharFromSWTText(char ch){
		System.out.println("Shared.charFromSWTText ch "+ch);
		// backtrack if '$'
		if(!this.backTrack) {
			this.sharedText.setTextCharAtPos(ch, sharedText.getTextLen());
			//sharedText.text[sharedText.getTextLen()]=ch;
			this.sharedText.incTextLen();
			this.available=sharedText.getTextLen()>sharedText.getTextPos();	
		}
		
	}
	
	/***********************************************************************************/
	/* proposal for draft's jason, s. comment above  */
	public void setCharFromJson(RichChar richChar){
		// 
		System.out.println("Shared.charFromSWTText ch "+richChar.ch);
		this.sharedText.setTextCharAtPos(richChar.ch, sharedText.getTextLen());
		this.sharedText.incTextLen();
		this.available=sharedText.getTextLen()>sharedText.getTextPos();	
		// backtrack if '$'
		if(richChar.ch=='$') this.backTrack=true;
	}
	
	/* End of Json interfacing    */
	/***********************************************************************************/
	
	public synchronized char getSym(){
		System.out.println("Shared.getSym entry");
		try {
			System.out.println("Shared.getSym nach try vor while");
			while (!available) Thread.sleep(100);
			System.out.println("Shared.getSym last ch "+
			this.sharedText.getTextCharAtPos(sharedText.getTextPos()));
		}
		catch (InterruptedException e){
			
		}
		available=false;
		//
		
		char ch= this.sharedText.getTextCharAtPos(this.sharedText.getTextPos());
		// sharedText.text[sharedText.getTextPos()];
		sharedText.incTextPos();
		return ch;
		
	}

	          
	public synchronized char getCharAtTextPos(int pos){
		
		if (pos <this.sharedText.getTextLen()){
			return this.sharedText.getTextCharAtPos(pos);
		} 
		else return this.getSym();
		
	
	}
}