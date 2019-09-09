package org.w3c.performance

public external interface GlobalPerformance {
    public abstract val performance: org.w3c.performance.Performance
        public abstract fun <get-performance>(): org.w3c.performance.Performance
    public open override /*1*/ /*fake_override*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
    public open override /*1*/ /*fake_override*/ fun hashCode(): kotlin.Int
    public open override /*1*/ /*fake_override*/ fun toString(): kotlin.String
}

public abstract external class Performance : org.w3c.dom.events.EventTarget {
    /*primary*/ public constructor Performance()
    public open val navigation: org.w3c.performance.PerformanceNavigation
        public open fun <get-navigation>(): org.w3c.performance.PerformanceNavigation
    public open val timing: org.w3c.performance.PerformanceTiming
        public open fun <get-timing>(): org.w3c.performance.PerformanceTiming
    public final override /*1*/ /*fake_override*/ fun addEventListener(/*0*/ type: kotlin.String, /*1*/ callback: ((org.w3c.dom.events.Event) -> kotlin.Unit)?, /*2*/ options: dynamic = ...): kotlin.Unit
    public final override /*1*/ /*fake_override*/ fun addEventListener(/*0*/ type: kotlin.String, /*1*/ callback: org.w3c.dom.events.EventListener?, /*2*/ options: dynamic = ...): kotlin.Unit
    public final override /*1*/ /*fake_override*/ fun dispatchEvent(/*0*/ event: org.w3c.dom.events.Event): kotlin.Boolean
    public open override /*1*/ /*fake_override*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
    public open override /*1*/ /*fake_override*/ fun hashCode(): kotlin.Int
    public final fun now(): kotlin.Double
    public final override /*1*/ /*fake_override*/ fun removeEventListener(/*0*/ type: kotlin.String, /*1*/ callback: ((org.w3c.dom.events.Event) -> kotlin.Unit)?, /*2*/ options: dynamic = ...): kotlin.Unit
    public final override /*1*/ /*fake_override*/ fun removeEventListener(/*0*/ type: kotlin.String, /*1*/ callback: org.w3c.dom.events.EventListener?, /*2*/ options: dynamic = ...): kotlin.Unit
    public open override /*1*/ /*fake_override*/ fun toString(): kotlin.String
}

public abstract external class PerformanceNavigation {
    /*primary*/ public constructor PerformanceNavigation()
    public open val redirectCount: kotlin.Short
        public open fun <get-redirectCount>(): kotlin.Short
    public open val type: kotlin.Short
        public open fun <get-type>(): kotlin.Short
    public open override /*1*/ /*fake_override*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
    public open override /*1*/ /*fake_override*/ fun hashCode(): kotlin.Int
    public open override /*1*/ /*fake_override*/ fun toString(): kotlin.String

    public companion object Companion {
        /*primary*/ private constructor Companion()
        public final val TYPE_BACK_FORWARD: kotlin.Short
            public final fun <get-TYPE_BACK_FORWARD>(): kotlin.Short
        public final val TYPE_NAVIGATE: kotlin.Short
            public final fun <get-TYPE_NAVIGATE>(): kotlin.Short
        public final val TYPE_RELOAD: kotlin.Short
            public final fun <get-TYPE_RELOAD>(): kotlin.Short
        public final val TYPE_RESERVED: kotlin.Short
            public final fun <get-TYPE_RESERVED>(): kotlin.Short
        public open override /*1*/ /*fake_override*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
        public open override /*1*/ /*fake_override*/ fun hashCode(): kotlin.Int
        public open override /*1*/ /*fake_override*/ fun toString(): kotlin.String
    }
}

public abstract external class PerformanceTiming {
    /*primary*/ public constructor PerformanceTiming()
    public open val connectEnd: kotlin.Int
        public open fun <get-connectEnd>(): kotlin.Int
    public open val connectStart: kotlin.Int
        public open fun <get-connectStart>(): kotlin.Int
    public open val domComplete: kotlin.Int
        public open fun <get-domComplete>(): kotlin.Int
    public open val domContentLoadedEventEnd: kotlin.Int
        public open fun <get-domContentLoadedEventEnd>(): kotlin.Int
    public open val domContentLoadedEventStart: kotlin.Int
        public open fun <get-domContentLoadedEventStart>(): kotlin.Int
    public open val domInteractive: kotlin.Int
        public open fun <get-domInteractive>(): kotlin.Int
    public open val domLoading: kotlin.Int
        public open fun <get-domLoading>(): kotlin.Int
    public open val domainLookupEnd: kotlin.Int
        public open fun <get-domainLookupEnd>(): kotlin.Int
    public open val domainLookupStart: kotlin.Int
        public open fun <get-domainLookupStart>(): kotlin.Int
    public open val fetchStart: kotlin.Int
        public open fun <get-fetchStart>(): kotlin.Int
    public open val loadEventEnd: kotlin.Int
        public open fun <get-loadEventEnd>(): kotlin.Int
    public open val loadEventStart: kotlin.Int
        public open fun <get-loadEventStart>(): kotlin.Int
    public open val navigationStart: kotlin.Int
        public open fun <get-navigationStart>(): kotlin.Int
    public open val redirectEnd: kotlin.Int
        public open fun <get-redirectEnd>(): kotlin.Int
    public open val redirectStart: kotlin.Int
        public open fun <get-redirectStart>(): kotlin.Int
    public open val requestStart: kotlin.Int
        public open fun <get-requestStart>(): kotlin.Int
    public open val responseEnd: kotlin.Int
        public open fun <get-responseEnd>(): kotlin.Int
    public open val responseStart: kotlin.Int
        public open fun <get-responseStart>(): kotlin.Int
    public open val secureConnectionStart: kotlin.Int
        public open fun <get-secureConnectionStart>(): kotlin.Int
    public open val unloadEventEnd: kotlin.Int
        public open fun <get-unloadEventEnd>(): kotlin.Int
    public open val unloadEventStart: kotlin.Int
        public open fun <get-unloadEventStart>(): kotlin.Int
    public open override /*1*/ /*fake_override*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
    public open override /*1*/ /*fake_override*/ fun hashCode(): kotlin.Int
    public open override /*1*/ /*fake_override*/ fun toString(): kotlin.String
}

