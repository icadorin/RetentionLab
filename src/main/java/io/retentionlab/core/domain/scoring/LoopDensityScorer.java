package io.retentionlab.core.domain.scoring;

import io.retentionlab.core.domain.parameters.ScoringParameters;

public class LoopDensityScorer {

    private final ScoringParameters parameters;

    public LoopDensityScorer(ScoringParameters parameters) {
        this.parameters = parameters;
    }

    public double calculate(int openLoops, int totalBlocks) {

        if (totalBlocks <= 0) return 0;

        double loopDensity =
            openLoops / (double) totalBlocks;

        double normalized =
            clamp(loopDensity / parameters.idealLoopDensity(), 0, 1);

        return normalized * 20;
    }

    private double clamp(double x, double min, double max) {
        return Math.max(min, Math.min(x, max));
    }
}