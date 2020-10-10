package com.osama.data.network

import com.osama.repository.Result
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Response

class ResponseResultMapperTest {
    private val subject = ResponseResultMapper()

    @Test
    fun `Test mapping a successful response to loaded result`() = runBlocking {
        val isSuccessful = true
        val body = 12

        val responseMock: Response<Int> = mockk("response")

        coEvery { responseMock.isSuccessful } returns isSuccessful
        coEvery { responseMock.body() } returns body

        assertTrue(
            "fail to map response:$body to result:$body",
            subject.mapResponse(responseMock) == Result.Loaded(body)
        )

        coVerifySequence {
            responseMock.isSuccessful
            responseMock.body()
        }

        confirmVerified(responseMock)
    }

    @Test
    fun `Test mapping a failing response to error result`() = runBlocking {
        val isSuccessful = false
        val code = 403
        val message = "Forbidden request code"

        val responseMock: Response<Int> = mockk("response")
        val okHttpResponseMock: okhttp3.Response = mockk("okHttpResponseMock")

        coEvery { responseMock.isSuccessful } returns isSuccessful
        coEvery { responseMock.raw() } returns okHttpResponseMock
        coEvery { okHttpResponseMock.code() } returns code
        coEvery { responseMock.message() } returns message
        assertTrue(
            "fail to map response:failure{$code,$message} to result:Error{$code,$message}",
            subject.mapResponse(responseMock) == Result.Error(code, message)
        )

        coVerifySequence {
            responseMock.isSuccessful
            responseMock.raw()
            okHttpResponseMock.code()
            responseMock.message()
        }

        confirmVerified(responseMock,okHttpResponseMock)
    }
}