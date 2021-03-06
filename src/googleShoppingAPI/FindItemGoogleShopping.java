package googleShoppingAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Francois KY
 * Eurecom DSMWare Project 2013 - Andrea SMETKO - Francois KY
 * Professor Y.ROUDIER
 *
 */

public class FindItemGoogleShopping {
	
	/**
	 * Returns a string containing the result of the HTTP request given a specified URL.
	 * The urlString argument should be string.
	 * 
	 * @param urlString
	 * @throws Exception
	 */
	private static String readUrl(String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read); 

			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}
	
	/**
	 * Returns a given list of string containing the name and the price of each find items.
	 * The outlet name is hardcoded and the currency as well.
	 * This method is fitted JSON parser for BestBuy API.
	 * 
	 * 
	 * @param keywords string matching the user's input
	 * @return a list of string as the result of Google Shopping API lookup
	 * @throws Exception
	 */

	public static List<String> findItemGoogleShopping (String keywords) throws Exception{
		List<String> res = new ArrayList<String>();
	try {
			JSONObject json = new JSONObject(readUrl("https://www.googleapis.com/shopping/search/v1/public/products?key=AIzaSyA60ZmMU9vLguggfj35Wp2onuPfM894TWI&country=US&q=" + keywords.replace(" ", "+") ));

			JSONArray items = json.getJSONArray("items");
			

			for (int i = 0; i < items.length(); i++) {  
				JSONObject item = items.getJSONObject(i);
				JSONObject products = item.getJSONObject("product");
				JSONArray inventories = products.getJSONArray("inventories");
				JSONObject author = products.getJSONObject("author");
				JSONObject prices = (JSONObject) inventories.get(0);
				
				res.add(author.get("name") + " - " + products.get("title").toString() + " - " +  prices.get("price") + " - "+ prices.get("currency"));
				
			}
		} catch (JSONException e) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			System.err.println(dateFormat.format(cal.getTime()) + "Something went wrong with the JSON response from Google Shopping API.");
		}
	
	return res;
	}



}