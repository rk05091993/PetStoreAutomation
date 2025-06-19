package api.utilites;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	// This will retreive all data from the excel sheet
	@DataProvider(name="Data")  //This method is responsible to generate the data and pass the data 
	public String[][] getAllData() throws IOException
	{
		String path=System.getProperty("user.dir")+"//TestData//Userdata.xlsx"; //This is the path of the file(Excel sheet) 
		ExcelUtility xl=new ExcelUtility(path); // Create the object of the excel utility file and pass the path of the file
		
		int rownum=xl.getRowCount("Sheet1");// This will find total no of rows & we will pass sheet name
		int colcount=xl.getCellCount("Sheet1",1);// This will find total no of columns & we will pass sheet name along with row number
		 // Array= Excel Same
		String apidata[][]=new String[rownum][colcount];//Array also have same no of rows and columns
		
		for(int i=1;i<=rownum;i++) // this loop will get the data from the sheet and assign the same data in 2d array
		{
			for(int j=0;j<colcount;j++)
			{
				apidata[i-1][j]=xl.getCellData("Sheet1",i,j);
			}
		}
		return apidata;
		
	}
	
	// This will retreive only username from the excel sheet
	@DataProvider(name="UserNames")
	public String[]getUserNames() throws IOException
	{
		String path=System.getProperty("user.dir")+"//TestData//Userdata.xlsx";
		ExcelUtility xl=new ExcelUtility(path);
		int rownum=xl.getRowCount("Sheet1");
		
		String apidata[]=new String[rownum];
		for(int i=1;i<=rownum;i++)
		{
			apidata[i-1]=xl.getCellData("Sheet1",i,1);
		}
		return apidata;
	}

}
