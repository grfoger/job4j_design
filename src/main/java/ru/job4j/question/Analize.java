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


        for (Map.Entry<Integer, String> entry : mapCurrent.entrySet()) {
            if (!mapPrevious.containsKey(entry.getKey())) {
                added++;
            }
        }

        for (User user : current) {
            if (mapPrevious.get(user.getId()) != null && !user.getName().equals(mapPrevious.get(user.getId()))) {
                changed++;
            }
        }

        for (Map.Entry<Integer, String> entry : mapPrevious.entrySet()) {
            if (!mapCurrent.containsKey(entry.getKey())) {
                deleted++;
            }
        }

        return new Info(added, changed, deleted);
    }

}

