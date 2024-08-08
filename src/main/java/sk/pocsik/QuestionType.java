package sk.pocsik;

public enum QuestionType {
    SINGLE("single"), MULTIPLE("multiple");

    private final String name;
    QuestionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
