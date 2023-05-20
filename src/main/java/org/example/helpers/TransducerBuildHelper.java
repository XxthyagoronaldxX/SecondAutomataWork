package main.java.org.example.helpers;


import main.java.org.example.models.TransducerTransitionModel;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TransducerBuildHelper {
    private final List<TransducerTransitionModel> transducerTransitionModelList;
    private String initialState;
    private final List<String> finalStateList;
    private final Set<String> stateSet;

    private TransducerBuildHelper() {
        this.transducerTransitionModelList = new ArrayList<>();
        this.finalStateList = new ArrayList<>();
        this.stateSet = new HashSet<>();
    }

    public static TransducerBuildHelper build() {
        return new TransducerBuildHelper();
    }

    public TransducerBuildHelper addTransition(String from, String inValue, String outValue, String to) {
        final TransducerTransitionModel transducerTransitionModel = new TransducerTransitionModel(from, inValue, outValue, to);

        this.transducerTransitionModelList.add(transducerTransitionModel);

        this.stateSet.add(from);
        this.stateSet.add(to);

        return this;
    }

    public TransducerBuildHelper addFinalState(String state) {
        if (stateNoExist(state))
            throw new InvalidParameterException("The state doesn't exist.");

        this.finalStateList.add(state);

        return this;
    }

    public TransducerBuildHelper setInitialState(String state) {
        if (stateNoExist(state))
            throw new InvalidParameterException("The state doesn't exist.");

        this.initialState = state;

        return this;
    }

    public boolean stateNoExist(String state) {
        return !stateSet.contains(state);
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
