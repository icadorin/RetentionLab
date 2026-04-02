package io.retentionlab.domain.segmentation;

import io.retentionlab.domain.model.ScriptStructure;

public class StructuralSegmentationService {

    public ScriptStructure segment(String text) {

        String trimmed = text.trim();

        if (trimmed.isEmpty()) {
            return new ScriptStructure(0);
        }

        String[] words = trimmed.split("\\s+");

        return new ScriptStructure(words.length);
    }
}