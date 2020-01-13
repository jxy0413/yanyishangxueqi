package cn.edu.bjfu.igarden.controller;

import cn.edu.bjfu.igarden.common.Signer;
import cn.edu.bjfu.igarden.entity.Plant;
import cn.edu.bjfu.igarden.entity.PlantList;
import cn.edu.bjfu.igarden.util.FileUtil;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SignController {
    private static final String KEY = "KWS2YS3wM494bArnT34U5Mx7EHGSDYRK";
    private static final String ClIENT = "LinD0102";
    private static final String GET_FLOWER_LIST_URL = "https://api.aiplants.cn/b64s/recognize";
//    private static String sFlowerUrl = "https://api.aiplants.cn/plants/%1$s/info";
    private static final String FOR_SIGN = "9T6FQ3GR9BU$4T5N";

    @PostMapping(value = "/sign")
    public String sign(@RequestParam("data") String data) {
        return Signer.hmacSha1(data, KEY);
    }

    @GetMapping(value = "/test")
    public String test(String path) {
        return FileUtil.getImageStr(path);
    }

    public PlantList getFlowerList(String data) {
        String position = "test";
        String sign = sign(data);
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setBufferRequestBody(false);
        RestTemplate restTemplate = new RestTemplate(factory);
        HttpHeaders headers = new HttpHeaders();
        //  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("image", data);
        params.add("client", ClIENT);
        params.add("position", position);
        params.add("sign", sign);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        //  执行HTTP请求
        ResponseEntity<PlantList> response = restTemplate.exchange(GET_FLOWER_LIST_URL, HttpMethod.POST, requestEntity, PlantList.class);
        return response.getBody();
    }

    public Plant getFlower(String code) {
        String time = String.valueOf(System.currentTimeMillis());
        String random = String.valueOf(Math.random());
        String sign = sign(time + random + FOR_SIGN);
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setBufferRequestBody(false);
        RestTemplate restTemplate = new RestTemplate(factory);
        HttpHeaders headers = new HttpHeaders();
        //  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client", ClIENT);
        params.add("time", time);
        params.add("random", random);
        params.add("sign", sign);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<Plant> response = restTemplate.exchange(code, HttpMethod.POST, requestEntity, Plant.class);
        return response.getBody();
    }
}
