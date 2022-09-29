package com.som3a.common

interface Mapper<I, O> {

    fun map(input: I): O

    fun reverseMap(input : O): I

    fun mapList(input: List<I>): List<O> {
        return input.map { i -> map(i) }
    }
}
