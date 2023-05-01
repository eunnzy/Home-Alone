package com.home.alone.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.home.alone.vo.NewsVO;

@Service
public class NewsServiceImpl implements NewsService{

	@Override
	public List<NewsVO> getNewsList() throws IOException {
		List<NewsVO> newsList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		Date currentTime = new Date();
		String date = sdf.format(currentTime);
		
		int page = 1;
		
		System.out.println(date);
		while(true) { 
			String url = "https://news.naver.com/main/list.naver?mode=LS2D&sid2=260&sid1=101&mid=shm&"
					+ "date=" + date + "&page=" + Integer.toString(page); // 크롤링 해 올 url
			
			System.out.println(url); 

			Document data = Jsoup.connect(url).get();
			Elements elements = data.select("div.list_body li dl");
			System.out.println();
			
			for(Element element : elements) {
				NewsVO newsVO = new NewsVO();
				newsVO.setUrl(element.select("dt a").attr("abs:href"));
				newsVO.setTitle(element.select("dt a").text());
				newsVO.setImgUrl(element.select("dt a img").attr("abs:src"));
				newsVO.setContent(element.select("dd span.lede").text());
				newsVO.setPublication(element.select("dd span.writing").text());
				newsList.add(newsVO);
			}
			
			System.out.println(newsList.size());
			
			Element nextPage = data.select("div.paging strong").next().first();			
			System.out.println(nextPage);
			
			if(nextPage == null) {	// 다음 페이지가 없으면 크롤링 종료
				break;
			}else {
				page++;
			}
		}
		
		return newsList;
	}

}
