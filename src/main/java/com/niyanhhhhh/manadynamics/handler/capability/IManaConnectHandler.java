package com.niyanhhhhh.manadynamics.handler.capability;

public interface IManaConnectHandler {

    int getMaxMana();

    int getMaxLinks();

    boolean isAllowInput();

    boolean isAllowOutput();

    default boolean isAllowLink() {
        return true;
    }

}
