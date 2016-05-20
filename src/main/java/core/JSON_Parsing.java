package core;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
//import com.gargoylesoftware.htmlunit.BrowserVersion;



public class JSON_Parsing 
{
 

	public static String url_item_1 = "http://www.amazon.com/Mountaintop-Outdoor-Waterproof-Climbing-Backpacks/dp/B00ZHIHQD4/ref=sr_1_99?s=outdoor-recreation&ie=UTF8&qid=1463525377&sr=1-99-spons&psc=1";    	
	public static String url_item_2 = "http://www.amazon.com/Ohuhu-Degree-Camping-Sleeping-Carrying/dp/B015DVHSOQ/ref=lp_2204498011_1_23?s=outdoor-recreation&ie=UTF8&qid=1463543339&sr=1-23";
	public static String url_item_3 = "http://www.amazon.com/gp/product/B014SCC3GO/ref=s9_zgift_hd_bw_bEKyT_g468_i2?pf_rd_m=ATVPDKIKX0DER&pf_rd_s=merchandised-search-9&pf_rd_r=1HED11DEPBWDRDJGTQDV&pf_rd_t=101&pf_rd_p=8367108d-16d3-5b14-b340-5115b1de99d0&pf_rd_i=3417221";
	public static String url_item_4 = "http://www.amazon.com/Native-Trango-Interchangeable-Polarized-Sunglasses/dp/B006WFTNNC/ref=sr_1_9?m=ATVPDKIKX0DER&s=sports-and-fitness&ie=UTF8&qid=1463545636&sr=1-9";
	public static String url_item_5 = "http://www.amazon.com/Nikon-Coolpix-L330-Digital-Camera/dp/B00HQDBLDO/ref=sr_1_5?s=photo&ie=UTF8&qid=1463546317&sr=1-5&refinements=p_89%3ANikon%2Cp_n_feature_three_browse-bin%3A10705321011|10705322011%2Cp_n_feature_seven_browse-bin%3A6792436011";
		
	public static  String item_01_name = "Casual Backpack";
	public static  String item_02_name = "Mummy Camping Sleeping Bag";
	public static  String item_03_name = "Salomon Womens Cross Country Skis";
	public static  String item_04_name = "Trango Polarized Sunglasses";
	public static  String item_05_name = "Nikon Coolpix L330 Digital Camera ";
		
	public static String item_1xpath = null;	
 	public static String HostName = null;
	public static String queryHost = null;
	public static String queryService1 = null;
	public static String queryService2 = null;
	public static String UScode = null;	
	
	public static final String element_01 = "geoplugin_countryName";
	public static final String element_02 = "geoplugin_currencyCode";
	public static final String element_03 = "Name";
	public static final String element_04 = "Rate";
	

 	public static String ipAddr_1 =	"88.191.179.56";
 	public static String ipAddr_2 = "61.135.248.220";
 	public static String ipAddr_3 =	"92.40.254.196";
 	public static String ipAddr_4 =	"93.183.203.67";
 	public static String ipAddr_5 =	"213.87.141.36";
	

