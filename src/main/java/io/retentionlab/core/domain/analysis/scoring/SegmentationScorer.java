package io.retentionlab.core.domain.analysis.scoring;

import io.retentionlab.core.domain.model.Block;
import io.retentionlab.core.domain.model.ScriptStructure;
import io.retentionlab.core.domain.parameters.ScoringParameters;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SegmentationScorer {

    private final ScoringParameters parameters;

    public SegmentationScorer(ScoringParameters parameters) {
        this.parameters = parameters;
    }

    public double calculate(ScriptStructure structure) {

        if (structure == null || structure.blocks().isEmpty()) {
            return 0;
        }

        List<Integer> wordsPerBlock = structure.blocks()
            .stream()
            .map(Block::wordCount)
            .toList();

        int n = wordsPerBlock.size();

        if (n == 0) {
            return 0;
        }

        // 1 Mean
        double mean = wordsPerBlock.stream()
            .mapToDouble(Integer::doubleValue)
            .average()
            .orElse(0);

        // 2 StdDev
        double variance = wordsPerBlock.stream()
            .mapToDouble(w -> Math.pow(w - mean, 2))
            .sum() / n;

        double stdDev = Math.sqrt(variance);

        // 3 Coefficient of Variation
        double cv = stdDev / (mean + parameters.epsilon());

        // 4 Normalização
        double normalized = clamp(1 - cv, 0, 1);

        // 5 Peso oficial
        return normalized * 25;
    }

    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(value, max));
    }
}