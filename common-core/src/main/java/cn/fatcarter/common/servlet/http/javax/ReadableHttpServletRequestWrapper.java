package cn.fatcarter.common.servlet.http.javax;

import cn.fatcarter.common.util.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
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

    protected ServletInputStream getReadableInputStream() throws IOException {
        return new ReadableServletInputStream(super.getInputStream());
    }

    private static class ReadableServletInputStream extends ServletInputStream {
        private final ByteArrayInputStream stream;

        public ReadableServletInputStream(ServletInputStream stream) throws IOException {
            this.stream = new ByteArrayInputStream(IOUtils.read(stream));
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
