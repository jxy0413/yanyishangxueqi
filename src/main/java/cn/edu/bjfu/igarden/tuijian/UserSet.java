package cn.edu.bjfu.igarden.tuijian;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccwant on 2018-12-14.
 */
public class UserSet {

    public List<SUser> users = new ArrayList<>();

    public UserSet() {
    }

    public SUser put(String username) {
        return new SUser(username);
    }


    public SUser getUser(int position) {
        return users.get(position);
    }

    public SUser getUser(String username) {
        for (SUser user : users) {
            if (user.username.equals(username)) {
                return user;
            }
        }
        return null;
    }


    public final class SUser {
        public String username;
        public List<Set> list = new ArrayList<>();

        private SUser(String username) {
            this.username = username;
        }

        public SUser set(String username, int score) {
            this.list.add(new Set(username, score));
            return this;
        }

        public void create() {
            users.add(this);
        }

        public Set find(String username) {
            for (Set set : list) {
                if (set.username.equals(username)) {
                    return set;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    '}';
        }
    }

    public final class Set implements Comparable<Set> {
        public String username;
        public int score;

        public Set(String username, int score) {
            this.username = username;
            this.score = score;
        }

        @Override
        public String toString() {
            return "Set{" +
                    "username='" + username + '\'' +
                    ", score=" + score +
                    '}';
        }

        @Override
        public int compareTo(Set o) {
            return score > o.score ? -1 : 1;
        }
    }

}
