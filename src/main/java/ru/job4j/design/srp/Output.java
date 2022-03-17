package ru.job4j.design.srp;

import java.nio.file.Path;

public interface Output {
    void outReport(String report, Path outPath);
}
