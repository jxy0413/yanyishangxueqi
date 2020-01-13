package cn.edu.bjfu.igarden.service;

import cn.edu.bjfu.igarden.controller.ExpertSolr;
import cn.edu.bjfu.igarden.entity.Experts;
import cn.edu.bjfu.igarden.entity.Question;
import cn.edu.bjfu.igarden.entity.SolrPlant;
import cn.edu.bjfu.igarden.entity.Supplier;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Cookie on 2019/5/22.
 */
@Service
public class PlantSolrService {
    @Autowired
    private SolrClient solrClient;

    public List<SolrPlant> recommendPlantByKeywords(String de) throws Exception{
        List<SolrPlant> list = null;
        // 关键字模糊查询
        SolrQuery query = new SolrQuery();
        String plant_alias = "plant_alias:" + de;
        String plant_name = " OR plant_name:" + de;
        String plant_description = " OR plant_description:" + de;
        String plant_xgsc = " OR plant_xgsc:" + de;
        String plant_jzgy = " OR plant_jzgy:" + de;
        String plant_fbdq = " OR plant_fbdq:" + de;
        String plant_bxtz = " OR plant_bxtz:" + de;
        query.set("q",plant_alias+plant_name+plant_description+plant_xgsc+plant_jzgy+plant_fbdq+plant_bxtz);
//        query.set("q",content + title+type+classification+research);
        query.setStart(0);
        query.setRows(15);
        try {
            QueryResponse response = solrClient.query(query);
            SolrDocumentList documentList = response.getResults();
            if (!documentList.isEmpty()) {
                Gson gson = new Gson();
                String listString = gson.toJson(documentList);
                list = gson.fromJson(listString, new TypeToken<List<SolrPlant>>() {
                }.getType());
            }
        } catch (SolrServerException e) {
            e.getMessage();
        }
        return list;
    }
    public List<Supplier> recommendSupplier(String de) throws Exception{
        List<Supplier> list = null;
        // 关键字模糊查询
        SolrQuery query = new SolrQuery();
        String supplier_flower = "supplier_flower:" + de;
        String supplier_address = " OR supplier_address:" + de;
        String supplier_name = " OR supplier_name:" + de;
        query.set("q",supplier_flower+supplier_address+supplier_name);
//        query.set("q",content + title+type+classification+research);
        query.setStart(0);
        query.setRows(3);
        try {
            QueryResponse response = solrClient.query(query);
            SolrDocumentList documentList = response.getResults();
            if (!documentList.isEmpty()) {
                Gson gson = new Gson();
                String listString = gson.toJson(documentList);
                list = gson.fromJson(listString, new TypeToken<List<Supplier>>() {
                }.getType());
            }
        } catch (SolrServerException e) {
            e.getMessage();
        }
        return list;
    }
    public List<Question> searchQuestion(String de) throws Exception{
        List<Question> list = null;
        // 关键字模糊查询
        SolrQuery query = new SolrQuery();
        String title = "questiontitle:" + de;
        String content = " OR questioncontent:" + de;
        query.set("q",title+content);
//        query.set("q",content + title+type+classification+research);
        query.setStart(0);
        query.setRows(30);
        try {
            QueryResponse response = solrClient.query(query);
            SolrDocumentList documentList = response.getResults();
            if (!documentList.isEmpty()) {
                Gson gson = new Gson();
                String listString = gson.toJson(documentList);
                list = gson.fromJson(listString, new TypeToken<List<Question>>() {
                }.getType());
            }
        } catch (SolrServerException e) {
            e.getMessage();
        }
        return list;
    }
    public List<Supplier> findSupplierById(int supplierId) throws Exception{
        List<Supplier> list = null;
        // 关键字模糊查询
        SolrQuery query = new SolrQuery();
        String supplierid = "supplierid:" + supplierId;
        query.set("q",supplierid);
//        query.set("q",content + title+type+classification+research);
        query.setStart(0);
        query.setRows(3);
        try {
            QueryResponse response = solrClient.query(query);
            SolrDocumentList documentList = response.getResults();
            if (!documentList.isEmpty()) {
                Gson gson = new Gson();
                String listString = gson.toJson(documentList);
                list = gson.fromJson(listString, new TypeToken<List<Supplier>>() {
                }.getType());
            }
        } catch (SolrServerException e) {
            e.getMessage();
        }
        return list;
    }
    public List<ExpertSolr> searchExpert(String de) throws Exception{
        List<ExpertSolr> list = null;
        // 关键字模糊查询
        SolrQuery query = new SolrQuery();
        String expertsclassification = "expertsclassification:" + de;
        String expertsachievement = " OR expertsachievement:" + de;
        String expertstype = " OR expertstype:" + de;
        query.set("q",expertsclassification+expertsachievement+expertstype);
//        query.set("q",content + title+type+classification+research);
        query.setStart(0);
        query.setRows(10);
        try {
            QueryResponse response = solrClient.query(query);
            SolrDocumentList documentList = response.getResults();
            if (!documentList.isEmpty()) {
                Gson gson = new Gson();
                String listString = gson.toJson(documentList);
                list = gson.fromJson(listString, new TypeToken<List<ExpertSolr>>() {
                }.getType());
            }
        } catch (SolrServerException e) {
            e.getMessage();
        }
        System.out.println(list.toString());
        return list;
    }
}
