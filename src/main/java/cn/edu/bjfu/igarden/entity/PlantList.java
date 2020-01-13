package cn.edu.bjfu.igarden.entity;

import java.util.List;

public class PlantList {

    /**
     * status : 0
     * message : OK
     * result : [{"score":"21.33","aliasList":[],"genus":"鼠尾草属","infoUrl":"https://api.aiplants.cn/plants/mt6Tlmvu8HuLsMfT/info","imageUrl":"https://static.nongbangzhu.cn/samples_v4/p11k/p11k-watermark/%E9%BC%A0%E5%B0%BE%E8%8D%89/125e28aeb06eeed7.jpg","name":"鼠尾草","latinName":"Salvia japonica","family":"唇形科"},{"score":"18.06","aliasList":["单色翼萼"],"genus":"蝴蝶草属","infoUrl":"https://api.aiplants.cn/plants/tQLpbQ2DPIH5VsQ9/info","imageUrl":"https://static.nongbangzhu.cn/samples_v4/p11k/p11k-watermark/%E5%8D%95%E8%89%B2%E8%9D%B4%E8%9D%B6%E8%8D%89/102196d0877b9c2e.jpg","name":"单色蝴蝶草","latinName":"Torenia concolor","family":"母草科"},{"score":"11.53","aliasList":["元参","浙玄参","水萝卜"],"genus":"玄参属","infoUrl":"https://api.aiplants.cn/plants/LG1XAMOjF1zjXBUE/info","imageUrl":"https://static.nongbangzhu.cn/samples_v4/p11k/p11k-watermark/%E7%8E%84%E5%8F%82/10214126bbfd1f99.jpg","name":"玄参","latinName":"Scrophularia ningpoensis","family":"玄参科"},{"score":"5.09","aliasList":["菠萝","露兜子"],"genus":"凤梨属","infoUrl":"https://api.aiplants.cn/plants/0o1BlVLIK8nT0d1i/info","imageUrl":"https://static.nongbangzhu.cn/samples_v4/p11k/p11k-watermark/%E5%87%A4%E6%A2%A8/112faebadf325910.jpg","name":"凤梨","latinName":"Ananas comosus","family":"凤梨科"},{"score":"5.06","aliasList":[],"genus":"鼠尾草属","infoUrl":"https://api.aiplants.cn/plants/7nKDYxFgq3suyrer/info","imageUrl":"https://static.nongbangzhu.cn/samples_v4/p11k/p11k-watermark/%E4%B8%B9%E5%8F%82/11095d364b200676.jpg","name":"丹参","latinName":"Salvia miltiorrhiza","family":"唇形科"}]
     */

    private int status;
    private String message;
    public List<ResultBean> result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * score : 21.33
         * aliasList : []
         * genus : 鼠尾草属
         * infoUrl : https://api.aiplants.cn/plants/mt6Tlmvu8HuLsMfT/info
         * imageUrl : https://static.nongbangzhu.cn/samples_v4/p11k/p11k-watermark/%E9%BC%A0%E5%B0%BE%E8%8D%89/125e28aeb06eeed7.jpg
         * name : 鼠尾草
         * latinName : Salvia japonica
         * family : 唇形科
         */

        private String score;
        private String genus;
        private String infoUrl;
        private String imageUrl;
        private String name;
        private String latinName;
        private String family;
        private List<?> aliasList;

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getGenus() {
            return genus;
        }

        public void setGenus(String genus) {
            this.genus = genus;
        }

        public String getInfoUrl() {
            return infoUrl;
        }

        public void setInfoUrl(String infoUrl) {
            this.infoUrl = infoUrl;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLatinName() {
            return latinName;
        }

        public void setLatinName(String latinName) {
            this.latinName = latinName;
        }

        public String getFamily() {
            return family;
        }

        public void setFamily(String family) {
            this.family = family;
        }

        public List<?> getAliasList() {
            return aliasList;
        }

        public void setAliasList(List<?> aliasList) {
            this.aliasList = aliasList;
        }
    }
}