	public static void main(String[] args) throws IOException {


			Properties property = new Properties();
			property.load(new FileInputStream("./src/main/resources/DataSource.properties"));
			
			item_1xpath = property.getProperty("item_1xpath");		
			HostName = property.getProperty("HostName");
			queryHost = property.getProperty("queryHost");
			queryService1 = property.getProperty("queryService1");
			queryService2 = property.getProperty("queryService2");
		    UScode = property.getProperty("UScode");		
		    
	    
     /*
	    WebDriver driver = new HtmlUnitDriver();
	    ((HtmlUnitDriver)driver).setJavascriptEnabled(true);
	    HtmlUnitDriver driver =new HtmlUnitDriver(BrowserVersion.FIREFOX_38);
	 */    
	 		WebDriver driver = new FirefoxDriver();
	 		
		    Double tem_01_usd_price = GetOriginPrice_of_Item(url_item_1 , driver, item_1xpath );
		    Double tem_02_usd_price = GetOriginPrice_of_Item(url_item_2 , driver, item_1xpath );
		    Double tem_03_usd_price = GetOriginPrice_of_Item(url_item_3 , driver, item_1xpath );
		    Double tem_04_usd_price = GetOriginPrice_of_Item(url_item_4 , driver, item_1xpath );
		    Double tem_05_usd_price = GetOriginPrice_of_Item(url_item_5 , driver, item_1xpath );	
			driver.quit();		 
		
						
		 Caller caller1 = new Caller(); // for each IP 
		 caller1.IP = ipAddr_1; //from csvFile
		 ParserResults webResults1 = JSON_Parsing.runParser(HostName, caller1); //runPlugin() returns values into object "webResults1" of class PluginResult
	//	 System.out.println("Country: " + webResults1.countryName);
	//	 System.out.println("Currency: " + webResults1.currencyCode);
		
	     Caller caller2 = new Caller();
		 caller2.IP = ipAddr_2; //from csvFile
		 ParserResults webResults2 = JSON_Parsing.runParser(HostName, caller2);
		
		 Caller caller3 = new Caller();
		 caller3.IP = ipAddr_3; //from csvFile
		 ParserResults webResults3 = JSON_Parsing.runParser(HostName, caller3);
			
		 Caller caller4 = new Caller();
		 caller4.IP = ipAddr_4; //from csvFile
		 ParserResults webResults4 = JSON_Parsing .runParser(HostName, caller4);
				
	     Caller caller5 = new Caller();
		 caller5.IP = ipAddr_5; //from csvFile
		 ParserResults webResults5 = JSON_Parsing .runParser(HostName, caller5);

		
//Find out currency exchange rate, using following JSON Web Service:
		Double currancyRate_EUR = rate_Parser(webResults1.currencyCode);
		Double currancyRate_CNY = rate_Parser(webResults2.currencyCode);
		Double currancyRate_GBP = rate_Parser(webResults3.currencyCode);
	    Double currancyRate_UAH = rate_Parser(webResults4.currencyCode);
		Double currancyRate_RUB = rate_Parser(webResults5.currencyCode);
		 
		 
		 
// Converts prices from US Dollar to local currenciese for each Item
		 
		 Double item_01_eur_price = getLocal_ItemPrice(tem_01_usd_price, currancyRate_EUR);
		 Double item_01_cny_price = getLocal_ItemPrice(tem_01_usd_price, currancyRate_CNY);
		 Double item_01_gbp_price = getLocal_ItemPrice(tem_01_usd_price, currancyRate_GBP);
		 Double item_01_uah_price = getLocal_ItemPrice(tem_01_usd_price, currancyRate_UAH);
		 Double item_01_rub_price = getLocal_ItemPrice(tem_01_usd_price, currancyRate_RUB);
	
		 Double item_02_eur_price = getLocal_ItemPrice(tem_02_usd_price, currancyRate_EUR);
		 Double item_02_cny_price = getLocal_ItemPrice(tem_02_usd_price, currancyRate_CNY);
		 Double item_02_gbp_price = getLocal_ItemPrice(tem_02_usd_price, currancyRate_GBP);
		 Double item_02_uah_price = getLocal_ItemPrice(tem_02_usd_price, currancyRate_UAH);
		 Double item_02_rub_price = getLocal_ItemPrice(tem_02_usd_price, currancyRate_RUB);

		 Double item_03_eur_price = getLocal_ItemPrice(tem_03_usd_price, currancyRate_EUR);
		 Double item_03_cny_price = getLocal_ItemPrice(tem_03_usd_price, currancyRate_CNY);
		 Double item_03_gbp_price = getLocal_ItemPrice(tem_03_usd_price, currancyRate_GBP);
		 Double item_03_uah_price = getLocal_ItemPrice(tem_03_usd_price, currancyRate_UAH);
		 Double item_03_rub_price = getLocal_ItemPrice(tem_03_usd_price, currancyRate_RUB);

		 Double item_04_eur_price = getLocal_ItemPrice(tem_04_usd_price, currancyRate_EUR);
		 Double item_04_cny_price = getLocal_ItemPrice(tem_04_usd_price, currancyRate_CNY);
		 Double item_04_gbp_price = getLocal_ItemPrice(tem_04_usd_price, currancyRate_GBP);
		 Double item_04_uah_price = getLocal_ItemPrice(tem_04_usd_price, currancyRate_UAH);
		 Double item_04_rub_price = getLocal_ItemPrice(tem_04_usd_price, currancyRate_RUB);

		 Double item_05_eur_price = getLocal_ItemPrice(tem_05_usd_price, currancyRate_EUR);
		 Double item_05_cny_price = getLocal_ItemPrice(tem_05_usd_price, currancyRate_CNY);
		 Double item_05_gbp_price = getLocal_ItemPrice(tem_05_usd_price, currancyRate_GBP);
		 Double item_05_uah_price = getLocal_ItemPrice(tem_05_usd_price, currancyRate_UAH);
		 Double item_05_rub_price = getLocal_ItemPrice(tem_05_usd_price, currancyRate_RUB);
		 
System.out.println("Item_01: " + item_01_name + "; US Price: " + tem_01_usd_price + "; Country: " + webResults1.countryName + "; Local Price: " + item_01_eur_price+"_"+webResults1.currencyCode);		 
System.out.println("Item_01: " + item_01_name + "; US Price: " + tem_01_usd_price + "; Country: " + webResults2.countryName + "; Local Price: " + item_01_cny_price+"_"+webResults2.currencyCode);	    	
System.out.println("Item_01: " + item_01_name + "; US Price: " + tem_01_usd_price + "; Country: " + webResults3.countryName + "; Local Price: " + item_01_gbp_price+"_"+webResults3.currencyCode);
System.out.println("Item_01: " + item_01_name + "; US Price: " + tem_01_usd_price + "; Country: " + webResults4.countryName + "; Local Price: " + item_01_uah_price+"_"+webResults4.currencyCode);
System.out.println("Item_01: " + item_01_name + "; US Price: " + tem_01_usd_price + "; Country: " + webResults5.countryName + "; Local Price: " + item_01_rub_price+"_"+webResults5.currencyCode);
System.out.println("======================================================================================================================");		 

System.out.println("Item_02: " + item_02_name + "; US Price: " + tem_02_usd_price + "; Country: " + webResults1.countryName + "; Local Price: " + item_02_eur_price+"_"+webResults1.currencyCode);		 
System.out.println("Item_02: " + item_02_name + "; US Price: " + tem_02_usd_price + "; Country: " + webResults2.countryName + "; Local Price: " + item_02_cny_price+"_"+webResults2.currencyCode);	    	
System.out.println("Item_02: " + item_02_name + "; US Price: " + tem_02_usd_price + "; Country: " + webResults3.countryName + "; Local Price: " + item_02_gbp_price+"_"+webResults3.currencyCode);
System.out.println("Item_02: " + item_02_name + "; US Price: " + tem_02_usd_price + "; Country: " + webResults4.countryName + "; Local Price: " + item_02_uah_price+"_"+webResults4.currencyCode);
System.out.println("Item_02: " + item_02_name + "; US Price: " + tem_02_usd_price + "; Country: " + webResults5.countryName + "; Local Price: " + item_02_rub_price+"_"+webResults5.currencyCode);
System.out.println("======================================================================================================================");	

System.out.println("Item_03: " + item_03_name + "; US Price: " + tem_03_usd_price + "; Country: " + webResults1.countryName + "; Local Price: " + item_03_eur_price+"_"+webResults1.currencyCode);		 
System.out.println("Item_03: " + item_03_name + "; US Price: " + tem_03_usd_price + "; Country: " + webResults2.countryName + "; Local Price: " + item_03_cny_price+"_"+webResults2.currencyCode);	    	
System.out.println("Item_03: " + item_03_name + "; US Price: " + tem_03_usd_price + "; Country: " + webResults3.countryName + "; Local Price: " + item_03_gbp_price+"_"+webResults3.currencyCode);
System.out.println("Item_03: " + item_03_name + "; US Price: " + tem_03_usd_price + "; Country: " + webResults4.countryName + "; Local Price: " + item_03_uah_price+"_"+webResults4.currencyCode);
System.out.println("Item_03: " + item_03_name + "; US Price: " + tem_03_usd_price + "; Country: " + webResults5.countryName + "; Local Price: " + item_03_rub_price+"_"+webResults5.currencyCode);

System.out.println("======================================================================================================================");	
System.out.println("Item_04: " + item_04_name + "; US Price: " + tem_04_usd_price + "; Country: " + webResults1.countryName + "; Local Price: " + item_04_eur_price+"_"+webResults1.currencyCode);		 
System.out.println("Item_04: " + item_04_name + "; US Price: " + tem_04_usd_price + "; Country: " + webResults2.countryName + "; Local Price: " + item_04_cny_price+"_"+webResults2.currencyCode);	    	
System.out.println("Item_04: " + item_04_name + "; US Price: " + tem_04_usd_price + "; Country: " + webResults3.countryName + "; Local Price: " + item_04_gbp_price+"_"+webResults3.currencyCode);
System.out.println("Item_04: " + item_04_name + "; US Price: " + tem_04_usd_price + "; Country: " + webResults4.countryName + "; Local Price: " + item_04_uah_price+"_"+webResults4.currencyCode);
System.out.println("Item_04: " + item_04_name + "; US Price: " + tem_04_usd_price + "; Country: " + webResults5.countryName + "; Local Price: " + item_04_rub_price+"_"+webResults5.currencyCode);

System.out.println("======================================================================================================================");	
System.out.println("Item_05: " + item_05_name + "; US Price: " + tem_05_usd_price + "; Country: " + webResults1.countryName + "; Local Price: " + item_05_eur_price+"_"+webResults1.currencyCode);		 
System.out.println("Item_05: " + item_05_name + "; US Price: " + tem_05_usd_price + "; Country: " + webResults2.countryName + "; Local Price: " + item_05_cny_price+"_"+webResults2.currencyCode);	    	
System.out.println("Item_05: " + item_05_name + "; US Price: " + tem_05_usd_price + "; Country: " + webResults3.countryName + "; Local Price: " + item_05_gbp_price+"_"+webResults3.currencyCode);
System.out.println("Item_05: " + item_05_name + "; US Price: " + tem_05_usd_price + "; Country: " + webResults4.countryName + "; Local Price: " + item_05_uah_price+"_"+webResults4.currencyCode);
System.out.println("Item_05: " + item_05_name + "; US Price: " + tem_05_usd_price + "; Country: " + webResults5.countryName + "; Local Price: " + item_05_rub_price+"_"+webResults5.currencyCode);

		
	
	} //main method



