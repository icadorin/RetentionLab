package io.retentionlab.core.domain.scoring;

import io.retentionlab.core.domain.parameters.ScoringParameters;

import java.util.List;

public class RiskScorer {

    private final ScoringParameters parameters;

    public RiskScorer(ScoringParameters parameters) {
        this.parameters = parameters;
    }

    public double calculate(List<Double> tensions) {

        int totalBlocks = tensions.size();
        if (totalBlocks == 0) return 0;

        long riskBlocks = tensions.stream()
            .filter(t -> t < parameters.riskThreshold())
            .count();

        double riskRatio =
            clamp(riskBlocks / (double) totalBlocks, 0, 1);

        return (1 - riskRatio) * 10;
    }

    private double clamp(double x, double min, double max) {
        return Math.max(min, Math.min(x, max));
    }
}