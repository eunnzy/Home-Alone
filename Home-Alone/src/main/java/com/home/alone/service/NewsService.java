package com.home.alone.service;

import java.io.IOException;
import java.util.List;

import com.home.alone.vo.NewsVO;

public interface NewsService {
	
	List<NewsVO> getNewsList()throws IOException;
}
