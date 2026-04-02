package io.retentionlab.domain.segmentation;

import io.retentionlab.domain.model.ScriptStructure;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StructuralSegmentationServiceTest {

    private final StructuralSegmentationService service =
        new StructuralSegmentationService();

    @Test
    void shouldCountWordsInSimpleText() {
        String text = "um dois tres";

        ScriptStructure result = service.segment(text);

        assertThat(result.getTotalWords()).isEqualTo(3);
    }

    @Test
    void shouldIgnoreMultipleSpaces() {
        String text = "um  dois   tres";

        ScriptStructure result = service.segment(text);

        assertThat(result.getTotalWords()).isEqualTo(3);
    }

    @Test
    void shouldIgnoreLineBreaksAndTabs() {
        String text = "um\ndois\ttres";

        ScriptStructure result = service.segment(text);

        assertThat(result.getTotalWords()).isEqualTo(3);
    }
}