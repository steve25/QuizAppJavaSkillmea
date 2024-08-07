package sk.pocsik;

import java.util.*;

public class Quiz {
    private final int MAX_QUESTIONS = 3;
    private final List<Question> questions = new ArrayList<>();
    private final List<Question> usedQuestions = new ArrayList<>();
    private final int numberOfQuestions;
    private int score = 0;
    Scanner scanner = new Scanner(System.in);


    public Quiz(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions > this.MAX_QUESTIONS ? 3 : numberOfQuestions;
        this.createQuestions();
    }

    private void createQuestions() {
        this.questions.add(new Question("What is the capital of France?", new String[]{"Paris", "London", "Rome", "Berlin"}, new char[]{'a'}));
        this.questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"Mars", "Earth", "Jupiter", "Venus"}, new char[]{'a'}));
        this.questions.add(new Question("Which of the following are programming languages?", new String[]{"Python", "HTML", "Java", "CSS"}, new char[]{'a', 'c'}));
        this.questions.add(new Question("Who wrote 'Romeo and Juliet'?", new String[]{"William Shakespeare", "Charles Dickens", "Mark Twain", "Jane Austen"}, new char[]{'a'}));
        this.questions.add(new Question("What is the chemical symbol for water?", new String[]{"H2O", "O2", "CO2", "H2"}, new char[]{'a'}));
        this.questions.add(new Question("Which countries are in Europe?", new String[]{"France", "Brazil", "Japan", "Germany"}, new char[]{'a', 'd'}));
        this.questions.add(new Question("What is the largest continent on Earth?", new String[]{"Asia", "Africa", "Europe", "Antarctica"}, new char[]{'a'}));
        this.questions.add(new Question("Which elements are noble gases?", new String[]{"Hydrogen", "Helium", "Lithium", "Neon"}, new char[]{'b', 'd'}));
        this.questions.add(new Question("Who is known as the father of modern physics?", new String[]{"Albert Einstein", "Isaac Newton", "Galileo Galilei", "Nikola Tesla"}, new char[]{'a'}));
        this.questions.add(new Question("How many sides does a hexagon have?", new String[]{"6", "5", "8", "7"}, new char[]{'a'}));
    }

    public void startQuiz() {
        System.out.println("Welcome to my Quiz.");
        System.out.printf("This quiz has %s questions%n", this.numberOfQuestions);

        this.quizLoop();
    }

    private void quizLoop() {
        int questionCount = 1;

        while (true) {
            Question question = this.chooseQuestion();

            question.printQuestion(questionCount);

            char[] userAnswer = getInput(question);

            if (question.isCorrectAnswer(userAnswer)) {
                this.score++;
            }

            if (questionCount >= MAX_QUESTIONS) {
                System.out.println();
                System.out.println("Your score is " + this.score + "/" + this.MAX_QUESTIONS);
                System.out.println();

                if (this.isContinue()) {
                    questionCount = 1;
                    this.score = 0;
                    continue;
                }
                break;
            }

            questionCount++;
        }

    }

    private boolean isContinue() {
        System.out.print("Do you wanna continue? (y/n): ");
        String input = this.scanner.nextLine();

        return input.equalsIgnoreCase("y");
    }

    private char[] getInput(Question question) {
        char endChar = (char) (97 + question.getOptions().length - 1);
        String regex = "[a-" + endChar + "]";
        String errorMessage = "Please enter a valid option (a-" + endChar + "). ";
        String input;
        char[] result;

        while (true) {
            input = this.scanner.nextLine().trim().toLowerCase();

            if (input.isBlank()) {
                System.out.print("Please enter a your option: ");
                continue;
            }

            result = input.toCharArray();

            if (this.isContainDoubledChar(result)) {
                System.err.print("Same character is allowed only once. " + errorMessage);
                continue;
            }

            if ((question.isMulti() && result.length > question.getOptions().length)
                    || (!question.isMulti() && result.length > 1)
            ) {
                System.err.print("Too many characters. " + errorMessage);
                continue;
            }

            if (this.isContainNotAllowedChar(result, regex)) {
                System.err.print(errorMessage);
                continue;
            }

            return result;
        }
    }

    private boolean isContainDoubledChar(char[] result) {
        List<Character> tempArray = new ArrayList<>();

        for (char c : result) {
            if (tempArray.contains(c)) {
                return true;
            }

            tempArray.add(c);
        }

        return false;
    }

    private boolean isContainNotAllowedChar(char[] result, String regex) {
        for (char c : result) {
            if (!String.valueOf(c).matches(regex)) {
                return true;
            }
        }

        return false;
    }

    private Question chooseQuestion() {
        Random random = new Random();
        Question result;

        do {
            result = this.questions.get(random.nextInt(this.questions.size()));
        } while (this.usedQuestions.contains(result));

        this.usedQuestions.add(result);
        return result;
    }
}
