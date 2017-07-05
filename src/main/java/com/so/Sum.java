package com.so;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
import java.util.HashSet;
import java.util.Set;

public class Sum {
    private final static long UINT_MASK = 0x00000000FFFFFFFFL;

    final static int INTS_PER_BUF = 8 * 1024;

    public int convertBytesToNum(byte[] bs) {
        ByteBuffer bb = ByteBuffer.wrap(bs).order(ByteOrder.LITTLE_ENDIAN);

        return bb.getInt();
    }

    public long toUnsignedInt(int i) {
        return i & UINT_MASK;
    }

    public String sumUsingDirectBufferAndFileChannel(String filePath, int intsPerBuf) {
        Path p = Paths.get(filePath);

        Set<OpenOption> options = new HashSet<>();
        options.add(StandardOpenOption.READ);

        long res = 0;
        ByteBuffer buffer = ByteBuffer.allocateDirect(4 * intsPerBuf).order(ByteOrder.LITTLE_ENDIAN);

        try (SeekableByteChannel ch = Files.newByteChannel(p, options)) {
            while (true) {

                long read = System.nanoTime();
                if (ch.read(buffer) <= 0)
                    break;
                //System.out.println("R ms: " + TimeUnit.MILLISECONDS.convert(System.nanoTime() - read, TimeUnit.NANOSECONDS));
                System.out.println("R ms: " + (System.nanoTime() - read));

                int pos = buffer.position();
                int numsAmount = pos / 4;

                buffer.flip();
                long sum = System.nanoTime();
//                for (int i = 0; i < numsAmount; i++) {
//                    long n = buffer.getInt() & UINT_MASK;
//                    res += n;
//                }
                //System.out.println("S ms: " + TimeUnit.MILLISECONDS.convert(System.nanoTime() - sum, TimeUnit.NANOSECONDS));
                System.out.println("S ms: " + (System.nanoTime() - sum));

                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Long.toUnsignedString(res);
    }

    public static void main(String[] args) {
        Sum s = new Sum();

        s.sumUsingDirectBufferAndFileChannel(args[0], INTS_PER_BUF);
    }
}
