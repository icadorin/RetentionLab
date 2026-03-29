package io.retentionlab.core.domain.scoring;

import io.retentionlab.core.domain.parameters.ScoringParameters;

public class HookScorer {

    private final ScoringParameters parameters;

    public HookScorer(ScoringParameters parameters) {
        this.parameters = parameters;
    }

    /**
     * Recebe um score já normalizado (0–1)
     * e aplica o peso definido para Opening.
     */
    public double calculate(double openingScore01) {
        return openingScore01 * parameters.openingWeight();
    }
}