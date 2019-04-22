# WiPro_Stock-Portfilio_API
This is an api created for a techincal exercise for WiPro.

This api can be tested by simply running as a normal java program, i.e. compile and the run. 
The api has the capability for add/deleting and updating stocks to a portfolio. 
Please note that in this case due to the lack of a connected database the portfolio is represented by a HashMap that will store the stock code and number of shares that the user owns. The portfolio is non-consistent.

To api is also able to request information about a stock relating to its records from a weekly, monthly or daily timeframe. This returns the last 100 datapoints for those timeperiods. The api also has a search function that can earch for stocks based on keywords passed into the function. There is also an ability to get a stock quote which return basic stock information from today. These functions return JSON formatted strings containing the necessary data. 

When running a demo of this api please follow these instructions: 

1. When the demo is run you will be prompted to enter in a command, these are the following commands and their purpose:
a (add stock to portfolio), d (delete stock), u (update stock), q (get stock quote), s (search for stock), i (get stock information), g (get current portfolio).

When a command has been entered you will then be prompted to enter in a stock and maybe one other parameter. The prompt will display the parameters needed to be entered. 
 
All parameters need to be on the same line and will be split by a single whitespace. 
If you have entered i please enter the following commands for the time period:
d (daily), da (daily adjusted), w (weekly), wa (weekly adjusted), m (monthly), ma (monthly adjusted). 
All integer values must be greater than or equal to zero. 

Once tested please enter quit to exit the demo. 


