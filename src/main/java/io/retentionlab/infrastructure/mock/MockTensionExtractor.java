package io.retentionlab.infrastructure.mock;

import io.retentionlab.core.domain.analysis.tension.TensionExtractor;
import io.retentionlab.core.domain.model.ScriptStructure;

import java.util.List;
import java.util.stream.IntStream;

public class MockTensionExtractor implements TensionExtractor {

    @Override
    public List<Double> extractTension(ScriptStructure structure, String fullText) {

        return IntStream.range(0, structure.blocks().size())
            .mapToDouble(i -> Math.min(0.2 + (i * 0.1), 1.0))
            .boxed()
            .toList();
    }
}