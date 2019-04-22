import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

//This is a basic prototype api for a stock portfolio.
public class StockPortfolio {
	
	public HashMap<String, Integer> portfolio;
	public static final String APIKEY = "T0VMBIYKZWJOW2J8";
	
	//This is the testing code to allow the user to test all the functionality.
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StockPortfolio stockPortfolio = new StockPortfolio();
		Scanner scanner = new Scanner(System.in);
		String input = new String();
		String info = new String();
		boolean success = false;
		
		do{
			try{
				System.out.println("Please enter an activity or enter quit to quit.");
				input = scanner.nextLine();
				String[] parameters;
				switch(input){
				case "a":
					System.out.println("Please enter the Stock code and the number of shares bought");
					input = scanner.nextLine();
					parameters = input.split(" ");
					success = stockPortfolio.addStock(parameters[0], Integer.parseInt(parameters[1]));
					System.out.println(success ? "Success" : "Failure");
					break;
				case "d":
					System.out.println("Please enter stock code of stock to delete.");
					input = scanner.nextLine();
					stockPortfolio.deleteStock(input);
					break;
				case "s":
					System.out.println("Please enter stock code you want to search for.");
					input = scanner.nextLine();
					info = stockPortfolio.searchStock(input);
					System.out.println(info);
					break;
				case "q":
					System.out.println("Please enter stock code you want a quote for.");
					input = scanner.nextLine();
					info = stockPortfolio.quote(input);
					System.out.println(info);
					break;
				case "u":
					System.out.println("Please enter stock code and new share amount.");
					input = scanner.nextLine();
					parameters = input.split(" ");
					success = stockPortfolio.updateStock(parameters[0], Integer.parseInt(parameters[1]));
					System.out.println(success ? "Success" : "Failure");
					break;
				case "g":
					System.out.println(stockPortfolio.getPortfolio().toString());
					break;
				case "i":
					System.out.println("Please enter the stock code and the time period you wish to view.");
					input = scanner.nextLine();
					parameters = input.split(" ");
					String series = new String();
					switch(parameters[1]){
					case "d":
						series = TimeSeries.DAILY.getSeries();
						break;
					case "da":
						series = TimeSeries.DAILY_ADJUSTED.getSeries();
						break;
					case "w":
						series = TimeSeries.WEEKLY.getSeries();
						break;
					case "wa":
						series = TimeSeries.WEEKLY_ADJUSTED.getSeries();
						break;
					case "m":
						series = TimeSeries.MONTHLY.getSeries();
						break;
					case "ma":
						series = TimeSeries.MONTHLY_ADJUSTED.getSeries();
						break;
					default:
						series = TimeSeries.DAILY.getSeries();
					}
					System.out.println(stockPortfolio.information(parameters[0], series));
					break;
				}
			}
			catch(Exception e){
				System.out.println("There was an error, please try again");
			}
		}while(input.equals("quit") == false);
		scanner.close();
		

	}
	
	//Initialise portfolio
	public StockPortfolio(){
		portfolio = new HashMap<String, Integer>();
	}
	
	//Add a stock to your portfolio along with the number of shares bought, return true if successful.
	public Boolean addStock(String stockCode, int numberOfShares){
		//add stock name and 
		if(portfolio.containsKey(stockCode) == false && numberOfShares >= 0){
			portfolio.put(stockCode, numberOfShares);
			return true;
		}
		else return false;
	}
	
	//Remove stock from portfolio.
	public void deleteStock(String stockCode){
		portfolio.remove(stockCode);
	}
	
	//Update the number of shares the owner has for a stock they own. 
	public Boolean updateStock(String stockCode, int newValue){
		if(portfolio.containsKey(stockCode) == true && newValue >= 0){
			portfolio.put(stockCode, newValue);
			return true;
		}
		else return false;
	}
	
	//Search for stocks, based on an inputed stock code.
	public String searchStock(String stockCode) throws Exception{
		try {
			URL url = new URL("https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=" + stockCode + "&apikey=" + APIKEY);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection(); 
			connection.setRequestMethod("GET");
			//connection.setRequestProperty("Authentication", "Basic " + APIKEY);
			
			BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String output = new String();
			while((output = bf.readLine()) != null){
				response.append(output);
			}
			bf.close();
			return response.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	//Return a copy of the current portfolio.
	public HashMap<String, Integer> getPortfolio(){
		return portfolio;
	}
	
	//Get daily/weekly/monthly information about chosen stock
	public String information(String stockCode, String function) throws Exception{
		try {
			URL url = new URL("https://www.alphavantage.co/query?function="+function + "&symbol=" + stockCode + "&apikey=" + APIKEY);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection(); 
			connection.setRequestMethod("GET");
			
			BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String output = new String();
			while((output = bf.readLine()) != null){
				response.append(output);
			}
			bf.close();
			return response.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	//Get a stock quote from a chosen stock
	public String quote(String stockCode)throws Exception{
		try {
			URL url = new URL("https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + stockCode + "&apikey=" + APIKEY);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection(); 
			connection.setRequestMethod("GET");
			
			BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String output = new String();
			while((output = bf.readLine()) != null){
				response.append(output);
			}
			bf.close();
			return response.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public enum TimeSeries{
		DAILY("TIME_SERIES_DAILY"),
		DAILY_ADJUSTED("TIME_SERIES_DAILY_ADJUSTED"),
		WEEKLY("TIME_SERIES_WEEKLY"),
		WEEKLY_ADJUSTED("TIME_SERIES_WEEKLY_ADJUSTED"),
		MONTHLY("TIME_SERIES_MONTHLY"),
		MONTHLY_ADJUSTED("TIME_SERIES_MONTHLY_ADJUSTED");
		
		private String timeSeries;
		
		TimeSeries(String series){
			timeSeries = series;
		}
		
		public String getSeries(){
			return timeSeries;
		}
	}

}
