package com.example.thegiphyapp.network

import com.example.thegiphyapp.utils.Constants

class GiphyServiceApiHelper(){
    private var serviceInterface: ServiceInterface?=null
    init {
        serviceInterface = NetworkAPIController.getApiClient(Constants.base_url)?.create(
                                ServiceInterface::class.java)
    }

    fun getServiceinterface(): ServiceInterface? {
        return serviceInterface
    }
}