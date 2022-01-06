package com.publicissapient.recruting.mowitnow.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Instruction {

    private final List<MowerAction> mowersAndActions;

    public static class MowerAction {
        private final Mower mower;
        private final String actions;

        public MowerAction(Mower mower, String actions) {
            this.mower = mower;
            this.actions = actions;
        }

        public Mower getMower() {
            return mower; // todo check mutability
        }

        public String getActions() {
            return actions; // TODO check mutability
        }
    }

    public Instruction(List<MowerAction> mowersAndActions) {
        this.mowersAndActions = mowersAndActions;
    }

    public List<MowerAction> getMowersAndActions() {
        return List.copyOf(mowersAndActions); // TODO check mutability
    }

    public List<Mower> execute() {
        mowersAndActions
                .forEach(mowerAction -> mowerAction.actions.chars()
                        .forEach(action -> mowerAction.mower.move((char) action)));

        return mowersAndActions.stream()
                .map(mowerAction -> mowerAction.mower)
                .collect(Collectors.toList());
    }
}
