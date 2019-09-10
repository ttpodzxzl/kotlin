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
    // "unsigned long long" should be converted to Number to allow to cast it to ULong [7]
    public open val connectEnd: kotlin.Number
        public open fun <get-connectEnd>(): kotlin.Number
    public open val connectStart: kotlin.Number
        public open fun <get-connectStart>(): kotlin.Number
    public open val domComplete: kotlin.Number
        public open fun <get-domComplete>(): kotlin.Number
    public open val domContentLoadedEventEnd: kotlin.Number
        public open fun <get-domContentLoadedEventEnd>(): kotlin.Number
    public open val domContentLoadedEventStart: kotlin.Number
        public open fun <get-domContentLoadedEventStart>(): kotlin.Number
    public open val domInteractive: kotlin.Number
        public open fun <get-domInteractive>(): kotlin.Number
    public open val domLoading: kotlin.Number
        public open fun <get-domLoading>(): kotlin.Number
    public open val domainLookupEnd: kotlin.Number
        public open fun <get-domainLookupEnd>(): kotlin.Number
    public open val domainLookupStart: kotlin.Number
        public open fun <get-domainLookupStart>(): kotlin.Number
    public open val fetchStart: kotlin.Number
        public open fun <get-fetchStart>(): kotlin.Number
    public open val loadEventEnd: kotlin.Number
        public open fun <get-loadEventEnd>(): kotlin.Number
    public open val loadEventStart: kotlin.Number
        public open fun <get-loadEventStart>(): kotlin.Number
    public open val navigationStart: kotlin.Number
        public open fun <get-navigationStart>(): kotlin.Number
    public open val redirectEnd: kotlin.Number
        public open fun <get-redirectEnd>(): kotlin.Number
    public open val redirectStart: kotlin.Number
        public open fun <get-redirectStart>(): kotlin.Number
    public open val requestStart: kotlin.Number
        public open fun <get-requestStart>(): kotlin.Number
    public open val responseEnd: kotlin.Number
        public open fun <get-responseEnd>(): kotlin.Number
    public open val responseStart: kotlin.Number
        public open fun <get-responseStart>(): kotlin.Number
    public open val secureConnectionStart: kotlin.Number
        public open fun <get-secureConnectionStart>(): kotlin.Number
    public open val unloadEventEnd: kotlin.Number
        public open fun <get-unloadEventEnd>(): kotlin.Number
    public open val unloadEventStart: kotlin.Number
        public open fun <get-unloadEventStart>(): kotlin.Number
    public open override /*1*/ /*fake_override*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
    public open override /*1*/ /*fake_override*/ fun hashCode(): kotlin.Int
    public open override /*1*/ /*fake_override*/ fun toString(): kotlin.String
}

