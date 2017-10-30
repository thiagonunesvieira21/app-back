package br.com.servico;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import redis.clients.jedis.Protocol;

import java.io.IOException;

/**
 * Created by thiago on 21/08/17.
 */
@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {
    private static RedisServer redisServer;

    @Bean
    public JedisConnectionFactory connectionFactory() throws IOException {
        redisServer = new RedisServer(Protocol.DEFAULT_HOST, Protocol.DEFAULT_PORT);
        return new JedisConnectionFactory();
    }
}
