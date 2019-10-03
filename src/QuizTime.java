import java.util.Scanner;


class QuizTime {
    private Scanner s;
    private String name;
    private String mode;
    private Quiz q;

    QuizTime() {
        s = new Scanner(System.in);
        start();
    }

    void start() {
        while (mode == null || !mode.equals("0")) {
            System.out.println("-------------GRAMMAR QUIZ TIME WITH COMPLEXITY CUSTOMIZATION-------------");
            if (name == null) getName();
            selectMode();
        }
    }

    void getName() {
        System.out.println("PLEASE ENTER YOUR NAME:");
        name = s.nextLine();
    }

    void selectMode() {
        System.out.println("NOTE: questions range from a MINIMUM difficulty level of 1 to MAXIMUM difficulty level of 6");
        System.out.println("CHOOSE MODE:");
        System.out.println("1: Random Quiz     2: Customized Quiz     0: Quit");
        mode = s.nextLine();
        while (!mode.equals("0") && !mode.equals("1") && !mode.equals("2")) {
            System.out.println("The mode you chose doesn't exist. Please enter again.");
            mode = s.nextLine();
        }
        if (mode.equals("0")) quit();
        if (mode.equals("1")) random();
        if (mode.equals("2")) custom();
        backMenu();
    }

    void backMenu() {
        System.out.println("Backing to Main Menu...");
        System.out.println();
    }

    void quit() {
        System.out.println("You have quit the quiz. See you again. :)");
        System.exit(0);
    }

    void random() {
        System.out.println("Welcome to the Random Quiz mode. You'll have 25 questions of complexities in a random range.");
        System.out.println("Press Enter to continue...");
        s.nextLine();
        q = new Quiz(25, name);
        q.giveQuiz();
    }

    void custom() {
        System.out.println("Welcome to the Customized Quiz mode. Enter the amount of questions (Maximum 25):");
        int questionQuantity = Integer.parseInt(s.nextLine());

        System.out.println("Enter the minimum difficulty level");
        int minLevel = Integer.parseInt(s.nextLine());
        System.out.println("Enter the maximum difficulty level");
        int maxLevel = Integer.parseInt(s.nextLine());

        q = new Quiz(questionQuantity, name);
        q.giveQuiz(minLevel, maxLevel);
    }
}
