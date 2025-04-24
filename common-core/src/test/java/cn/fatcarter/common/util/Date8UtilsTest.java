package cn.fatcarter.common.util;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Date8UtilsTest {
    @Test
    public void testListDays() {
        List<LocalDate> dates = Date8Utils.listDays(LocalDate.of(2025, 4, 24), LocalDate.of(2025, 5, 2));
        System.out.println(dates);
        Assert.assertEquals(9, dates.size());
        Assert.assertEquals(LocalDate.of(2025, 4, 24), dates.get(0));
        Assert.assertEquals(LocalDate.of(2025, 4, 25), dates.get(1));
        Assert.assertEquals(LocalDate.of(2025, 4, 26), dates.get(2));
        Assert.assertEquals(LocalDate.of(2025, 4, 27), dates.get(3));
    }

    @Test
    public void testEachDays() {
        List<LocalDate> dates = new ArrayList<>();
        Date8Utils.eachDays(LocalDate.of(2025,4,24),LocalDate.of(2025,4,27),date->{
            dates.add(date);
        });
        System.out.println(dates);
        Assert.assertEquals(4, dates.size());
        Assert.assertEquals(LocalDate.of(2025, 4, 24), dates.get(0));
        Assert.assertEquals(LocalDate.of(2025, 4, 25), dates.get(1));
        Assert.assertEquals(LocalDate.of(2025, 4, 26), dates.get(2));
        Assert.assertEquals(LocalDate.of(2025, 4, 27), dates.get(3));
    }


    @Test
    public void testListMonths(){
        List<LocalDate> months = Date8Utils.listMonths(LocalDate.of(2025, 4, 24), LocalDate.of(2025, 6, 27));
        System.out.println(months);
        Assert.assertEquals(3, months.size());
        Assert.assertEquals(LocalDate.of(2025, 4, 1), months.get(0));
        Assert.assertEquals(LocalDate.of(2025, 5, 1), months.get(1));
        Assert.assertEquals(LocalDate.of(2025, 6, 1), months.get(2));
    }

    @Test
    public void testEachMonths(){
        List<LocalDate> months = new ArrayList<>();
        Date8Utils.eachMonths(LocalDate.of(2025, 4, 24), LocalDate.of(2025, 6, 27), month -> {
            months.add(month);
        });
        System.out.println(months);
        Assert.assertEquals(3, months.size());
        Assert.assertEquals(LocalDate.of(2025, 4, 1), months.get(0));
        Assert.assertEquals(LocalDate.of(2025, 5, 1), months.get(1));
        Assert.assertEquals(LocalDate.of(2025, 6, 1), months.get(2));
    }

}
