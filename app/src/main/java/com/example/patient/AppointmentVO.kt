package com.example.patient

import java.util.ArrayList

class AppointmentVO  {

    private var appointmentId: String = ""
    private var code: String = ""

    constructor() {
    	//constructor
    }

    constructor(appointmentIdx: String, 
        codex: String
        ) {
        this.appointmentId = appointmentIdx
        this.code = codex
    }

    constructor (x: Appointment) {
        appointmentId = x.appointmentId
        code = x.code
    }

    override fun toString(): String {
        return "appointmentId = $appointmentId,code = $code"
    }

    fun toStringList(list: List<AppointmentVO>): List<String> {
        val res: MutableList<String> = ArrayList()
        for (i in list.indices) {
            res.add(list[i].toString())
        }
        return res
    }
    
    fun getAppointmentId(): String {
        return appointmentId
    }
    
    fun getCode(): String {
        return code
    }
    

    fun setAppointmentId(x: String) {
    	appointmentId = x
    }
    
    fun setCode(x: String) {
    	code = x
    }
    
}
