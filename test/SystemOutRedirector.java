import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SystemOutRedirector implements java.io.Closeable {
    PrintStream originalOut = System.out;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    public SystemOutRedirector() {
        System.setOut(new PrintStream(baos));
    }

    public String getOutputText() {
        return baos.toString();
    }

    public String takeOutputText() {
        String output = baos.toString();
        baos.reset();
        return output;
    }

    public String takeLine() throws IOException {
        String output = baos.toString();
        baos.reset();
        List<String> lines = new ArrayList<>(output.lines().toList());
        String firstLine = lines.remove(0);
        baos.write(String.join(System.lineSeparator(), lines).getBytes(StandardCharsets.UTF_8));
        return firstLine;
    }

    @Override
    public void close() throws IOException {
        baos.close();
        System.setOut(originalOut);
    }
}
