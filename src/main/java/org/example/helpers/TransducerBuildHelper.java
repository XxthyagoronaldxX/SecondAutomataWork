package main.java.org.example.helpers;


import main.java.org.example.models.TransducerTransitionModel;

import java.util.ArrayList;
import java.util.List;

public class TransducerBuildHelper {
    private final List<TransducerTransitionModel> transducerTransitionModelList;
    private String initialState;
    private final List<String> finalStateList;

    private TransducerBuildHelper() {
        this.transducerTransitionModelList = new ArrayList<>();
        this.finalStateList = new ArrayList<>();
    }

    public static TransducerBuildHelper build() {
        return new TransducerBuildHelper();
    }

    public TransducerBuildHelper addTransition(String from, String inValue, String outValue, String to) {
        final TransducerTransitionModel transducerTransitionModel = new TransducerTransitionModel(from, inValue, outValue, to);

        this.transducerTransitionModelList.add(transducerTransitionModel);

        return this;
    }

    public TransducerBuildHelper addFinalState(String state) {
        this.finalStateList.add(state);

        return this;
    }

    public TransducerBuildHelper setInitialState(String state) {
        this.initialState = state;

        return this;
    }

    public List<TransducerTransitionModel> getTransducerTransitionModelList() {
        return transducerTransitionModelList;
    }

    public String getInitialState() {
        return initialState;
    }

    public List<String> getFinalStateList() {
        return finalStateList;
    }
}
