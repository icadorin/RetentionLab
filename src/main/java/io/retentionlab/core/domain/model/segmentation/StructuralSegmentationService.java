package io.retentionlab.core.domain.model.segmentation;

import io.retentionlab.core.domain.model.Block;
import io.retentionlab.core.domain.model.ScriptStructure;

import java.util.ArrayList;
import java.util.List;

public class StructuralSegmentationService {

    private static final int DEFAULT_WORDS_PER_MINUTE = 150;
    private static final int DEFAULT_BLOCK_SIZE = 150;

    private final int wordsPerMinute;
    private final int blockSize;

    public StructuralSegmentationService() {
        this(DEFAULT_WORDS_PER_MINUTE, DEFAULT_BLOCK_SIZE);
    }

    public StructuralSegmentationService(int wordsPerMinute, int blockSize) {
        this.wordsPerMinute = wordsPerMinute;
        this.blockSize = blockSize;
    }

    public ScriptStructure analyze(String text) {
        if (isBlank(text)) {
            return emptyScript();
        }

        int totalWords = countWords(text);

        return new ScriptStructure(
            totalWords,
            calculateDuration(totalWords),
            createBlocks(totalWords)
        );
    }

    private boolean isBlank(String text) {
        return text == null || text.isBlank();
    }

    private ScriptStructure emptyScript() {
        return new ScriptStructure(0, 0.0, List.of());
    }

    private int countWords(String text) {
        return text.trim().split("\\s+").length;
    }

    private double calculateDuration(int totalWords) {
        return totalWords / (double) wordsPerMinute;
    }

    private List<Block> createBlocks(int totalWords) {
        List<Block> blocks = new ArrayList<>();
        int blockId = 1;

        for (int startWord = 0; startWord < totalWords; startWord += blockSize) {
            int endWord = Math.min(startWord + blockSize, totalWords);
            blocks.add(createBlock(blockId++, startWord, endWord));
        }

        return blocks;
    }

    private Block createBlock(int id, int startWord, int endWord) {
        int wordCount = endWord - startWord;

        return new Block(
            id,
            startWord / (double) wordsPerMinute,
            endWord / (double) wordsPerMinute,
            wordCount
        );
    }
}