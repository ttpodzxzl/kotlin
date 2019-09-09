package org.w3c.dom.pointerevents

@kotlin.internal.InlineOnly public inline fun PointerEventInit(/*0*/ pointerId: kotlin.Int? = ..., /*1*/ width: kotlin.Double? = ..., /*2*/ height: kotlin.Double? = ..., /*3*/ pressure: kotlin.Float? = ..., /*4*/ tangentialPressure: kotlin.Float? = ..., /*5*/ tiltX: kotlin.Int? = ..., /*6*/ tiltY: kotlin.Int? = ..., /*7*/ twist: kotlin.Int? = ..., /*8*/ pointerType: kotlin.String? = ..., /*9*/ isPrimary: kotlin.Boolean? = ..., /*10*/ screenX: kotlin.Int? = ..., /*11*/ screenY: kotlin.Int? = ..., /*12*/ clientX: kotlin.Int? = ..., /*13*/ clientY: kotlin.Int? = ..., /*14*/ button: kotlin.Short? = ..., /*15*/ buttons: kotlin.Short? = ..., /*16*/ relatedTarget: org.w3c.dom.events.EventTarget? = ..., /*17*/ ctrlKey: kotlin.Boolean? = ..., /*18*/ shiftKey: kotlin.Boolean? = ..., /*19*/ altKey: kotlin.Boolean? = ..., /*20*/ metaKey: kotlin.Boolean? = ..., /*21*/ modifierAltGraph: kotlin.Boolean? = ..., /*22*/ modifierCapsLock: kotlin.Boolean? = ..., /*23*/ modifierFn: kotlin.Boolean? = ..., /*24*/ modifierFnLock: kotlin.Boolean? = ..., /*25*/ modifierHyper: kotlin.Boolean? = ..., /*26*/ modifierNumLock: kotlin.Boolean? = ..., /*27*/ modifierScrollLock: kotlin.Boolean? = ..., /*28*/ modifierSuper: kotlin.Boolean? = ..., /*29*/ modifierSymbol: kotlin.Boolean? = ..., /*30*/ modifierSymbolLock: kotlin.Boolean? = ..., /*31*/ view: org.w3c.dom.Window? = ..., /*32*/ detail: kotlin.Int? = ..., /*33*/ bubbles: kotlin.Boolean? = ..., /*34*/ cancelable: kotlin.Boolean? = ..., /*35*/ composed: kotlin.Boolean? = ...): org.w3c.dom.pointerevents.PointerEventInit

