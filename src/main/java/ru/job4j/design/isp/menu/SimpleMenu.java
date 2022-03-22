package ru.job4j.design.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean isAdded = false;
        if (parentName == null) {
            rootElements.add(new SimpleMenuItem(childName, actionDelegate));
            isAdded = true;
        } else {
            ItemInfo info = findItem(parentName).get();
            info.menuItem.getChildren().add(new SimpleMenuItem(childName, actionDelegate));
            isAdded = true;
        }
        return isAdded;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        return Optional.of(new MenuItemInfo(findItem(itemName).get().menuItem, findItem(itemName).get().number));
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        Iterator<ItemInfo> it = new DFSIterator();
        List<MenuItemInfo> list = new ArrayList<>();
        while (it.hasNext()) {
         ItemInfo info = it.next();
         list.add(new MenuItemInfo(info.menuItem, info.number));
        }
        return list.iterator();
    }

    private Optional<ItemInfo> findItem(String name) {
        Iterator<ItemInfo> it = new DFSIterator();
        Optional<ItemInfo> value = Optional.empty();
        while (it.hasNext()) {
            ItemInfo info = it.next();
            if (name.equals(info.menuItem.getName())) {
                value = Optional.of(info);
            }
        }
        return value;
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }

}