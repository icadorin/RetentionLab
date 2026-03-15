package io.retentionlab.core.domain.analysis.risk;

import java.util.List;

public record RiskResult(
    List<Double> tensions
) {
    public int riskBlockCount(double threshold) {
        return (int) tensions.stream()
            .filter(t -> t < threshold)
            .count();
    }
}