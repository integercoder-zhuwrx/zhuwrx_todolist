public class ProgressBar {
    public int max;
    public int value;

    public ProgressBar(int max, int value) {
        this.max = max;
        this.value = value;
    }

    public static int getPercentage(ProgressBar progressBar) {
        double fraction = (double) progressBar.value / progressBar.max;
        return (int) (fraction * 100);
    }

    public static String getProgressBar(ProgressBar progressBar, int widthInChars) {
        int doneMarkerCount = (int) (getPercentage(progressBar) / 100.0 * widthInChars);
        int undoneMarkerCount = widthInChars - doneMarkerCount;
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < doneMarkerCount; i++) {
            sb.append('=');
        }
        for (int i = 0; i < undoneMarkerCount; i++) {
            sb.append(' ');
        }
        sb.append(']');
        return sb.toString();
    }
}
