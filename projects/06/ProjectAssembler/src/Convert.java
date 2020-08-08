import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
public class Convert {
	public String mapJump(String code)
	{
		if (code.equals("JGT")) {
			return "001";
		}
		else if (code.equals("JEQ")) {
			return "010";
		}
		else if (code.equals("JGE")) {
			return "011";
		}
		else if (code.equals("JLT")) {
			return "100";
		}
		else if (code.equals("JNE")) {
			return "101";
		}
		else if (code.equals("JLE")) {
			return "110";
		}
		else if (code.equals("JMP")) {
			return "111";
		}
		else {
			return "000";
		}
	}
	
	
	public String mapDest(String code)
	{
		if (code.equals("M")) {
			return "001";
		}
		else if (code.equals("D")) {
			return "010";
		}
		else if (code.equals("MD")) {
			return "011";
		}
		else if (code.equals("A")) {
			return "100";
		}
		else if (code.equals("AM")) {
			return "101";
		}
		else if (code.equals("AD")) {
			return "110";
		}
		else if (code.equals("AMD")) {
			return "111";
		}
		else {
			return "000";
		}
	}
	
	public String mapComp(String code)
	{
		if (code.equals("0")) {
			return "0101010";
		}
		else if (code.equals("1")) {
			return "0111111";
		}
		else if (code.equals("-1")) {
			return "0111010";
		}
		else if (code.equals("D")) {
			return "0001100";
		}
		else if (code.equals("A")) {
			return "0110000";
		}
		else if (code.equals("M")) {
			return "1110000";
		}
		else if (code.equals("!D")) {
			return "0001101";
		}else if (code.equals("!A")) {
			return "0110001";
		}
		else if (code.equals("!M")) {
			return "1110001";
		}
		else if (code.equals("-D")) {
			return "0001111";
		}
		else if (code.equals("-A")) {
			return "0110011";
		}else if (code.equals("-M")) {
			return "1110011";
		}
		else if (code.equals("D+1")) {
			return "0011111";
		}
		else if (code.equals("A+1")) {
			return "0110111";
		}
		else if (code.equals("M+1")) {
			return "1110111";
		}else if (code.equals("D-1")) {
			return "0001110";
		}
		else if (code.equals("A-1")) {
			return "0110010";
		}
		else if (code.equals("M-1")) {
			return "1110010";
		}
		else if (code.equals("D+A")) {
			return "0000010";
		}
		else if (code.equals("D+M")) {
			return "1000010";
		}
		else if (code.equals("D-A")) {
			return "0010011";
		}
		else if (code.equals("D-M")) {
			return "1010011";
		}
		else if (code.equals("A-D")) {
			return "0000111";
		}
		else if (code.equals("M-D")) {
			return "1000111";
		}
		else if (code.equals("D&A")) {
			return "0000000";
		}
		else if (code.equals("D&M")) {
			return "1000000";
		}
		else if (code.equals("D|A")) {
			return "0010101";
		}
		else if (code.equals("D|M")) {
			return "1010101";
		}
		else {
			return "000";
		}
		
	}
	
	
	
	public void binaryCode(String source,String fileName, HashMap<String, Integer> hmp)
	{
		Scanner scanner;
		try {
			FileWriter myWriter = new FileWriter(source+".hack");
			scanner = new Scanner(new File(fileName));
			while(scanner.hasNextLine())
			{
				String str = scanner.nextLine();
				str =str.trim();
				//check if it is an A-instruction
				if (str.charAt(0)=='@') 
				{
					str = str.substring(1);
					int valString;
					if (str.matches("-?\\d+")) {
						valString = Integer.parseInt(str);
					}
					else {
						 valString = hmp.get(str);						
					}

					String command =String.format("%16s", Integer.toBinaryString(valString)).replace(' ', '0');
				
					myWriter.write(command+"\n");
				}
				else if (str.charAt(0)=='(') {
					//Loop variable skip
				}
				else { // it is a C-instruction
					String jmp="000";
					String dest="000";
					String comp;
					if (str.contains(";")) {
						String[] arrOfStr = str.split(";", -5);
						String jmpVar = arrOfStr[arrOfStr.length-1];
						jmpVar = jmpVar.trim();
						jmp = mapJump(jmpVar);						
					}
					 if (str.contains("=")) {
						String[] arrOfStr = str.split("=", -5);
						String destVar = arrOfStr[0].trim();
						dest=mapDest(destVar);
						if(arrOfStr[1].contains(";")) {
							String []secArr = arrOfStr[1].split(";",-5);
							String compVar=secArr[0].trim();
							comp = mapComp(compVar);							
						}
						else {
							String compVar=arrOfStr[1].trim();						
							comp = mapComp(compVar);
						}						
					} 
					else
					{
						if (str.contains(";")) {
							String[] arrOfStr = str.split(";", -5);
							String compVar=arrOfStr[0].trim();
							comp = mapComp(compVar);
						}
						else{
							String compVar=str.trim();
							comp = mapComp(compVar);
						}
						
					}
					 
					 String command="111"+comp+dest+jmp;
					 myWriter.write(command+"\n");
				}
				
			}
			scanner.close();
			myWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
