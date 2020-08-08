import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//This will create a intermediate file
// which will not contain any comments and white space
public class Parser {
	public void parse(String fileName)
	{
		try {
			FileWriter myWriter = new FileWriter("inter.txt");
		    
			
			Scanner scanner = new Scanner(new File(fileName));
			while(scanner.hasNextLine())
			{
				String str = scanner.nextLine();
				str =str.trim();
				
				//String is a blank line
				if(str.length()==0) {
					continue;
				}
				else if(str.contains("//"))
				{
					
					if(str.substring(0,2).equals("//")) 
					{						
						continue;
					}
					else {
						String[] arrOfStr = str.split("//", -5);
						String temp = arrOfStr[0];
						temp =temp.trim();
						myWriter.write(temp+"\n");
					    
					}
					
				}
				else // Line is code only write it on inter file
				{
					//System.out.println(str.charAt(0));
					myWriter.write(str+"\n");
				}
			}
			
			myWriter.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
