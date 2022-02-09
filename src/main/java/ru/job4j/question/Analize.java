package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;

        Map<Integer, String> mapPrevious = previous.stream().collect(Collectors.toMap(User::getId, User::getName));
        Map<Integer, String> mapCurrent = current.stream().collect(Collectors.toMap(User::getId, User::getName));


        for (User user : current) {
            if (!previous.contains(user)) {
                added++;
            }
        }

        for (User user : previous) {
            if (mapCurrent.get(user.getId()) != null && !user.getName().equals(mapCurrent.get(user.getId()))) {
                changed++;
            }
            if (!mapCurrent.containsKey(user.getId())) {
                deleted++;
            }
        }

        return new Info(added, changed, deleted);
    }

}

