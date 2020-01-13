package cn.edu.bjfu.igarden.service;

//import cn.edu.bjfu.igarden.dao.InsectSearchDao;

import cn.edu.bjfu.igarden.entity.ChinayuanlinDetail;
import cn.edu.bjfu.igarden.entity.InsectDetail;
import cn.edu.bjfu.igarden.entity.InsectSearchEntity;
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

/**
 * Created by yxy on 2019/4/15.
 */
@Service
public class InsectSearchService {
    @Autowired
    private SolrClient solrClient;

    public List<InsectSearchEntity> recommendInsectByKeywords(String de) throws Exception{
        List<InsectSearchEntity> list = null;
        // 关键字模糊查询
        SolrQuery query = new SolrQuery();
        String insecttitle = "insect_title:" + de;
        String insectothername = "OR insect_othername"+de;
        String insectintroduce = " OR insect_introduce:" + de;
        String insectzhengzhuang = " OR insect_zhengzhuang:" + de;
        String insectyinsu = " OR insect_yinsu:" + de;
        String insectbingyuan = " OR insect_bingyuan:" + de;
        String insectfangfa = " OR insect_fangfa:" + de;
        query.set("q",insecttitle+insectintroduce+insectzhengzhuang+insectyinsu+insectbingyuan+insectfangfa);
//        query.set("q",content + title+type+classification+research);
        query.setStart(0);
        query.setRows(20);
        try {
            QueryResponse response = solrClient.query(query);
            SolrDocumentList documentList = response.getResults();
            if (!documentList.isEmpty()) {
                Gson gson = new Gson();
                String listString = gson.toJson(documentList);
                list = gson.fromJson(listString, new TypeToken<List<InsectSearchEntity>>() {
                }.getType());
            }
        } catch (SolrServerException e) {
            e.getMessage();
        }
        return list;
    }
    public List<InsectSearchEntity> recommendChinayuanlinByKeywords(String de) throws Exception{
        List<InsectSearchEntity> list = null;
        // 关键字模糊查询
        SolrQuery query = new SolrQuery();
        String chinatitle = "china_title:" + de;
        String chinazhengzhuang = " OR china_zhengzhuang:" + de;
//        String chinaxunhuan = " OR china_xunhuan" + de;
        String chinayinsu = " OR china_yinsu:" + de;
        String chinabingyuan = " OR china_bingyuan:" + de;
        String chinafangfa = " OR china_fangfa:" + de;
        query.set("q",chinatitle+chinazhengzhuang+chinayinsu+chinabingyuan+chinafangfa);
//        query.set("q",content + title+type+classification+research);
        query.setStart(0);
        query.setRows(20);
        try {
            QueryResponse response = solrClient.query(query);
            SolrDocumentList documentList = response.getResults();
            if (!documentList.isEmpty()) {
                Gson gson = new Gson();
                String listString = gson.toJson(documentList);
                list = gson.fromJson(listString, new TypeToken<List<InsectSearchEntity>>() {
                }.getType());
            }
        } catch (SolrServerException e) {
            e.getMessage();
        }
        return list;
    }
}
