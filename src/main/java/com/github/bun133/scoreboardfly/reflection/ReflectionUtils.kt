package com.github.bun133.scoreboardfly.reflection

import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible

fun <T : Any> T.getAllFields(): Collection<KProperty1<out T, *>> {
    return this::class.declaredMemberProperties
}

fun Any.getAllFieldNames(): List<String> {
    return getAllFields().map { it.name }
}

fun <T : Any> T.getAllFieldValues(): List<Any?> {
    return this::class.declaredMemberProperties.map {
        it.isAccessible = true
        it.getter.call(this)
    }
}

fun <T : Any> T.getAllFieldNameAndValues(): List<Pair<String, Any?>> {
    return getAllFields()
        .map {
            it.isAccessible = true
            it.name to it.getter.call(this)
        }
}

fun <T : Any> T.getAllFieldAndValueWithoutAnnotation(vararg annotation: Annotation): List<Pair<String, Any?>> {
    return getAllFields()
        .filter { !it.annotations.any { an -> annotation.contains(an) } }
        .map {
            it.isAccessible = true
            it.name to it.getter.call(this)
        }
}