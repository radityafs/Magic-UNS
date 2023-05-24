package com.magic.officeapp.data.api

import com.magic.officeapp.data.model.request.PayrollRequest
import com.magic.officeapp.data.model.response.AddPayrollResponse
import com.magic.officeapp.data.model.response.PayrollDetailResponse
import com.magic.officeapp.data.model.response.PayrollResponse
import com.magic.officeapp.data.model.response.PayrollResponseDataItem
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiPayrollInterface {

    @POST("api/payrolls")
    suspend fun insertPayroll(
        @Body payrollRequest: PayrollRequest
    ): AddPayrollResponse

    @GET("api/payrolls")
    suspend fun getAllPayrollData(
        @Query("populate[user][populate]") populate: String = "*",
        @Query("sort") sort: String = "createdAt:desc"
    ): PayrollResponse

    @GET("api/payrolls")
    suspend fun getPayrollByUser(
        @Query("filters[user][id]") id: String,
        @Query("populate[user][populate]") populate: String = "*",
        @Query("sort") sort: String = "createdAt:desc"
    ): PayrollResponse

    @GET("api/payrolls/{id}")
    suspend fun getPayrollById(
        @Path("id") id: String,
        @Query("populate[user][populate]") populate: String = "job_role",
        @Query("sort") sort: String = "createdAt:desc"
    ): PayrollDetailResponse

}