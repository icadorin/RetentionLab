package io.retentionlab.core.domain.analysis.risk;

import java.util.List;

public record RiskResult(
    List<RiskBlock> riskBlocks,
    List<Double> tensions
) {
    public int riskBlockCount(double threshold) {
        return (int) riskBlocks.stream()
            .filter(r -> r.tensionValue() <  threshold)
            .count();
    }
}