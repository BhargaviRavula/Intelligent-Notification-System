package domain;

import java.util.ArrayList;
import java.util.List;

import beans.Category;
import beans.UserPreference;
import beans.UserPreferenceGroup;
import dao.CategoryDB;


public class CategoryService {
	

	public int getCategoryID(String categoryName) throws Exception {
		
		CategoryDB categorydb = new CategoryDB();
		return categorydb.getCategoryID(categoryName);
		
	}
	
	public List<Category> getUnregisteredCategory(List<UserPreference> userPreferences) throws Exception{
		CategoryDB categoryDb = new CategoryDB();
		List<Category> category = categoryDb.getCategories();
		
		
			for(UserPreference usercategory : userPreferences){
				List<Category> unregisteredCategory = new ArrayList<Category>();
				for(Category catgry : category){
					if(catgry.getCategory_ID().compareTo(usercategory.getCategoryID())!=0){
					unregisteredCategory.add(catgry);
				}
			}category = unregisteredCategory;
		}
		return category;
	}
	
	public List<UserPreferenceGroup> getCategoryName(List<UserPreference> userpref) throws Exception{
		
		CategoryDB categorydb= new CategoryDB();
		List<UserPreferenceGroup> grp = new ArrayList<UserPreferenceGroup>();
		for(UserPreference pref : userpref){
			UserPreferenceGroup usrgrp = new UserPreferenceGroup();
			usrgrp.setCategoryName(categorydb.getCategoryName(pref.getCategoryID()));
			grp.add(usrgrp);
		}
		return grp;
	}
}
