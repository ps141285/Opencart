package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\DDT.xlsx";
		ExcelUtility xlutil=new ExcelUtility(path);
		
		
		
		int totalrows=xlutil.getrowcount("Sheet1");
		int totalcol=xlutil.getcellcount("Sheet1",1);
		
		String logindata[][]=new String[totalrows][totalcol];
		
		for(int i=1;i<=totalrows;i++) //i is row j is colom
		{
			for(int j=0;j<totalcol;j++)
			{
				logindata[i-1][j]=xlutil.getcelldata("Sheet1", i, j);
			}
		}
		return logindata;
	}
	@DataProvider(name="LoginData1")//we can use specific data from  data provider using "indices"
	public String [][] get_Data()
	{
		String[][] Data= {
				           {"manojpratap21@gmail.com","manoj@123","valid"},
				           {"xyz123@gmail.com","xyz@123","Invalid"},
				           {"pqr123@gmail.com","pqr@123","Invalid"},
				           {"abc141285@gmail.com","abc@123","Invalid"},
				           {"monubhadoriya786@gmail.com","monu@123","Valid"}
				
		                 };
		return Data;
		
	}

}
