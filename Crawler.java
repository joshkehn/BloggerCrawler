import java.net.*;
import java.io.*;
import java.util.*;

public class Crawler {
	
	public static ArrayList<String> getMarkup (String startingURL) throws IOException, MalformedURLException {
		
		URL start = new URL(startingURL);

		InputStreamReader pageStream = new InputStreamReader(start.openStream());
		
		BufferedReader pageBuffer = new BufferedReader(pageStream);
		
		ArrayList<String> pageMarkup = new ArrayList<String>();
		
		do {
			pageMarkup.add(pageBuffer.readLine());
		} while (pageMarkup.get(pageMarkup.size()-1)!=null);
				
		
		return pageMarkup;
	}
	
	public static void main (String[] args) {
		
		try {
			ArrayList<String> markup = getMarkup("http://www.particleburst.com");
			System.out.print(markup);
		}
		
		catch (IOException e) {
			System.out.println("Could not read from URL");
		}
	}
	
}
	
