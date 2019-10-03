import java.io.*;
import java.nio.charset.StandardCharsets;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


class Quiz {
    private List<Question> questions;
    private List<Question> questionsInRange;
    private Scanner s;
    private int correct;
    private String user;
    private int quantity;

    Quiz(int quantity, String user) {
        this.quantity = quantity;
        this.user = user;
        questions = new ArrayList<>();
        questionsInRange = new ArrayList<>();
        s = new Scanner(System.in);
        correct = 0;
        load("src/data.txt");
    }

    private void load(String path) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)), StandardCharsets.UTF_8));
            String lineTxt;
            while ((lineTxt = br.readLine()) != null) {
                String[] questionLine = lineTxt.split("-");
                Question q = new Question(questionLine[0], questionLine[1], questionLine[2]);
                q.setComplexity(Integer.parseInt(questionLine[3]));
                questions.add(q);
            }
        } catch (ExportException | FileNotFoundException | UnsupportedEncodingException e) {
            System.err.println("The question dataset has read errors :" + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ask(List<Question> questions, int questionQuantity) {
        Collections.shuffle(questions);
        List<Question> quizQuestions = questions.subList(0, questionQuantity);
        for (int i = 0; i < quizQuestions.size(); i++) {
            Question currQuestion = quizQuestions.get(i);
            System.out.println((i + 1) + ". " + currQuestion.getQuestion());
            System.out.println(currQuestion.getOptions());
            System.out.println("[Enter your answer:]");
            String candidate = s.nextLine();
            if (currQuestion.answerCorrect(candidate)) {
                correct++;
            }
        }
        System.out.println("Congratulations! You have finished all the questions.");
        System.out.println();
        double grade = (double) correct / quantity * 100;
        System.out.println("-------------------------QUIZ RESULT-------------------------");
        System.out.println();
        System.out.println("CORRECT ANSWERS:" + correct + "         INCORRECT ANSWERS:" + (quantity - correct));
        System.out.println();
        System.out.println(user + ", you got " + grade + " points in this quiz!");
    }

    void giveQuiz() {
        ask(questions, 25);
    }

    void giveQuiz(int min, int max) {
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getComplexity() <= max && questions.get(i).getComplexity() >= min) {
                questionsInRange.add(questions.get(i));
            }
        }
        ask(questionsInRange, quantity);
    }

}
