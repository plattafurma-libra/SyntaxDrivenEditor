package texts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import texts.Shared;
import texts.Texts;

public class Regex {
	
	/* Typeface, style etc.
	 * 
	 Pattern p = Pattern.compile("a*b")--> in Ebnf
	 Matcher m = p.matcher("aaaaab");
	 boolean b = m.matches();
	*/
	Pattern p;
	
	//private String str;
	
	public static Regex RegexFactory(String comp)
	{
		if(Texts.ok) if(Texts.ok) System.out.println(" Regex comp: "+comp);
		Regex regex=new Regex();
		/*if ((comp.startsWith("\\Q"))&& (comp.endsWith("\\E")))
				// || (comp.length()==1)) 
			{
			//
			comp=comp.substring(2,comp.length()-2);
			if(Texts.ok) if(Texts.ok) System.out.println("comp literal: "+comp);
			regex.p=Pattern.compile(comp,Pattern.LITERAL);
		} else
		*/
			regex.p=Pattern.compile(comp);
		
		//regex.str="";
		return regex;
	}
		
	private boolean fontMatch(RichChar ch) {
		return true;
	}
	
	private int match(int i,texts.Shared shared) throws Exception{
		int end=0;
		int len;
		Matcher m=null;
		if(Texts.ok) if(Texts.ok) System.out.println("Regex match entry");
		RichChar ch= shared.getSym();
		if(Texts.ok) System.out.println("Regex match ch: "+ch.ch);
		String str="";
		
		while (true) {
			str=str+ch.ch;
			//////////
			str="Affe.";
			if(Texts.ok) System.out.println("Regex match: "+str);
			if (this.p==null)
				if(Texts.ok) System.out.println("Regex match p == null");
			m = this.p.matcher(str);
			if(Texts.ok) System.out.println(
			"Regex.match pattern: "+this.p.toString());
			
			if(Texts.ok) System.out.println("Regex match nach matcher");
			if (m==null)
				if(Texts.ok) System.out.println("Regex match m == null");
			
			
			if (!m.lookingAt()) {
				
				if(Texts.ok) System.out.println
				("Regex match lookingAt false");
				break;
			}
			end=m.end();
			len=str.length();
			if(len>end) {
				if(Texts.ok) System.out.println("Regex match end: "+end+
					" length: "+len);
				break;
			}
			if (!fontMatch(ch)) {
				return 0; // zero string for false
			}
			
			ch= shared.getSym();
			
		}//
		if (end>0){
			String matchString=str.substring(0,end);
			m = this.p.matcher(matchString);
			if (m.matches()) return end;
			else return 0;
		}
		else return 0;
	}
	
	
	public boolean editMatch(texts.Shared shared) {
	    try {
	    	if(Texts.ok) System.out.println("Regex editMatch entry");
	    	int end=0;
	    	int i=shared.getSharedText().getParsePos();  
	    	end=this.match(i,shared);
	    	if (end>0) {
	    		if(Texts.ok) System.out.println("Regex editMatch true: "//+str
	    				);
	    		shared.getSharedText().setParsePos(i+end);
	    		return true;//todo
	    	}
	    	else {
			//reset
	    		shared.getSharedText().setParsePos(i);
	    		if(Texts.ok) System.out.println("Regex editMatch false: "//+str
	    				);
	    		return false;
	    	}
	    } 
	    catch (Exception e) {
	    	e.printStackTrace();
	    	return false;
	    }
	    
	}
	
}
