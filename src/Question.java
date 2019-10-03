


public class Question {
    private String question, options, answer;
    private int complexityLevel;


    public Question(String query, String choices, String result) {
        question = query;
        options = choices;
        answer = result;
        complexityLevel = 1;
    }


    public void setQuestion(String question) {
        this.question = question;
    }


    public void setOptions(String options) {
        this.options = options;
    }


    public void setComplexity(int level) {
        complexityLevel = level;
    }


    public int getComplexity() {
        return complexityLevel;
    }


    public String getQuestion() {
        return question;
    }


    public String getOptions() {
        return options;
    }


    public String getAnswer() {
        return answer;
    }


    public boolean answerCorrect(String candidateAnswer) {
        return answer.equals(candidateAnswer);
    }


    public String toString() {
        return question + "\n" + answer;
    }


}
