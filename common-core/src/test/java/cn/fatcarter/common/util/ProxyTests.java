package cn.fatcarter.common.util;

import cn.fatcarter.common.proxy.ThrowableProxy;
import org.junit.Assert;
import org.junit.Test;

public class ProxyTests {

    @Test
    public void throwableProxyTest() {
        DemoService demoService = new DemoServiceImpl();
        var helloAlice = demoService.sayHello("Alice");
        Assert.assertEquals("Hello, Alice", helloAlice);
        DemoService proxied = ThrowableProxy.ignoreThrowable(demoService);
        var helloJohn = proxied.sayHello("John");
        Assert.assertEquals("Hello, John", helloJohn);

        DemoService proxied1 = ThrowableProxy.ignoreThrowable(demoService);
        var helloLucy = proxied1.sayHello("Lucy");
        Assert.assertEquals("Hello, Lucy", helloLucy);
    }


    public static interface DemoService {
        String sayHello(String name);
    }

    private static class DemoServiceImpl implements DemoService {
        @Override
        public String sayHello(String name) {
            return "Hello, " + name;
        }
    }
}
