package com.umc.dodam.src.main.Api

import kotlinx.coroutines.flow.Flow

public interface DataStore<T> {
    public val data: Flow<T>
    public suspend fun updateData(transform: suspend (t: T) -> T): T
}
