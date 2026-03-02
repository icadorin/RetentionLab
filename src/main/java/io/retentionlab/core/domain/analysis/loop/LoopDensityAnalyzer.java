package io.retentionlab.core.domain.analysis.loop;

import io.retentionlab.core.domain.model.ScriptStructure;

import java.util.List;

public class LoopDensityAnalyzer {

    private static final double IDEAL_LOOP_DENSITY = 0.30; // loops por bloco
    private final LoopExtractor loopExtractor;

    public LoopDensityAnalyzer(LoopExtractor loopExtractor) {
        this.loopExtractor = loopExtractor;
    }

    public LoopDensityResult analyze(ScriptStructure structure, String fullText) {
        List<Loop> loops = loopExtractor.extractLoops(structure, fullText);

        int totalBlocks = structure.blocks().size();
        int openLoops = countOpenLoops(loops);

        // Densidade = loops abertos por bloco estrutural
        // Ex: 3 loops abertos / 10 blocos = 0.3
        double density = totalBlocks > 0 ?
            (double) openLoops / totalBlocks : 0.0;

        // Normaliza comparando com densidade ideal (0.30)
        // Se atingir ou ultrapassar o ideal → score máximo (1.0)
        double normalizedScore = Math.min(density / IDEAL_LOOP_DENSITY, 1.0);

        // Converte score normalizado (0–1) para peso da spec (0–20)
        double finalScore = normalizedScore * 20;

        return new LoopDensityResult(
            loops,
            openLoops,
            density,
            finalScore
        );
    }

    private int countOpenLoops(List<Loop> loops) {
        return (int) loops.stream()
            .filter(Loop::isOpen)
            .count();
    }
}