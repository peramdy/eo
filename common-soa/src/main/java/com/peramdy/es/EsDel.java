package com.peramdy.es;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;

import java.util.concurrent.ExecutionException;

/**
 * @author peramdy
 * @date 2017/12/25.
 */
public class EsDel {


    /**
     * del value
     *
     * @param index
     * @param searchKey
     * @param searchValue
     * @return
     */
    public Long delByQuery(String index, String searchKey, String searchValue) {
        Long status = null;
        TransportClient client = EsBuilder.builder();
        DeleteByQueryRequestBuilder builder = DeleteByQueryAction.INSTANCE.newRequestBuilder(client);
        builder.filter(QueryBuilders.matchQuery(searchKey, searchValue));
        builder.source(index);
        try {
            BulkByScrollResponse response = builder.execute().get();
            status = response.getDeleted();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * del value listen
     *
     * @param index
     * @param searchKey
     * @param searchValue
     */
    public void delByQueryListen(String index, String searchKey, String searchValue) {
        Long status = null;
        TransportClient client = EsBuilder.builder();
        DeleteByQueryRequestBuilder builder = DeleteByQueryAction.INSTANCE.newRequestBuilder(client);
        builder.filter(QueryBuilders.matchQuery(searchKey, searchValue));
        builder.source(index);

        builder.execute(new ActionListener<BulkByScrollResponse>() {
            @Override
            public void onResponse(BulkByScrollResponse response) {
                long deleted = response.getDeleted();
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("del fail");
            }
        });
    }


    /**
     * del index
     *
     * @param indices
     * @return
     */
    public DeleteIndexResponse delIndices(String... indices) {
        TransportClient client = EsBuilder.builder();
        DeleteIndexResponse response = client.admin().indices().prepareDelete(indices).get();
        return response;
    }

}
