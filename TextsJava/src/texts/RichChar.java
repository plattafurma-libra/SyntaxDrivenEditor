package texts;


public class RichChar {
	public char ch;
	public char size[], style[], typeface[], weight[], 
	color[];
	
	private static boolean match(char[]one,char[]two) {
		int pos=0;
		while(true) {
			if (one[pos]==00) {
				return (two[pos]==00);
			}
			else if (one[pos]!=two[pos]) return false;
			else pos++;
		}
		
	}
	
	public boolean size(char[]size) {
		return match(this.size,size);
	}
	
	public boolean style(char[]style) {
		return match(this.style,style);
	}
	
	public boolean typeface(char[]typeface) {
		return match(this.typeface,typeface);
	}
	
	public boolean weight(char[]weight) {
		return match(this.weight,weight);
	}
	
	public boolean color(char[]color) {
		return match(this.color,color);
	}
}
