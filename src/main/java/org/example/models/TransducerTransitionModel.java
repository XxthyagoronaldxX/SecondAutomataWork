package main.java.org.example.models;

public class TransducerTransitionModel {
    private final String from;
    private final String inValue;
    private final String outValue;
    private final String to;

    public TransducerTransitionModel(String from, String inValue, String outValue, String to) {
        this.from = from;
        this.inValue = inValue;
        this.outValue = outValue;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getInValue() {
        return inValue;
    }

    public String getOutValue() {
        return outValue;
    }

    public String getTo() {
        return to;
    }
}
