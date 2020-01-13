package cn.edu.bjfu.igarden.entity;

import java.util.List;

public class Plant {

    /**
     * status : 0
     * message : OK
     * result : {"nameStd":"丁香杜鹃","nameLt":"Rhododendron farrerae","familyCn":"杜鹃花科","genusCn":"杜鹃属","alias":"华丽杜鹃","description":"该种与满山红相似，但不同在于该种的叶较小， 先端钝， 基部近圆形;叶柄较短， 通常⻓ 仅2毫米， 密被锈色⻓ 柔毛， 花冠紫丁香色;果柄弯曲， 易于区别。 ","info":{"xgsc":"","jzgy":"杜鹃枝繁叶茂，绮丽多姿，萌发力强，耐修 剪，根桩奇特，是优良的盆景材料。园林中最宜在林缘、溪边、池畔及岩 石旁成丛成片栽植，也可于疏林下散植，是花篱的良好材料，可经修剪培 育成各种形态。","hyyy":"","fbdq":"产中国江⻄、福建、湖南、广东、广⻄。","mcll":"","yhjs":"杜鹃生⻓发育要求酸性土壤。由于北方土壤多偏碱性， 因此盆土需用腐熟的松针叶土等腐植土混合配制。 杜鹃的根系 为须状细根， 对肥料浓度及水质的要求严格， 施肥时要遵循适时适量、 薄 肥勤施的原则。 在生⻓ 期、 开花期肥水要求较多， 冬季休眠、 夏季生⻓ 缓 慢时要控制肥水， 以防烂根。 杜鹃花喜湿润和凉爽的环境， 北方气候干 燥， 应及时浇水并喷雾， 以保持较高空气湿度。 ","bxtz":"落叶灌木，高1.5-3米。枝短而坚硬，⻩褐 色，幼时被铁锈色⻓柔毛，后渐近无毛。叶近于革质，常集生枝顶，卵 形。花1-2朵顶生，先花后叶;花冠辐状漏斗形，紫丁香色，径3.8-5厘 米，花冠管短而狭筒状，5裂，裂片开展。蒴果⻓圆柱形，弯曲，密被红 棕色⻓柔毛。","hksj":"花期5-6月，果期7-8月。"},"images":["http://192.168.1.51:8080/resource/1/丁香 杜鹃/1303824.jpg","http://192.168.1.51:8080/resource/1/丁香 杜鹃/2643773.jpg","http://192.168.1.51:8080/resource/1/丁香 杜鹃/2643774.jpg","http://192.168.1.51:8080/resource/1/丁香 杜鹃/1303825.jpg"]}
     */

    private int status;
    private String message;
    public ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * nameStd : 丁香杜鹃
         * nameLt : Rhododendron farrerae
         * familyCn : 杜鹃花科
         * genusCn : 杜鹃属
         * alias : 华丽杜鹃
         * description : 该种与满山红相似，但不同在于该种的叶较小， 先端钝， 基部近圆形;叶柄较短， 通常⻓ 仅2毫米， 密被锈色⻓ 柔毛， 花冠紫丁香色;果柄弯曲， 易于区别。
         * info : {"xgsc":"","jzgy":"杜鹃枝繁叶茂，绮丽多姿，萌发力强，耐修 剪，根桩奇特，是优良的盆景材料。园林中最宜在林缘、溪边、池畔及岩 石旁成丛成片栽植，也可于疏林下散植，是花篱的良好材料，可经修剪培 育成各种形态。","hyyy":"","fbdq":"产中国江⻄、福建、湖南、广东、广⻄。","mcll":"","yhjs":"杜鹃生⻓发育要求酸性土壤。由于北方土壤多偏碱性， 因此盆土需用腐熟的松针叶土等腐植土混合配制。 杜鹃的根系 为须状细根， 对肥料浓度及水质的要求严格， 施肥时要遵循适时适量、 薄 肥勤施的原则。 在生⻓ 期、 开花期肥水要求较多， 冬季休眠、 夏季生⻓ 缓 慢时要控制肥水， 以防烂根。 杜鹃花喜湿润和凉爽的环境， 北方气候干 燥， 应及时浇水并喷雾， 以保持较高空气湿度。 ","bxtz":"落叶灌木，高1.5-3米。枝短而坚硬，⻩褐 色，幼时被铁锈色⻓柔毛，后渐近无毛。叶近于革质，常集生枝顶，卵 形。花1-2朵顶生，先花后叶;花冠辐状漏斗形，紫丁香色，径3.8-5厘 米，花冠管短而狭筒状，5裂，裂片开展。蒴果⻓圆柱形，弯曲，密被红 棕色⻓柔毛。","hksj":"花期5-6月，果期7-8月。"}
         * images : ["http://192.168.1.51:8080/resource/1/丁香 杜鹃/1303824.jpg","http://192.168.1.51:8080/resource/1/丁香 杜鹃/2643773.jpg","http://192.168.1.51:8080/resource/1/丁香 杜鹃/2643774.jpg","http://192.168.1.51:8080/resource/1/丁香 杜鹃/1303825.jpg"]
         */
        private String id;
        private String nameStd;
        private String nameLt;
        private String familyCn;
        private String genusCn;
        private String devisionCn;
        private String alias;
        private String description;
        private String qr;
        private InfoBean info;
        private List<String> images;

