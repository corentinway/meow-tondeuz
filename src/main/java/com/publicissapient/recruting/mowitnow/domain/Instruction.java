package com.publicissapient.recruting.mowitnow.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Instruction {

    private final List<MowerAction> mowersAndActions;

    public static class MowerAction {
        private Mower mower;
        private String actions;

        public MowerAction(Mower mower, String actions) {
            this.mower = mower;
            this.actions = actions;
        }
    }

    public Instruction(List<MowerAction> mowersAndActions) {
        this.mowersAndActions = mowersAndActions;
    }

    public List<Mower> getMowers() {
        return mowersAndActions.stream()
                .map(mowerAction -> mowerAction.mower)
                .collect(Collectors.toList());
    }

    public void execute() {
        mowersAndActions
                .stream()
                .forEach(mowerAction -> {
                    mowerAction.actions.chars()
                            .forEach(action -> mowerAction.mower.move((char) action));
                });
    }
}
