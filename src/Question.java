import java.io.*;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class for generating question
 *
 * @author neekoleung
 */
public class Question {
    private String question, options, answer;
    private int complexityLevel;

    /**
     * Sets up the question with a default complexity.
     */
    public Question(String query, String choices, String result) {
        question = query;
        options = choices;
        answer = result;
        complexityLevel = 1;
    }

    /**
     * Sets the question.
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Sets the options for this question.
     */
    public void setOptions(String options) {
        this.options = options;
    }

    /**
     * Sets the complexity level for this question.
     */
    public void setComplexity(int level) {
        complexityLevel = level;
    }

    /**
     * Returns the complexity level for this question.
     */
    public int getComplexity() {
        return complexityLevel;
    }

    /**
     * Returns the question.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Returns the options for this question.
     */
    public String getOptions() {
        return options;
    }

    /**
     * Returns the answer to this question.
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Returns true if the candidate answer matches the answer.
     */
    public boolean answerCorrect(String candidateAnswer) {
        return answer.equals(candidateAnswer);
    }

    /**
     * Returns this question (and its answer) as a string.
     */
    public String toString() {
        return question + "\n" + answer;
    }


}
