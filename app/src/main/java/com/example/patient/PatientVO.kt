package com.example.patient

import java.util.ArrayList

class PatientVO  {

    private var patientId: String = ""
    private var name: String = ""
    private var appointmentId: String = ""

    constructor() {
    	//constructor
    }

    constructor(patientIdx: String, 
        namex: String, 
        appointmentIdx: String
        ) {
        this.patientId = patientIdx
        this.name = namex
        this.appointmentId = appointmentIdx
    }

    constructor (x: Patient) {
        patientId = x.patientId
        name = x.name
        appointmentId = x.appointmentId
    }

    override fun toString(): String {
        return "patientId = $patientId,name = $name,appointmentId = $appointmentId"
    }

    fun toStringList(list: List<PatientVO>): List<String> {
        val res: MutableList<String> = ArrayList()
        for (i in list.indices) {
            res.add(list[i].toString())
        }
        return res
    }
    
    fun getPatientId(): String {
        return patientId
    }
    
    fun getName(): String {
        return name
    }
    
    fun getAppointmentId(): String {
        return appointmentId
    }
    

    fun setPatientId(x: String) {
    	patientId = x
    }
    
    fun setName(x: String) {
    	name = x
    }
    
    fun setAppointmentId(x: String) {
    	appointmentId = x
    }
    
}
