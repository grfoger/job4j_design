package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        for (User user: current) {
            if (!previous.contains(user)) {
                added++;
            }
            if (previous.contains(user)){
                user.getId();
            }
        }
        for (User user: previous) {
            if (!current.contains(user)) {
                deleted++;
            }
        }

        return new Info(added, changed, deleted);
    }

}