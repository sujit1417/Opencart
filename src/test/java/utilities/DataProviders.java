package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider 1
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\Opencart_LoginData.xlsx";
		ExcelUtility xlutil=new ExcelUtility(path);
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][]=new String[totalrows][totalcols];
		
		for(int r=1;r<=totalrows;r++)
		{
			for(int c=0;c<totalcols;c++)
			{
				//getting data from excel(index starting 1,0) and storing it in 2D array(index starting 0,0)
				logindata[r-1][c]=xlutil.getCellData("Sheet1", r, c);
			}
		}
		
		return logindata;
	}
	
	//DataProvider 2
	
	//DataProvider 3
		
	//DataProvider 4

}
