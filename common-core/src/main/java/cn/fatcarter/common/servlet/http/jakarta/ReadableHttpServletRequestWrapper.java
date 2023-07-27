package cn.fatcarter.common.servlet.http.jakarta;

import cn.fatcarter.common.util.IOUtils;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.Getter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class ReadableHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private ServletInputStream stream;
    private Set<String> excludes = new HashSet<>();


    public ReadableHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public ReadableHttpServletRequestWrapper excludeUrl(String url) {
        this.excludes.add(url);
        return this;
    }

    public ReadableHttpServletRequestWrapper excludeUrls(String... url) {
        this.excludes.addAll(Arrays.asList(url));
        return this;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (stream == null) {
            stream = getReadableInputStream();
        }
        return stream;
    }

    public byte[] getInputBytes() throws IOException {
        ServletInputStream stream = getInputStream();
        if (stream instanceof ReadableServletInputStream) {
            return ((ReadableServletInputStream) stream).getBuf();
        } else {
            return IOUtils.read(stream);
        }
    }

    protected ServletInputStream getReadableInputStream() throws IOException {
        return new ReadableServletInputStream(super.getInputStream());
    }

    private static class ReadableServletInputStream extends ServletInputStream {
        @Getter
        private final ByteArrayInputStream stream;
        private final byte[] buf;


        public ReadableServletInputStream(ServletInputStream stream) throws IOException {
            this.buf = IOUtils.read(stream);
            this.stream = new ByteArrayInputStream(this.buf);
        }

        private byte[] getBuf() {
            return Arrays.copyOf(buf, buf.length);
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener readListener) {

        }

        @Override
        public int read() throws IOException {
            return stream.read();
        }
    }
}
