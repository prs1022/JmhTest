package com.string;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 *  实验结果
 *  Benchmark                              Mode  Cnt      Score   Error   Units
 *  BenchMarkForString.testSimpleString   thrpt    2    131.140          ops/ms
 *  BenchMarkForString.testStringBuilder  thrpt    2  88125.936          ops/ms
 *
 *
 * @author rensong.pu
 * @date 2020/7/31 16:14 星期五
 **/
@Fork(1)
@Threads(8)
@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@Measurement(iterations = 2, time = 3, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class BenchMarkForString {
    @Benchmark
    public void testSimpleString() {
        String s = "Hello world!";
        for (int i = 0; i < 10; i++) {
            s += s;
        }
    }

    @Benchmark
    public void testStringBuilder() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(i);
        }
    }

    public static void main(String[] args) throws Exception {
        Options opts = new OptionsBuilder()
                .include(BenchMarkForString.class.getSimpleName())
                .resultFormat(ResultFormatType.CSV)
                .build();

        new Runner(opts).run();
    }
}
