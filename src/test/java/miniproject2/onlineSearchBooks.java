package miniproject2;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import utility.ExcelUtils;

public class onlineSearchBooks {
	public static String file="C:\\Users\\2303911\\eclipse-workspace\\Testing\\testdata\\Book1.xlsx";
	public WebDriver driver;
	
	public void createDriver() {
		//WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();      
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//1)Launch the browser
		driver.manage().window().maximize();
		driver.get("https://www.bookswagon.com/");
	}
	
	public void searchText() throws Exception{
		//2)Enter "Selenium Webdriver" in the search textbox
		
				String searchText=ExcelUtils.getCellData(file, "Sheet1", 0, 0);
				driver.findElement(By.id("inputbar")).sendKeys(searchText); 
				Thread.sleep(4000);
				driver.findElement(By.id("btnTopSearch")).click();
	}

	public void validateSearchResults() {
		driver.findElement(By.id("btnTopSearch")).click();
		//4)check the number of search results displayed are greater than 10
		String bha = driver.findElement(By.xpath("//*[@id=\"site-wrapper\"]/div[1]/div[2]/div[1]/div[1]/div/b")).getText();
		int txt=Integer.parseInt(bha);
		System.out.println(txt);
		if(txt>=10)
		{
			System.out.println("search result is greater than 10 ");
		}
		else
		{
			System.out.println("search result is less than 10 ");
		}
	}
	
	public void sortResults() throws IOException, InterruptedException {
		//5)click on sort by "Price - Low to High"
				String sort=ExcelUtils.getCellData(file, "Sheet1", 0, 1);
				driver.findElement(By.name("btnTopSearch")).click();
				Select sc=new Select(driver.findElement(By.id("ddlSort")));
				sc.selectByVisibleText(sort);
				Thread.sleep(10000);
	}
	
	public void printingResults() throws Exception {
		//6)get the name and price of first 5 displayed results and print
				int j=0;
				
			    for(int i=1;i<6;i++)
				{
					Thread.sleep(3000);
					String Bookname=driver.findElement(By.xpath("//*[@id=\"listSearchResult\"]/div["+i+"]/div[3]/div[1]/a")).getText();			
					List<WebElement> Bookprices=driver.findElements(By.xpath("//div[@class=\"sell\"]"));
					
					System.out.println("Book name : " +Bookname);
		            
		            String bookprice = Bookprices.get(j).getText();
		            System.out.println("Price : " +bookprice);
		            j++;
		            
				}
	}
	
	public void closeBrowser() {
	//7)close the browser
			driver.close();  
	}
	public static void main(String[] args) throws Exception{
		onlineSearchBooks st = new onlineSearchBooks();
		st.createDriver();
		st.searchText();
		st.validateSearchResults();
		st.sortResults();
		st.printingResults();
		st.closeBrowser();
		
		
		                                                             
		
	}
}
