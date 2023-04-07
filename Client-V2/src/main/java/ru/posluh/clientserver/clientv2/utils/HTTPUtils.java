package ru.posluh.clientserver.clientv2.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import okhttp3.*;
import ru.posluh.clientserver.clientv2.entity.AuthorEntity;
import ru.posluh.clientserver.clientv2.entity.BookEntity;
import ru.posluh.clientserver.clientv2.entity.PublishingEntity;
import ru.posluh.clientserver.clientv2.response.BaseResponse;

import java.io.IOException;
import static ru.posluh.clientserver.clientv2.controller.ApplicationController.apiBook;

import static ru.posluh.clientserver.clientv2.controller.ApplicationController.*;


public class HTTPUtils {
    static HTTPUtils http = new HTTPUtils();
    static OkHttpClient client = new OkHttpClient();
    static GsonBuilder builder = new GsonBuilder();
    static Gson gson = builder.create();

    //Запросы

    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        Request request = new Request.Builder().url(url).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String get(String url, String args) throws IOException {
        Request request = new Request.Builder().url(url + args).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String put(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        Request request = new Request.Builder().url(url).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public BaseResponse delete(String url, Long Id) throws IOException {
        System.out.println("delete book (id) - " + Id);
        Request request = new Request.Builder().url(url + "delete/" + Id).delete().build();
        try (Response response = client.newCall(request).execute()) {
            return gson.fromJson(response.body().string(), BaseResponse.class);
        }
    }

    //Книги
    public static String getBooks() throws IOException {
        return http.get(apiBook, "all");
    }

    public static BookEntity sendBookAndGetData(BookEntity book) throws IOException {
        String response = http.post(apiBook + "add", gson.toJson(book));
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        return gson.fromJson(jsonObject.get("book"), BookEntity.class);
    }

    public static void updateBook(BookEntity book) throws IOException {
        http.put(apiBook + "update", gson.toJson(book));
    }

    public static void deleteBook(BookEntity book) throws IOException {
        http.delete(apiBook, book.getId());
    }

   // Авторы
    public static String getAuthor() throws IOException {
        return http.get(apiAuthor, "all");
    }

    public static AuthorEntity sendAuthorAndGetData(AuthorEntity author) throws IOException {
        String response = http.post(apiAuthor + "add", gson.toJson(author));
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        return gson.fromJson(jsonObject.get("author"), AuthorEntity.class);
    }

    public static void updateAuthor(AuthorEntity author) throws IOException {
        http.put(apiAuthor + "update", gson.toJson(author));
    }

    public static void deleteAuthor(AuthorEntity author) throws IOException {
        http.delete(apiAuthor, author.getId());
    }

    // Издательства
    public static String getPublishing() throws IOException {
        return http.get(apiPublishing, "all");
    }

    public static PublishingEntity sendPublishingAndGetData(PublishingEntity publishing) throws IOException {
        String response = http.post(apiPublishing + "add", gson.toJson(publishing));
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        return gson.fromJson(jsonObject.get("publishing"), PublishingEntity.class);
    }

    public static String updatePublishing(PublishingEntity publishing) throws IOException {
        return http.put(apiPublishing + "update", gson.toJson(publishing));
    }

    public static BaseResponse deletePublishing(PublishingEntity publishing) throws IOException {
        return http.delete(apiPublishing, publishing.getId());
    }



}


