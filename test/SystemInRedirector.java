import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

class ByteArrayBuilder {
    private final StringBuilder sb = new StringBuilder();

    public void append(Object o) {
        sb.append(o);
    }

    public void appendLine(Object o) {
        sb.append(o);
        sb.append(System.lineSeparator());
    }

    public byte[] buildByteArray() {
        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }
}

public class SystemInRedirector implements java.io.Closeable {
    InputStream originalIn = System.in;
    ByteArrayInputStream bais;

    public SystemInRedirector(ByteArrayBuilder byteArrayBuilder) {
    }

    @Override
    public void close() throws IOException {

    }
}
