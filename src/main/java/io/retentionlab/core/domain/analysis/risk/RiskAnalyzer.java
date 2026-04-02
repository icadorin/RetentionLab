package io.retentionlab.core.domain.analysis.risk;

import io.retentionlab.core.domain.analysis.loop.Loop;
import io.retentionlab.core.domain.model.Block;
import io.retentionlab.core.domain.model.ScriptStructure;

import java.util.ArrayList;
import java.util.List;

public class RiskAnalyzer {

    private static final double LOW_TENSION_THRESHOLD = 0.3;

    public RiskResult analyze(
        ScriptStructure structure,
        List<Double> tensionValues,
        List<Loop> loops
    ) {

        if (tensionValues.size() != structure.blocks().size()) {
            throw new IllegalArgumentException(
                "Tension values must match blocks count"
            );
        }

        List<RiskBlock> risks = new ArrayList<>();

        boolean hasLoops = loops != null && !loops.isEmpty();

        for (int i = 0; i < structure.blocks().size(); i++) {

            Block block = structure.blocks().get(i);
            double tension = tensionValues.get(i);

            if (tension < LOW_TENSION_THRESHOLD) {
                risks.add(new RiskResult(
                    block,
                    tension,
                    RiskType.LOW_TENSION,
                    1 - tension
                ));
            }

            if (i > 0) {
                Block prev = structure.blocks().get(i-1);

                if (Math.abs(block.wordCount() - prev.wordCount()) < 10) {
                    risks.add(new RiskResult(
                        block,
                        tension,
                        RiskType.REPETITION,
                        0.5
                    ));
                }
            }

            if (block.wordCount() < 50) {
                risks.add(new RiskBlock(
                    block,
                    tension,
                    RiskType.HIGH_ABSTRACTION,
                    0.4
                ));
            }

            if (!hasLoops) {
                risks.add(new RiskBlock(
                    block,
                    tension,
                    RiskType.NO_LOOPS,
                    0.6
                ));
            }
        }

        return new RiskResult(risks, tensionValues);
    }
}