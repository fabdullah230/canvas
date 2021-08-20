package org.veriguide.canvas.Cookie;

public interface CookieDataRepository {

    void save(CookieData cookieData);
    CookieData findByData(String data);
    void delete(String data);

}