public open external class PointerEvent : org.w3c.dom.events.MouseEvent {
    /*primary*/ public constructor PointerEvent(/*0*/ type: kotlin.String, /*1*/ eventInitDict: org.w3c.dom.pointerevents.PointerEventInit = ...)
    public open override /*1*/ /*fake_override*/ val altKey: kotlin.Boolean
        public open override /*1*/ /*fake_override*/ fun <get-altKey>(): kotlin.Boolean
    public open override /*1*/ /*fake_override*/ val bubbles: kotlin.Boolean
        public open override /*1*/ /*fake_override*/ fun <get-bubbles>(): kotlin.Boolean
    public open override /*1*/ /*fake_override*/ val button: kotlin.Short
        public open override /*1*/ /*fake_override*/ fun <get-button>(): kotlin.Short
    public open override /*1*/ /*fake_override*/ val buttons: kotlin.Short
        public open override /*1*/ /*fake_override*/ fun <get-buttons>(): kotlin.Short
    public open override /*1*/ /*fake_override*/ val cancelable: kotlin.Boolean
        public open override /*1*/ /*fake_override*/ fun <get-cancelable>(): kotlin.Boolean
    public open override /*1*/ /*fake_override*/ val clientX: kotlin.Int
        public open override /*1*/ /*fake_override*/ fun <get-clientX>(): kotlin.Int
    public open override /*1*/ /*fake_override*/ val clientY: kotlin.Int
        public open override /*1*/ /*fake_override*/ fun <get-clientY>(): kotlin.Int
    public open override /*1*/ /*fake_override*/ val composed: kotlin.Boolean
        public open override /*1*/ /*fake_override*/ fun <get-composed>(): kotlin.Boolean
    public open override /*1*/ /*fake_override*/ val ctrlKey: kotlin.Boolean
        public open override /*1*/ /*fake_override*/ fun <get-ctrlKey>(): kotlin.Boolean
    public open override /*1*/ /*fake_override*/ val currentTarget: org.w3c.dom.events.EventTarget?
        public open override /*1*/ /*fake_override*/ fun <get-currentTarget>(): org.w3c.dom.events.EventTarget?
    public open override /*1*/ /*fake_override*/ val defaultPrevented: kotlin.Boolean
        public open override /*1*/ /*fake_override*/ fun <get-defaultPrevented>(): kotlin.Boolean
    public open override /*1*/ /*fake_override*/ val detail: kotlin.Int
        public open override /*1*/ /*fake_override*/ fun <get-detail>(): kotlin.Int
    public open override /*1*/ /*fake_override*/ val eventPhase: kotlin.Short
        public open override /*1*/ /*fake_override*/ fun <get-eventPhase>(): kotlin.Short
    public open val height: kotlin.Double
        public open fun <get-height>(): kotlin.Double
    public open val isPrimary: kotlin.Boolean
        public open fun <get-isPrimary>(): kotlin.Boolean
    public open override /*1*/ /*fake_override*/ val isTrusted: kotlin.Boolean
        public open override /*1*/ /*fake_override*/ fun <get-isTrusted>(): kotlin.Boolean
    public open override /*1*/ /*fake_override*/ val metaKey: kotlin.Boolean
        public open override /*1*/ /*fake_override*/ fun <get-metaKey>(): kotlin.Boolean
    public open override /*1*/ /*fake_override*/ val offsetX: kotlin.Double
        public open override /*1*/ /*fake_override*/ fun <get-offsetX>(): kotlin.Double
    public open override /*1*/ /*fake_override*/ val offsetY: kotlin.Double
        public open override /*1*/ /*fake_override*/ fun <get-offsetY>(): kotlin.Double
    public open override /*1*/ /*fake_override*/ val pageX: kotlin.Double
        public open override /*1*/ /*fake_override*/ fun <get-pageX>(): kotlin.Double
    public open override /*1*/ /*fake_override*/ val pageY: kotlin.Double
        public open override /*1*/ /*fake_override*/ fun <get-pageY>(): kotlin.Double
    public open val pointerId: kotlin.Int
        public open fun <get-pointerId>(): kotlin.Int
    public open val pointerType: kotlin.String
        public open fun <get-pointerType>(): kotlin.String
    public open val pressure: kotlin.Float
        public open fun <get-pressure>(): kotlin.Float
    public open override /*1*/ /*fake_override*/ val region: kotlin.String?
        public open override /*1*/ /*fake_override*/ fun <get-region>(): kotlin.String?
    public open override /*1*/ /*fake_override*/ val relatedTarget: org.w3c.dom.events.EventTarget?
        public open override /*1*/ /*fake_override*/ fun <get-relatedTarget>(): org.w3c.dom.events.EventTarget?
    public open override /*1*/ /*fake_override*/ val screenX: kotlin.Int
        public open override /*1*/ /*fake_override*/ fun <get-screenX>(): kotlin.Int
    public open override /*1*/ /*fake_override*/ val screenY: kotlin.Int
        public open override /*1*/ /*fake_override*/ fun <get-screenY>(): kotlin.Int
    public open override /*1*/ /*fake_override*/ val shiftKey: kotlin.Boolean
        public open override /*1*/ /*fake_override*/ fun <get-shiftKey>(): kotlin.Boolean
    public open val tangentialPressure: kotlin.Float
        public open fun <get-tangentialPressure>(): kotlin.Float
    public open override /*1*/ /*fake_override*/ val target: org.w3c.dom.events.EventTarget?
        public open override /*1*/ /*fake_override*/ fun <get-target>(): org.w3c.dom.events.EventTarget?
    public open val tiltX: kotlin.Int
        public open fun <get-tiltX>(): kotlin.Int
    public open val tiltY: kotlin.Int
        public open fun <get-tiltY>(): kotlin.Int
    public open override /*1*/ /*fake_override*/ val timeStamp: kotlin.Number
        public open override /*1*/ /*fake_override*/ fun <get-timeStamp>(): kotlin.Number
    public open val twist: kotlin.Int
        public open fun <get-twist>(): kotlin.Int
    public open override /*1*/ /*fake_override*/ val type: kotlin.String
        public open override /*1*/ /*fake_override*/ fun <get-type>(): kotlin.String
    public open override /*1*/ /*fake_override*/ val view: org.w3c.dom.Window?
        public open override /*1*/ /*fake_override*/ fun <get-view>(): org.w3c.dom.Window?
    public open val width: kotlin.Double
        public open fun <get-width>(): kotlin.Double
    public open override /*1*/ /*fake_override*/ val x: kotlin.Double
        public open override /*1*/ /*fake_override*/ fun <get-x>(): kotlin.Double
    public open override /*1*/ /*fake_override*/ val y: kotlin.Double
        public open override /*1*/ /*fake_override*/ fun <get-y>(): kotlin.Double
    public final override /*1*/ /*fake_override*/ fun composedPath(): kotlin.Array<org.w3c.dom.events.EventTarget>
    public open override /*1*/ /*fake_override*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
    public final override /*1*/ /*fake_override*/ fun getModifierState(/*0*/ keyArg: kotlin.String): kotlin.Boolean
    public open override /*1*/ /*fake_override*/ fun hashCode(): kotlin.Int
    public final override /*1*/ /*fake_override*/ fun initEvent(/*0*/ type: kotlin.String, /*1*/ bubbles: kotlin.Boolean, /*2*/ cancelable: kotlin.Boolean): kotlin.Unit
    public final override /*1*/ /*fake_override*/ fun preventDefault(): kotlin.Unit
    public final override /*1*/ /*fake_override*/ fun stopImmediatePropagation(): kotlin.Unit
    public final override /*1*/ /*fake_override*/ fun stopPropagation(): kotlin.Unit
    public open override /*1*/ /*fake_override*/ fun toString(): kotlin.String
}

