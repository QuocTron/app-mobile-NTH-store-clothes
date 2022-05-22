package com.group7.appsellclothes.response;

import com.group7.appsellclothes.R;
import com.group7.appsellclothes.model.Review;

import java.io.Serializable;

public class ReviewResponse implements Serializable {
    private boolean success;
    private String message;
   private ReviewRes review;

    public ReviewResponse() {
    }

    public ReviewResponse(boolean success, String message, ReviewRes review) {
        this.success = success;
        this.message = message;
        this.review = review;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ReviewRes getReview() {
        return review;
    }

    public void setReview(ReviewRes review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "ReviewResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", review=" + review +
                '}';
    }
}
