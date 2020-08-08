import java.util.HashMap;

/*
 * This class handles the main functions of Assembler
 * 
 * */
public class Main {

	public static void main(String[] args) 
	{
		// Parser will produce inter.txt. A file w/o whitespace and comments
		Parser p = new Parser();
		SymbolTableGenerator stg = new SymbolTableGenerator();
		Convert cnv = new Convert();
		String sourceFile = "PongL";
		p.parse(sourceFile+".asm");
		HashMap<String, Integer> hashMap = stg.symTable("inter.txt");
		cnv.binaryCode(sourceFile,"inter.txt", hashMap);
		
		
	}

}
