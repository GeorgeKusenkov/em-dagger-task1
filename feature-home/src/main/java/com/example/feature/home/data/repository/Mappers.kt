package com.example.feature.home.data.repository

import com.example.core.data.model.BreakingBadQuotes
import com.example.core.data.model.Cat

fun List<Cat>.toCatDomain(): String {
    return this.first().url
}

fun List<BreakingBadQuotes>.toQuotesDomain(): String {
    return this.first().quote
}