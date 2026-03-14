package io.retentionlab.core.domain.analysis.opening;

import io.retentionlab.core.domain.analysis.opening.signal.OpeningSignalScore;

public record OpeningAnalysisResult(
    OpeningHookScore hookScore,
    OpeningSignalScore signalScore,
    OpeningScoreAggregate scoreAggregate
) {}