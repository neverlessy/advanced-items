package ru.moongl.minecraft.advanceditems.chat;

public enum MessageTypes {
    INFO("§7[§b\uD83D\uDD25§7] "),
    WARN("§7[§e✮§7] "),
    SUCCESS("§7[§2✔§7] "),
    ERROR("§7[§c✘§7] ");

    private final String prefix;
    MessageTypes(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
