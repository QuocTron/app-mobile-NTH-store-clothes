package com.group7.appsellclothes.response;

import com.group7.appsellclothes.model.Category;

import java.io.Serializable;
import java.util.List;

public class ListCategoryResponse implements Serializable {
    private List<Category> categories;
    private boolean success;
    private  int countDocument;
    private int resultPerPage;

    public ListCategoryResponse() {
    }

    public ListCategoryResponse(List<Category> categories, boolean success, int countDocument, int resultPerPage) {
        this.categories = categories;
        this.success = success;
        this.countDocument = countDocument;
        this.resultPerPage = resultPerPage;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCountDocument() {
        return countDocument;
    }

    public void setCountDocument(int countDocument) {
        this.countDocument = countDocument;
    }

    public int getResultPerPage() {
        return resultPerPage;
    }

    public void setResultPerPage(int resultPerPage) {
        this.resultPerPage = resultPerPage;
    }
}
