package com.concurrent;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@Fork(1)
@State(Scope.Benchmark)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.Throughput)
@Threads(20)
public class RWLockBenchmark {

    RWLockMap<String, String> rwMap = new RWLockMap<>();
    LockMap<String, String> lockMap = new LockMap<>();

    @Benchmark
    public void readWriteLock(Blackhole bh) {
        bh.consume(rwMap.get("1"));
    }

    @Benchmark
    public void rLock(Blackhole bh) {
        bh.consume(lockMap.get("1"));
    }

    public static void main(String[] args) throws Exception {
        Options opts = new OptionsBuilder()
                .include(RWLockBenchmark.class.getSimpleName())
                .resultFormat(ResultFormatType.CSV)
                .build();
        new Runner(opts).run();
    }
}
