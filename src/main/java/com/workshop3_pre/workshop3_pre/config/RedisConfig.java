package com.workshop3_pre.workshop3_pre.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.workshop3_pre.util.Util;

// @Configuration
// public class RedisConfig {
//     @Value("${spring.redis.host}")
//     private String redisHost;

//     @Value("${spring.redis.port}")
//     private Optional<Integer> redisPort;
    
//     @Bean
//     @Scope("singleton")
//     public RedisTemplate<String,Object> createRedisTemplate() {
//         final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
//         config.setDatabase(0);
//         config.setHostName(redisHost);

//         final JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();
//         final JedisConnectionFactory jedisFac = new JedisConnectionFactory(config,jedisClient);

//         jedisFac.afterPropertiesSet();

//         final RedisTemplate<String, Object> template = new RedisTemplate<>(); 
//         template.setConnectionFactory(jedisFac);

//         // template.setKeySerializer(new StringRedisSerializer());
//         // template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

//         //works
//         //
//         template.setKeySerializer(new StringRedisSerializer());
//         template.setValueSerializer(new StringRedisSerializer());
//         //

//         // template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//         // template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
//         // template.setHashKeySerializer(new StringRedisSerializer());
//         // template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//         // template.setHashKeySerializer(new JdkSerializationRedisSerializer());
//         // template.setValueSerializer(new JdkSerializationRedisSerializer());


//         //will get linkedhashmap error
//         //
//        // Set key serializer as String
//     //     template.setKeySerializer(new StringRedisSerializer());
//     //     template.setHashKeySerializer(new StringRedisSerializer());

//     //     //Set value serializer as GenericJackson2JsonRedisSerializer for JSON serialization
        

//     //    //Set hash key serializer as String 
//     //     ObjectMapper objectMapper = new ObjectMapper();
//     //     objectMapper.registerModule(new JavaTimeModule()); // Register JavaTimeModule
//     //     objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Format dates as ISO strings
//     //     objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // Exclude null values
//     //     GenericJackson2JsonRedisSerializer jsonSerializer = new GenericJackson2JsonRedisSerializer(objectMapper);

//     //     //Set hash value serializer as GenericJackson2JsonRedisSerializer for JSON
//     //     template.setValueSerializer(jsonSerializer);
//     //     template.setHashValueSerializer(jsonSerializer);
//     //     //

        
//         //cannot cast
//         //
//         // template.setKeySerializer(new StringRedisSerializer());
//         // template.setHashKeySerializer(new StringRedisSerializer());
//         // template.setHashKeySerializer(new JdkSerializationRedisSerializer());
//         // template.setValueSerializer(new JdkSerializationRedisSerializer());
//         // template.setEnableTransactionSupport(true);
//         // template.afterPropertiesSet();
//         //

        
//         return template;



//     }

    
// }

@Configuration
public class RedisConfig {
    
    // slide 17

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private Integer redisPort;

    @Value("${spring.data.redis.username}")
    private String redisUsername;

    @Value("${spring.data.redis.password}")
    private String redisPassword;


    // slide 18

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration rsc = new RedisStandaloneConfiguration();
        rsc.setHostName(redisHost);
        rsc.setPort(redisPort);

        if(redisUsername.trim().length() > 0) {
            rsc.setUsername(redisUsername);
            rsc.setPassword(redisPassword);
        }

        JedisClientConfiguration jcc = JedisClientConfiguration.builder().build();
        JedisConnectionFactory jcf = new JedisConnectionFactory(rsc, jcc);
        jcf.afterPropertiesSet();

        return jcf;
    }

    @Bean(Util.template01)
    public RedisTemplate<String, String> redisObjectTemplate01() {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());
        return template;
    }

    // @Bean(Util.template02)
    // public RedisTemplate<String, Object> redisObjectTemplate02() {
    //     RedisTemplate<String, Object> template = new RedisTemplate<>();
    //     template.setConnectionFactory(jedisConnectionFactory());
    //     template.setKeySerializer(new StringRedisSerializer());
    //     template.setValueSerializer(new StringRedisSerializer());

    //     return template;
    // }
}