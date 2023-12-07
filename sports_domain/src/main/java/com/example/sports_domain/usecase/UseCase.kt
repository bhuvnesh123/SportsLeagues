package com.example.sports_domain.usecase

import com.example.sports_domain.domainmodels.wrapper.ApiResult
import kotlinx.coroutines.flow.Flow

interface UseCase<Params, T> {
    operator fun invoke(params: Params): Flow<ApiResult<T?>>
}
