package cn.edu.bjfu.igarden.util;

import cn.edu.bjfu.igarden.dao.ChinayuanlindetailRepository;
import cn.edu.bjfu.igarden.entity.ChinayuanlinDetail;
import cn.edu.bjfu.igarden.entity.Collect;
import cn.edu.bjfu.igarden.entity.User;
import cn.edu.bjfu.igarden.service.CollectService;
import cn.edu.bjfu.igarden.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Comparator;

@RestController
public class DiseaseItemSimilarity {

    @Autowired
    private UserService userService;
    @Autowired
    ChinayuanlindetailRepository chinayuanlindetailRepository;
    @Autowired
    private CollectService collectService;

    @PostMapping(value = "/DiseaseSimilarity")
    public Object DiseaseSimilarity(@RequestParam("userid") int userid) {
        JSONObject jsonObject = new JSONObject();
        //通过计算余弦相似度并取TopN, 返回为uid的用户生成的5个推荐文章
        ArrayList<ChinayuanlinDetail> preList = new ArrayList<>(); //预处理的列表

        ArrayList<Collect> likeLists;                                       //其他用户喜欢的论文列表

        ArrayList<User> users = userService.getAllUser();//所有用户列表
        ArrayList<ChinayuanlinDetail> papers = collectService.findAllchinayuanlindetail();               //所有病虫害列表
        int num = papers.get(papers.size() - 1).getId();  //获取病虫害详情的最后一个Id号作为数组的大小
        int[][] curMatrix = new int[num+5][num+5];   //当前矩阵
        int[][] comMatrix = new int[num+5][num+5];   //共现矩阵
        int[] N = new int[num+5];                              //喜欢每个病虫害的人数

        for (int i = 0; i < papers.size(); i++) {
            for (int j = 0; j < papers.size(); j++) {
                comMatrix[i][j] = 0;                               //共现矩阵初始化
            }
        }
        for (User user : users) {
            likeLists = (ArrayList<Collect>) collectService.getCollectListByType(2, user.getId()); //当前用户的喜欢列表
            for (int i = 0; i < papers.size(); i++) {
                for (int j = 0; j < papers.size(); j++) {
                    curMatrix[i][j] = 0;                               //清空矩阵
                }
            }
            for (int i = 0; i < likeLists.size(); i++) {                //构建当前矩阵
                int pid1 = likeLists.get(i).getChinayuanlinid();
                ++N[pid1];
                for (int j = i + 1; j < likeLists.size(); j++) {
                    int pid2 = likeLists.get(j).getChinayuanlinid();
                    ++curMatrix[pid1][pid2];
                    ++curMatrix[pid2][pid1]; //两两加一
                }
            }

            //累加所有矩阵, 得到共现矩阵
            for (int i = 0; i < papers.size(); i++) {
                for (int j = 0; j < papers.size(); j++) {
                    int pid1 = papers.get(i).getId();
                    int pid2 = papers.get(j).getId();
                    comMatrix[pid1][pid2] += curMatrix[pid1][pid2];
                }
            }
        }
        likeLists = (ArrayList<Collect>) collectService.getCollectListByType(2, userid);
        boolean[] used = new boolean[num+5];  //判重数组
        boolean[] update = new boolean[num+5];//更新权值
        for (int i = 0; i < likeLists.size(); i++) {
            int myid = likeLists.get(i).getChinayuanlinid();
            used[myid] = true;
        }
        for (int i = 0; i < likeLists.size(); i++) {
            int Nij = 0;                         //既喜欢i又喜欢j的人数
            double Wij;                          //相似度
            ChinayuanlinDetail tmp;              //当前的病虫害
            int myid = likeLists.get(i).getChinayuanlinid();
            for (int j = 0; j < num; j++) {
                int otherid = j;
                if (used[otherid]) {
                    continue;
                }
                if (comMatrix[myid][otherid] != 0) {
                    Nij = comMatrix[myid][otherid];
                    Wij = (double) Nij / Math.sqrt(N[myid] * N[otherid]);
                    tmp = chinayuanlindetailRepository.findChinayuanlinDetailById(otherid); //他人喜欢的病虫害
                    if (update[otherid]) {
                        if (tmp.getW() < Wij) {
                            tmp.setW(Wij);
                        }
                        continue;
                    }
                    tmp.setW(Wij);
                    preList.add(tmp);
                    update[otherid] = true;
                }
            }
        }

        preList.sort(new Comparator<ChinayuanlinDetail>() {                  //将得到的余弦值排序
            @Override
            public int compare(ChinayuanlinDetail arg0, ChinayuanlinDetail arg1) {
                if (arg0.getW() > arg1.getW()) {
                    return -1;
                } else if (arg0.getW() == arg1.getW()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        ArrayList<ChinayuanlinDetail> recomLists = new ArrayList<>();      //生成的推荐结果
        if(preList.size() >0){  //得到推荐结果将结果注入最终数组中
            for (int i = 0;i < 5 && i < preList.size(); i++) {
                System.out.println(preList.get(i));
                recomLists.add(preList.get(i));
            }
            if(recomLists.size()<5){
                int needNum=5-recomLists.size();
                for(int i=0;i<needNum;i++){
                    recomLists.add(collectService.findTop().get(i)); //推荐结果不足5个，按照readtimes从大到小进行注入
                }

            }

        }else{
            for (int i=0;i<5;i++){
                recomLists.add(collectService.findTop().get(i));//推荐无结果，按照readtimes从大到小进行注入
            }

        }
        jsonObject.put("code", "200");
        jsonObject.put("message", "回答成功");
        jsonObject.put("data", recomLists);

        //测试小Demo
        ArrayList<ChinayuanlinDetail> v = recomLists;
        System.out.println("正在生成针对用户id为" + userid + "的推荐...");
        for (int i = 0; i < v.size(); i++)
            System.out.println("第" + (i + 1) + "个推荐: 题目:" + v.get(i).getTitle() + "");
        return jsonObject;

    }
}