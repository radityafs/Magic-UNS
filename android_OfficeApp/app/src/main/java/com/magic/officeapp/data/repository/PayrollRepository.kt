package com.magic.officeapp.data.repository

import com.magic.officeapp.data.api.ApiPayrollInterface
import com.magic.officeapp.data.model.request.PayrollRequest
import com.magic.officeapp.data.model.response.AddPayrollResponse
import com.magic.officeapp.data.model.response.PayrollDetailResponse
import com.magic.officeapp.data.model.response.PayrollResponse
import com.magic.officeapp.data.model.response.PayrollResponseDataItem
import com.magic.officeapp.utils.constants.Result
import javax.inject.Inject

class PayrollRepository @Inject constructor(
    private val apiService: ApiPayrollInterface
) {

    suspend fun insertPayroll(payrollBody: PayrollRequest): Result<AddPayrollResponse> {
        return try {
            Result.Success(
                apiService.insertPayroll(
                    payrollBody
                )
            )
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    suspend fun getAllPayrollData() : Result<PayrollResponse>{
        return try {
            Result.Success(apiService.getAllPayrollData())
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    suspend fun getPayrollByUser(id: String): Result<PayrollResponse> {
        return try {
            Result.Success(apiService.getPayrollByUser(id))
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    suspend fun getPayrollById(id: String): Result<PayrollDetailResponse> {
        return try {
            Result.Success(apiService.getPayrollById(id))
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

}