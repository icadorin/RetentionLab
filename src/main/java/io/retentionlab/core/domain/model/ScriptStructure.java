package io.retentionlab.core.domain.model;

import java.util.List;

public record ScriptStructure(
    int totalWords,
    double estimatedDurationMinutes,
    List<Block> blocks
) {
    public ScriptStructure {
        if (totalWords < 0) {
            throw new IllegalArgumentException("Total words cannot be negative");
        }
        if (estimatedDurationMinutes < 0) {
            throw new IllegalArgumentException("Duration cannot be negative");
        }
        if (blocks == null) {
            throw new IllegalArgumentException("Blocks cannot be null");
        }
    }

    /**
     * Retorna a quantidade de palavras em cada bloco.
     * Necessário para cálculo de Segmentation Score.
     */
    public List<Integer> wordsPerBlock() {
        return blocks.stream()
            .map(Block::wordCount)
            .toList();
    }

    /**
     * Total de blocos.
     */
    public int totalBlocks() {
        return blocks.size();
    }
}