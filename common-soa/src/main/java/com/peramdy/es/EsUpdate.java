package com.peramdy.es;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.rest.RestStatus;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author peramdy
 * @date 2017/12/25.
 */
public class EsUpdate {

    /**
     * update script
     *
     * @param index
     * @param type
     * @param id
     * @param scriptStr
     * @return
     */
    public RestStatus updateScript(String index, String type, String id, String scriptStr) {
        RestStatus status = null;
        UpdateRequest request = new UpdateRequest(index, type, id);
        request.script(scriptStr);
        TransportClient client = EsBuilder.builder();
        try {
            UpdateResponse response = client.update(request).get();
            status = response.status();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return status;
    }


    /**
     * update doc
     *
     * @param index
     * @param type
     * @param id
     * @param map
     * @return
     */
    public RestStatus updateDoc(String index, String type, String id, Map<String, Object> map) {
        RestStatus status = null;
        TransportClient client = EsBuilder.builder();
        XContentBuilder builder;
        try {
            builder = XContentFactory.jsonBuilder();
            builder.startObject();
            for (String key : map.keySet()) {
                builder.field(key, map.get(key));
            }
            builder.endObject();
            UpdateRequest request = new UpdateRequest(index, type, id);
            request.doc(builder);
            UpdateResponse response = client.update(request).get();
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


    /**
     * upset
     * <p>
     * If the document does not exist, the one in indexRequest will be added
     *
     * @param index
     * @param type
     * @param id
     * @param map
     * @return
     */
    public RestStatus upsert(String index, String type, String id, Map<String, Object> map) {
        RestStatus status = null;
        TransportClient client = EsBuilder.builder();
        IndexRequest indexRequest = new IndexRequest(index, type, id)
                .source(map);
        UpdateRequest updateRequest = new UpdateRequest(index, type, id)
                .doc(map)
                .upsert(indexRequest);
        try {
            UpdateResponse response = client.update(updateRequest).get();
            status = response.status();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return status;
    }


}
