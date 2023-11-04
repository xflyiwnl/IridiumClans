package me.xflyiwnl.iridiumclans.object;

public interface Nameable {

    String getName();
    default String getFormattedName() {
        return getName().replace("_", " ");
    }

}
