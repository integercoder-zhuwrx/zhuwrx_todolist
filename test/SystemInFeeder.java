import java.io.*;

public class SystemInFeeder implements java.io.Closeable {
    private final InputStream originalIn;
    private BufferedWriter writer;

    public SystemInFeeder() throws IOException {
        PipedInputStream pis = new PipedInputStream();
        PipedOutputStream pos = new PipedOutputStream();
        pis.connect(pos);
        originalIn = System.in;
        System.setIn(pis);
        writer = new BufferedWriter(new OutputStreamWriter(pos));
    }

    public void writeLine(Object o) throws IOException {
        writer.write(String.valueOf(o));
        writer.newLine();
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        writer.close();
        System.setIn(originalIn);
    }
}
