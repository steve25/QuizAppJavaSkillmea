package sk.pocsik;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question {
    private final String question;
    private final List<Answer> answers = new ArrayList<>();
    private final QuestionType questionType;

    public Question(String question, String[] answers, List<Character> correctAnswers) {
        this.question = question;

        for (int i = 0; i < answers.length; i++) {
            char optionChar = (char) (i + (97));
            boolean isCorrect = correctAnswers.contains(optionChar);

            this.answers.add(new Answer(optionChar, answers[i], isCorrect));
        }
        this.questionType = correctAnswers.size() > 1 ? QuestionType.MULTIPLE : QuestionType.SINGLE;
    }

    public int getAnswersLength() {
        return answers.size();
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void printQuestion(int questionCount) {

        System.out.println();
        System.out.println(questionCount + ". " + this.question + " (" + questionType.getName() + " answer)");

        for (Answer answer : this.answers) {
            System.out.println("\t" + answer.getOptionChar() + ". " + answer.getAnswer());
        }

        System.out.println();
        System.out.print("Your answer: ");
    }

    public boolean isCorrectAnswer(char[] userAnswers) {
        List<Character> correctAnswers = new ArrayList<>();
        for (Answer answer : this.answers) {
            if (answer.isCorrectAnswers()) {
                correctAnswers.add(answer.getOptionChar());
            }
        }

        Arrays.sort(userAnswers);
        Collections.sort(correctAnswers);

        return convertArrayToList(userAnswers).equals(correctAnswers);
    }

    private List<Character> convertArrayToList(char[] array) {
        List<Character> list = new ArrayList<>();

        for (char c : array) {
            list.add(c);
        }

        return list;
    }
}
