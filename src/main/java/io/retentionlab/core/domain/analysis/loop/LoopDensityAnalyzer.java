package io.retentionlab.core.domain.analysis.loop;

import io.retentionlab.core.domain.model.ScriptStructure;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoopDensityAnalyzer {

    private final LoopExtractor loopExtractor;

    public LoopDensityAnalyzer(LoopExtractor loopExtractor) {
        this.loopExtractor = loopExtractor;
    }

    public LoopDensityResult analyze(
        ScriptStructure structure,
        String fullText
    ) {

        List<Loop> loops =
            loopExtractor.extractLoops(structure, fullText);

        int openLoops = countOpenLoops(loops);
        int closedLoops = countClosedLoops(loops);

        return new LoopDensityResult(
            loops,
            openLoops,
            closedLoops
        );
    }

    private int countOpenLoops(List<Loop> loops) {
        return (int) loops.stream()
            .filter(loop -> loop.closingBlockId() == null)
            .count();
    }

    private int countClosedLoops(List<Loop> loops) {
        return (int) loops.stream()
            .filter(loop -> loop.closingBlockId() != null)
            .count();
    }
}