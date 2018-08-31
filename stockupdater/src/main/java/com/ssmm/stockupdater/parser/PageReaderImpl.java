package com.ssmm.stockupdater.parser;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.ssmm.stockupdater.AppSettings;

public class PageReaderImpl implements PageReader {

	@Override
	public Document read(String url) {
		// TODO Auto-generated method stub

		try {
			Connection connection = Jsoup.connect(URLDecoder.decode(url, "UTF-8")).timeout(AppSettings.TIMEOUT);
			connection.header("Host", "m.search.naver.com");
			connection.header("Connection", "keep-alive");
			connection.header("Cache-Control", "max-age=0");
			connection.header("Upgrade-Insecure-Requests", "1");
			connection.header("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
			connection.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			connection.header("Accept-Encoding", "gzip, deflate, sdch, br");
			connection.header("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4");
			connection.header("Cookie",
					"Cookie: NNB=R5OIDEDWDAZVS; page_uid=TgKz6wpVuqwssteWhAGssssss7o-068819; _naver_usersession_=SMs6MTU5bT0BxpBow5Hntw==; BMR=");
			return connection.get();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}