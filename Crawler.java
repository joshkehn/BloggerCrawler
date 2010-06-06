import java.net.*;
import java.io.*;
import java.util.*;

public abstract class Crawler {
	
	LinkedList<URL> URLQueue;
	int processedURLs;
	
	//Currently an arraylist of string arraylists.
	//Will be replaced with different data type later.
	ArrayList<ArrayList<String>> data;
	
	public Crawler(URL startingURL) {
		URLQueue.add(startingURL);
		processedURLs = 0;
	}
		
	public String getCurrentURL() {
		return URLQueue.peek().toString();
	}
	
	public int getProcessedURLs() {
		return processedURLs;
	}
	
	public ArrayList<ArrayList<String>> getData() {
		return data;
	}
	
	public ArrayList<String> getMarkup (URL pageURL) throws IOException {
				
		InputStreamReader pageStream = new InputStreamReader(pageURL.openStream());
		
		BufferedReader pageBuffer = new BufferedReader(pageStream);
		
		ArrayList<String> pageMarkup = new ArrayList<String>();
		
		do {
			pageMarkup.add(pageBuffer.readLine());
		} while (pageMarkup.get(pageMarkup.size()-1)!=null);
				
		
		return pageMarkup;
	}
	
	public void step() throws IOException {
		//Get markup from front-of-queue URL
		ArrayList<String> markup = getMarkup(URLQueue.poll());
		
		//Process the markup
		data = process(markup);
		processedURLs++;
		
		//Get new URLs and add them to the queue
		LinkedList<URL> newURLS = new LinkedList<URL>();
		
		for (URL c:newURLS) {
			URLQueue.add(c);
		}
		
	}
	
	public abstract ArrayList<ArrayList<String>> process(ArrayList<String> markup);
	
	public abstract ArrayList<URL> getNewURLs(ArrayList<String> markup);
}
	
