package com.so;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

/**
 * @author Ilya Ismagilov <ilya@singulator.net>
 */
public class PerformanceTest {
    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();

    //10.93
    @Ignore
    @BenchmarkOptions(warmupRounds = 1, benchmarkRounds = 2)
    @Test
    public void testDirectBuffer1K() {
        Sum s = new Sum();

        s.sumUsingDirectBuffer("huge2billions-4295016525523462715.txt", 1024);
    }

    //10.75
    @Ignore
    @BenchmarkOptions(warmupRounds = 1, benchmarkRounds = 2)
    @Test
    public void testDirectBuffer2K() {
        Sum s = new Sum();

        s.sumUsingDirectBuffer("huge2billions-4295016525523462715.txt", 2 * 1024);
    }

    //10.32
    @Ignore
    @BenchmarkOptions(warmupRounds = 1, benchmarkRounds = 2)
    @Test
    public void testDirectBuffer1M() {
        Sum s = new Sum();

        s.sumUsingDirectBuffer("huge2billions-4295016525523462715.txt", 1024 * 1024);
    }

    //11.21
    @Ignore
    @BenchmarkOptions(warmupRounds = 1, benchmarkRounds = 2)
    @Test
    public void testDirectBuffer8M() {
        Sum s = new Sum();

        s.sumUsingDirectBuffer("huge2billions-4295016525523462715.txt", 8 * 1024 * 1024);
    }

    //8.9
    @Ignore
    @BenchmarkOptions(warmupRounds = 1, benchmarkRounds = 2)
    @Test
    public void testDirectBuffer32M() {
        Sum s = new Sum();

        s.sumUsingDirectBuffer("huge2billions-4295016525523462715.txt",32 * 1024 * 1024);
    }

    //10.95
    @Ignore
    @BenchmarkOptions(warmupRounds = 1, benchmarkRounds = 2)
    @Test
    public void testDirectBuffer128M() {
        Sum s = new Sum();

        s.sumUsingDirectBuffer("huge2billions-4295016525523462715.txt", 128 * 1024 * 1024);
    }
}
