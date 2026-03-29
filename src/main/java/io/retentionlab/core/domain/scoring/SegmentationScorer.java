package io.retentionlab.core.domain.scoring;

import io.retentionlab.core.domain.model.ScriptStructure;
import io.retentionlab.core.domain.parameters.ScoringParameters;

import java.util.List;

public class SegmentationScorer {

    private final ScoringParameters parameters;

    public SegmentationScorer(ScoringParameters parameters) {
        this.parameters = parameters;
    }

    public double calculate(ScriptStructure structure) {

        List<Integer> blocks = structure.wordsPerBlock();
        int n = blocks.size();

        if (n == 0) return 0;

        double mean = blocks.stream()
            .mapToDouble(Integer::doubleValue)
            .average()
            .orElse(0);

        double variance = blocks.stream()
            .mapToDouble(w -> Math.pow(w - mean, 2))
            .sum() / n;

        double stdDev = Math.sqrt(variance);

        double cv = stdDev / (mean + parameters.epsilon());

        double normalized = clamp(1 - cv, 0, 1);

        return normalized * 25;
    }

    private double clamp(double x, double min, double max) {
        return Math.max(min, Math.min(x, max));
    }
}