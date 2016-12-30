package ata.util;

import ata.config.StaticContextAccessor;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/*
*       try (Jedis j = RedisFactory.getJedis() )  {
*         // do stuff with redis
*       }
* */

public class RedisFactory {
  public RedisFactory() {
    jedisPool = StaticContextAccessor.getBean(JedisPool.class);
  }

  public static Jedis getJedis() {
    return getInstance().getJedisPool().getResource();
  }

  public JedisPool getJedisPool() {
    return jedisPool;
  }

  public static RedisFactory getInstance() {
    if (instance == null) {
      instance = new RedisFactory();
    }
    return instance;
  }

  private static RedisFactory instance;
  private static JedisPool jedisPool;
}
