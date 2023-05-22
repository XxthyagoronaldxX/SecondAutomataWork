package main.java.org.example.models;

import main.java.org.example.helpers.TransducerBuildHelper;

import java.util.List;
import java.util.Optional;

public class TransducerModel {
    private final List<TransducerTransitionModel> transducerTransitionModelList;
    private final String initialState;
    private final List<String> finalStateList;

    private TransducerModel(List<TransducerTransitionModel> transducerTransitionModelList, String initialState, List<String> finalStateList) {
        this.transducerTransitionModelList = transducerTransitionModelList;
        this.initialState = initialState;
        this.finalStateList = finalStateList;
    }

    public static TransducerModel fromBuild(TransducerBuildHelper build) {
        return new TransducerModel(
                build.getTransducerTransitionModelList(),
                build.getInitialState(),
                build.getFinalStateList()
        );
    }

    public Optional<String> run(String tape) {
        String currentState = initialState;

        String value = "";
        StringBuilder result = new StringBuilder();

        for (char character : tape.toCharArray()) {
            boolean noExistState =true;

            if (character != '$') {
                value += character;
            } else {
                for (TransducerTransitionModel transducerTransitionModel : transducerTransitionModelList) {
                    if (transducerTransitionModel.getFrom().equals(currentState)) {
                        if (transducerTransitionModel.getInValue().equals(value)) {
                            currentState = transducerTransitionModel.getTo();
                            result.append(transducerTransitionModel.getOutValue());
                            noExistState = false;
                            break;
                        }
                    }
                }

                if (noExistState) return Optional.empty();

                value = "";
            }
        }

        if (!finalStateList.contains(currentState)) return Optional.empty();

        return Optional.of(result.toString());
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
