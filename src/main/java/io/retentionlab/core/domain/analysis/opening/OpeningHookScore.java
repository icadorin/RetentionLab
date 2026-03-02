package io.retentionlab.core.domain.analysis.opening;

public record OpeningHookScore(
    double densityScore,
    int wordsAnalyzed,
    int expectedWords
) {
    public static final OpeningHookScore ZERO = new OpeningHookScore(0.0, 0, 0);

    public OpeningHookScore {
        if (densityScore < 0 || densityScore > 1) {
            throw new IllegalArgumentException("Density score must be between 0 and 1");
        }
        if (wordsAnalyzed < 0 || expectedWords < 0) {
            throw new IllegalArgumentException("Word counts cannot be negative");
        }
    }
}