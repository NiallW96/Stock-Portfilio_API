# Stock-Portfilio_API
This is an api created for a techincal exercise for WiPro.

This api can be tested by simply running as a normal java program, i.e. compile and the run. 
The api has the capability for add/deleting and updating stocks to a portfolio. 
Please note that in this case due to the lack of a connected database the portfolio is represented by a HashMap that will store the stock code and number of shares that the user owns. The portfolio is non-consistent.

To api is also able to request information about a stock relating to its records from a weekly, monthly or daily timeframe. This returns the last 100 datapoints for those time periods. The api also has a search function that can earch for stocks based on keywords passed into the function. There is also an ability to get a stock quote which return basic stock information from today. These functions return JSON formatted strings containing the necessary data. 

When running a demo of this api please follow these instructions: 

1. When the demo is run you will be prompted to enter in a command, these are the following commands and their purpose:
a (add stock to portfolio), d (delete stock), u (update stock), q (get stock quote), s (search for stock), i (get stock information), g (get current portfolio).

2. When a command has been entered you will then be prompted to enter in a stock and maybe one other parameter. The prompt will display the parameters needed to be entered. 
 
3. All parameters need to be on the same line and will be split by a single whitespace. 

4. If you have entered i please enter the following commands for the time period:
 d (daily), da (daily adjusted), w (weekly), wa (weekly adjusted), m (monthly), ma (monthly adjusted). 
 All integer values must be greater than or equal to zero. 

Once tested please enter quit to exit the demo. 

# Explaning the API Design
This api is designed to allow the web interface to perform basic opertaions on the portfolio, i.e add/update/delete which are standard operations and needed to be included to allow the pofolio to operate. 
This api also allows the interface to request information that will be returned in JSON format. This information comes from the recommded API and is not parsed or filtered due to the fact that it is unknown what information the front end user will need and thus it returns all the information it recieves. This is shown as a String in the demo.

The thought process going into the design involved, thinking about the basic operations that would need to be performed on a portfolio as well as considering what operations a user would want to perform before purchasing stock. This was the reason for introducing the capbility to search, research and get stock quotes into the api. 

If I had more time I would implement better error handling into the API as well as logging feature so that records could be kept if it goes wrong. Also I would add more information to be stored into the portfolio which right now only consists of the stock code and the nunber of shares owned.




