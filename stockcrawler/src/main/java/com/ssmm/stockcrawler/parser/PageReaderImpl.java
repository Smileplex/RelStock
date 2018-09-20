package com.ssmm.stockcrawler.parser;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.ssmm.stockcrawler.AppSettings;

public class PageReaderImpl implements PageReader {

	@Override
	public Document read(String url) {
		// TODO Auto-generated method stub

		try {
			Connection connection = Jsoup.connect(URLDecoder.decode(url, "UTF-8")).timeout(AppSettings.TIMEOUT);
			connection.header("Host", "m.search.naver.com");
			connection.header("Connection", "keep-alive");
			connection.header("Pragma", "no-cache");
			connection.header("Cache-Control", "no-cache");
			connection.header("Upgrade-Insecure-Requests", "1");
			connection.header("User-Agent",
					"Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1");
			connection.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			connection.header("Accept-Encoding", "gzip, deflate, sdch, br");
			connection.header("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4");
			connection.header("Cookie",
					"NNB=XIKVTEKVRT6FS; npic=MXf4LBODVhKAZic/5MLWvaxHNczZDj9R87qlDfW13NQOY2N8foo4Q9SA2tAhcpnrCA==; nx_ssl=2; _ga=GA1.2.1544979620.1536746302; _naver_usersession_=wneTBv3JwYp4GAgDmpXJeg==; page_uid=TIKqulpySowsss6QsOZsssssskK-392307; BMR=s=1537419817492&r=https%3A%2F%2Fm.search.naver.com%2Fsearch.naver%3Fsm%3Dtop_hty%26fbm%3D1%26ie%3Dutf8%26query%3Dlg%25EB%2594%2594%25EC%258A%25A4%25ED%2594%258C%25EB%25A0%2588%25EC%259D%25B4%26where%3Dm&r2=");
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