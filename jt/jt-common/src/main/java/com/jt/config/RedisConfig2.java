package com.jt.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

//标识为配制类    xml <bean id="jedis"  class="redis.clients.jedis.Jedis"/>
@Configuration
@PropertySource("classpath:/properties/redis.properties")
public class RedisConfig2 {
	@Value("${redis.nodes}")
	private String nodes;
	public JedisCluster jedisCluster() {
		Set<HostAndPort> nodes=getNodes();
		return new JedisCluster(nodes);
	}
	private Set<HostAndPort> getNodes() {
		HashSet<HostAndPort> set = new HashSet<HostAndPort>();
		String[] nodeArray = nodes.split(",");
		for (String node : nodeArray) {
			String host=node.split(":")[0];
			String port1=node.split(":")[1];
			int port=Integer.parseInt(port1);
			set.add(new HostAndPort(host, port));
		}
		return set;
	}
	
}	
	
	
	
	
