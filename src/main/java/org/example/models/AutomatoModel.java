package main.java.org.example.models;

import main.java.org.example.helpers.AutomatoBuildHelper;

import java.util.List;

public class AutomatoModel {
    private final List<AutomatoTransitionModel> automatoTransitionModelList;

    private final String initialState;

    private final List<String> finalStateList;

    public AutomatoModel(List<AutomatoTransitionModel> automatoTransitionModelList, String initialState, List<String> finalStateList) {
        this.automatoTransitionModelList = automatoTransitionModelList;
        this.initialState = initialState;
        this.finalStateList = finalStateList;
    }

    public static AutomatoModel fromBuild(AutomatoBuildHelper automatoBuildHelper) {
        return new AutomatoModel(
                automatoBuildHelper.getTransitionModelList(),
                automatoBuildHelper.getInitialState(),
                automatoBuildHelper.getFinalStatelist()
        );
    }

    public boolean run(String tape) {
        String currentState = initialState;

        for (char value : tape.toCharArray()) {
            boolean noExistState =true;

            for (AutomatoTransitionModel automatoTransitionModel : automatoTransitionModelList) {
                if (automatoTransitionModel.getFrom().equals(currentState)) {
                    if (automatoTransitionModel.getValue() == value) {
                        currentState = automatoTransitionModel.getTo();
                        noExistState = false;
                        break;
                    }
                }
            }

            if (noExistState) return false;
        }

        return finalStateList.contains(currentState);
    }

    public List<AutomatoTransitionModel> getTransitionModelList() {
        return automatoTransitionModelList;
    }

    public String getInitialState() {
        return initialState;
    }

    public List<String> getFinalStateList() {
        return finalStateList;
    }
}
