package sk.pocsik;

public class Answer {
//    private final String[] answers;
    private final char optionChar;
    private final String answer;
    private final boolean isCorrectAnswers;

    public Answer(char option, String answer, boolean isCorrectAnswers) {
        this.optionChar = option;
        this.answer = answer;
        this.isCorrectAnswers = isCorrectAnswers;
    }

    public char getOptionChar() {
        return optionChar;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isCorrectAnswers() {
        return isCorrectAnswers;
    }
}
