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
	
	 public void valueChanged(Text text) {
		    if (!text.isFocusControl())
		      return;
		    if (editParse==null){
		    	editParse=new EditParse();
		    	editParse.startThread();
		    }	
	      	
		    System.out.println(text.getText());
		    String str=text.getText();
		   	
		    editParse.shared.charInGUISet(str.charAt(str.length()-1));	
	 };
		   	 
	public void gui() {
		 /*-----------------------------------------*/
        
         editParse= null;
      
      	/*-----------------------------------------*/
    	Display display = new Display();
        Shell shell = new Shell(display);
        shell.setLayout(new GridLayout(1, false));
       
        // Create a multiple line edit_parse field
	    Text text = new Text(shell, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
	    text.setLayoutData(new GridData(GridData.FILL_BOTH));
	    
	    ModifyListener listener = new ModifyListener() {
		    /** {@inheritDoc} */
		    public void modifyText(ModifyEvent e) {
		        // Handle event
		    	valueChanged(text);
		    }
		};	
		text.addModifyListener(listener);	    
        shell.open();
       
       
        while (!shell.isDisposed()) {
        if (!display.readAndDispatch ()) display.sleep ();
        }
        display.dispose();
    }

   
}
