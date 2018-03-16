package com.zifisense.jll.model;

import java.util.ArrayList;
import java.util.List;

public class ProjectSysSourceExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table project_sys_source
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table project_sys_source
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table project_sys_source
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_sys_source
     *
     * @mbggenerated
     */
    public ProjectSysSourceExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_sys_source
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_sys_source
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_sys_source
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_sys_source
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_sys_source
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_sys_source
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_sys_source
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_sys_source
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_sys_source
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_sys_source
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table project_sys_source
     *
     * @mbggenerated
     */
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNull() {
            addCriterion("project_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("project_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(Long value) {
            addCriterion("project_id =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(Long value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(Long value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(Long value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(Long value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(Long value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<Long> values) {
            addCriterion("project_id in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<Long> values) {
            addCriterion("project_id not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(Long value1, Long value2) {
            addCriterion("project_id between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(Long value1, Long value2) {
            addCriterion("project_id not between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andSysAppSourceIdIsNull() {
            addCriterion("sys_app_source_id is null");
            return (Criteria) this;
        }

        public Criteria andSysAppSourceIdIsNotNull() {
            addCriterion("sys_app_source_id is not null");
            return (Criteria) this;
        }

        public Criteria andSysAppSourceIdEqualTo(Long value) {
            addCriterion("sys_app_source_id =", value, "sysAppSourceId");
            return (Criteria) this;
        }

        public Criteria andSysAppSourceIdNotEqualTo(Long value) {
            addCriterion("sys_app_source_id <>", value, "sysAppSourceId");
            return (Criteria) this;
        }

        public Criteria andSysAppSourceIdGreaterThan(Long value) {
            addCriterion("sys_app_source_id >", value, "sysAppSourceId");
            return (Criteria) this;
        }

        public Criteria andSysAppSourceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("sys_app_source_id >=", value, "sysAppSourceId");
            return (Criteria) this;
        }

        public Criteria andSysAppSourceIdLessThan(Long value) {
            addCriterion("sys_app_source_id <", value, "sysAppSourceId");
            return (Criteria) this;
        }

        public Criteria andSysAppSourceIdLessThanOrEqualTo(Long value) {
            addCriterion("sys_app_source_id <=", value, "sysAppSourceId");
            return (Criteria) this;
        }

        public Criteria andSysAppSourceIdIn(List<Long> values) {
            addCriterion("sys_app_source_id in", values, "sysAppSourceId");
            return (Criteria) this;
        }

        public Criteria andSysAppSourceIdNotIn(List<Long> values) {
            addCriterion("sys_app_source_id not in", values, "sysAppSourceId");
            return (Criteria) this;
        }

        public Criteria andSysAppSourceIdBetween(Long value1, Long value2) {
            addCriterion("sys_app_source_id between", value1, value2, "sysAppSourceId");
            return (Criteria) this;
        }

        public Criteria andSysAppSourceIdNotBetween(Long value1, Long value2) {
            addCriterion("sys_app_source_id not between", value1, value2, "sysAppSourceId");
            return (Criteria) this;
        }

        public Criteria andSysProjectCodeIsNull() {
            addCriterion("sys_project_code is null");
            return (Criteria) this;
        }

        public Criteria andSysProjectCodeIsNotNull() {
            addCriterion("sys_project_code is not null");
            return (Criteria) this;
        }

        public Criteria andSysProjectCodeEqualTo(String value) {
            addCriterion("sys_project_code =", value, "sysProjectCode");
            return (Criteria) this;
        }

        public Criteria andSysProjectCodeNotEqualTo(String value) {
            addCriterion("sys_project_code <>", value, "sysProjectCode");
            return (Criteria) this;
        }

        public Criteria andSysProjectCodeGreaterThan(String value) {
            addCriterion("sys_project_code >", value, "sysProjectCode");
            return (Criteria) this;
        }

        public Criteria andSysProjectCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sys_project_code >=", value, "sysProjectCode");
            return (Criteria) this;
        }

        public Criteria andSysProjectCodeLessThan(String value) {
            addCriterion("sys_project_code <", value, "sysProjectCode");
            return (Criteria) this;
        }

        public Criteria andSysProjectCodeLessThanOrEqualTo(String value) {
            addCriterion("sys_project_code <=", value, "sysProjectCode");
            return (Criteria) this;
        }

        public Criteria andSysProjectCodeLike(String value) {
            addCriterion("sys_project_code like", value, "sysProjectCode");
            return (Criteria) this;
        }

        public Criteria andSysProjectCodeNotLike(String value) {
            addCriterion("sys_project_code not like", value, "sysProjectCode");
            return (Criteria) this;
        }

        public Criteria andSysProjectCodeIn(List<String> values) {
            addCriterion("sys_project_code in", values, "sysProjectCode");
            return (Criteria) this;
        }

        public Criteria andSysProjectCodeNotIn(List<String> values) {
            addCriterion("sys_project_code not in", values, "sysProjectCode");
            return (Criteria) this;
        }

        public Criteria andSysProjectCodeBetween(String value1, String value2) {
            addCriterion("sys_project_code between", value1, value2, "sysProjectCode");
            return (Criteria) this;
        }

        public Criteria andSysProjectCodeNotBetween(String value1, String value2) {
            addCriterion("sys_project_code not between", value1, value2, "sysProjectCode");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table project_sys_source
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table project_sys_source
     *
     * @mbggenerated
     */
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