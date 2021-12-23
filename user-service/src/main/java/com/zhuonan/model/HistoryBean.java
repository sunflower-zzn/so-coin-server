package com.zhuonan.model;

/**
 * @Author zhuonan
 * @Date 2021/12/13
 * @Description 查询历史
 */
public class HistoryBean {
    private Integer id;
    private Integer userId;
    private String question;
    private String answer;

    public HistoryBean(Integer userId, String question, String answer) {
        this.userId = userId;
        this.question = question;
        this.answer = answer;
    }

    public HistoryBean(Integer id, Integer userId, String question, String answer) {
        this.id = id;
        this.userId = userId;
        this.question = question;
        this.answer = answer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
