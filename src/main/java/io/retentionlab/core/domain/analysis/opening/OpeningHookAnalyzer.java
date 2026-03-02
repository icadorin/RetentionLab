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

        // Quantidade esperada de palavras nos primeiros 30 segundos
        // Ex: 150 wpm → 75 palavras em 30s
        int expectedWords = (wordsPerMinute / 60) * OPENING_SECONDS;

        // Soma as palavras apenas dos blocos dentro dos primeiros 30s (0.5 minuto)
        int wordsInOpening = structure.blocks().stream()
            .filter(block -> block.startMinute() < 0.5)
            .mapToInt(Block::wordCount)
            .sum();

        // Relação entre palavras reais e esperadas
        // > 1 significa que passou do ideal
        double rawScore = wordsInOpening / (double) expectedWords;

        // Limitamos a 1.0 (100%)
        double densityScore = Math.min(rawScore, 1.0);

        return new OpeningHookScore(
            densityScore,
            wordsInOpening,
            expectedWords
        );
    }
}