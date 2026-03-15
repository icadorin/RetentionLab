package io.retentionlab.core.domain.analysis.scoring;

import org.springframework.stereotype.Service;

@Service
public class RetentionScoringService {

    public double calculate(
        double segmentation,
        double hook,
        double loopDensity,
        double tension,
        double risk,
        double closure
    ) {

        double total =
            segmentation +
                hook +
                loopDensity +
                tension +
                risk +
                closure;

        return clamp(total, 0, 100);
    }

    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(value, max));
    }
}