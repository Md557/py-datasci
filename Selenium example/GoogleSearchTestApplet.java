package com.seleniumcookbook.examples.chapter01;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.edge.*;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.SimpleUniverse;

import org.openqa.selenium.remote.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.lang.*;
import java.util.ArrayList;
//import com.google.common.*;
//List for find element loop https://stackoverflow.com/questions/17255611/selenium-webdriver-getcssvalue-method
import java.util.List;
import java.util.ListIterator;

import javax.media.j3d.BranchGroup;

import java.applet.Applet;
import java.awt.*;

public class GoogleSearchTestApplet extends java.applet.Applet {
    private static WebElement bodyElement;
	private static WebDriver driver;
	@Before
	public static void setUp() {
	// Launch a new Firefox instance
	System.setProperty("webdriver.chrome.driver","D:/Downloads/chromedriver_win32/chromedriver.exe");		
	//	System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
	driver = new ChromeDriver();//RemoteWebDriver(new ChromeOptions());
    //EdgeDriver();//FirefoxDriver();
	// Maximize the browser window
	driver.manage().window().maximize();//fullscreen();
	// Navigate to Google
	driver.get("http://www.google.com");
	}
	@Test
	public static void testGoogleSearch() {
	// Find the text input element by its name
	WebElement element = driver.findElement(By.name("q"));
	// Clear the existing text value
	element.clear();

	// Enter something to search for
	element.sendKeys("tropical storm");
	// Now submit the form
	element.submit();
	// Google's search is rendered dynamically with JavaScript.

	
	String searchKey[]= {"body","doc-info","viewport"};
	String searchKey2[]= {"searchform","ostyle","gstyle","gsr"};
	ArrayList<WebElement> elements=new ArrayList<WebElement>();
	
	int i2 = 0;
	try{
		for (int i=0; i<searchKey2.length;i++) { 
			
			elements.add( driver.findElement(By.id(searchKey2[i])));	
		}
	}
	finally {
     System.out.println("number of manual elements selected: " + searchKey2.length); //elements.size()
 	 ListIterator<WebElement> webIter= elements.listIterator();

     while(webIter.hasNext()) {
    	 WebElement t=webIter.next();
         System.out.println("ID="+t.getAttribute("id"));
         System.out.println("hash code="+t.hashCode());
    	 System.out.println(t.getText());
     }
     System.out.println("########################################################");     
     bodyElement=driver.findElement(By.tagName("body"));
     System.out.println("bodyElement: "+bodyElement.getTagName());
     System.out.println("ID: "+bodyElement.getAttribute("id"));
     System.out.println("hash code="+bodyElement.hashCode());
	 System.out.println(bodyElement.getText());    
     System.out.println("########################################################");  
     WebElement idElement=driver.findElement(By.id(searchKey2[0]));
     System.out.println("idElement: "+idElement.getTagName());
     System.out.println("ID: "+idElement.getAttribute("id"));
     System.out.println("hash code="+idElement.hashCode());     
	 System.out.println(idElement.getText());    
	 
     System.out.println("########################################################");	 
     
     
 	//automatic capture of  elements
 	ArrayList<WebElement> elements2=(ArrayList<WebElement>) driver.findElements(By.cssSelector("*"));	
     
     
     System.out.println("number of total elements: " + elements2.size()+" (only first 40 shown)");     
     for(WebElement ele : elements2){
    	i2++;
         System.out.println("******************************************************");
         System.out.println("name="+ele.getTagName());

        System.out.println("hash code="+ele.hashCode());
        System.out.println("ID"+ele.getAttribute("id"));
        System.out.println("font-size"+ele.getCssValue("font-size"));
        System.out.println("------------------------------------------------------------");
        System.out.println(ele.getText());
        System.out.println("------------------------------------------------------------");
        if(i2>40) break;
        
     }

     //Could iterate over ele : elements2

	}
	// wait for the page to load, timeout after 10 seconds
	
	/*new WebDriverWait(driver, 15).until( new ExpectedCondition<Boolean>() {
		public Boolean apply(WebDriver d) 
		{
			return d.getTitle().toLowerCase().startsWith("test");
		}
	});

	assertEquals("Selenium testing tools cookbook - Google Search",	driver.getTitle());
	*/
	//MID add
	System.out.println(driver.getTitle());
	}
	@After
	public static void tearDown() throws Exception {
	// Close the browser

	driver.quit();
	}
    // Overriding paint() method 
    @Override
    public void paint(Graphics g)  
    { 
    	
        //g.drawString(bodyElement.getAttribute("id"), 20, 20); 
        //g.drawString(bodyElement.getTagName(), 20, 30); 
        //g.drawString(bodyElement.getText(), 20, 40); 
        
        //g.
    } 
    // Overriding paint() method 
    public void init ()
    {	
	    setSize(1500, 1600);
	    setLocation(2100,100);
	    setUp();
    	testGoogleSearch();
		String label1 = new String("Tag Name: "+bodyElement.getTagName()+"\nID: "+bodyElement.getAttribute("id")+"\nHashCode: "+bodyElement.hashCode());
		String bodyText=bodyElement.getText();
		int beginSIndex=bodyText.indexOf("Search Results");
		int beginTIndex=bodyText.indexOf("Twitter Results");
		int beginWIndex=bodyText.indexOf("Web results");
		int endWIndex=bodyText.indexOf("Page Navigation");
		int endSIndex=beginTIndex-1;
		int endTIndex=beginWIndex-1;
		System.out.println("start"+beginTIndex+"end"+endTIndex);
		String searchText=new String("");
		String twitterText=new String("");
		String webText= new String("");
		
		searchText=bodyText.substring(beginSIndex, endSIndex);
		twitterText=bodyText.substring(beginTIndex, endTIndex);
		webText=bodyText.substring(beginWIndex, endWIndex);
		
		//TextField textField1 = new TextField(bodyElement.getText());
	    TextArea typeText = new TextArea(label1, 3, 40);                     
	    
	    TextArea displayText = new TextArea(searchText);//searchText);                     
	    displayText.setRows(30);
	    displayText.setColumns(200);
	    TextArea displayText2 = new TextArea(twitterText,30,200);                     
	    TextArea displayText3 = new TextArea(webText,30,200);                     
	    
	    //typeText.addTextListener(this);
	 
	    add(typeText, BorderLayout.NORTH);
	    add(displayText, BorderLayout.NORTH);
	    add(displayText2, BorderLayout.NORTH);
	    add(displayText3, BorderLayout.NORTH);
	 

	    setVisible(true);
	
		//add(textField1);
    	//simpleU.addBranchGraph(CreateScene());
    }
    public void start()
    {
    	
    }
    public void stop()
    {
    	
    }
    public void destroy()
    {
    	try {
			tearDown();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	@SuppressWarnings("restriction")
	public static void main() {
        new MainFrame(new GoogleSearchTestApplet(), 1280, 1024);
	}
	
}
