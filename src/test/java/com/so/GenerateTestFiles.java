package com.so;

import com.google.common.primitives.UnsignedInteger;
import com.google.common.primitives.UnsignedLong;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author Ilya Ismagilov <ilya@singulator.net>
 */
public class GenerateTestFiles {
    private final int INTS_PER_BUF = 1024 * 1024;
    private final int BUF_SIZE = 4 * INTS_PER_BUF;

    @Test
    @Ignore
    public void genSimpleFile() {
        genFile("simple4", 4);
    }

    @Test
    @Ignore
    public void genHugeFile() {
        genFile("huge2billions", 2 * 1000 * 1000 * 1000);
    }

    public void genFile(String fileName, int numsAmount) {
        UnsignedLong res = UnsignedLong.ZERO;

        Path filePath = Paths.get(fileName + ".txt");

        Random r = new Random(System.currentTimeMillis());

        Set<OpenOption> options = new HashSet<>();
        options.add(StandardOpenOption.CREATE);
        options.add(StandardOpenOption.WRITE);
        options.add(StandardOpenOption.TRUNCATE_EXISTING);

        try (SeekableByteChannel ch = Files.newByteChannel(filePath, options)) {
            ByteBuffer bb = ByteBuffer.allocate(BUF_SIZE).order(ByteOrder.LITTLE_ENDIAN);
            int cntInts = 0;
            for (int i = numsAmount; i > 0; i--) {
                int num = r.nextInt();
                bb.putInt(num);
                cntInts++;

                UnsignedInteger ui = UnsignedInteger.fromIntBits(num);
                res = res.plus(UnsignedLong.fromLongBits(ui.longValue()));

                if (cntInts == INTS_PER_BUF) {
                    bb.flip();
                    ch.write(bb);

                    bb.clear();
                    cntInts = 0;
                }
            }

            if (cntInts > 0) {
                bb.flip();
                ch.write(bb);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileNameWithRes = fileName + "-" + res.toString() + ".txt";
        try {
            Files.move(filePath, filePath.resolveSibling(fileNameWithRes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
