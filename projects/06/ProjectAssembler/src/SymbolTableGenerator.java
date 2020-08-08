import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class SymbolTableGenerator {
	public HashMap<String, Integer> symTable(String fileName){
		HashMap<String, Integer> tableHashMap = new HashMap<String, Integer>();
		//Populate with predefined symbols
		tableHashMap.put("SP",0);
		tableHashMap.put("LCL",1);
		tableHashMap.put("ARG",2);
		tableHashMap.put("THIS",3);
		tableHashMap.put("THAT",4);
		tableHashMap.put("R0",0);
		tableHashMap.put("R1",1);
		tableHashMap.put("R2",2);
		tableHashMap.put("R3",3);
		tableHashMap.put("R4",4);
		tableHashMap.put("R5",5);
		tableHashMap.put("R6",6);
		tableHashMap.put("R7",7);
		tableHashMap.put("R8",8);
		tableHashMap.put("R9",9);
		tableHashMap.put("R10",10);
		tableHashMap.put("R11",11);
		tableHashMap.put("R12",12);
		tableHashMap.put("R13",13);
		tableHashMap.put("R14",14);
		tableHashMap.put("R15",15);
		tableHashMap.put("SCREEN",16384);
		tableHashMap.put("KBD",24576);
		
		
		
		try {
			
			// First pass for loop variables
			Scanner scanner = new Scanner(new File(fileName));
			int count =0;
			while(scanner.hasNextLine())
			{
				String str = scanner.nextLine();
				str =str.trim();
				if (str.charAt(0)=='(') {
					String tempString = str.substring(1,str.length()-1);
					tempString=tempString.trim();
					if (!tableHashMap.containsKey(tempString))
					{
						tableHashMap.put(tempString, count);
					}
				}
				else {
					count++;
				}
			}
			scanner.close();
			
			
			// Second pass for user defined variables
			Scanner scanner2 = new Scanner(new File(fileName));
			count =16;
			while(scanner2.hasNextLine())
			{
				String str = scanner2.nextLine();
				str =str.trim();
				if (str.charAt(0)=='@') {
					String tempString = str.substring(1);
					tempString=tempString.trim();
					if (!tableHashMap.containsKey(tempString) && !tempString.matches("-?\\d+"))
					{
						tableHashMap.put(tempString, count);
						count++;
					}
				}				
			}
			scanner2.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tableHashMap;
		
	}
}
