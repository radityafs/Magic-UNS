package com.magic.officeapp.data.repository

import com.magic.officeapp.data.api.ApiAttendanceInterface
import com.magic.officeapp.data.api.ApiEmployeeInterface
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    private val apiService: ApiEmployeeInterface
) {


}