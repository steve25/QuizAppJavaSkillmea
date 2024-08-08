package sk.pocsik;

import java.util.Arrays;

public class Question {
    private final String question;
    private final String[] options;
    private final char[] correctAnswers;
    private final QuestionType questionType;

    public Question(String question, String[] options, char[] correctAnswers) {
        this.question = question;
        this.options = options;
        this.correctAnswers = correctAnswers;
        this.questionType = correctAnswers.length > 1 ? QuestionType.MULTIPLE : QuestionType.SINGLE;
    }

    public String[] getOptions() {
        return options;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void printQuestion(int questionCount) {

        System.out.println();
        System.out.println(questionCount + ". " + this.question + " (" + questionType.getName() + " answer)");

        for (int i = 0; i < this.options.length; i++) {
            System.out.println("\t" +(char) (97 + i) + ". " + this.options[i]);
        }

        System.out.println();
        System.out.print("Your answer: ");
    }

    public boolean isCorrectAnswer(char[] userAnswers) {
        Arrays.sort(userAnswers);
        Arrays.sort(this.correctAnswers);

        return Arrays.equals(userAnswers, this.correctAnswers);
    }
}
