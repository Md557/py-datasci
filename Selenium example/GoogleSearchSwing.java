package gsts;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import org.junit.After;
import org.openqa.selenium.By;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.j3d.utils.applet.MainFrame;

public class GoogleSearchSwing {

	private static WebDriver driver;
    private static JFrame f; 
    private static JTextArea jTA1;
    private static JTextArea jTA2;
    private static JTextArea jTA3;
    private static JTextPane jTP1;
    public static String searchString;
	public static void setUp() { //@Before // public void setUp(){
		
	System.setProperty("webdriver.chrome.driver","D:/Downloads/chromedriver_win32/chromedriver.exe");		
	//	System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
	driver = new ChromeDriver();//RemoteWebDriver(new ChromeOptions());
    //EdgeDriver();//FirefoxDriver();
	// Maximize the browser window
	driver.manage().window().maximize();//fullscreen();
	// Navigate to Google
	driver.get("http://www.google.com");
	}

	public static void testGoogleSearch() {
	// Find the text input element by its name
	WebElement element = driver.findElement(By.name("q"));
	// Clear the existing text value
	element.clear();
	searchString="Tropical Storm";
	// Enter something to search for
	element.sendKeys(searchString);
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
     System.out.println("******************************************************");     
     WebElement bodyElement=driver.findElement(By.tagName("body"));
     System.out.println("bodyElement: "+bodyElement.getTagName());
     System.out.println("ID: "+bodyElement.getAttribute("id"));
     System.out.println("hash code="+bodyElement.hashCode());
	 //System.out.println(bodyElement.getText());    

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
	 System.out.println("\n~/~/~/~/~/SEARCH RESULTS~/~/~/~/~/\n"+searchText);
	 System.out.println("\n~/~/~/~/~/TWITTER FEED~/~/~/~/~/\n"+twitterText);
	 System.out.println("\n~/~/~/~/~/WEB RESULTS~/~/~/~/~/\n"+webText);
	 
	 f = new JFrame();
	 JPanel subPanel= new JPanel();
	 JPanel subPanel2= new JPanel();
	 JPanel subPanel3= new JPanel();
	 
	 jTA1=new JTextArea(searchText);
	 //jTASearch=new JTextArea(searchText);
	 
	 jTA2=new JTextArea(twitterText);
	 jTA3=new JTextArea(webText);
	 	 
	 //JScrollPane jS1=new JScrollPane(jTA2);
	 JScrollPane jS2=new JScrollPane(jTA2);
	 JScrollPane jS3=new JScrollPane(jTA3);
	 //JTextPane jTP1= new JTextPane();
	 //jTP1.add(twitterText);
	 //jTA1.setSize(400,400);
	 //jTA2.setSize(400,400);
	 //jS1.setVisible(true);
	 f.setLayout(new GridLayout(2,1)); 
	 subPanel.setLayout(new GridLayout(1,2)); 
     Border blackLine = BorderFactory.createLineBorder(Color.black);
     Border empty = BorderFactory.createEmptyBorder(10, 10, 10, 10);
     CompoundBorder line = new CompoundBorder(empty, blackLine);
     Border grid2Border = BorderFactory.createTitledBorder(line, "Search results");
     Border grid3Border = BorderFactory.createTitledBorder(line, "Web results");
     Border gridBorderT = BorderFactory.createTitledBorder(line, "Twitter results");
     Border gridBorderS = BorderFactory.createTitledBorder(line, "Search String");
     
     JTextField textField1 = new JTextField(searchString);
	 subPanel2.setLayout(new GridLayout(2,1)); 
     subPanel2.add(textField1);
     subPanel2.add(jTA1);
     //subPanel3.add(jS3);
	 subPanel.add(subPanel2);
	 subPanel.add(jS3);
	 f.add(subPanel);
     f.add(jS2);

     textField1.setBorder(gridBorderS);
     //subPanel2.setBorder(grid2Border);
     jS3.setBorder(grid3Border);
     jS2.setBorder(gridBorderT);
     
	 
	 //f.add(jTP1);
	 f.setSize(1800,1200);
	 f.setVisible(true);
     f.addWindowListener(new WindowAdapter()  
     { 

         @Override
         public void windowClosing(WindowEvent e)  
         { 
             try {
				tearDown();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
             System.exit(0); 
         } 
           
     }); 
     
     //new WebDriverWait(driver, 15);  
     

	// Get the Alert
	 

	 
	 /*
     WebElement idElement=driver.findElement(By.id(searchKey2[0]));
     System.out.println("idElement: "+idElement.getTagName());
     System.out.println("ID: "+idElement.getAttribute("id"));
     System.out.println("hash code="+idElement.hashCode());     
	 System.out.println(idElement.getText());    
	 */
	 
	 
	 /*
     System.out.println("******************************************************");	 
 	//automatic capture of  elements
 	ArrayList<WebElement> elements2=(ArrayList<WebElement>) driver.findElements(By.cssSelector("*"));	
     
     
     System.out.println("number of total elements: " + elements2.size()+" (only first 20 shown)");     
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
        if(i2>20) break;
     }
	*/
 	
	}
	
	/*
	// wait for the page to load, timeout after 10 seconds
	new WebDriverWait(driver, 15).until( new ExpectedCondition<Boolean>() {
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
	public GoogleSearchSwing() {
		setUp();
		testGoogleSearch();		
	}

	public static void main(String[] args) {
		// MUST USE STANDALONE SERVER .JAR in Project -> Properties -> Libraries (when using class as a main & not a Maven test...................

		new GoogleSearchSwing();
	}
}
