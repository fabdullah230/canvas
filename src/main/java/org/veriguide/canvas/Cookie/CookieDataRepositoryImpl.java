package org.veriguide.canvas.Cookie;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CookieDataRepositoryImpl implements CookieDataRepository{

    private RedisTemplate<String, CookieData> redisTemplate;

    private HashOperations hashOperations;

    public CookieDataRepositoryImpl(RedisTemplate<String, CookieData> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(CookieData cookieData) {
        hashOperations.put("COOKIEDATA", cookieData.getData(), cookieData);
    }

    @Override
    public CookieData findByData(String data) {
        return (CookieData) hashOperations.get("COOKIEDATA", data);
    }

    @Override
    public void delete(String data) {
        hashOperations.delete("COOKIEDATA", data);
    }
}
