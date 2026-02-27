package io.retentionlab.core.domain.analysis.opening;

import io.retentionlab.core.domain.model.Block;
import io.retentionlab.core.domain.model.ScriptStructure;

public class OpeningHookAnalyzer {

    private static final int OPENING_SECONDS = 30;

    private final int wordsPerMinute;

    public OpeningHookAnalyzer(int wordsPerMinute) {
        this.wordsPerMinute = wordsPerMinute;
    }

    public OpeningHookScore analyze(ScriptStructure structure) {

        int expectedWords = (wordsPerMinute / 60) * OPENING_SECONDS;

        int wordsInOpening = structure.blocks().stream()
            .filter(block -> block.startMinute() < 0.5)
            .mapToInt(Block::wordCount)
            .sum();

        double rawScore = wordsInOpening / (double) expectedWords;
        double densityScore = Math.min(rawScore, 1.0);

        return new OpeningHookScore(
            densityScore,
            wordsInOpening,
            expectedWords
        );
    }
}