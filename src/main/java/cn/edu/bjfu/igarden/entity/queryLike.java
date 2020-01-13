package cn.edu.bjfu.igarden.entity;

public class queryLike {
    int user_id; //发起喜欢的用户
    int like_id; //被喜欢的文章
    int lid; //该喜欢行为的id

    public queryLike(int uid, int pid, int lid) {
        this.user_id = uid;
        this.like_id = pid;
        this.lid = lid;
    }

    public queryLike(){

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getLike_id() {
        return like_id;
    }

    public void setLike_id(int like_id) {
        this.like_id = like_id;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }
}
