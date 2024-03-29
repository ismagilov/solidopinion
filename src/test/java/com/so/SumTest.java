package com.so;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */

import org.junit.Assert;
import org.junit.Test;

public class SumTest {
    @Test
    public void testConvertSmallOneByteNumber() {
        Sum s = new Sum();

        long num = s.convertBytesToNum(new byte[] {0x5, 0x0, 0x0, 0x0});

        Assert.assertEquals(5, num);
    }

    @Test
    public void testConvertBigOneByteNumber() {
        Sum s = new Sum();

        long num = s.convertBytesToNum(new byte[] {0x0, 0x0, 0x0, 0x1});

        Assert.assertEquals(16777216, num);
    }

    @Test
    public void testReadNegativeNumber() {
        Sum s = new Sum();

        long num = s.convertBytesToNum(new byte[] {(byte)0xFD, (byte)0xFF, (byte)0xFF, (byte)0xFF});

        Assert.assertEquals(-3, num);
    }

    @Test
    public void testPositiveIntToUnsignedInt() {
        Sum s = new Sum();

        long l = s.toUnsignedInt(1024);

        Assert.assertEquals(1024, l);
    }

    @Test
    public void testNegativeIntToUnsignedInt() {
        Sum s = new Sum();

        long l = s.toUnsignedInt(-512);

        Assert.assertEquals(4294966784L, l);
    }

    @Test
    public void test64BitPrecisionTwoMaxs() {
        long l1 = 0xFFFFFFFFFFFFFFFFL;
        long l2 = 0xFFFFFFFFFFFFFFFFL;

        Assert.assertTrue(Long.toHexString(l1 + l2).equalsIgnoreCase("FFFFFFFFFFFFFFFE"));
    }

    @Test
    public void test64BitPrecisionMaxAndSmall() {
        long l1 = 0xFFFFFFFFFFFFFFFFL;
        long l2 = 0xC0L;

        Assert.assertTrue(Long.toHexString(l1 + l2).equalsIgnoreCase("BF"));
    }
}
