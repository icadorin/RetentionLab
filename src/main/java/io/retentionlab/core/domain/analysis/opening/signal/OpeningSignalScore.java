package io.retentionlab.core.domain.analysis.opening.signal;

public record OpeningSignalScore(
    int conflictSignals,
    int curiositySignals,
    int promiseSignals,
    double normalizedScore
) {

    public OpeningSignalScore {
        if (normalizedScore < 0 || normalizedScore > 1) {
            throw new IllegalArgumentException(
                "Normalized score must be between 0 and 1"
            );
        }
    }
}