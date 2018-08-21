package com.kefet.dao;

import java.util.List;

import com.kefet.utility.model.UIArticle;

public interface SearchDAO {
	
	 void doIndex();
	
	 List<UIArticle> search(String word, int firstResult, int maxResult);
	
	 long searchCount(String word);

}
