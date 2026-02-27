package io.retentionlab.core.domain.analysis.opening;

public record OpeningHookScore(
    double densityScore,
    int wordsAnalyzed,
    int expectedWords
) {
    public OpeningHookScore {
        if (densityScore < 0 || densityScore > 1) {
            throw new IllegalArgumentException("Density score must be between 0 and 1");
        }
    }
}