package com.thedeanda.lorem.exceptions;

public class CategoryNotExist extends Exception{

    public CategoryNotExist(String categoryName){
        super(categoryName + " category is not loaded");
    }
}
