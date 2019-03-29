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

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpAspect.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

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
     *
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
     *
     * @param key
     * @param map
     * @param time 如果已存在的hash表有时间,这里将会替换原有的时间
     * @return
     */
    public Boolean hashSave(String key, Map<String, Object> map, Long time) {
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
     *
     * @param key
     * @param item
     * @param o
     * @return
     */
    public Boolean hashPut(String key, String item, Object o) {
        try {
            redisTemplate.opsForHash().put(key, item, o);
            return true;
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建并设置时间
     *
     * @param key
     * @param item
     * @param o
     * @param time 如果已存在的hash表有时间,这里将会替换原有的时间
     * @return
     */
    public Boolean hashPut(String key, String item, Object o, Long time) {
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

    /**
     * 删除hash表中的值
     *
     * @param key
     * @param items
     * @return
     */
    public Boolean hashDel(String key, String... items) {
        try {
            redisTemplate.opsForHash().delete(key, items);
            return true;
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key
     * @param item
     * @return
     */
    public Boolean hashHas(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key
     * @param item
     * @param db
     * @return
     */
    public Double hashIncr(String key, String item, Double db) {
        if (db < 0) {
            throw new RedisException(ResultEnum.PLURA_ARG);
        }
        return redisTemplate.opsForHash().increment(key, item, db);
    }

    /**
     * hash递减
     *
     * @param key
     * @param item
     * @param db
     * @return
     */
    public Double hashDecr(String key, String item, Double db) {
        if (db < 0) {
            throw new RedisException(ResultEnum.PLURA_ARG);
        }
        return redisTemplate.opsForHash().increment(key, item, -db);
    }

    /**
     * 根据key获取Set中的所有值
     *
     * @param key
     * @return
     */
    public Set<Object> setGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key
     * @param value
     * @return
     */
    public Boolean setHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     *
     * @param key
     * @param values
     * @return
     */
    public Long setPut(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return 0L;
        }
    }

    /**
     * 将set数据放入缓存并设置时间
     *
     * @param key
     * @param time
     * @param values
     * @return
     */
    public Long setPut(String key, Long time, Object... values) {
        try {
            redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return 1L;
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return 0L;
        }
    }

    /**
     * 获取set缓存的长度
     *
     * @param key
     * @return
     */
    public Long setGetSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 移除值为value的
     *
     * @param key
     * @param values
     * @return
     */
    public Long setRemove(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().remove(key, values);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return 0L;
        }
    }

    /**
     * 获取list缓存的内容
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<Object> listGet(String key, Long start, Long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return null;
        }

    }

    /**
     * 获取list缓存的长度
     *
     * @param key
     * @return
     */
    public Long listSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return 0L;
        }

    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key
     * @param index
     * @return
     */
    public Object listGetByIndex(String key, Long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return null;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key
     * @param o
     * @return
     */
    public Boolean listPush(String key, Object o) {
        try {
            redisTemplate.opsForList().rightPush(key, o);
            return true;
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }

    /**
     * 将list放入缓存并设置时间
     *
     * @param key
     * @param o
     * @param time
     * @return
     */
    public Boolean listPush(String key, Object o, Long time) {
        try {
            redisTemplate.opsForList().rightPush(key, o);
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
     * 将list所有放入缓存
     *
     * @param key
     * @param objects
     * @return
     */
    public Boolean listPushAll(String key, List<Object> objects) {
        try {
            redisTemplate.opsForList().rightPushAll(key, objects);
            return true;
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return false;
        }

    }

    /**
     * 将list所有放入缓存并设置时间
     *
     * @param key
     * @param objects
     * @param time
     * @return
     */
    public Boolean listPushAll(String key, List<Object> objects, Long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, objects);
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
     * 根据索引修改list中的某条数据
     *
     * @param key
     * @param index
     * @param o
     * @return
     */
    public Boolean listSetByIndex(String key, Long index, Object o) {
        try {
            redisTemplate.opsForList().set(key, index, o);
            return true;
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }

    /**
     * 移除N个值为value的对象
     *
     * @param key
     * @param count
     * @param o
     * @return
     */
    public Boolean listRemoveByCount(String key, Long count, Object o) {
        try {
            redisTemplate.opsForList().remove(key, count, o);
            return true;
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }

}