public external interface PointerEventInit : org.w3c.dom.events.MouseEventInit {
    public open override /*1*/ /*fake_override*/ var altKey: kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <get-altKey>(): kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <set-altKey>(/*0*/ value: kotlin.Boolean?): kotlin.Unit
    public open override /*1*/ /*fake_override*/ var bubbles: kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <get-bubbles>(): kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <set-bubbles>(/*0*/ value: kotlin.Boolean?): kotlin.Unit
    public open override /*1*/ /*fake_override*/ var button: kotlin.Short?
        public open override /*1*/ /*fake_override*/ fun <get-button>(): kotlin.Short?
        public open override /*1*/ /*fake_override*/ fun <set-button>(/*0*/ value: kotlin.Short?): kotlin.Unit
    public open override /*1*/ /*fake_override*/ var buttons: kotlin.Short?
        public open override /*1*/ /*fake_override*/ fun <get-buttons>(): kotlin.Short?
        public open override /*1*/ /*fake_override*/ fun <set-buttons>(/*0*/ value: kotlin.Short?): kotlin.Unit
    public open override /*1*/ /*fake_override*/ var cancelable: kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <get-cancelable>(): kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <set-cancelable>(/*0*/ value: kotlin.Boolean?): kotlin.Unit
    public open override /*1*/ /*fake_override*/ var clientX: kotlin.Int?
        public open override /*1*/ /*fake_override*/ fun <get-clientX>(): kotlin.Int?
        public open override /*1*/ /*fake_override*/ fun <set-clientX>(/*0*/ value: kotlin.Int?): kotlin.Unit
    public open override /*1*/ /*fake_override*/ var clientY: kotlin.Int?
        public open override /*1*/ /*fake_override*/ fun <get-clientY>(): kotlin.Int?
        public open override /*1*/ /*fake_override*/ fun <set-clientY>(/*0*/ value: kotlin.Int?): kotlin.Unit
    public open override /*1*/ /*fake_override*/ var composed: kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <get-composed>(): kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <set-composed>(/*0*/ value: kotlin.Boolean?): kotlin.Unit
    public open override /*1*/ /*fake_override*/ var ctrlKey: kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <get-ctrlKey>(): kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <set-ctrlKey>(/*0*/ value: kotlin.Boolean?): kotlin.Unit
    public open override /*1*/ /*fake_override*/ var detail: kotlin.Int?
        public open override /*1*/ /*fake_override*/ fun <get-detail>(): kotlin.Int?
        public open override /*1*/ /*fake_override*/ fun <set-detail>(/*0*/ value: kotlin.Int?): kotlin.Unit
    public open var height: kotlin.Double?
        public open fun <get-height>(): kotlin.Double?
        public open fun <set-height>(/*0*/ value: kotlin.Double?): kotlin.Unit
    public open var isPrimary: kotlin.Boolean?
        public open fun <get-isPrimary>(): kotlin.Boolean?
        public open fun <set-isPrimary>(/*0*/ value: kotlin.Boolean?): kotlin.Unit
    public open override /*1*/ /*fake_override*/ var metaKey: kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <get-metaKey>(): kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <set-metaKey>(/*0*/ value: kotlin.Boolean?): kotlin.Unit
    public open override /*1*/ /*fake_override*/ var modifierAltGraph: kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <get-modifierAltGraph>(): kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <set-modifierAltGraph>(/*0*/ value: kotlin.Boolean?): kotlin.Unit
    public open override /*1*/ /*fake_override*/ var modifierCapsLock: kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <get-modifierCapsLock>(): kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <set-modifierCapsLock>(/*0*/ value: kotlin.Boolean?): kotlin.Unit
    public open override /*1*/ /*fake_override*/ var modifierFn: kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <get-modifierFn>(): kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <set-modifierFn>(/*0*/ value: kotlin.Boolean?): kotlin.Unit
    public open override /*1*/ /*fake_override*/ var modifierFnLock: kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <get-modifierFnLock>(): kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <set-modifierFnLock>(/*0*/ value: kotlin.Boolean?): kotlin.Unit
    public open override /*1*/ /*fake_override*/ var modifierHyper: kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <get-modifierHyper>(): kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <set-modifierHyper>(/*0*/ value: kotlin.Boolean?): kotlin.Unit
    public open override /*1*/ /*fake_override*/ var modifierNumLock: kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <get-modifierNumLock>(): kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <set-modifierNumLock>(/*0*/ value: kotlin.Boolean?): kotlin.Unit
    public open override /*1*/ /*fake_override*/ var modifierScrollLock: kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <get-modifierScrollLock>(): kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <set-modifierScrollLock>(/*0*/ value: kotlin.Boolean?): kotlin.Unit
    public open override /*1*/ /*fake_override*/ var modifierSuper: kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <get-modifierSuper>(): kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <set-modifierSuper>(/*0*/ value: kotlin.Boolean?): kotlin.Unit
    public open override /*1*/ /*fake_override*/ var modifierSymbol: kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <get-modifierSymbol>(): kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <set-modifierSymbol>(/*0*/ value: kotlin.Boolean?): kotlin.Unit
    public open override /*1*/ /*fake_override*/ var modifierSymbolLock: kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <get-modifierSymbolLock>(): kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <set-modifierSymbolLock>(/*0*/ value: kotlin.Boolean?): kotlin.Unit
    public open var pointerId: kotlin.Int?
        public open fun <get-pointerId>(): kotlin.Int?
        public open fun <set-pointerId>(/*0*/ value: kotlin.Int?): kotlin.Unit
    public open var pointerType: kotlin.String?
        public open fun <get-pointerType>(): kotlin.String?
        public open fun <set-pointerType>(/*0*/ value: kotlin.String?): kotlin.Unit
    public open var pressure: kotlin.Float?
        public open fun <get-pressure>(): kotlin.Float?
        public open fun <set-pressure>(/*0*/ value: kotlin.Float?): kotlin.Unit
    public open override /*1*/ /*fake_override*/ var region: kotlin.String?
        public open override /*1*/ /*fake_override*/ fun <get-region>(): kotlin.String?
        public open override /*1*/ /*fake_override*/ fun <set-region>(/*0*/ value: kotlin.String?): kotlin.Unit
    public open override /*1*/ /*fake_override*/ var relatedTarget: org.w3c.dom.events.EventTarget?
        public open override /*1*/ /*fake_override*/ fun <get-relatedTarget>(): org.w3c.dom.events.EventTarget?
        public open override /*1*/ /*fake_override*/ fun <set-relatedTarget>(/*0*/ value: org.w3c.dom.events.EventTarget?): kotlin.Unit
    public open override /*1*/ /*fake_override*/ var screenX: kotlin.Int?
        public open override /*1*/ /*fake_override*/ fun <get-screenX>(): kotlin.Int?
        public open override /*1*/ /*fake_override*/ fun <set-screenX>(/*0*/ value: kotlin.Int?): kotlin.Unit
    public open override /*1*/ /*fake_override*/ var screenY: kotlin.Int?
        public open override /*1*/ /*fake_override*/ fun <get-screenY>(): kotlin.Int?
        public open override /*1*/ /*fake_override*/ fun <set-screenY>(/*0*/ value: kotlin.Int?): kotlin.Unit
    public open override /*1*/ /*fake_override*/ var shiftKey: kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <get-shiftKey>(): kotlin.Boolean?
        public open override /*1*/ /*fake_override*/ fun <set-shiftKey>(/*0*/ value: kotlin.Boolean?): kotlin.Unit
    public open var tangentialPressure: kotlin.Float?
        public open fun <get-tangentialPressure>(): kotlin.Float?
        public open fun <set-tangentialPressure>(/*0*/ value: kotlin.Float?): kotlin.Unit
    public open var tiltX: kotlin.Int?
        public open fun <get-tiltX>(): kotlin.Int?
        public open fun <set-tiltX>(/*0*/ value: kotlin.Int?): kotlin.Unit
    public open var tiltY: kotlin.Int?
        public open fun <get-tiltY>(): kotlin.Int?
        public open fun <set-tiltY>(/*0*/ value: kotlin.Int?): kotlin.Unit
    public open var twist: kotlin.Int?
        public open fun <get-twist>(): kotlin.Int?
        public open fun <set-twist>(/*0*/ value: kotlin.Int?): kotlin.Unit
    public open override /*1*/ /*fake_override*/ var view: org.w3c.dom.Window?
        public open override /*1*/ /*fake_override*/ fun <get-view>(): org.w3c.dom.Window?
        public open override /*1*/ /*fake_override*/ fun <set-view>(/*0*/ value: org.w3c.dom.Window?): kotlin.Unit
    public open var width: kotlin.Double?
        public open fun <get-width>(): kotlin.Double?
        public open fun <set-width>(/*0*/ value: kotlin.Double?): kotlin.Unit
    public open override /*1*/ /*fake_override*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
    public open override /*1*/ /*fake_override*/ fun hashCode(): kotlin.Int
    public open override /*1*/ /*fake_override*/ fun toString(): kotlin.String
}

