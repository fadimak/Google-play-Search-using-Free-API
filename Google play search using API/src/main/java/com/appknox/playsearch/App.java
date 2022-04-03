package com.appknox.playsearch;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
public class App {
	public static void main(String[] args ) {
		HttpURLConnection c = null;
		try {
			URL u = new URL("https://serpapi.com/search.json?engine=google_play&q=weather&gl=us&hl=en&store=apps&api_key=cf97edc84284da1d8c81be5b8c1ff5223ad281b6da11cf1ab622d39e97208ce2");
			c = (HttpURLConnection) u.openConnection();
			c.setRequestMethod("GET");
			c.setRequestProperty("Content-length", "0");
			c.setUseCaches(false);
			c.setAllowUserInteraction(false);
			c.setConnectTimeout(1000);
			c.setReadTimeout(1000);
			c.connect();
			int status = c.getResponseCode();
			System.out.println("searching for weather applications!!!!");
			switch (status) {
				case 200:
				case 201:
					BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
					StringBuilder sb = new StringBuilder();
					String line;
					while ((line = br.readLine()) != null) {
						sb.append(line+"\n");
					}
					br.close();
					System.out.println("SB: "+ sb);
			}

		} catch (MalformedURLException ex) {
			Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (c != null) {
				try {
					c.disconnect();
				} catch (Exception ex) {
					Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}
}