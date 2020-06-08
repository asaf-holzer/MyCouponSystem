package com.asafh.dao;

import com.asafh.beans.Category;
import com.asafh.utils.duplicateCategoryException;

public interface CategoriesDAO {

	boolean isCtegoryExist(Category category);
	void addCategory(Category catgory) throws duplicateCategoryException;
	void deleteCategory(int categoryID);
	Category getCategory(int categoryID);
	
	
	
}
