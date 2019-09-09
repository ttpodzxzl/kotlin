package org.w3c.dom.parsing

public open external class DOMParser {
    /*primary*/ public constructor DOMParser()
    public open override /*1*/ /*fake_override*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
    public open override /*1*/ /*fake_override*/ fun hashCode(): kotlin.Int
    public final fun parseFromString(/*0*/ str: kotlin.String, /*1*/ type: dynamic): org.w3c.dom.Document
    public open override /*1*/ /*fake_override*/ fun toString(): kotlin.String
}

public open external class XMLSerializer {
    /*primary*/ public constructor XMLSerializer()
    public open override /*1*/ /*fake_override*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
    public open override /*1*/ /*fake_override*/ fun hashCode(): kotlin.Int
    public final fun serializeToString(/*0*/ root: org.w3c.dom.Node): kotlin.String
    public open override /*1*/ /*fake_override*/ fun toString(): kotlin.String
}

