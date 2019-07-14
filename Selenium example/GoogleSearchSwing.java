package gsts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ListIterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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
	private WebElement element,bodyElement;
    private static JFrame f; 
    private static JTextArea jTA1,jTA2,jTA3;
    private static JScrollPane jS1,jS2,jS3;
    private static JPanel subPanel,subPanel2,subPanel2a,subPanel3;
    private static JTextPane jTP1;
    private static Dimension d1;
    private int beginSIndex,beginWIndex,beginTIndex, finalIndex;
    private List<Integer> searchList;
    private JButton submitButton;
    private static boolean started=false;
    public static String searchString;
    private static JTextField textField1;
    private static GridBagConstraints constraints;
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

	public void testGoogleSearch() {
	// Find the text input element by its name
	element = driver.findElement(By.name("q"));
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
     bodyElement=driver.findElement(By.tagName("body"));
     System.out.println("bodyElement: "+bodyElement.getTagName());
     System.out.println("ID: "+bodyElement.getAttribute("id"));
     System.out.println("hash code="+bodyElement.hashCode());
	 //System.out.println(bodyElement.getText());    

	 String bodyText=bodyElement.getText();
	 beginSIndex=bodyText.indexOf("Search Results");
	 beginTIndex=bodyText.indexOf("Twitter Results");
	 beginWIndex=bodyText.indexOf("Web results");
	 finalIndex=bodyText.indexOf("Page Navigation");
	 System.out.println(beginSIndex+" "+beginTIndex+" "+beginWIndex+" "+finalIndex);
	 searchList=Arrays.asList(beginSIndex,beginWIndex,beginTIndex,finalIndex);
	 
	 int endWIndex=findNext(beginWIndex);//bodyText.indexOf("Page Navigation");
	 int endSIndex=findNext(beginSIndex);
	 int endTIndex=bodyText.indexOf("View on Twitter");
 	
	 System.out.println("start"+beginTIndex+"end"+endTIndex);
	 String searchText=new String("");
	 String twitterText=new String("");
	 String webText= new String("");
		
	 searchText=bodyText.substring(beginSIndex, endSIndex);
	 if(beginTIndex>0) {
		 twitterText=bodyText.substring(beginTIndex, endTIndex);
	 }
   	 else twitterText="No Twitter feed on main page";
	 webText=bodyText.substring(beginWIndex, endWIndex);
	 System.out.println("\n~/~/~/~/~/SEARCH RESULTS~/~/~/~/~/\n"+searchText);
	 System.out.println("\n~/~/~/~/~/TWITTER FEED~/~/~/~/~/\n"+twitterText);
	 System.out.println("\n~/~/~/~/~/WEB RESULTS~/~/~/~/~/\n"+webText);
	 
	 f = new JFrame();
	 subPanel= new JPanel();
	 subPanel2= new JPanel();
	 subPanel2a= new JPanel();
	 
	 subPanel3= new JPanel();
	 
	 jTA1=new JTextArea(searchText);
	 //jTASearch=new JTextArea(searchText);
	 
	 jTA2=new JTextArea(twitterText);
	 jTA3=new JTextArea(webText);
	 	 
	 //JScrollPane jS1=new JScrollPane(jTA2);
	 jS1=new JScrollPane(jTA1);
	 jS2=new JScrollPane(jTA2);
	 jS3=new JScrollPane(jTA3);
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
     Border gridBorderSearch = BorderFactory.createTitledBorder(line, "Top Stories");
     Border grid3Border = BorderFactory.createTitledBorder(line, "Web results");
     Border gridBorderT = BorderFactory.createTitledBorder(line, "Twitter results");
     Border gridBorderS = BorderFactory.createTitledBorder(line, "Search String");
     
     textField1 = new JTextField(searchString);
     //JTextField textField2 = new JTextField(searchText);
     submitButton = new JButton("Submit");
	 //subPanel2.setLayout(new GridLayout(3,1));
	 subPanel2.setLayout(new GridBagLayout());
	 
	 subPanel2a.setLayout(new GridLayout(1,2)); 
	 
	 constraints = new GridBagConstraints();
     constraints.gridx = 0;
     constraints.gridy = 0;
     constraints.gridwidth = 3;
     constraints.anchor = GridBagConstraints.NORTHWEST;
     constraints.insets = new Insets(12, 12, 0, 0);
     textField1.setSize(600,80);
     //textField1.setBounds(5, 5, 400, 80);
     subPanel2a.add(textField1);
     subPanel2a.add(new TextField("                     "));
     
     subPanel2a.setSize(700,100);
     subPanel2.add(subPanel2a,constraints);
	 
     constraints.gridx = 3;
     constraints.gridy = 0;
     constraints.gridwidth = GridBagConstraints.REMAINDER;
     constraints.anchor = GridBagConstraints.NORTHEAST;
     constraints.insets = new Insets(12, 12, 0, 0);
     subPanel2.add(submitButton,constraints);
     subPanel2.setBorder(gridBorderS);
     
     constraints.gridx = 0;
     constraints.gridy = 1;
     constraints.gridwidth = 4;
     constraints.anchor = GridBagConstraints.NORTHWEST;
     constraints.insets = new Insets(12, 12, 0, 0);
     //subPanel2.add(jS1);//,constraints);
     //subPanel3.add(jS3);

     subPanel.add(subPanel2);
	 subPanel.add(jS3);
     subPanel3.setLayout(new GridLayout(2,1)); 
     subPanel3.add(jS1);
     subPanel3.add(jS2);
     
	 f.add(subPanel);
     f.add(subPanel3);
     
     jS1.setBorder(gridBorderSearch);
     
     //subPanel2.setBorder(grid2Border);
     jS3.setBorder(grid3Border);
     jS2.setBorder(gridBorderT);
     
	 
	 //f.add(jTP1);
	 f.setSize(1800,1200);
	 f.setVisible(true);
	 jS1.setSize(900,400);
	 d1=jS1.getSize();
	 System.out.println("JS1 dimensions after setSize: "+d1.getWidth()+", "+d1.getHeight());
	 resizeSearchBox();
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
     f.addComponentListener(new ComponentAdapter() {
    	    public void componentResized(ComponentEvent componentEvent) {
    	    	resizeSearchBox();
    	    }
    	});
     
     submitButton.addActionListener(buttonListener);
     started=true;
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
    ActionListener buttonListener=new ActionListener(){
        public void actionPerformed(ActionEvent evt){
            if(evt.getSource()==submitButton){
                if(started==true) {
                	searchString=textField1.getText();
                	System.out.println("New Search string:"+searchString);
                	reSubmit();
            	}    
            }
        }
    };
    private void resizeSearchBox() {
   	    d1=jS1.getSize();
	    System.out.println("JS1 dimensions after componentResize: "+d1.getWidth()+", "+d1.getHeight());
	    int hSearchBox=textField1.getHeight();
	    textField1.setSize(600,hSearchBox);
	    subPanel2a.setSize(700,100);
	    System.out.println("textField1 dimensions after componentResize: "+textField1.getWidth()+", "+textField1.getHeight());
	    System.out.println("submitButton dimensions after componentResize: "+submitButton.getWidth()+", "+submitButton.getHeight());

	    if(d1.getWidth()<200 || d1.getHeight()<200) {
	    	jTA1.setSize(550,250);
	    	jS1.setSize(600,300); 
	    	
    	}
	    //jS1.validate();
	    //jTA1.validate();
	    textField1.validate();
	    subPanel2.validate();

    }
    private void reSubmit() {
    	element = driver.findElement(By.name("q"));
    	// Clear the existing text value
    	element.clear();
    	// Enter something to search for
    	element.sendKeys(searchString);
    	// Now submit the form
    	element.submit();
    	bodyElement=driver.findElement(By.tagName("body"));
   	 	String bodyText=bodyElement.getText();
   	 	beginSIndex=bodyText.indexOf("Search Results");
   	 	beginTIndex=bodyText.indexOf("Twitter Results");
   	 	beginWIndex=bodyText.indexOf("Web results");
   	 	finalIndex=bodyText.indexOf("Page Navigation");
   	    System.out.println(beginSIndex+" "+beginTIndex+" "+beginWIndex+" "+finalIndex+" end "+bodyText.length());
   	 	searchList=Arrays.asList(beginSIndex,beginWIndex,beginTIndex,finalIndex);

   	 	int endWIndex=findNext(beginWIndex);//bodyText.indexOf("Page Navigation");
   	 	int endSIndex=findNext(beginSIndex);
   	 	int endTIndex=bodyText.indexOf("View on Twitter");
   	 	System.out.println("start"+beginTIndex+"end"+endTIndex);
   	 	String searchText=new String("");
   	 	String twitterText=new String("");
   	 	String webText= new String("");
   		
   	 	searchText=bodyText.substring(beginSIndex, endSIndex);
   	 	if(beginTIndex>0) {
   	 		twitterText=bodyText.substring(beginTIndex, endTIndex);
   	 	}
   	 	else twitterText="No Twitter feed on main page";
   	 	webText=bodyText.substring(beginWIndex, endWIndex);
   	 	System.out.println("\n~/~/~/~/~/RESUBMIT SEARCH RESULTS~/~/~/~/~/\n"+searchText);
   	 	System.out.println("\n~/~/~/~/~/RESUBMIT TWITTER FEED~/~/~/~/~/\n"+twitterText);
   	 	System.out.println("\n~/~/~/~/~/RESUBMIT WEB RESULTS~/~/~/~/~/\n"+webText);
   	 	
   	 	jTA1.setText(searchText);//=new JTextArea(searchText);
   	 	jTA2.setText(twitterText);//=new JTextArea(searchText);
   	 	jTA3.setText(webText);//=new JTextArea(searchText);

   	 	resizeSearchBox();
   	 	jTA1.validate();
   	 	jTA2.validate();
   	 	jTA3.validate();

   	 	textField1.validate();
   	 	submitButton.validate();
   	 	jS1.validate();
   	 	subPanel2.validate();
   	 	subPanel.validate();
   	 	f.validate();
   	 	//System.out.println("new search field"+jTA1.getText());
   	 	
   	 	//jS1=new JScrollPane(jTA1);
   	 	//subPanel2.removeAll();
   	 	//subPanel2.add(textField1,constraints);
   	 	//subPanel2.add(submitButton,constraints);
   	 	//subPanel2.add(jS1);
        //subPanel2.add(jS1,constraints);
    }
    private int findNext(int cur) {
    	int next=cur;
    	Collections.sort(searchList);
    	boolean done=false;
    	int i=0;
    	while(done==false && i<searchList.size()) {
    		if(searchList.get(i)>cur) {
    			next=searchList.get(i);
    			done=true;
    		}else i++;
    	}
    	return next;
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
