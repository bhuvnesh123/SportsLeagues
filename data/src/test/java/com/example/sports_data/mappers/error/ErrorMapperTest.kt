package com.example.sports_data.mappers.error

import com.example.sports_data.mappers.fakes.ResponseDTOFake
import com.example.sports_domain.domainmodels.error.ErrorModel
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ErrorMapperTest {
    private lateinit var errorMapper: ErrorMapper

    @BeforeEach
    fun setup() {
        errorMapper = ErrorMapper()
    }

    @Test
    fun `GIVEN error dto as input WHEN mapper method called THEN return converted domain error model`() {
        val input = ResponseDTOFake.getErrorResponseDTO()
        val expectedOutput = ErrorModel(
            cause = "No matched country found",
            message = "Please try with different country",
        )

        val result = errorMapper.map(input = input)

        Assertions.assertEquals(
            expectedOutput,
            result,
        )
    }
}
