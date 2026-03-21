package io.retentionlab.core.domain.analysis.segmentation;

import io.retentionlab.core.domain.model.ScriptStructure;

public class SegmentationAnalyzer {

    public double analyze(ScriptStructure structure) {

        if (structure.blocks().isEmpty()) {
            return 0.0;
        }

        // Quanto mais blocos equilibrados melhor
        double idealBlockCount = 6.0;
        double ratio = Math.min(structure.blocks().size() / idealBlockCount, 1.0);

        return ratio; // 0–1
    }
}