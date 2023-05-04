package com.home.alone.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.home.alone.service.NewsService;
import com.home.alone.vo.NewsVO;

@Controller
public class NewsController {
//	private static final String CACHE = "news_cache";
//	private static final int CACHE_EXPIRE = 3600;
	
	@Autowired
	NewsService newsService;
	
	@GetMapping("/news")
	public String propertyNews(Model model, @RequestParam(value="currPage", defaultValue="1") int currPage) throws IOException {
		System.out.println("currPage " + currPage);
		int pageSize = 20; // 한 페이지 당 보여줄 게시글 수 
		List<NewsVO> getNewsList = newsService.getNewsList();
		
		
		// 전체 페이지 수 
		int totalPage = (getNewsList.size() % pageSize == 0) ? getNewsList.size() / pageSize : (getNewsList.size() / pageSize) + 1;
		
		System.out.println(totalPage);
		
		 // 현재 페이지에서 보여줄 기사 추출
        List<NewsVO> newsList = new ArrayList<>();
        int startIndex = (currPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, getNewsList.size());
        for (int i = startIndex; i < endIndex; i++) {
        	newsList.add(getNewsList.get(i));
        }
		
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
//		Date currentTime = new Date();
//		String date = sdf.format(currentTime);
//		
//		int page = 1;
//		
//		System.out.println(date);
//		while(true) { 
//			String url = "https://news.naver.com/main/list.naver?mode=LS2D&sid2=260&sid1=101&mid=shm&"
//					+ "date=" + date + "&page=" + Integer.toString(page); // 크롤링 해 올 url
//			
//			System.out.println(url); 
//			
//
//			Document data = Jsoup.connect(url).get();
//			Elements elements = data.select("div.list_body li dl");
//			
//		//	System.out.println(elements);
//			
//			for(Element element : elements) {
//				NewsVO newsVO = new NewsVO();
//				newsVO.setUrl(element.select("dt a").attr("abs:href"));
//				newsVO.setTitle(element.select("dt a").text());
//				newsVO.setImgUrl(element.select("dt a img").attr("abs:src"));
//				newsVO.setContent(element.select("dd span.lede").text());
//				newsVO.setPublication(element.select("dd span.writing").text());
//			//	System.out.println(newsVO);
//				crawlNewsList.add(newsVO);
//			}
//			
//			
//			elements = data.select("ul.type06 li dl");
//			
//			if(elements != null) {
//				for(Element element : elements) {
//					NewsVO newsVO = new NewsVO();
//					newsVO.setUrl(element.select("dt a").attr("abs:href"));
//					newsVO.setTitle(element.select("dt a").text());
//					newsVO.setImgUrl(element.select("dt a img").attr("abs:src"));
//					newsVO.setContent(element.select("dd span.lede").text());
//					newsVO.setPublication(element.select("dd span.writing").text());
//				//	System.out.println(newsVO);
//					crawlNewsList.add(newsVO);
//				
//				}
//			}
//			
//			Element nextPage = data.select("div.paging strong").next().first();			
//			// Element nextPage = data.select("div.paging strong").after("a").first();
//			System.out.println(nextPage);
//			
//			if(nextPage == null) {	// 다음 페이지가 없으면 크롤링 종료
//				break;
//			}else {
//				page++;
//			}
//		}
//		
//		int pageSize = 20; 	// 한 페이지 당 20개 
//		int totalPage = (int) Math.ceil(getNewsList.size() / (double) pageSize);	// 총 페이지 수 계산
//		int start = currPage * pageSize;
//		int end = Math.min(start + pageSize, getNewsList.size());
//		
//		List<NewsVO> newsList = getNewsList.subList(start, end);
        
        int pageNum = 10;	// 보여질 페이지 번호 개수
        int startPage = Math.max(1, currPage - pageNum/2);	// 시작페이지
        int endPage = Math.min(totalPage, startPage + pageNum - 1);	// 종료페이지
        
        
        if (startPage > 1) {
            model.addAttribute("prevPage", startPage - 1);
        }

        // 종료 페이지가 총 페이지 수(totalPage) 보다 작은 경우 '다음' 버튼 추가
        if (endPage < totalPage) {
            model.addAttribute("nextPage", endPage + 1);
        }
        
        model.addAttribute("newsList", newsList);
        model.addAttribute("currPage", currPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        
        
		// model.addAttribute("newsList", newsList);
		return "news";
	}
	
	
	
}
