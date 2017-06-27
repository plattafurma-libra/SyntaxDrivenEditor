package edit_parse;

import texts.Shared;
import CP.Ebnf.*;


public class EditParse {
	
	Shared shared;
	//TestAx t=new TestAx();
	
	public EditParse() {
		this.shared=new Shared();
		
		//System.out.println("EditParse Constructor Ebnf.compile"+
		//TestAx.ch);
		
		System.out.println("EditParse Constructor nach Ebnf.compile");
	}
	
	private int counter=0;
	private boolean stop=false;
	
	public void startThread(){		
	     ThreadWord t1 = new ThreadWord(shared);	    
	     t1.start();	   
	}
	
	// 
	class  ThreadWord extends Thread {
		   private Shared shared;
		  
		   
		   public ThreadWord(Shared shared) {
		      this.shared = shared;		     
		   }
		   
		   // here to enter editParse CP
		   private void word(){
			   StringBuffer word=new StringBuffer();
			   System.out.println("word entry");
			   while(true){
				   try {
					   char ch= shared.getSym();
					   System.out.println("word ch: "+ch);
					   if ((ch!=' ') && (ch != '\r')&&(ch!='\n')&&(ch!='$')) word.append(ch);
					   else {System.out.println(); 
					   		stop=ch=='$';
					   		break;
					   	};
				   
				   }catch (Exception e) {
				   
				   }
			   
			   }
			   System.out.println("word: "+word);
			   System.out.println("word exit");
		   }// word
		   
		   private void editSyntaxDriven (){
			  System.out.println("editSyntaxDriven vor parse ok");
			  //this.shared.texts.textLen=0;
			  while (true){
				  counter++;
				  if (counter>100){stop=true;break;};
				  try {
					  if (Ebnf.parse(Ebnf.startsymbol)) 
						  {System.out.println("parse ok");break;}
			  			  
				  } catch (Exception e){
				  }stop=true;
			  } 
		   }//editSyntaxDriven
		   
		   public void run() {
			  Ebnf.init(true,this.shared);
			  System.out.println("TestEditParse ThrWord Thread run entry");
			  while(!stop){
				  if (counter >1000)stop=true;
				  System.out.println("TestEditParse ThrWord counter: "+counter);
				  try {
					  System.out.println("TestEditParse ThrWord vor getSym()");
					  // hier kommt der Parser rein aus dem ein getSym aufgerufen wird,
					  // das �ber TestShared mit Zeichen aus dem Editor versorgt wird
					 
					  //char ch= shared.getSym();
					  //word();
					  editSyntaxDriven();
					  System.out.println("TestEditParse ThrWord nach word");
					  sleep(10);					  
				  }
				  catch (Exception e){}
			 
				  
				  counter++;
			  }  
				  
	   }
			  
	}

	

	
}
