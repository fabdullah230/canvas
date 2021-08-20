package org.veriguide.canvas.Cookie;

import java.io.Serializable;

public class CookieData implements Serializable {
    public String data;

    public CookieData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
