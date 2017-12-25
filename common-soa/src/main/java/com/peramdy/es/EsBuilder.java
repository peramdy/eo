package com.peramdy.es;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author peramdy
 * @date 2017/12/22.
 */
public class EsBuilder {

    public static TransportClient builder() {

        TransportClient transportClient = null;
        try {
            Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
            TransportAddress transportAddress = new TransportAddress(InetAddress.getByName("192.168.136.130"), 19300);
            transportClient = new PreBuiltTransportClient(settings);
            transportClient.addTransportAddress(transportAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return transportClient;
    }

}
