package cn.edu.bjfu.igarden.entity;

import java.util.List;

public class DiseaseQuestion {
    private int id;
    private String question;
    private List<Option> options;
    public static class Option {
        private int id;
        private int nextQuestionId;
        private String answer;
        private String imageUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNextQuestionId() {
            return nextQuestionId;
        }

        public void setNextQuestionId(int nextQuestionId) {
            this.nextQuestionId = nextQuestionId;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}
