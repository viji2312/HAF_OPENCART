package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//dataprovider 1
	 @DataProvider(name="LoginData")
	public String[][] getdata() throws IOException{
		
		// String path=System.getProperty("user.dir")+"\\testData\\Opencart_LoginTest.xlsx";
		 
		 String path=".\\testData\\Opencart_LoginTest.xlsx";//taking excel file from testdata
		 Excelutility  xlutil=new Excelutility(path);//creating object for excelutility class
	int totalrows = xlutil.getRowCount("sheet1");
	int totalcolumns=xlutil.getCellCount("sheet1",1);
	String logindata[][]=new String[totalrows][totalcolumns];// created two dimensional array
		
	
	for(int i=1;i<=totalrows;i++)//1 since zeroth row has header
	{
			for(int j=0;j<totalcolumns; j++)//0
			{
				logindata[i-1][j]=xlutil.getCellData("sheet1", i,j);//row location given as i-1 because indexing of aray starts from zero, if not given zero indexing in array will be empty	
			}
			
		}
		return logindata;//returns two dimensional array
	}
	 	//dataprovider 2
	 
		//dataprovider 2
	 
		//dataprovider 2
	 
}
