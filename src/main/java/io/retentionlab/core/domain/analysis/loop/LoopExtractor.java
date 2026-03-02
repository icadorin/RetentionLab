package io.retentionlab.core.domain.analysis.loop;

import io.retentionlab.core.domain.model.ScriptStructure;
import java.util.List;

public interface LoopExtractor {
    List<Loop> extractLoops(ScriptStructure structure, String fullText);
}