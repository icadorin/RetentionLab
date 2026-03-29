package io.retentionlab.core.domain.scoring;

import io.retentionlab.core.domain.parameters.ScoringParameters;

import java.util.List;

public class TensionScorer {

    private final ScoringParameters parameters;

    public TensionScorer(ScoringParameters parameters) {
        this.parameters = parameters;
    }

    public double calculate(List<Double> tensions) {

        int totalBlocks = tensions.size();

        if (totalBlocks < 3) return 0;

        int positiveSteps = 0;

        for (int i = 0; i < totalBlocks - 1; i++) {
            double delta = tensions.get(i + 1) - tensions.get(i);
            if (delta > 0) positiveSteps++;
        }

        double increasingRatio =
            positiveSteps / (double) (totalBlocks - 1);

        double normalized =
            clamp(increasingRatio / parameters.idealIncreasingRatio(), 0, 1);

        return normalized * 15;
    }

    private double clamp(double x, double min, double max) {
        return Math.max(min, Math.min(x, max));
    }
}