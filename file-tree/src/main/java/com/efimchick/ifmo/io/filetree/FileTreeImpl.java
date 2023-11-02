package com.efimchick.ifmo.io.filetree;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class FileTreeImpl implements FileTree {

    @Override
    public Optional<String> tree(Path path) {
        if (path == null || Files.notExists(path)) {
            return Optional.empty();
        }
        try {
            if (!Files.isDirectory(path)) {
                String result = path.getFileName() + " " + getFileSize(path);
                return Optional.of(result);
            }
            return Optional.of(printFolder(new Node<>(path.toFile(), null, false, 0), new StringBuilder()).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private static String getFileSize(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            return Files.walk(path)
                    .map(Path::toFile).filter(File::isFile).mapToLong(File::length).sum() + " bytes";
        } else
            return Files.size(path) + " bytes";
    }

    private static StringBuilder printFolder(Node<File> folder, StringBuilder sb) throws IOException {
        if (folder.level == 0) {
            sb.append(folder.data.getName())
                    .append(" ").append(getFileSize(folder.data.toPath())).append("\n");
        }

        File[] objects = folder.data.listFiles();
        Comparator<File> myComparator2 = (o1, o2) -> {
            if (Files.isDirectory(o1.toPath()) && !Files.isDirectory(o2.toPath())) {
                return -1;
            } else if (!Files.isDirectory(o1.toPath()) && Files.isDirectory(o2.toPath())) {
                return 1;
            } else return o1.toString().compareToIgnoreCase(o2.toString());
        };
        Arrays.sort(objects, myComparator2);

        for (int i = 0; i < objects.length; i++) {
            boolean last = ((i + 1) == objects.length);
            if (last) {
                if (objects[i].isDirectory()) {
                    for (int j = 0; j < folder.level; j++) {
                        if (folder.skipPos.contains(j)) {
                            sb.append("   ");
                        } else
                            sb.append("│  ");
                    }
                    sb.append("└─ ").append(objects[i].getName())
                            .append(" ").append(getFileSize(objects[i].toPath())).append("\n");
                    printFolder(new Node<>(objects[i], folder, true, new ArrayList<>(folder.skipPos), folder.level, folder.level + 1), sb);
                } else {
                    printFile(new Node<>(objects[i], folder, true, new ArrayList<>(folder.skipPos), folder.level, folder.level + 1), sb);
                }
            } else if (objects[i].isDirectory()) {
                for (int j = 0; j < folder.level; j++) {
                    if (folder.skipPos.contains(j)) {
                        sb.append("   ");
                    } else
                        sb.append("│  ");
                }
                sb.append("├─ ").append(objects[i].getName())
                        .append(" ").append(getFileSize(objects[i].toPath())).append("\n");
                printFolder(new Node<>(objects[i], folder, false, new ArrayList<>(folder.skipPos), folder.level + 1), sb);
            } else {
                printFile(new Node<>(objects[i], folder, false, new ArrayList<>(folder.skipPos), folder.level + 1), sb);
            }
        }


        return sb;
    }

    private static void printFile(Node<File> file, StringBuilder sb) throws IOException {
        if (file.skipPos.size() == 0) {
            for (int i = 0; i < file.level - 1; i++) {
                sb.append("│  ");
            }
        } else {
            for (int i = 0; i < file.level - 1; i++) {
                if (file.skipPos.contains(i)) {
                    sb.append("   ");
                } else
                    sb.append("│  ");
            }
        }
        if (file.isLast) {
            sb.append("└─ ").append(file.data.getName())
                    .append(" ").append(getFileSize(file.data.toPath())).append("\n");
            return;
        }
        sb.append("├─ ").append(file.data.getName())
                .append(" ").append(getFileSize(file.data.toPath())).append("\n");
    }

    private static class Node<T> {
        T data;
        Node<T> parent;
        ArrayList<Integer> skipPos = new ArrayList<>();
        int level;
        boolean isLast;

        public Node(T data, Node<T> parent, boolean isLast, int level) {
            this.data = data;
            this.parent = parent;
            this.level = level;
            this.isLast = isLast;
        }

        public Node(T data, Node<T> parent, boolean isLast, ArrayList<Integer> skipArray, int skipPos, int level) {
            this.data = data;
            this.parent = parent;
            this.skipPos = skipArray;
            this.skipPos.add(skipPos);
            this.level = level;
            this.isLast = isLast;
        }

        public Node(T data, Node<T> parent, boolean isLast, ArrayList<Integer> skipArray, int level) {
            this.data = data;
            this.parent = parent;
            this.skipPos = skipArray;
            this.level = level;
            this.isLast = isLast;
        }
    }
}
