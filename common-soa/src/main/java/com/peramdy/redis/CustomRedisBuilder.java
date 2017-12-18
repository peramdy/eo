package com.peramdy.redis;

import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author peramdy
 * @date 2017/12/18.
 */
@Data
public class CustomRedisBuilder {
    private GenericObjectPoolConfig genericObjectPoolConfig;
    private String host;
    private int port;
    private int timeout;
    private String password;
    private String addressConfigs;
    private int maxAttempts;

    @Ignore
    private Pattern pattern = Pattern.compile("^.+[:]\\d{1,5}\\s*$");


    /**
     * jedis
     *
     * @return
     */
    public Jedis build() {
        JedisPool jedisPool;
        Jedis jedis = null;
        synchronized (this) {
            jedisPool = new JedisPool(genericObjectPoolConfig, host, port, timeout, password);
        }
        if (jedisPool != null) {
            jedis = jedisPool.getResource();
        }
        return jedis;
    }


    /**
     * redisCluster
     *
     * @return
     */
    public JedisCluster buildCluster() {

        JedisCluster jedisCluster;
        synchronized (this) {
            jedisCluster = new JedisCluster(parseHostAndPort(), timeout, maxAttempts, genericObjectPoolConfig);
        }
        return jedisCluster;

    }


    /**
     * 解析ipandport
     *
     * @return
     * @throws Exception
     */
    private Set<HostAndPort> parseHostAndPort() {
        try {
            if (StringUtils.isBlank(addressConfigs)) {
                throw new IllegalArgumentException("未配置redis集群ip地址！");
            }
            String[] ipports = addressConfigs.split(",");
            Set<HostAndPort> hostAndPorts = new HashSet<HostAndPort>();
            for (String ipPort : ipports) {
                boolean isIpPort = pattern.matcher(ipPort).matches();
                if (!isIpPort) {
                    throw new IllegalArgumentException("ip 或 port 不合法:" + ipPort);
                }
                String[] ipAndPort = ipPort.split(":");
                HostAndPort hap = new HostAndPort(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
                hostAndPorts.add(hap);
            }
            return hostAndPorts;
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new IllegalArgumentException("解析 jedis 配置文件失败", ex);
        }
    }


}
