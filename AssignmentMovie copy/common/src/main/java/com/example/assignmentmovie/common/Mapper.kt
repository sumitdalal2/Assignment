package com.example.assignmentmovie.common

import java.util.concurrent.Flow

interface Mapper<P,R> {
    fun mapInto(para: P): R
}