	 /** Opens Item url and finds Item USPrice using WebDriver"
		 * Opens Item url
		 * Find Item_price by xpath
		 * Convert price as String to Double
		 * 
		 */
	   public static Double GetOriginPrice_of_Item(String url_item, WebDriver driver, String item_xpath) throws NumberFormatException {
			driver.get(url_item);
			String dollar_sign = java.util.regex.Matcher.quoteReplacement("$"); 	//conversion "$" into String "$"              
			String item_price = driver.findElement(By.xpath(item_xpath)).getText();    //output: $39.99

			String price_trimed = item_price.replaceAll(dollar_sign, "");  //trimming $ 
			Double decimal_price = Double.parseDouble(price_trimed);    //conversion price as String to Double
			return decimal_price;		
		}
		
	 /** Parsing http://www.geoplugin.net/json.gp?ip=  WEB_Service with different IP-addresses
		**  class "Caller" contains  IP(address) field;
		**  class "ParserResults" contains:  String countryName;
		**                                   String currencyCode;
		     Method  runParser( host,   IP)  returns object "pluginResults" (var inside method) with type "ParserResults" class		    
	     */
	   public static ParserResults runParser (String serviceLocation, Caller caller) throws IOException {    
			URL url = new URL(serviceLocation + caller.IP);
			InputStream is = url.openStream();
			JsonParser parser = Json.createParser(is);
		    ParserResults pluginResults = new ParserResults();
			while (parser.hasNext()) {	
				Event e = parser.next();		
				if (e == Event.KEY_NAME) {		
					switch (parser.getString()) {	
					case JSON_Parsing .element_01:
						parser.next();
						pluginResults.countryName = parser.getString();
					break;	
					case JSON_Parsing .element_02:
						parser.next();
						pluginResults.currencyCode =parser.getString();	
					break;
					}			
			   }    	
	        }
			return pluginResults;
	    }

