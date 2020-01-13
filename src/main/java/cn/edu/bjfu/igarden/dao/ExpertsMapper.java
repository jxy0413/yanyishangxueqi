package cn.edu.bjfu.igarden.dao;

import cn.edu.bjfu.igarden.entity.Experts;
import cn.edu.bjfu.igarden.entity.Invite;
import cn.edu.bjfu.igarden.entity.chat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by yxy on 2019/5/6.
 */
@Mapper
public interface ExpertsMapper {
    List<Experts> getAllExperts();
    List<Experts> getExpertsListByType(Experts experts);
    Experts getExpertById(Experts experts);
    List getExpertByName(Experts experts);
    int insertInviteRecord(Invite invite);
    List<Integer> getChatUserList(chat chat);
    List<Integer> getChatToOther(chat chat);
    List<chat> getChatListByUser(chat chat);
    List<chat> getChatListToOther(chat chat);
    int addChat(chat chat);
    int updateChatByreadtime(chat chat);

}
