package io.retentionlab.core.domain.model.segmentation;

import io.retentionlab.core.domain.model.Block;
import io.retentionlab.core.domain.model.ScriptStructure;

import java.util.ArrayList;
import java.util.List;

public class StructuralSegmentationService {
    private static final int WORD_PER_MINUTE = 150;
    private static final int BLOCK_SIZE = 150;

    public ScriptStructure analyse(String text) {

        if (text == null || text.isBlank()) {
            return new ScriptStructure(0, 0, List.of());
        }

        String[] word = text.trim().split("\\s+");
        int totalWords = word.length;

        double duration = totalWords / (double)WORD_PER_MINUTE;

        List<Block> blocks = new ArrayList<>();

        int blockId = 1;
        for (int i = 0; i < totalWords; i += BLOCK_SIZE) {

            int remaining = Math.min(BLOCK_SIZE, totalWords - i);
            double startMinute = i / (double) WORD_PER_MINUTE;
            double endMinute = (i + remaining) / (double) WORD_PER_MINUTE;

            blocks.add(new Block(
                blockId++,
                startMinute,
                endMinute,
                remaining
            ));
        }

        return new ScriptStructure(totalWords, duration, blocks);
    }
}
