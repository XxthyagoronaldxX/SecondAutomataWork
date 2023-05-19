package main.java.org.example;


import main.java.org.example.helpers.AutomatoBuildHelper;
import main.java.org.example.helpers.TransducerBuildHelper;
import main.java.org.example.models.AutomatoModel;
import main.java.org.example.models.TransducerModel;
import main.java.org.example.usecases.ShiftAndUsecase;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class SecondAutomatoWork {
    public static void main(String[] args) {
        question01();
        question02();
        question03();
    }

    private static void question01() {
        AutomatoBuildHelper automatoBuildHelper = AutomatoBuildHelper.build()
                .addTransition("q1", 'a', "q2")
                .addTransition("q2", 'a', "q2")
                .addTransition("q2", 'b', "q3")
                .addTransition("q2", 'c', "q4")
                .addTransition("q3", 'b', "q3")
                .addTransition("q3", 'c', "q4")
                .addTransition("q4", 'c', "q4")
                .addTransition("q4", 'a', "q2")
                .addFinalState("q1")
                .addFinalState("q2")
                .addFinalState("q3")
                .addFinalState("q4")
                .setInitialState("q1");

        AutomatoModel automatoModelA = AutomatoModel.fromBuild(automatoBuildHelper);

        automatoBuildHelper = AutomatoBuildHelper.build()
                .addTransition("q1", 'a', "q2")
                .addTransition("q1", 'b', "q5")
                .addTransition("q1", 'c', "q5")
                .addTransition("q2", 'a', "q3")
                .addTransition("q3", 'a', "q4")
                .addTransition("q4", 'b', "q4")
                .addTransition("q4", 'c', "q4")
                .addTransition("q5", 'b', "q5")
                .addTransition("q5", 'c', "q5")
                .addTransition("q5", 'a', "q6")
                .addTransition("q6", 'a', "q7")
                .addTransition("q7", 'a', "q8")
                .addFinalState("q4")
                .addFinalState("q8")
                .setInitialState("q1");

        AutomatoModel automatoModelB = AutomatoModel.fromBuild(automatoBuildHelper);

        automatoBuildHelper = AutomatoBuildHelper.build()
                .addTransition("q1", 'a', "q2")
                .addTransition("q1", 'b', "q3")
                .addTransition("q2", 'a', "q4")
                .addTransition("q2", 'b', "q5")
                .addTransition("q4", 'a', "q4")
                .addTransition("q4", 'b', "q3")
                .addTransition("q5", 'b', "q5")
                .addFinalState("q2")
                .addFinalState("q3")
                .addFinalState("q5")
                .setInitialState("q1");

        AutomatoModel automatoModelC = AutomatoModel.fromBuild(automatoBuildHelper);

        automatoBuildHelper = AutomatoBuildHelper.build()
                .addTransition("q1", 'a', "q2")
                .addTransition("q1", 'b', "q3")
                .addTransition("q2", 'a', "q2")
                .addTransition("q2", 'b', "q3")
                .addTransition("q3", 'b', "q3")
                .addTransition("q3", 'a', "q4")
                .addTransition("q4", 'c', "q4")
                .addFinalState("q2")
                .addFinalState("q4")
                .setInitialState("q1");

        AutomatoModel automatoModelD = AutomatoModel.fromBuild(automatoBuildHelper);

        System.out.println(automatoModelA.run("abbbbbbcccaaac"));
        System.out.println(automatoModelB.run(""));
        System.out.println(automatoModelC.run(""));
        System.out.println(automatoModelD.run(""));
    }

    private static void question02() {
        try {
            String text = Files.readString(Path.of("text.txt").toAbsolutePath(), StandardCharsets.UTF_8);

            ShiftAndUsecase shiftAndUsecase = new ShiftAndUsecase();

            Map<Integer, Integer> markMap = shiftAndUsecase.execute(text, "computador");

            markMap.forEach((key, value) -> {
                System.out.println(key + " - " + value);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void question03() {
        TransducerBuildHelper transducerBuildHelper = TransducerBuildHelper.build()
                .addTransition("q0", "100", "1", "q0")
                .addTransition("q0", "25", "0", "q1")
                .addTransition("q0", "50", "0", "q2")
                .addTransition("q1", "100", "1", "q1")
                .addTransition("q1", "25", "0", "q2")
                .addTransition("q1", "50", "0", "q3")
                .addTransition("q2", "100", "1", "q2")
                .addTransition("q2", "50", "1", "q0")
                .addTransition("q2", "25", "0", "q3")
                .addTransition("q3", "100", "1", "q3")
                .addTransition("q3", "25", "1", "q0")
                .addTransition("q3", "50", "1", "q1")
                .addFinalState("q1")
                .addFinalState("q2")
                .addFinalState("q0")
                .addFinalState("q3")
                .setInitialState("q0");

        TransducerModel transducerModel = TransducerModel.fromBuild(transducerBuildHelper);

        System.out.println(transducerModel.run("50$25$50$100$25$50$100$"));
    }
}
