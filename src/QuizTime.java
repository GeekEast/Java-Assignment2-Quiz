import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class QuizTime {

    public static void main(String[] args) {
        // Obtains the questions from outsource data.
        List<String[]> questionsSet = Quiz.getQuestionsFromData("src/data.txt");
        // Puts the questions into the questionBank.
        List<Question> questionBank = new ArrayList<Question>();
        for (int i = 0; i < questionsSet.size(); i++) {
            String[] questionTxt = questionsSet.get(i);
            Question q = new Question(questionTxt[0], questionTxt[1], questionTxt[2]);
            q.setComplexity(Integer.parseInt(questionTxt[3]));
            questionBank.add(q);
        }
        // Initializes a quiz.
        Quiz q = new Quiz();
        Scanner s = new Scanner(System.in);
        System.out.println("-------------------------------GRAMMAR QUIZ TIME WITH COMPLEXITY COSTOMIZATION-------------------------------");
        System.out.println("PLEASE ENTER YOUR NAME:");
        String name = s.nextLine();
        System.out.println("NOTE: questions range from a MINIMUM difficulty level of 1 to MAXIMUM difficulty level of 6");

        // Asks user to choose a mode.
        System.out.println("CHOOSE MODE:");
        System.out.println("1: Random Quiz     2: Customized Quiz     0: Quit");
        String mode = s.nextLine();
        // Asks user to reenter the mode number if the user input is invalid.
        while (!mode.equals("0") && !mode.equals("1") && !mode.equals("2")) {
            System.out.println("The mode you chose doesn't exist. Please enter again.");
            mode = s.nextLine();
        }

        // Mode 1 : Random Quiz mode. User will go through 25 random questions with complexity from all range.
        if (mode.equals("1")) {
            System.out.println("Welcome to the Random Quiz mode. You'll have 25 questions of complexities in a random range.");
            System.out.println("Press Enter to continue...");
            s.nextLine();

            // Obtains the quiz questions.
            List<Question> quizQuestions = q.giveQuiz(questionBank);
            String[] candidateAnswers = new String[25];  // Initialize a array to store all the user's answers.
            for (int i = 0; i < quizQuestions.size(); i++) {
                System.out.println(String.valueOf(i + 1) + ". " + quizQuestions.get(i).getQuestion()); // Display the questions.
                System.out.println(quizQuestions.get(i).getOptions());  // Display the options.
                System.out.println("[Enter your answer:]");
                candidateAnswers[i] = s.nextLine();
            }
            System.out.println("Congratulations! You have finished all the questions.");
            System.out.println();

            // Calculates the number of correct answers and the grade.
            int correct = q.Grading(quizQuestions, candidateAnswers);
            double grade = (double) correct / 25 * 100;
            // Displays the quiz result.
            System.out.println("-------------------------QUIZ RESULT-------------------------");
            System.out.println("");
            System.out.println("CORRECT ANSWERS:" + correct + "         INCORRECT ANSWERS:" + String.valueOf(25 - correct));
            System.out.println("");
            System.out.println(name + ", you got " + String.valueOf(grade) + " points in this quiz!");

        } else

            // Mode 2: Customized Quiz mode. User will define the amount of questions and difficulty level himself.
            if (mode.equals("2")) {
                System.out.println("Welcome to the Customized Quiz mode. Enter the amount of questions (Maximum 25):");
                String questionQuantity = s.nextLine();
                //Asks user to reenter number if the user input is invalid or larger than 25.
                Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
                while (!pattern.matcher(questionQuantity).matches() || Integer.parseInt(questionQuantity) > 25) {
                    System.out.println("The number you entered is invalid. Please enter again.");
                    questionQuantity = s.nextLine();
                }
                System.out.println("Enter the minimum difficulty level");
                String minLevel = s.nextLine();
                System.out.println("Enter the maximum difficulty level");
                String maxLevel = s.nextLine();

                // Obtains the quiz questions.
                List<Question> quizQuestions = q.giveQuiz(questionBank, Integer.parseInt(minLevel), Integer.parseInt(maxLevel), Integer.parseInt(questionQuantity));   //Obtain the quiz questions
                String[] candidateAnswers = new String[Integer.parseInt(questionQuantity)];
                for (int i = 0; i < quizQuestions.size(); i++) {
                    System.out.println(String.valueOf(i + 1) + ". " + quizQuestions.get(i).getQuestion());
                    System.out.println(quizQuestions.get(i).getOptions());
                    System.out.println("[Enter your answer:]");
                    candidateAnswers[i] = s.nextLine();
                }
                System.out.println("Congratulations! You have finished all the questions.");
                System.out.println();

                // Calculates the number of correct answers and the grade.
                int correct = q.Grading(quizQuestions, candidateAnswers);
                double grade = (double) correct / Integer.parseInt(questionQuantity) * 100;
                // Displays the quiz result.
                System.out.println("-------------------------QUIZ RESULT-------------------------");
                System.out.println("");
                System.out.println("CORRECT ANSWERS:" + correct + "         INCORRECT ANSWERS:" + String.valueOf(Integer.parseInt(questionQuantity) - correct));
                System.out.println("");
                System.out.println(name + ", you got " + String.valueOf(grade) + " points in this quiz!");

            } else

                // Mode 3: Quit.
                if (mode.equals("0")) {
                    System.out.println("You have quit the quiz. See you again. :)");
                }

    }
}
