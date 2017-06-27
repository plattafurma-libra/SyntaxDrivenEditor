package texts;

public class Shared {

	
	//private int pos=0;
	private boolean available=false;
	public Texts texts;
	
	
	public Shared(){
		this.texts=new Texts();		
	}
	
	// ch is put to texts.text from Editor Gui (s. EditParseGUI where charInGUISet is called
	public void charInGUISet(char ch){
		System.out.println("Shared.charInGUISet ch "+ch);
		texts.text[texts.getTextLen()]=ch;
		texts.incTextLen();
		available=texts.getTextLen()>texts.getTextPos();		
	}
	
	public synchronized char getSym(){
		System.out.println("Shared.getSym entry");
		try {
			System.out.println("Shared.getSym nach try vor while");
			while (!available) Thread.sleep(100);
			System.out.println("Shared.getSym last ch "+texts.text[texts.getTextPos()]);
		}
		catch (InterruptedException e){
			
		}
		available=false;
		//
		
		char ch= texts.text[texts.getTextPos()];
		texts.incTextPos();
		return ch;
		
	}

	          
	public synchronized char getCharAtTextPos(int pos){
		
		if (pos <this.texts.getTextLen()){
			return this.texts.getCharAtPos(pos);
		} 
		else return this.getSym();
		
	
	}
}