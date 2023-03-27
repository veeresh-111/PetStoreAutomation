package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="Data")
	public String [][] getAllData() throws IOException
	{
		
		String path="./testData/UserData.xlsx";
		int rownum=XLUtility.getRowCount(path, "Sheet1");
		int colcount=XLUtility.getCellCount(path,"Sheet1",1);
		
		String apidata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				apidata[i-1][j]=XLUtility.getCellData(path,"Sheet1", i,j);//1 0
			}
				
		}
	return apidata;
	}
	@DataProvider(name="UserNames")
	public String [] getUserNames() throws IOException
	{
		
		String path="./testData/UserData.xlsx";
		int rownum=XLUtility.getRowCount(path, "Sheet1");
		
		String apidata1[]=new String[rownum];
		
		for(int i=1;i<=rownum;i++)
		{
				apidata1[i-1]=XLUtility.getCellData(path,"Sheet1",i,1);//1 1
		}
	return apidata1;
	}
	
}
