package com.ying.bible;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtil {
	public static String getWebPageInFullHttpResponse(String sitelink) {
		StringBuffer webPageBuffer = new StringBuffer();

		try {
			URL url = new URL(sitelink);

			URLConnection uc = url.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(uc
					.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				webPageBuffer.append(inputLine);
			}
			in.close();
		} catch (Exception e) {

		}

		return webPageBuffer.toString();
	}
	
}
