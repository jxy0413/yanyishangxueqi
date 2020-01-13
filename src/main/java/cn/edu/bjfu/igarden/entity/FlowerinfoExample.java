package cn.edu.bjfu.igarden.entity;

import java.util.ArrayList;
import java.util.List;

public class FlowerinfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FlowerinfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andFlowerIdIsNull() {
            addCriterion("flower_id is null");
            return (Criteria) this;
        }

        public Criteria andFlowerIdIsNotNull() {
            addCriterion("flower_id is not null");
            return (Criteria) this;
        }

        public Criteria andFlowerIdEqualTo(Integer value) {
            addCriterion("flower_id =", value, "flowerId");
            return (Criteria) this;
        }

        public Criteria andFlowerIdNotEqualTo(Integer value) {
            addCriterion("flower_id <>", value, "flowerId");
            return (Criteria) this;
        }

        public Criteria andFlowerIdGreaterThan(Integer value) {
            addCriterion("flower_id >", value, "flowerId");
            return (Criteria) this;
        }

        public Criteria andFlowerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("flower_id >=", value, "flowerId");
            return (Criteria) this;
        }

        public Criteria andFlowerIdLessThan(Integer value) {
            addCriterion("flower_id <", value, "flowerId");
            return (Criteria) this;
        }

        public Criteria andFlowerIdLessThanOrEqualTo(Integer value) {
            addCriterion("flower_id <=", value, "flowerId");
            return (Criteria) this;
        }

        public Criteria andFlowerIdIn(List<Integer> values) {
            addCriterion("flower_id in", values, "flowerId");
            return (Criteria) this;
        }

        public Criteria andFlowerIdNotIn(List<Integer> values) {
            addCriterion("flower_id not in", values, "flowerId");
            return (Criteria) this;
        }

        public Criteria andFlowerIdBetween(Integer value1, Integer value2) {
            addCriterion("flower_id between", value1, value2, "flowerId");
            return (Criteria) this;
        }

        public Criteria andFlowerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("flower_id not between", value1, value2, "flowerId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andFamilyIsNull() {
            addCriterion("family is null");
            return (Criteria) this;
        }

        public Criteria andFamilyIsNotNull() {
            addCriterion("family is not null");
            return (Criteria) this;
        }

        public Criteria andFamilyEqualTo(String value) {
            addCriterion("family =", value, "family");
            return (Criteria) this;
        }

        public Criteria andFamilyNotEqualTo(String value) {
            addCriterion("family <>", value, "family");
            return (Criteria) this;
        }

        public Criteria andFamilyGreaterThan(String value) {
            addCriterion("family >", value, "family");
            return (Criteria) this;
        }

        public Criteria andFamilyGreaterThanOrEqualTo(String value) {
            addCriterion("family >=", value, "family");
            return (Criteria) this;
        }

        public Criteria andFamilyLessThan(String value) {
            addCriterion("family <", value, "family");
            return (Criteria) this;
        }

        public Criteria andFamilyLessThanOrEqualTo(String value) {
            addCriterion("family <=", value, "family");
            return (Criteria) this;
        }

        public Criteria andFamilyLike(String value) {
            addCriterion("family like", value, "family");
            return (Criteria) this;
        }

        public Criteria andFamilyNotLike(String value) {
            addCriterion("family not like", value, "family");
            return (Criteria) this;
        }

        public Criteria andFamilyIn(List<String> values) {
            addCriterion("family in", values, "family");
            return (Criteria) this;
        }

        public Criteria andFamilyNotIn(List<String> values) {
            addCriterion("family not in", values, "family");
            return (Criteria) this;
        }

        public Criteria andFamilyBetween(String value1, String value2) {
            addCriterion("family between", value1, value2, "family");
            return (Criteria) this;
        }

        public Criteria andFamilyNotBetween(String value1, String value2) {
            addCriterion("family not between", value1, value2, "family");
            return (Criteria) this;
        }

        public Criteria andAppearanceIsNull() {
            addCriterion("appearance is null");
            return (Criteria) this;
        }

        public Criteria andAppearanceIsNotNull() {
            addCriterion("appearance is not null");
            return (Criteria) this;
        }

        public Criteria andAppearanceEqualTo(String value) {
            addCriterion("appearance =", value, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceNotEqualTo(String value) {
            addCriterion("appearance <>", value, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceGreaterThan(String value) {
            addCriterion("appearance >", value, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceGreaterThanOrEqualTo(String value) {
            addCriterion("appearance >=", value, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceLessThan(String value) {
            addCriterion("appearance <", value, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceLessThanOrEqualTo(String value) {
            addCriterion("appearance <=", value, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceLike(String value) {
            addCriterion("appearance like", value, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceNotLike(String value) {
            addCriterion("appearance not like", value, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceIn(List<String> values) {
            addCriterion("appearance in", values, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceNotIn(List<String> values) {
            addCriterion("appearance not in", values, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceBetween(String value1, String value2) {
            addCriterion("appearance between", value1, value2, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceNotBetween(String value1, String value2) {
            addCriterion("appearance not between", value1, value2, "appearance");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("location is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("location is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("location =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("location <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("location >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("location >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("location <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("location <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("location like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("location not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("location in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("location not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("location between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("location not between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andBloomingtimeIsNull() {
            addCriterion("bloomingTime is null");
            return (Criteria) this;
        }

        public Criteria andBloomingtimeIsNotNull() {
            addCriterion("bloomingTime is not null");
            return (Criteria) this;
        }

        public Criteria andBloomingtimeEqualTo(String value) {
            addCriterion("bloomingTime =", value, "bloomingtime");
            return (Criteria) this;
        }

        public Criteria andBloomingtimeNotEqualTo(String value) {
            addCriterion("bloomingTime <>", value, "bloomingtime");
            return (Criteria) this;
        }

        public Criteria andBloomingtimeGreaterThan(String value) {
            addCriterion("bloomingTime >", value, "bloomingtime");
            return (Criteria) this;
        }

        public Criteria andBloomingtimeGreaterThanOrEqualTo(String value) {
            addCriterion("bloomingTime >=", value, "bloomingtime");
            return (Criteria) this;
        }

        public Criteria andBloomingtimeLessThan(String value) {
            addCriterion("bloomingTime <", value, "bloomingtime");
            return (Criteria) this;
        }

        public Criteria andBloomingtimeLessThanOrEqualTo(String value) {
            addCriterion("bloomingTime <=", value, "bloomingtime");
            return (Criteria) this;
        }

        public Criteria andBloomingtimeLike(String value) {
            addCriterion("bloomingTime like", value, "bloomingtime");
            return (Criteria) this;
        }

        public Criteria andBloomingtimeNotLike(String value) {
            addCriterion("bloomingTime not like", value, "bloomingtime");
            return (Criteria) this;
        }

        public Criteria andBloomingtimeIn(List<String> values) {
            addCriterion("bloomingTime in", values, "bloomingtime");
            return (Criteria) this;
        }

        public Criteria andBloomingtimeNotIn(List<String> values) {
            addCriterion("bloomingTime not in", values, "bloomingtime");
            return (Criteria) this;
        }

        public Criteria andBloomingtimeBetween(String value1, String value2) {
            addCriterion("bloomingTime between", value1, value2, "bloomingtime");
            return (Criteria) this;
        }

        public Criteria andBloomingtimeNotBetween(String value1, String value2) {
            addCriterion("bloomingTime not between", value1, value2, "bloomingtime");
            return (Criteria) this;
        }

        public Criteria andFlowerImageIsNull() {
            addCriterion("flower_image is null");
            return (Criteria) this;
        }

        public Criteria andFlowerImageIsNotNull() {
            addCriterion("flower_image is not null");
            return (Criteria) this;
        }

        public Criteria andFlowerImageEqualTo(String value) {
            addCriterion("flower_image =", value, "flowerImage");
            return (Criteria) this;
        }

        public Criteria andFlowerImageNotEqualTo(String value) {
            addCriterion("flower_image <>", value, "flowerImage");
            return (Criteria) this;
        }

        public Criteria andFlowerImageGreaterThan(String value) {
            addCriterion("flower_image >", value, "flowerImage");
            return (Criteria) this;
        }

        public Criteria andFlowerImageGreaterThanOrEqualTo(String value) {
            addCriterion("flower_image >=", value, "flowerImage");
            return (Criteria) this;
        }

        public Criteria andFlowerImageLessThan(String value) {
            addCriterion("flower_image <", value, "flowerImage");
            return (Criteria) this;
        }

        public Criteria andFlowerImageLessThanOrEqualTo(String value) {
            addCriterion("flower_image <=", value, "flowerImage");
            return (Criteria) this;
        }

        public Criteria andFlowerImageLike(String value) {
            addCriterion("flower_image like", value, "flowerImage");
            return (Criteria) this;
        }

        public Criteria andFlowerImageNotLike(String value) {
            addCriterion("flower_image not like", value, "flowerImage");
            return (Criteria) this;
        }

        public Criteria andFlowerImageIn(List<String> values) {
            addCriterion("flower_image in", values, "flowerImage");
            return (Criteria) this;
        }

        public Criteria andFlowerImageNotIn(List<String> values) {
            addCriterion("flower_image not in", values, "flowerImage");
            return (Criteria) this;
        }

        public Criteria andFlowerImageBetween(String value1, String value2) {
            addCriterion("flower_image between", value1, value2, "flowerImage");
            return (Criteria) this;
        }

        public Criteria andFlowerImageNotBetween(String value1, String value2) {
            addCriterion("flower_image not between", value1, value2, "flowerImage");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}