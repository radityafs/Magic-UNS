package com.magic.officeapp.data.repository

import javax.inject.Inject
import com.magic.officeapp.data.api.ApiAnnoucementInterface

class AnnoucementRepository @Inject constructor(
    private val apiService: ApiAnnoucementInterface
) {

}