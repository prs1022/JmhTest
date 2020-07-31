package com.atomic;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * 吞吐量
 * Benchmark   Mode  Cnt    Score     Error   Units
 * Main.run0  thrpt    5   62.007 ±   0.747  ops/us
 * Main.run1  thrpt    5  951.455 ± 149.423  ops/us
 *
 * 平均耗时
 * Benchmark  Mode  Cnt  Score    Error  Units
 * Main.run0  avgt    5  0.183 ±  0.001  us/op
 * Main.run1  avgt    5  0.010 ±  0.001  us/op
 *
 * @author rensong.pu
 * @date 2020/7/31 17:58 星期五
 **/
@OutputTimeUnit(TimeUnit.MICROSECONDS)
//@BenchmarkMode(Mode.Throughput)
@BenchmarkMode(Mode.AverageTime)
public class Main {

    private static AtomicLong count = new AtomicLong();
    private static LongAdder longAdder = new LongAdder();

    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder().include(Main.class.getName()).forks(1).build();
        new Runner(options).run();
    }

    @Benchmark
    @Threads(10)
    public void run0() {
        count.getAndIncrement();
    }

    @Benchmark
    @Threads(10)
    public void run1() {
        longAdder.increment();
    }
}
