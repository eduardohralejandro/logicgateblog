package com.main.logicgate.common.enums;

public enum ProgrammingLanguage {
    HASKELL,
    JAVA,
    JAVASCRIPT,
    RUST,
    C,
    PYTHON,
    CSHARP,
    SQL,
    GO,
    OCAML,
    ASM,
    PHP,
    CPLUSPLUS("C++"),
    LUA;

    private String value;

    ProgrammingLanguage(String value) {
        this.value = value;
    }

    ProgrammingLanguage() {
        this.value = name();
    }

    public String getValue() {
        return value;
    }
}
