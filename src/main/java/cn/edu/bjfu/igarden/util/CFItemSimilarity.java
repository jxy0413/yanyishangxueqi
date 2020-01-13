package cn.edu.bjfu.igarden.util;


import cn.edu.bjfu.igarden.entity.Like;
import cn.edu.bjfu.igarden.entity.PlantTuijian;
import cn.edu.bjfu.igarden.entity.User;
import cn.edu.bjfu.igarden.service.PlantService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

@RestController
public class CFItemSimilarity {
    @Autowired
    PlantService plantService;
    @PostMapping(value = "/CFrecommend")
    public  Object Test(int uid) {
//        int uid = 5;
        List<PlantTuijian> v = recommend(uid);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("data",v);
        jsonObject.put("msg","返回数据成功");
        jsonObject.put("code",200);
        return jsonObject;
    }


    public  List<PlantTuijian> recommend(int uid){


        List<Like> likeLists;
        List<User> users = plantService.getAllUsers();
        List<PlantTuijian> papers = plantService.getAllPapers();
        int[][] curMatrix = new int[papers.size()+5][papers.size()+5];
        int[][] comMatrix = new int[papers.size()+5][papers.size()+5];
        int[] N = new int[papers.size()+5];

        for(User user: users){
            if(user.getId()==uid) continue;

            likeLists = plantService.findLikesByUser(user.getId());

            for(int i = 0; i < papers.size(); i++)
                for(int j = 0; j < papers.size(); j++)
                    curMatrix[i][j] = 0;

            for(int i = 0; i < likeLists.size(); i++){
                int pid1 = likeLists.get(i).getPid();
                ++N[pid1];
                for(int j = i+1; j < likeLists.size(); j++){
                    int pid2 = likeLists.get(j).getPid();
                    ++curMatrix[pid1][pid2];
                    ++curMatrix[pid2][pid1];
                }
            }

            for(int i = 0; i < papers.size(); i++){
                for(int j = 0; j < papers.size(); j++){
                    int pid1 = papers.get(i).getId(), pid2 = papers.get(j).getId();
                    comMatrix[pid1][pid2] += curMatrix[pid1][pid2];

                    comMatrix[pid1][pid2] += curMatrix[pid1][pid2];
                }
            }
        }


        TreeSet<PlantTuijian> preList = new TreeSet<PlantTuijian>(new Comparator<PlantTuijian>() {
            @Override
            public int compare(PlantTuijian o1, PlantTuijian o2) {
                if(o1.getW()!=o2.getW())
                    return (int) (o1.getW()-o2.getW())*100;
                else
                    return o1.getHits()-o2.getHits();
            }
        });

        likeLists = plantService.findLikesByUser(uid);
        boolean[] used = new boolean[papers.size()+5];
        for(Like like: likeLists){
            int Nij = 0;
            double Wij;
            PlantTuijian tmp;

            int i = like.getPid();
            for(PlantTuijian paper: papers){
                if(like.getPid() == paper.getId()) continue;
                int j = paper.getId();

                Nij = comMatrix[i][j];
                Wij = (double)Nij/Math.sqrt(N[i]*N[j]);

                tmp = plantService.findPaperById(paper.getId());
                tmp.setW(Wij);

                if(used[tmp.getId()]) continue;
                preList.add(tmp);
                used[tmp.getId()] = true;
            }
        }

        List<PlantTuijian> recomLists = new ArrayList<>();
        for(int i = 0; preList.size()>0 && i<5; i++){
            System.out.println(recomLists);
            recomLists.add(preList.pollLast());
            preList.pollLast();
        }
        if(recomLists.size()<5){
            recomLists = plantService.addTopNPapers(recomLists);
        }

        return recomLists;
    }
}