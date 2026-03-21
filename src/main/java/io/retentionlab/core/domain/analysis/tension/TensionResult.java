package io.retentionlab.core.domain.analysis.tension;

import java.util.List;

public record TensionResult(
    List<Double> tensionValues,
    double increasingRatio,
    boolean hasPeak,
    double variance,
    double score
) {

    public static final TensionResult INSUFFICIENT_DATA =
        new TensionResult(List.of(), 0.0, false, 0.0, 0.0);
}