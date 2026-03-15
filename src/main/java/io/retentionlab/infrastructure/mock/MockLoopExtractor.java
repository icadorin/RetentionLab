package io.retentionlab.infrastructure.mock;

import io.retentionlab.core.domain.analysis.loop.Loop;
import io.retentionlab.core.domain.analysis.loop.LoopExtractor;
import io.retentionlab.core.domain.analysis.loop.LoopType;
import io.retentionlab.core.domain.model.ScriptStructure;

import java.util.List;

public class MockLoopExtractor implements LoopExtractor {

    @Override
    public List<Loop> extractLoops(ScriptStructure structure, String fullText) {

        if (structure.blocks().isEmpty()) {
            return List.of();
        }

        return List.of(
            new Loop("loop-1", 1, null, "Mock loop", LoopType.MYSTERY)
        );
    }
}