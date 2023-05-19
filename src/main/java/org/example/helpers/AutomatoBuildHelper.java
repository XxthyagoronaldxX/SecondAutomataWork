package main.java.org.example.helpers;


import main.java.org.example.models.AutomatoTransitionModel;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AutomatoBuildHelper {
    private final List<AutomatoTransitionModel> automatoTransitionModelList;
    private String initialState;
    private final List<String> finalStatelist;
    private final Set<String> stateSet;

    public AutomatoBuildHelper() {
        this.automatoTransitionModelList = new ArrayList<>();
        this.finalStatelist = new ArrayList<>();
        this.stateSet = new HashSet<>();
    }

    public static AutomatoBuildHelper build() {
        return new AutomatoBuildHelper();
    }

    public AutomatoBuildHelper addTransition(String from, char value, String to) {
        AutomatoTransitionModel automatoTransitionModel = new AutomatoTransitionModel(from, value, to);

        automatoTransitionModelList.add(automatoTransitionModel);

        stateSet.add(from);
        stateSet.add(to);

        return this;
    }

    public AutomatoBuildHelper setInitialState(String state) {
        if (stateNoExists(state))
            throw new InvalidParameterException("The state doesn't exist.");

        this.initialState = state;

        return this;
    }

    private boolean stateNoExists(String state) {
        return !stateSet.contains(state);
    }

    public AutomatoBuildHelper addFinalState(String state) {
        if (stateNoExists(state))
            throw new InvalidParameterException("The state doesn't exist.");

        this.finalStatelist.add(state);

        return this;
    }

    public List<AutomatoTransitionModel> getTransitionModelList() {
        return automatoTransitionModelList;
    }

    public String getInitialState() {
        return initialState;
    }

    public List<String> getFinalStatelist() {
        return finalStatelist;
    }
}