        public String getNameStd() {
            return nameStd;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setNameStd(String nameStd) {
            this.nameStd = nameStd;
        }

        public String getNameLt() {
            return nameLt;
        }

        public void setNameLt(String nameLt) {
            this.nameLt = nameLt;
        }

        public String getFamilyCn() {
            return familyCn;
        }

        public void setFamilyCn(String familyCn) {
            this.familyCn = familyCn;
        }

        public String getGenusCn() {
            return genusCn;
        }

        public void setGenusCn(String genusCn) {
            this.genusCn = genusCn;
        }

        public String getDevisionCn() {
            return devisionCn;
        }

        public void setDevisionCn(String devisionCn) {
            this.devisionCn = devisionCn;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getQr() {
            return qr;
        }

        public void setQr(String qr) {
            this.qr = qr;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public static class InfoBean {
            /**
             * xgsc 相关诗词 :
             * jzgy 价值功用 : 杜鹃枝繁叶茂，绮丽多姿，萌发力强，耐修 剪，根桩奇特，是优良的盆景材料。园林中最宜在林缘、溪边、池畔及岩 石旁成丛成片栽植，也可于疏林下散植，是花篱的良好材料，可经修剪培 育成各种形态。
             * hyyy :
             * fbdq 分布地区 : 产中国江⻄、福建、湖南、广东、广⻄。
             * mcll :
             * yhjs 养护技术 : 杜鹃生⻓发育要求酸性土壤。由于北方土壤多偏碱性， 因此盆土需用腐熟的松针叶土等腐植土混合配制。 杜鹃的根系 为须状细根， 对肥料浓度及水质的要求严格， 施肥时要遵循适时适量、 薄 肥勤施的原则。 在生⻓ 期、 开花期肥水要求较多， 冬季休眠、 夏季生⻓ 缓 慢时要控制肥水， 以防烂根。 杜鹃花喜湿润和凉爽的环境， 北方气候干 燥， 应及时浇水并喷雾， 以保持较高空气湿度。
             * bxtz 表型特征 : 落叶灌木，高1.5-3米。枝短而坚硬，⻩褐 色，幼时被铁锈色⻓柔毛，后渐近无毛。叶近于革质，常集生枝顶，卵 形。花1-2朵顶生，先花后叶;花冠辐状漏斗形，紫丁香色，径3.8-5厘 米，花冠管短而狭筒状，5裂，裂片开展。蒴果⻓圆柱形，弯曲，密被红 棕色⻓柔毛。
             * hksj 花开时节: 花期5-6月，果期7-8月。
             */

            private String xgsc;
            private String jzgy;
            private String hyyy;
            private String fbdq;
            private String mcll;
            private String yhjs;
            private String bxtz;
            private String hksj;

            public String getXgsc() {
                return xgsc;
            }

            public void setXgsc(String xgsc) {
                this.xgsc = xgsc;
            }

            public String getJzgy() {
                return jzgy;
            }

            public void setJzgy(String jzgy) {
                this.jzgy = jzgy;
            }

            public String getHyyy() {
                return hyyy;
            }

            public void setHyyy(String hyyy) {
                this.hyyy = hyyy;
            }

            public String getFbdq() {
                return fbdq;
            }

            public void setFbdq(String fbdq) {
                this.fbdq = fbdq;
            }

            public String getMcll() {
                return mcll;
            }

            public void setMcll(String mcll) {
                this.mcll = mcll;
            }

            public String getYhjs() {
                return yhjs;
            }

            public void setYhjs(String yhjs) {
                this.yhjs = yhjs;
            }

            public String getBxtz() {
                return bxtz;
            }

            public void setBxtz(String bxtz) {
                this.bxtz = bxtz;
            }

            public String getHksj() {
                return hksj;
            }

            public void setHksj(String hksj) {
                this.hksj = hksj;
            }
        }
    }
}
