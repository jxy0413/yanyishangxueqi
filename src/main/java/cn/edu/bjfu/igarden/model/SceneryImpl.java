package cn.edu.bjfu.igarden.model;

import cn.edu.bjfu.igarden.dao.SceneryRepository;
import cn.edu.bjfu.igarden.entity.Province;
import cn.edu.bjfu.igarden.entity.Scenery;
import cn.edu.bjfu.igarden.entity.SceneryTable;
import cn.edu.bjfu.igarden.util.LogUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class SceneryImpl {
    // 当前默认仅允许搜索当前城市的景区，后期可扩展到全国搜索，增加省份选择列表
    private static final String SHOWAPI_APPID = "66705";
    private static final String SHOWAPI_SIGN = "1f40230ffc50421ebba0a3e3c44b0740";
    private static final String GET_SCENERY_URL = "http://route.showapi.com/268-1";
    private static final String GET_PROVINCE = "http://route.showapi.com/268-2";

    @Autowired
    SceneryRepository sceneryRepository;

    private List<SceneryTable> saveScenery(Scenery scenery) {
        List<SceneryTable> sceneryTables = new ArrayList<>();
        for (Scenery.ShowapiResBodyBean.PagebeanBean.ContentlistBean content : scenery.getShowapi_res_body().getPagebean().getContentlist()) {
            SceneryTable saveTable;
            SceneryTable sceneryTable = sceneryRepository.findByName(content.getName() == null ? null : content.getName().trim());
            if (sceneryTable != null) {
                saveTable = sceneryTable;
                saveTable.setDeleteTime(0);
            } else {
                saveTable = new SceneryTable();
                saveTable.setCreateTime(System.currentTimeMillis() / 1000);
            }
            saveTable.setName(content.getName() == null ? null : content.getName().trim());
            saveTable.setAreaName(content.getAreaName() == null ? null : content.getAreaName().trim());
            saveTable.setCityName(content.getCityName() == null ? null : content.getCityName().trim());
            saveTable.setProName(content.getProName() == null ? null : content.getProName().trim());
            saveTable.setAddress(content.getAddress() == null ? null : content.getAddress().trim());
            saveTable.setSummary(content.getSummary() == null ? null : content.getSummary().trim());
            saveTable.setOpenTime(content.getOpentime() == null ? null : content.getOpentime().trim());
            saveTable.setAttention(content.getAttention() == null ? null : content.getAttention().trim());
            saveTable.setCoupon(content.getCoupon() == null ? null : content.getCoupon().trim());
            saveTable.setPicList(new Gson().toJson(content.getPicList()));
            saveTable.setPics(content.getPicList());
            saveTable.setStar(content.getStar() == null ? null : content.getStar().trim());
            if (content.getLocation() == null || Double.valueOf(content.getLocation().getLat()) == 0) {
                saveTable.setLatitude(null);
            } else {
                saveTable.setLatitude(content.getLocation().getLat());
            }
            if (content.getLocation() == null || Double.valueOf(content.getLocation().getLon()) == 0) {
                saveTable.setLongitude(null);
            } else {
                saveTable.setLongitude(content.getLocation().getLon());
            }
            saveTable.setUpdateTime(System.currentTimeMillis() / 1000);
            sceneryRepository.save(saveTable);
            saveTable.setPicList(null);
            sceneryTables.add(saveTable);
        }
        return sceneryTables;
    }

    /**
     * 景区搜索
     *
     * @param keyword 关键字
     * @param page    页数
     * @param proId   省份id
     * @return 返回景区数据
     */
    public List<SceneryTable> getScenery(String keyword, String page, String proId) {
        if (keyword.contains("'")) {
            keyword = keyword.replaceAll("'", "");
        }
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setBufferRequestBody(false);
        RestTemplate restTemplate = new RestTemplate(factory);
        HttpHeaders headers = new HttpHeaders();
        //  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("showapi_appid", SHOWAPI_APPID);
        params.add("showapi_sign", SHOWAPI_SIGN);
        params.add("keyword", keyword);
        params.add("page", page);
        params.add("proId", proId);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.exchange(GET_SCENERY_URL, HttpMethod.POST, requestEntity, String.class);
        String json = response.getBody();

        Scenery scenery = new Gson().fromJson(json, Scenery.class);
        return saveScenery(scenery);
    }

    /**
     * 获取省份信息
     */
    public Province getProvince() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setBufferRequestBody(false);
        RestTemplate restTemplate = new RestTemplate(factory);
        HttpHeaders headers = new HttpHeaders();
        //  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("showapi_appid", SHOWAPI_APPID);
        params.add("showapi_sign", SHOWAPI_SIGN);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.exchange(GET_PROVINCE, HttpMethod.POST, requestEntity, String.class);
        String json = response.getBody();

        return new Gson().fromJson(json, Province.class);
    }
}
