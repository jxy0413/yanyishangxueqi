package cn.edu.bjfu.igarden.rec_engin;


import cn.edu.bjfu.igarden.rec_engin.handle.sort;
import cn.edu.bjfu.igarden.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sunyang on 2017/5/19.
 * 这个类负责添加新颖性,为用户推荐完全不相关的菜谱.
 */
public class novelty {
    @Autowired
    PlantService plantService;
    public  Map<Integer,Double> getNovelty(int id){


        Map<Integer,Double> res=new HashMap<Integer, Double>();
        ArrayList<Integer> userLikeList=plantService.queryLike(id);


//        ArrayList<String> typeList= NovelDAO.getColumn("节点ID");
//        ArrayList<Integer> userTypeList=new ArrayList<Integer>();
//        /*
//        把用户标记过得菜谱类型号存进去
//         */
//        for (int i = 0; i < userLikeList.size(); i++) {
//            int typeid=Integer.parseInt(NovelDAO.getColumn("菜谱类型号",userLikeList.get(i)));
//            if(!userTypeList.contains(typeid)) userTypeList.add(typeid);
//        }
//        /*
//        遍历一遍所有的类型号,把用户没有的存进去,已有的删除掉
//         */
//        for (int i = 0; i < typeList.size(); i++) {
//            int typeid=Integer.parseInt(typeList.get(i));
//            Iterator<String> iterator = typeList.iterator();
//            while(iterator.hasNext()){
//                int it = Integer.parseInt(iterator.next());
//                if(it == typeid){
//                    iterator.remove();
//                }
//                else userTypeList.add(typeid);
//            }
//        }

        /*
        用随机数生成
        while循环判断,如果已经在用户喜欢列表里呢就重新生成一个
        当生成了2个了就退出.
         */
        int min=0;
        int max=plantService.idlist().size();
        boolean flag=true;
        while(flag){
            int num = min + (int)(Math.random() * (max-min+1));
            int tmp=Integer.parseInt(plantService.idlist().get(num).toString());
            if (!userLikeList.contains(tmp)){
                res.put(tmp,0.0);
            }
            if(res.size()==2) flag=false;
        }

        return res;
    }
    public static List<Map.Entry<Integer,Double>> getRes(Map<Integer,Double> res){
        return sort.sortRes(res);
    }
}
