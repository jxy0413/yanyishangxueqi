//package cn.edu.bjfu.igarden.model;
//
//import cn.edu.bjfu.igarden.dao.AnswerMapper;
//import cn.edu.bjfu.igarden.dao.SearchRepository;
//import cn.edu.bjfu.igarden.entity.*;
//import cn.edu.bjfu.igarden.service.ParkService;
//import com.google.gson.Gson;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.*;
//import org.springframework.http.client.SimpleClientHttpRequestFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//
//
//    @Service
//    @Transactional
//    public class ParkServiceImpl implements ParkService {
//        @Autowired
//        private AnswerMapper plantDetailMapper;
//
//        public List<Parkinfo> getParkQueryList(String name,String type,String zone) {
//
//            ParkinfoExample parkinfoExample=new ParkinfoExample();
//            ParkinfoExample .Criteria exampleCriteria = parkinfoExample.createCriteria();
//            if(parkinfoExample.getName()!=null) {
//                exampleCriteria.andNameLike("%" + queryVo.getName() + "%");
//            }
//            if(queryVo.getZone()!=null) {
//                exampleCriteria.andZoneEqualTo(queryVo.getZone());
//            }
//            if(queryVo.getType()!=null) {
//                exampleCriteria.andTypeEqualTo(queryVo.getType());
//
//            }
//
//
//            return saveScenery(scenery);
//        }
//}
