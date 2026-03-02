package io.retentionlab.core.domain.analysis.loop;

import java.util.List;

public record LoopDensityResult(
    List<Loop> loops,
    int openLoops,
    double density,
    double score
) {
    public static final LoopDensityResult EMPTY =
        new LoopDensityResult(List.of(), 0, 0.0, 0.0);
}