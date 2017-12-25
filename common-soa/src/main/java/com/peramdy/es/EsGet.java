package com.peramdy.es;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;

import java.util.Map;

/**
 * @author peramdy
 * @date 2017/12/25.
 */
public class EsGet {

    /**
     * get
     *
     * @param index
     * @param type
     * @param id
     * @return
     */
    public Map<String, Object> get(String index, String type, String id) {
        TransportClient client = EsBuilder.builder();
        GetResponse response = client.prepareGet(index, type, id).get();
        Map<String, Object> map = response.getSource();
        return map;
    }

    /**
     * get thread
     *
     * @param index
     * @param type
     * @param id
     * @param isThread
     * @return
     */
    public Map<String, Object> get(String index, String type, String id, boolean isThread) {
        TransportClient client = EsBuilder.builder();
        GetResponse response = client.prepareGet()
                .setIndex(index)
                .setType(type)
                .setId(id)
                .setOperationThreaded(isThread)
                .get();
        Map<String, Object> map = response.getSource();
        return map;
    }

}
