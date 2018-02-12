MODULE RegexReplace;

IMPORT StringLib,TextsCP,RTS,texts;

(*CONST nr=200;
*)

TYPE String = ARRAY 40 OF CHAR;
	 Replace = POINTER TO RECORD source, dest:String;
	 END;
	 (*Replaces = ARRAY nr OF Replace;*)
	 Replaces = VECTOR OF Replace;

VAR replaces:Replaces;(*index:INTEGER;*)
	nativeStr:RTS.NativeString;
	(*last:INTEGER;*)

PROCEDURE replaceInRegex*(VAR regex:ARRAY OF CHAR);

VAR i,pos:INTEGER;changed,found:BOOLEAN;
BEGIN
	changed:=TRUE;
	WHILE changed DO
		changed:=FALSE;
		i:=0;
		WHILE i < LEN(replaces) DO
			StringLib.FindNext(replaces[i].source,regex,0,found,pos);
			IF found THEN
				changed:=TRUE;
				(*  *)
				TextsCP.WriteString("replaceInRegex: ");
				TextsCP.WriteString(regex);
				TextsCP.WriteLn;
				TextsCP.WriteString(" ");
				StringLib.Delete(regex,pos,TextsCP.Length(replaces[i].source));
				TextsCP.WriteString("Delete: ");
				TextsCP.WriteString(regex);
				TextsCP.WriteLn;
				StringLib.Insert(replaces[i].dest,pos,regex);	
				TextsCP.WriteString(" Insert: ");
				TextsCP.WriteString(regex);
				TextsCP.WriteLn;
			END;		
			INC(i);
		END (* while i < nr *)
	END (*while changed*)
	
END replaceInRegex;

PROCEDURE ReadRegex();
VAR R:TextsCP.Reader; index:INTEGER;ch:CHAR;pos:INTEGER;
	replace:Replace;
BEGIN
	R.filename:= texts.Texts.regex;	
	TextsCP.OpenReader(R);
	index:=0;ch:=' ';
	WHILE ~R.eot DO
		(*
		IF index >nr THEN THROW ("too much regex replaces");
		END;
		*)
		TextsCP.Read(R,ch);
		(*
		 TextsCP.Write(ch);
		 *)
		IF R.eol THEN TextsCP.Read(R,ch);
		END;
		pos:=0;
		NEW(replace);
		WHILE ch # ':' DO
			
			replace.source[pos]:=ch;
			INC(pos);
			IF pos > LEN(replace.source) THEN 
				THROW("regex replace too long");
			END;
			TextsCP.Read(R,ch);
			(*TextsCP.Write(ch);*)			
		END (* while ch # ':'*);
		replace.source[pos]:=0X;
		pos:=0;
		TextsCP.Read(R,ch);
		WHILE (~R.eol) & (~R.eot) DO			
			replace.dest[pos]:=ch;
			INC(pos);
			IF pos > LEN(replace.dest) THEN 
				THROW("regex replace too long");
			END;
			TextsCP.Read(R,ch);			
		END (* while ch # eot , eol*);
		replace.dest[pos]:=0X;
		(*
		INC(index);
		IF index > nr THEN
			THROW("regex index too great");
		END;
		*)
		APPEND(replaces,replace);
	END;
	(*
		last:=index;
	*)
END ReadRegex;

PROCEDURE Init*();
VAR index:INTEGER;replace:Replace;
BEGIN
	NEW(replaces,10);
	TextsCP.WriteString("Regex.Replace Init Entry");
	TextsCP.WriteLn;
	ReadRegex();
	
	(* unicode *)
	FOR index:=0 TO LEN(replaces)-1 DO
		replace:=replaces[index];
		TextsCP.WriteString("Regex.replace.source ");
		TextsCP.WriteString(replace.source);
		TextsCP.WriteLn;
		IF (replace.source[0] = '\') &
		(replace.source[1] ='u') THEN
			nativeStr:=MKSTR(replace.source);
			replace.dest[0]:=
			texts.Texts.convertUnicode(nativeStr);
			TextsCP.WriteString(" unicode: ");
			TextsCP.WriteString(replace.dest);			
			TextsCP.WriteLn;
			replace.dest[1]:=0X;(*terminator*)
		END;		
	END;
	
END Init;
	 
BEGIN (* read from file? *)
	
END RegexReplace.