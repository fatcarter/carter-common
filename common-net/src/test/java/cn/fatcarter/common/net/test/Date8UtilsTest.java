package cn.fatcarter.common.net.test;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;

public class Date8UtilsTest {

    @Test
    public void testDuration(){
        LocalDate now = LocalDate.now();
        LocalDate prev = now.minusDays(25);
        System.out.println(Duration.between(prev.atStartOfDay(), now.atStartOfDay()).toDays());
    }

    public static void main(String[] args) {
        Path path = Paths.get("/Users/fatcarter/Downloads/test");
        System.out.println(path.resolve("/"));
    }
}
