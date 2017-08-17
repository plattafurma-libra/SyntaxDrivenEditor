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
		    /*if (editParse==null){
		    	editParse=new EditParse();
		    	editParse.startThread();
		    }*/	
	      	
		    System.out.println("swtText: "+swtText.getText());
		   
		   	char lastEditedChar=swtText.getText().charAt(swtText.getText().length()-1);
		    editParse.shared.setCharFromSWTText(lastEditedChar);	
		    if (lastEditedChar=='$'){
		    	// delete last char '$'
		    	String text = swtText.getText(0,swtText.getText().length()-2);
		    	swtText.setText(text);
		    	editParse.shared.backTrack=true;
		    	System.out.println("swtText after '$' entered: "+swtText.getText());
		    }
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
