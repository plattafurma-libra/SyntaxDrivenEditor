package edit_parse;

import texts.Shared;
import CP.Ebnf.*;


public class EditParse {
	
	Shared shared;
	
	
	public EditParse() {
		this.shared=new Shared();				
		System.out.println("EditParse Constructor nach Ebnf.compile");
	}
	
	//
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
		   
		   private void syntaxDrivenEdit (){
			  System.out.println("EditParse.syntaxDrivenEdit vor Ebnf.parse");
			  //
			  while (true){
				  
				  try {
					  if (Ebnf.parse(Ebnf.startsymbol)) 
						  {System.out.println("EditParse.syntaxDrivenEdit Ebnf.parse true");
						  break;}
					  else 
					  	{System.out.println
						 ("EditParse.syntaxDrivenEdit Ebnf.parse failed ErrorPosition: "
					  	 +Ebnf.maxPosInParse);
					  	break;}
			  			  
				  } catch (Exception e){
				  }stop=true; 
			  } 
		   }//syntaxDrivenEdit
		   
		   public void run() {
			  Ebnf.init(this.shared);
			  System.out.println("TestEditParse after Ebnf.Init ThrWord Thread run entry");
			  while(!stop){
				  
				  try {
					  System.out.println("TestEditParse ThrWord vor syntaxDrivenEdit()");
					  // hier kommt der Parser rein aus dem ein getSym aufgerufen wird,
					  // das  ueber TestShared mit Zeichen aus dem Editor versorgt wird
					 
					  //char ch= shared.getSym();
					  //word();
					  syntaxDrivenEdit();
					  System.out.println("TestEditParse ThrWord nach word");
					  // check events here, close or other, e.g. backtrack.
					  while (!Shared.available()) {/* nothing entered */};
					  
					  sleep(10);					  
				  }
				  catch (Exception e){}
			 
				  
			  } //while 
				  
	   } // run
			  
	}

	

	
}
