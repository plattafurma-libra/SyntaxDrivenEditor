package edit_parse;



import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Shell;

public class EditParseGUI {
	
	EditParse editParse;
	
	
	 public void valueChanged(Text swtText) {
		    if (!swtText.isFocusControl())
		      return;
		    	      	
		    System.out.println("swtText: "+swtText.getText());
		   
		    /**************************************/
		    int caretPos= swtText.getCaretPosition();
		    int len=swtText.getText().length();
		    
		   
		    // backtrack necessary if pos < len (e.g. due to insertion) 
		   
		    System.out.println("caretPosition: "+caretPos+ " length: "+len);
		   
		     
		   
		   	//char lastEditedChar=swtText.getText().charAt(pos-1);
		    editParse.shared.setFromSWTText(swtText.getText(),caretPos,len);	
		    // 
		    // Vogt, Schildkamp:
		    // swtText kann auch ganz hier geloescht werden.
		    // (bislang bleibt der Text erhalten und wird wieder
		    // geparsed)
		    // dann sollte editParse.shared.text.textLength auf 0 gesetzt werden
		    // dafür in Texts neue setter methode;
		    // private text ist von  shared ueber getText Methode zugreifbar
		    //
		    
		    
		    /*
		    if (lastEditedChar=='$'){
		    	// delete last char '$'
		    	String text = swtText.getText(0,swtText.getText().length()-2);
		    	swtText.setText(text);
		    	// das wird in EBNF und in Regex fuer backTrack gebraucht.
		    	editParse.shared.backTrack=true;
		    	System.out.println("swtText after '$' entered: "+swtText.getText());
		    }
		    */
	 };
		   	 
	public void gui() {
		 /*-----------------------------------------*/
        
         editParse= null;
      
      	/*-----------------------------------------*/
    	Display display = new Display();
        Shell shell = new Shell(display);
        shell.setLayout(new GridLayout(1, false));
       
        // Create a multiple line edit_parse field
	    Text swtText = new Text(shell, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
	    swtText.setLayoutData(new GridData(GridData.FILL_BOTH));
	    
	    /*-------------------------------*/
	    editParse=new EditParse();
	    //
    	editParse.startThread();
    	
    	/*--------------------------------*/
	    ModifyListener listener = new ModifyListener() {
		    /** {@inheritDoc} */
		    public void modifyText(ModifyEvent e) {
		        // Handle event
		    	valueChanged(swtText);
		    }
		};	
		swtText.addModifyListener(listener);	    
        shell.open();
       
       
        while (!shell.isDisposed()) {
        if (!display.readAndDispatch ()) display.sleep ();
        }
        display.dispose();
    }

   
}
