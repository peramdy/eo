package com.peramdy.es;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author peramdy
 * @date 2017/12/22.
 */
public class EsIndex {

    /**
     * index for json
     *
     * @param index
     * @param type
     * @param id
     * @param json
     * @return
     */
    public RestStatus indexString(String index, String type, String id, String json) {
        TransportClient client = EsBuilder.builder();
        IndexResponse response = client.prepareIndex(index, type, id).setSource(json, XContentType.JSON).get();
        RestStatus status = response.status();
        return status;
    }


    /**
     * index for map
     *
     * @param index
     * @param type
     * @param id
     * @param map
     * @return
     */
    public RestStatus indexMap(String index, String type, String id, Map map) {
        TransportClient client = EsBuilder.builder();
        IndexResponse response = client.prepareIndex(index, type, id).setSource(map).get();
        RestStatus status = response.status();
        return status;
    }

    /**
     * index for bean
     *
     * @param index
     * @param type
     * @param id
     * @param t
     * @param <T>
     * @return
     */
    public <T> RestStatus indexBean(String index, String type, String id, T t) {
        RestStatus status = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            byte[] json = mapper.writeValueAsBytes(mapper);
            TransportClient client = EsBuilder.builder();
            IndexResponse response = client.prepareIndex(index, type, id).setSource(json).get();
            status = response.status();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return status;
    }


    /**
     * index for map
     *
     * @param index
     * @param type
     * @param id
     * @param map
     * @return
     */
    public RestStatus indexEsHelper(String index, String type, String id, Map<String, Object> map) {
        RestStatus status = null;
        if (map == null || map.size() < 1) {
            return null;
        }
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder();
            builder.startObject();
            for (String key : map.keySet()) {
                builder.field(key, map.get(key));
            }
            builder.endObject();
            TransportClient client = EsBuilder.builder();
            IndexResponse response = client.prepareIndex(index, type, id).setSource(builder).execute().get();
            status = response.status();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return status;
    }

}
