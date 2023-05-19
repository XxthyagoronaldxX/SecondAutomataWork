package main.java.org.example.models;

public class AutomatoTransitionModel {
    private final String from;
    private final char value;
    private final String to;

    public AutomatoTransitionModel(String from, char value, String to) {
        this.from = from;
        this.value = value;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public char getValue() {
        return value;
    }

    public String getTo() {
        return to;
    }
}