	 /** Defines Query Url for JSON Web Service with particular currancy
		 * 
		 */
	   public static String getQueryUrl(String queryHost,String queryService1,String queryService2,String UScode,String countryCode) {
			String Host = queryHost;
			String Service1 = queryService1;
			String Service2 = queryService2;	
			String us_code = UScode;	
			String code = countryCode;
			String currencyPair = us_code +code;	
			String big_url = Host+Service1+currencyPair+Service2;
			return big_url;
		}
	   
	 /** To find out currency exchange rate, using following JSON Web Service:
		 */
	   public static Double rate_Parser(String country_code) throws MalformedURLException, IOException {
			String string_rate = "";
			URL url = new URL(getQueryUrl(queryHost, queryService1, queryService2, UScode, country_code ));
			InputStream is = url.openStream();
			JsonParser parser = Json.createParser(is);

			while (parser.hasNext()) {	
				Event e = parser.next();		
				if (e == Event.KEY_NAME) {		
					switch (parser.getString()) {	
					case element_03:
						parser.next();
						string_rate = parser.getString();
					break;	
					case element_04:
						parser.next();
					
						string_rate = parser.getString();
					break;
					} //switch
					}//if			
			   } //while   	
				return Double.parseDouble(string_rate);
		}
	   	   
	 /** Method calculates the local price if any Item and aplyes Bunkers Rounding rule
	 */
	   public static Double getLocal_ItemPrice(Double item_usd_price, Double currrancyRate) {
		Double item_local_price = item_usd_price  * currrancyRate;
		BigDecimal round_item_local_price = new BigDecimal(item_local_price);
		round_item_local_price =round_item_local_price.setScale(2, RoundingMode.HALF_EVEN); //Bunkers Rounding rule - where arg (2) is number decimal digints after (.)
		return round_item_local_price.doubleValue();
   	      }

	   	
} //main class
