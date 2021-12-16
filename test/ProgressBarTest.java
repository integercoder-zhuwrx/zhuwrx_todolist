import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProgressBarTest {
    @Test
    void getPercentage01() {
        var progressBar = new ProgressBar(0, 0);
        int percent = ProgressBar.getPercent(progressBar);
        assertThat(percent).isEqualTo(0);
    }

    @Test
    void getPercentage02() {
        var progressBar = new ProgressBar(5, 0);
        int percent = ProgressBar.getPercent(progressBar);
        assertThat(percent).isEqualTo(0);
    }

    @Test
    void getProgressBar01() {
        var progressBar = new ProgressBar(0, 0);
        String progress = ProgressBar.getProgressBar(progressBar, 4);
        assertThat(progress).isEqualTo("[    ]");
    }

    @Test
    void getProgressBar02() {
        var progressBar = new ProgressBar(5, 0);
        String progress = ProgressBar.getProgressBar(progressBar, 4);
        assertThat(progress).isEqualTo("[    ]");
    }
}