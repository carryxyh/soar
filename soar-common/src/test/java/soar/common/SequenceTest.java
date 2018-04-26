package soar.common;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 3)
@BenchmarkMode({Mode.Throughput, Mode.AverageTime})
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class SequenceTest {

    static final int THREADS = Runtime.getRuntime().availableProcessors() << 1;

    /*
        Benchmark           Mode  Cnt    Score     Error   Units
        SequenceTest.seq1  thrpt    3    0.041 ±   0.012  ops/ns
        SequenceTest.seq2  thrpt    3    0.356 ±   0.242  ops/ns
        SequenceTest.seq3  thrpt    3    0.018 ±   0.003  ops/ns
        SequenceTest.seq1   avgt    3  434.871 ± 435.180   ns/op
        SequenceTest.seq2   avgt    3   59.087 ± 109.713   ns/op
        SequenceTest.seq3   avgt    3  854.506 ± 822.850   ns/op
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SequenceTest.class.getSimpleName())
                .threads(THREADS)
                .build();

        new Runner(opt).run();
//        for (; ; ) {
//            System.out.println(seq2());
//        }
    }

    static AtomicLong seq1 = new AtomicLong();
    static LongSequence seq2 = new LongSequence(128);
    static long seq3 = 0L;
    static final Object LOCK = new Object();

    @Benchmark
    public static long seq1() {
        return seq1.getAndIncrement();
    }

    @Benchmark
    public static long seq2() {
        return seq2.next();
    }

    @Benchmark
    public static long seq3() {
        synchronized (LOCK) {
            return seq3++;
        }
    }
}