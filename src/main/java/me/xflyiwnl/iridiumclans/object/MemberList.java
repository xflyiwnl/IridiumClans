package me.xflyiwnl.iridiumclans.object;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface MemberList {

    List<ClanPlayer> getMembers();

    default List<String> getFormattedMembers() {
        List<String> members = new ArrayList<String>();
        for (ClanPlayer member : getMembers()) {
            members.add(member.getName());
        }
        return members;
    }

    default void addMember(ClanPlayer player) {
        getMembers().add(player);
    }

    default void kickMember(ClanPlayer player) {
        getMembers().remove(player);
    }

    default ClanPlayer getMember(UUID uniqueId) {
        for (ClanPlayer member : getMembers()) {
            if (member.getUniqueId().equals(uniqueId)) {
                return member;
            }
        }
        return null;
    }

}
