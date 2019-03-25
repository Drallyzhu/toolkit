package com.drally.toolkit.config;

import com.drally.toolkit.util.BeanHelper;
import com.drally.toolkit.util.JsonHelper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, java.lang.reflect.Method method, Object... params) {
                StringBuffer sb = new StringBuffer();
                char sp = ':';
                sb.append(target.getClass()).append(sp);
                sb.append(method.getName()).append(sp);
                if (params.length > 0){
                    for (Object obj : params) {
                        if (BeanHelper.isSimpleValueType(obj.getClass())) {
                            sb.append(obj);
                        } else {
                            sb.append(JsonHelper.toJson(obj).hashCode());
                        }
                    }
                }else{
                    sb.append(0);
                }
                return sb.toString();
            }
        };
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new ObjectSerializer());
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(connectionFactory);
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new ObjectSerializer()));
        configuration.disableKeyPrefix();
//        configuration.entryTtl(Duration.ofDays(1));
        return new RedisCacheManager(writer, configuration);
    }

}