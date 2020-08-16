package com.hzy.es.springbootesdemo;

import com.alibaba.fastjson.JSON;
import com.hzy.es.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
class SpringBootEsDemoApplicationTests {

    @Resource
    private RestHighLevelClient client;

    /**
     * 创建索引
     *
     * @throws IOException
     */
    @Test
    void createIndicesDemo() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("springboot_api");
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        log.info(JSON.toJSONString(response));
    }

    /**
     * 测试索引是否存在
     */
    @Test
    public void testExistIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("springboot_api");
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        log.info("测试是否存在索引{}", exists);
    }

    /**
     * 测试删除索引
     *
     * @throws IOException
     */
    @Test
    void testDeleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("springboot_api");
        AcknowledgedResponse acknowledgedResponse = client.indices().delete(request, RequestOptions.DEFAULT);
        log.info(JSON.toJSONString(acknowledgedResponse));
    }

    /**
     * 测试添加文档
     */
    @Test
    void testAddDocument() throws IOException {
        User user = new User("张三", 18, "chengxuyuan");
        // 创建请求
        IndexRequest request = new IndexRequest("springboot_api");
        request.id("1");
        request.timeout("1s");
        request.timeout(TimeValue.timeValueSeconds(1));
        // 放入数据
        request.source(JSON.toJSON(user), XContentType.JSON);
        // 发送请求
        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);

        log.info(JSON.toJSONString(indexResponse));
        log.info(JSON.toJSONString(indexResponse.status()));
    }

    /**
     * 判断文档是否存在
     */
    @Test
    void testExistsDocument() throws IOException {
        GetRequest request = new GetRequest("springboot_api", "1");
        // 配置不用获取 source 的上下文
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");

        boolean exists = client.exists(request, RequestOptions.DEFAULT);
        log.info("判断是否存在{}", exists);
    }

    /**
     * 获取文档内容
     *
     * @throws IOException
     */
    @Test
    void testGetDocument() throws IOException {
        GetRequest request = new GetRequest("springboot_api", "1");
        GetResponse getResponse = client.get(request, RequestOptions.DEFAULT);
        log.info(JSON.toJSONString(getResponse));
        log.info(JSON.toJSONString(getResponse.getSourceAsString()));
    }

    /**
     * 测试更新文档
     *
     * @throws IOException
     */
    @Test
    void testUpdateDocument() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("springboot_api", "1");
        updateRequest.timeout("1s");
        User user = new User("法外狂徒", 17, "chengxuyuan");
        updateRequest.doc(JSON.toJSONString(user), XContentType.JSON);
        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
        log.info(JSON.toJSONString(updateResponse));
        log.info(JSON.toJSONString(updateResponse.status()));
    }

    /**
     * 测试删除文档
     *
     * @throws IOException
     */
    @Test
    void testDeleteDocument() throws IOException {
        DeleteRequest request = new DeleteRequest("springboot_api", "1");
        request.timeout("1s");

        DeleteResponse deleteResponse = client.delete(request, RequestOptions.DEFAULT);
        log.info(JSON.toJSONString(deleteResponse));
        log.info(JSON.toJSONString(deleteResponse.status())); // NOT_FOUND 或者 OK
    }

    /**
     * 批量增加文档
     * @throws IOException
     */
    @Test
    void testBatchRequest() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");

        ArrayList<User> users = new ArrayList<>();
        users.add(new User("张三", 18, "测试张三"));
        users.add(new User("李四", 18, "测试李四"));
        users.add(new User("张三岁", 3, "测试张三岁"));
        users.add(new User("张三强", 42, "测试张三强"));
        users.add(new User("玛丽", 44, "测试玛丽"));
        users.add(new User("王五", 22, "测试王五"));
        users.add(new User("王宝强", 15, "测试王宝强"));
        users.add(new User("张三丰", 75, "测试张三丰"));

        for (int i = 0; i < users.size(); i++) {
            bulkRequest.add(new IndexRequest("springboot_api")
                    .id("" + (i + 1)) //不设置则为随机ID
                    .source(JSON.toJSONString(users.get(i)), XContentType.JSON));
            BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
            log.info(JSON.toJSONString(bulkResponse));
            log.info(String.valueOf(bulkResponse.hasFailures()));
        }
    }

    /**
     * 测试搜索
     * @throws IOException
     */
    @Test
    void testSearch() throws IOException {
        // 创建请求
        SearchRequest searchRequest = new SearchRequest("springboot_api");
        // 构造搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 设置高亮
//        sourceBuilder.highlighter();
        // 精确查询
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("username","张三");
        sourceBuilder.query(termQueryBuilder);
        // 匹配所有
//        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
//        sourceBuilder.query(matchAllQueryBuilder);
        // 超时
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        // 设置搜索条件
        searchRequest.source(sourceBuilder);

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        log.info(JSON.toJSONString(hits));
        for (SearchHit hit : hits) {
            log.info(JSON.toJSONString(hit));
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            log.info(JSON.toJSONString(sourceAsMap));
        }

    }


}
