import java.io.*;
import java.rmi.server.ExportException;
import java.util.*;



public class Quiz {
    List<Question> questionList;


    public Quiz() {
        questionList = new ArrayList<Question>();
    }


    public static List<String[]> getQuestionsFromData(String dataLocation) {
        List questionSet = new ArrayList();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(dataLocation)), "UTF-8"));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                String[] questionLine = lineTxt.split("-");
                questionSet.add(questionLine);
            }
        } catch (ExportException | FileNotFoundException | UnsupportedEncodingException e) {
            System.err.println("The question dataset has read errors :" + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionSet;
    }


    public void add(Question question) {
        questionList.add(question);
    }


    public List<Question> giveQuiz(List<Question> questionBank) {
        Collections.shuffle(questionBank);   //Randomly shuffle the questions in the questionBank
        List<Question> quiz = questionBank.subList(0, 25);   //Extract the first 25 questions as the quiz questions
        return quiz;
    }


    public List<Question> giveQuiz(List<Question> questionBank, int min, int max, int questionQuantity) {
        List<Question> questionsInRange = new ArrayList<Question>();
        for (int i = 0; i < questionBank.size(); i++) {
            if (questionBank.get(i).getComplexity() <= max && questionBank.get(i).getComplexity() >= min) {
                questionsInRange.add(questionBank.get(i));
            }
        }
        Collections.shuffle(questionsInRange);
        List<Question> quiz = questionsInRange.subList(0, questionQuantity);
        return quiz;
    }


    public int Grading(List<Question> quiz, String[] userAnswer) {
        int correct = 0;
        for (int i = 0; i < quiz.size(); i++) {
            if (quiz.get(i).answerCorrect(userAnswer[i])) {
                correct++;
            }
        }
        return correct;
    }

}
