package com.workshop3_pre.workshop3_pre.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Optional<Integer> redisPort;
    
    @Bean
    @Scope("singleton")
    public RedisTemplate<String,Object> createRedisTemplate() {
        final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setDatabase(0);
        config.setHostName(redisHost);

        final JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();
        final JedisConnectionFactory jedisFac = new JedisConnectionFactory(config,jedisClient);

        jedisFac.afterPropertiesSet();

        final RedisTemplate<String, Object> template = new RedisTemplate<>(); 
        template.setConnectionFactory(jedisFac);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        // template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        // template.setHashKeySerializer(new StringRedisSerializer());
        // template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // template.setHashKeySerializer(new JdkSerializationRedisSerializer());
        // template.setValueSerializer(new JdkSerializationRedisSerializer());


        
        // Set key serializer as String
        // template.setKeySerializer(new StringRedisSerializer());
        // template.setHashKeySerializer(new StringRedisSerializer());

        // // Set value serializer as GenericJackson2JsonRedisSerializer for JSON serialization
        

        // Set hash key serializer as String
        // ObjectMapper objectMapper = new ObjectMapper();
        // objectMapper.registerModule(new JavaTimeModule()); // Register JavaTimeModule
        // objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Format dates as ISO strings
        // objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // Exclude null values
        // GenericJackson2JsonRedisSerializer jsonSerializer = new GenericJackson2JsonRedisSerializer(objectMapper);

        // // Set hash value serializer as GenericJackson2JsonRedisSerializer for JSON
        // template.setValueSerializer(jsonSerializer);
        // template.setHashValueSerializer(jsonSerializer);

        // template.setKeySerializer(new StringRedisSerializer());
        // template.setHashKeySerializer(new StringRedisSerializer());
        // template.setHashKeySerializer(new JdkSerializationRedisSerializer());
        // template.setValueSerializer(new JdkSerializationRedisSerializer());
        // template.setEnableTransactionSupport(true);
        // template.afterPropertiesSet();

        
        return template;



    }

    
}
