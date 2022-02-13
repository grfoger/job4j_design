package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {


    public void packFiles(List<File> sources, File target) {

        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source: sources) {
                String path = source.getPath().substring(16);
                try {
                    zip.putNextEntry(new ZipEntry(source.getPath()));
                    try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                        zip.write(out.readAllBytes());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArgsName arguments = ArgsName.of(args);

        List<File> fileList = new ArrayList<>();
        try {
            Search.search(Path.of(arguments.get("d")), x -> !x.toString().endsWith(arguments.get("e"))).forEach(x -> fileList.add(x.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Zip zip = new Zip();
       zip.packFiles(fileList,new File(arguments.get("o")));

//        zip.packSingleFile(
//                new File("result.txt"),
//                new File("result.zip")
//        );
        //System.out.println(fileList.toString());
    }
}