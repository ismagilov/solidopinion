package com.so;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

/**
 * @author Ilya Ismagilov <ilya@singulator.net>
 */
public class PerformanceTest {
    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();

    //8.47
    @BenchmarkOptions(warmupRounds = 0, benchmarkRounds = 1)
    @Test
    public void test1Round1M() {
        Sum s = new Sum();

        s.sumUsingDirectBufferAndFileChannel("huge2billions-4295016525523462715.txt", 1024 * 1024);
    }

    //8.76 9.25
    @BenchmarkOptions(warmupRounds = 1, benchmarkRounds = 2)
    @Test
    public void test1WarmUp2Rounds1M() {
        Sum s = new Sum();

        s.sumUsingDirectBufferAndFileChannel("huge2billions-4295016525523462715.txt", 1024 * 1024);
    }

    //9.97
    @BenchmarkOptions(warmupRounds = 0, benchmarkRounds = 1)
    @Test
    public void test1Round10M() {
        Sum s = new Sum();

        s.sumUsingDirectBufferAndFileChannel("huge2billions-4295016525523462715.txt", 10 * 1024 * 1024);
    }

    //10.37
    @BenchmarkOptions(warmupRounds = 1, benchmarkRounds = 2)
    @Test
    public void test1WarmUp2Rounds10M() {
        Sum s = new Sum();

        s.sumUsingDirectBufferAndFileChannel("huge2billions-4295016525523462715.txt",10 * 1024 * 1024);
    }

    //8.08
    @BenchmarkOptions(warmupRounds = 0, benchmarkRounds = 1)
    @Test
    public void test1Round8K() {
        Sum s = new Sum();

        s.sumUsingDirectBufferAndFileChannel("huge2billions-4295016525523462715.txt", 8 * 1024);
    }

    //6.95 9.10
    @BenchmarkOptions(warmupRounds = 1, benchmarkRounds = 2)
    @Test
    public void test1WarmUp2Rounds8K() {
        Sum s = new Sum();

        s.sumUsingDirectBufferAndFileChannel("huge2billions-4295016525523462715.txt",8 * 1024);
    }
}
