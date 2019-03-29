package com.funkman.lunch.util;

import com.funkman.lunch.aspect.HttpAspect;
import com.funkman.lunch.execption.RedisException;
import com.funkman.lunch.resultEnum.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpAspect.class);

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 指定缓存失效时间
     *
     * @param key
     * @param time
     * @return
     */
    public Boolean expire(String key, Long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key
     * @return (返回0代表为永久有效)
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    public Boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }

    /**
     * 删除redis中的记录
     *
     * @param keys
     */
    public void delete(String... keys) {
        if (keys != null && keys.length > 0) {
            if (keys.length == 1) {
                redisTemplate.delete(keys[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(keys));
            }
        } else {
            throw new RedisException(ResultEnum.EMPTY_ARGS);
        }
    }

    /**
     * 普通缓存获取
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key
     * @param o
     * @return
     */
    public Boolean set(String key, Object o) {
        try {
            redisTemplate.opsForValue().set(key, o);
            return true;
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key
     * @param o
     * @param time
     * @return
     */
    public Boolean set(String key, Object o, Long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, o, time, TimeUnit.SECONDS);
            } else {
                set(key, o);
            }
            return true;
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }

    /**
     * 递增
     *
     * @param key
     * @param delta
     * @return
     */
    public Long incr(String key, Long delta) {
        if (delta < 0) {
            throw new RedisException(ResultEnum.PLURA_ARG);
        } else {
            return redisTemplate.opsForValue().increment(key, delta);
        }
    }

    /**
     * 递减
     *
     * @param key
     * @param delta
     * @return
     */
    public Long decr(String key, Long delta) {
        if (delta < 0) {
            throw new RedisException(ResultEnum.PLURA_ARG);
        } else {
            return redisTemplate.opsForValue().decrement(key, delta);
        }
    }

    /**
     * 获取map中的对象
     *
     * @param key
     * @param item
     * @return
     */
    public Object hashGet(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key
     * @return
     */
    public Map<Object, Object> hashitems(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 将map存入redis
     * @param key
     * @param map
     * @return
     */
    public Boolean hashSave(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }

    /**
     * 将map存入redis并设置时间
     * @param key
     * @param map
     * @param time
     * @return
     */
    public Boolean hashSave(String key,Map<String,Object> map,Long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key
     * @param item
     * @param o
     * @return
     */
    public Boolean hashPut(String key,String item,Object o) {
        try {
            redisTemplate.opsForHash().put(key, item, o);
            return true;
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key
     * @param item
     * @param o
     * @param time
     * @return
     */
    public Boolean hashPut(String key,String item,Object o,Long time) {
        try {
            redisTemplate.opsForHash().put(key, item, o);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }
}
