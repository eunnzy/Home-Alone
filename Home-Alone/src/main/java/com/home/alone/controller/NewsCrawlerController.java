package com.home.alone.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.ui.Model;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.home.alone.vo.PropertyNewsVO;

@Controller
public class NewsCrawlerController {
	private static final String CACHE = "news_cache";
	private static final int CACHE_EXPIRE = 3600;
	
	
	@RequestMapping("/propertyNews")
	public String propertyNews(Model model) throws IOException {
		List<PropertyNewsVO> list = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		Date currentTime = new Date();
		
		String date = sdf.format(currentTime);
		int page = 1;
		
		System.out.println(date);
		while(page<20) {
			String url = "https://news.naver.com/main/list.naver?mode=LS2D&sid2=260&sid1=101&mid=shm&"
					+ "date=" + date + "&page=" + Integer.toString(page); // 크롤링 해 올 url
			
			System.out.println(url);
			
			Document data = Jsoup.connect(url).timeout(2000).get();
			Elements elements = data.select("ul.type06_headline li dl dt");
			
			for(Element element : elements) {
				PropertyNewsVO propertyNewsVO = new PropertyNewsVO();
				propertyNewsVO.setUrl(element.select("a").attr("abs:href"));
				propertyNewsVO.setTitle(element.select("a").text());
				propertyNewsVO.setImgUrl(element.select("a img").attr("abs:src"));
				propertyNewsVO.setPage(page);
				System.out.println(propertyNewsVO);
				list.add(propertyNewsVO);
			}
			page++;
		}
		
		model.addAttribute("newsList", list);
		return "propertyNews";
	}
	
	
	
}
