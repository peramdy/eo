package com.peramdy.es;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author peramdy
 * @date 2017/12/25.
 */
public class EsSearch {

    /**
     * search all
     *
     * @param index
     * @param type
     * @param pageIndex
     * @param pageSize
     * @param orderByStr
     * @return
     */
    public List<String> searchAll(String index, String type, Integer pageIndex, Integer pageSize, String orderByStr) {
        TransportClient client = EsBuilder.builder();
        SearchRequestBuilder builder = client.prepareSearch(index);
        builder.setTypes(type);
        builder.setQuery(QueryBuilders.matchAllQuery());
        builder.setSearchType(SearchType.QUERY_THEN_FETCH);
        builder.setFrom(pageIndex);
        builder.setSize(pageSize);
        builder.addSort(orderByStr, SortOrder.DESC);
        SearchResponse response = builder.get();
        SearchHits hits = response.getHits();
        SearchHit[] searchHits = hits.getHits();
        List<String> list = new ArrayList<>();
        for (SearchHit s : searchHits) {
            list.add(s.getSourceAsString());
            System.out.println(s.getSourceAsString());
        }
        return list;
    }


    /**
     * search
     *
     * @param index
     * @param type
     * @param searchKey
     * @param searchValue
     * @return
     */
    public List<String> searchKey(String index, String type, String searchKey, Object searchValue) {
        TransportClient client = EsBuilder.builder();
        SearchRequestBuilder builder = client.prepareSearch(index);
        builder.setTypes(type);
        builder.setQuery(QueryBuilders.matchPhraseQuery(searchKey, searchValue));
        builder.setSearchType(SearchType.QUERY_THEN_FETCH);
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        /**** highlight ****/
        highlightBuilder.preTags("<font color='red'>");
        highlightBuilder.postTags("</font>");
        builder.highlighter(highlightBuilder);
        builder.setFrom(0);
        builder.setSize(10);
        SearchResponse response = builder.get();
        SearchHits hits = response.getHits();
        SearchHit[] searchHits = hits.getHits();
        List<String> list = new ArrayList<>();
        for (SearchHit s : searchHits) {
            list.add(s.getSourceAsString());
            System.out.println(s.getSourceAsString());
        }
        return list;
    }

}
