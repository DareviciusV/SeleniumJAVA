package objects;

import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HTTPSChecker {
	public URL url;
	public HttpsURLConnection connection;

	public HTTPSChecker(String websiteUrl) throws IOException {
		this.url = new URL(websiteUrl);
		this.connection = (HttpsURLConnection) this.url.openConnection();
	}

	public boolean isSecure() {
		try {
			return connection.getResponseCode() == 200;
		} catch (IOException e) {
			return false;
		}
	}

	public String getCipherSuite() {
		return connection.getCipherSuite();
	}
}
