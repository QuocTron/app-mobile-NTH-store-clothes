package com.group7.appsellclothes.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.group7.appsellclothes.model.ShipmentDetail;
import com.group7.appsellclothes.province.Province;
import com.group7.appsellclothes.response.CartResponse;
import com.group7.appsellclothes.response.ListCategoryResponse;
import com.group7.appsellclothes.response.ListOrderResponse;
import com.group7.appsellclothes.response.ListProductResponse;
import com.group7.appsellclothes.response.ListReviewResponse;
import com.group7.appsellclothes.model.User;
import com.group7.appsellclothes.response.ApiResponse;
import com.group7.appsellclothes.response.OrderResponse;
import com.group7.appsellclothes.response.ProductResponse;
import com.group7.appsellclothes.response.ReviewResponse;
import com.group7.appsellclothes.utils.ApiToken;
import com.group7.appsellclothes.response.UserResponse;
import com.group7.appsellclothes.utils.Util;


import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    // api
    ApiService apiService = new Retrofit.Builder()
            .baseUrl(Util.URI_API)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    ApiService apiProvince = new Retrofit.Builder()
            .baseUrl(Util.URI_API_PROVINCES)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("?depth=3")
    Call<List<Province>> getProvinces();

    // ????ng nh???p
    @FormUrlEncoded
    @POST("auth/login")
    Call<User> loginUser(@Field("username") String username, @Field("password") String password);

    // ????ng k??
    @FormUrlEncoded
    @POST("auth/register")
    Call<ApiResponse> createUser(@Field("username") String username,
                                 @Field("email") String email,
                                 @Field("password") String password);

    // Qu??n m???t kh???u
    @FormUrlEncoded
    @POST("password/forgot")
    Call<ApiResponse> forgotPassword(@Field("email") String email);

    // ?????i m???t kh???u
    @FormUrlEncoded
    @POST("password/update")
    Call<ApiResponse> resetPassword(@Header("Accept") String accept,
                                    @Header("token") String token,
                                    @Field("currentPassword") String currentPassword,
                                    @Field("newPassword") String newPassword,
                                    @Field("confirmPassword") String confirmPassword);

    // ????ng xu???t
    @POST("auth/logout")
    Call<ApiResponse> logoutUser(@Header("token") String token);

    // Refresh token
    @FormUrlEncoded
    @POST("auth/refresh")
    Call<ApiToken> refreshToken(@Field("refreshToken") String refreshToken);

    // Get All Category
    @GET("categories")
    Call<ListCategoryResponse> getAllCategories();

    @GET("products")
    Call<ListProductResponse> getAllProducts(@Query("limit") String limit,
                                             @Query("page") String page,
                                             @Query("sort") String sort,
                                             @Query("discount[gte]") String filterDiscount);

    @GET("product/{id}")
    Call<ProductResponse> getProduct(@Path("id") String id);

    @GET("reviews/{idProduct}")
    Call<ListReviewResponse> getAllReviewByProduct(@Path("idProduct") String productID);

    @GET("products/{idCate}")
    Call<ListProductResponse> getAllProductByCategory(@Path("idCate") String categoryID);

    @GET("cart/my-cart")
    Call<CartResponse> getMyCart(@Header("Accept") String accept,
                                 @Header("token") String token);

    @PUT("cart/remove-item-from-cart/{id}")
    Call<CartResponse> removeItemFromCart(@Header("Accept") String accept,
                                          @Path("id") String cartItemID,
                                          @Header("token") String token);

    // ?????i m???t kh???u
    @FormUrlEncoded
    @POST("cart/add-to-cart")
    Call<CartResponse> addItemToCart(@Header("Accept") String accept,
                                     @Header("token") String token,
                                     @Field("product") String productID,
                                     @Field("size") String size,
                                     @Field("color") String color,
                                     @Field("quantity") int quantity);

    // ?????i m???t kh???u
    @FormUrlEncoded
    @PUT("cart/{id}")
    Call<CartResponse> updateQuantityCart(@Header("Accept") String accept,
                                          @Header("token") String token,
                                          @Path("id") String cartItemID,
                                          @Field("quantity") int quantity);

    // get profile
    @GET("me/profile")
    Call<UserResponse> getMyAccount(@Header("Accept") String accept,
                                    @Header("token") String token);

    @Multipart
    @PUT("me/update")
    Call<UserResponse> updateAvatar(@Header("token") String token, @Part MultipartBody.Part avatar);

    @POST("me/shipment-detail")
    Call<UserResponse> addShipmentDetail(@Header("Accept") String accept,
                                         @Header("token") String token,
                                         @Body ShipmentDetail shipmentDetail);

    @PUT("me/shipment-detail/{id}")
    Call<UserResponse> updateShipment(
            @Header("token") String token,
            @Path("id") String shipmentID,
            @Body ShipmentDetail shipmentDetail);

    @PUT("me/shipment-detail/remove/{id}")
    Call<UserResponse> removeShipment(
            @Header("token") String token,
            @Path("id") String shipmentID);

    @FormUrlEncoded
    @POST("order/new")
    Call<OrderResponse> createOrder(@Header("Accept") String accept,
                                    @Header("token") String token,
                                    @Field("fullName") String fullName,
                                    @Field("phone") String phone,
                                    @Field("province") String province,
                                    @Field("district") String district,
                                    @Field("ward") String ward,
                                    @Field("address") String address,
                                    @Field("isPaid") boolean isPaid,
                                    @Field("shippingPrice") int shippingPrice);
    // get order
    @GET("order/me")
    Call<ListOrderResponse> getMyOrder(@Header("Accept") String accept,
                                       @Header("token") String token,
                                       @Query("orderStatus[regex]") String orderStatus);

    //Review
    @FormUrlEncoded
    @POST("review/new/{id}")
    Call<ReviewResponse> addReview(@Header("Accept") String accept,
                                   @Header("token") String token,
                                   @Field("content") String content,
                                   @Field("product") String product,
                                   @Field("star") int star,
                                    @Path("id") String orderItemID);

}
