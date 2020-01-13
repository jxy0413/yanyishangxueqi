package cn.edu.bjfu.igarden.service;

//import cn.edu.bjfu.igarden.dao.InsectSearchDao;

import cn.edu.bjfu.igarden.dao.AnswerMapper;
import cn.edu.bjfu.igarden.entity.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class DiseaseTuiliSolr {
    @Autowired
    private SolrClient solrClient;
    @Autowired
    private AnswerMapper AnswerMapper;

    public List<DiseaseTuili> SearchDiseaseByTuili(DiseaseTuili tuili) throws Exception{
        List<DiseaseTuili> list = null;
        // 关键字模糊查询
        SolrQuery query = new SolrQuery();
        String china_disease="china_disease:"+tuili.getChina_disease();
        String china_buwei = " AND china_buwei:"+tuili.getChina_buwei();
        String china_weihaizz = " AND china_weihaizz:" + tuili.getChina_weihaizz();
        String china_zhengzhuang = " AND china_zhengzhuang:" + tuili.getChina_zhengzhuang();
        query.set("q",china_disease+china_buwei+china_weihaizz+china_zhengzhuang);
//        query.set("q",content + title+type+classification+research);

        query.setStart(0);
        query.setRows(20);
        try {
            QueryResponse response = solrClient.query(query);
            SolrDocumentList documentList = response.getResults();
            if (!documentList.isEmpty()) {
                Gson gson = new Gson();
                String listString = gson.toJson(documentList);
                list = gson.fromJson(listString, new TypeToken<List<DiseaseTuili>>() {
                }.getType());
            }
        } catch (SolrServerException e) {
            e.getMessage();
        }
        return list;
    }
    public disease_inference searchInference(String result) {
        return AnswerMapper.searchInference(result);
    }
    public disease_rule searchAllrule(Integer rule) {
        return AnswerMapper.searchAllrule(rule);
    }
}
