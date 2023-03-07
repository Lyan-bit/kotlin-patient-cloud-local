package com.example.patient

import android.content.Context
import java.util.ArrayList


class ModelFacade private constructor(context: Context) {

    private var cdb: FirebaseDB = FirebaseDB.getInstance()
    private var db: DB
    private var fileSystem: FileAccessor

    private var currentPatient: PatientVO? = null
    private var currentPatients: ArrayList<PatientVO> = ArrayList()
    private var currentAppointment: AppointmentVO? = null
	private var currentAppointments: ArrayList<AppointmentVO> = ArrayList()

    init {
    	//init
        db = DB(context, null)
        fileSystem = FileAccessor(context)
	}

    companion object {
        private var instance: ModelFacade? = null
        fun getInstance(context: Context): ModelFacade {
            return instance ?: ModelFacade(context)
        }
    }
    
	/* This metatype code requires OclType.java, OclAttribute.java, OclOperation.java */
	fun initialiseOclTypes() {
			val patientOclType: OclType = OclType.createByPKOclType("Patient")
		patientOclType.setMetatype(Patient::class.java)
		    val appointmentOclType: OclType = OclType.createByPKOclType("Appointment")
		appointmentOclType.setMetatype(Appointment::class.java)
    }
    
    fun createPatient(x: PatientVO) { 
			  editPatient(x)
	}
				    
    fun editPatient(x: PatientVO) {
		var obj = getPatientByPK(x.getPatientId())
		if (obj == null) {
			obj = Patient.createByPKPatient(x.getPatientId())
		}
			
		  obj.patientId = x.getPatientId()
		  obj.name = x.getName()
		  obj.appointmentId = x.getAppointmentId()
		cdb.persistPatient(obj)
		currentPatient = x
	}
		
	fun deletePatient(id: String) {
			  val obj = getPatientByPK(id)
			  if (obj != null) {
			      cdb.deletePatient(obj)
			          Patient.killPatient(id)
			      }
			  currentPatient = null	
		}
				    
    fun setSelectedPatient(x: PatientVO) {
			  currentPatient = x
	}
    fun createAppointment(x: AppointmentVO) { 
          db.createAppointment(x)
          currentAppointment = x
	}
		    
    fun setSelectedAppointment(x: AppointmentVO) {
	      currentAppointment = x
	}
	    
    fun editAppointment(x: AppointmentVO) {
        db.editAppointment(x)
          currentAppointment = x
	}	
		
    fun deleteAppointment(id: String) {
          db.deleteAppointment(id)
          currentAppointment = null
	}
		
    fun addPatientattendsAppointment(appointmentId: String, patientId: String) {
	      var obj = getPatientByPK(patientId)
	      if (obj == null) {
	          obj = Patient.createByPKPatient(patientId)
          }
	      obj.appointmentId = appointmentId
	      cdb.persistPatient(obj)
	      currentPatient = PatientVO(obj)
	          
	 }
	    
    fun removePatientattendsAppointment(appointmentId: String, patientId: String) {
		     var obj = getPatientByPK(patientId)
		     if (obj == null) {
	             obj = Patient.createByPKPatient(patientId)
	         }
		     obj.appointmentId = "Null"
		     cdb.persistPatient(obj)
	         currentPatient = PatientVO(obj)
		          
	}
	

	fun listAppointment(): ArrayList<AppointmentVO> {
        currentAppointments = db.listAppointment()
		
        return currentAppointments
	}

	fun listAllAppointment(): ArrayList<Appointment> {	
		currentAppointments = db.listAppointment()
		var res = ArrayList<Appointment>()
			for (x in currentAppointments.indices) {
					val vo: AppointmentVO = currentAppointments[x]
				    val itemx = Appointment.createByPKAppointment(vo.getAppointmentId())
	            itemx.appointmentId = vo.getAppointmentId()
            itemx.code = vo.getCode()
			res.add(itemx)
		}
		return res
	}

    fun stringListAppointment(): ArrayList<String> {
        currentAppointments = db.listAppointment()
        val res: ArrayList<String> = ArrayList()
        for (x in currentAppointments.indices) {
            res.add(currentAppointments[x].toString())
        }
        return res
    }

    fun getAppointmentByPK(value: String): Appointment? {
        val res: ArrayList<AppointmentVO> = db.searchByAppointmentappointmentId(value)
	        return if (res.isEmpty()) {
	            null
	        } else {
	            val vo: AppointmentVO = res[0]
	            val itemx = Appointment.createByPKAppointment(value)
            itemx.appointmentId = vo.getAppointmentId()
            itemx.code = vo.getCode()
	            itemx
	        }
    }
    
    fun retrieveAppointment(value: String): Appointment? {
        return getAppointmentByPK(value)
    }

    fun allAppointmentAppointmentIds(): ArrayList<String> {
        currentAppointments = db.listAppointment()
        val res: ArrayList<String> = ArrayList()
            for (appointment in currentAppointments.indices) {
                res.add(currentAppointments[appointment].getAppointmentId())
            }
        return res
    }

    fun setSelectedAppointment(i: Int) {
        if (i < currentAppointments.size) {
            currentAppointment = currentAppointments[i]
        }
    }

    fun getSelectedAppointment(): AppointmentVO? {
        return currentAppointment
    }

    fun persistAppointment(x: Appointment) {
        val vo = AppointmentVO(x)
        db.editAppointment(vo)
        currentAppointment = vo
    }
	

	    	fun listPatient(): ArrayList<PatientVO> {
		  val patients: ArrayList<Patient> = Patient.PatientAllInstances
		  currentPatients.clear()
		  for (i in patients.indices) {
		       currentPatients.add(PatientVO(patients[i]))
		  }
			      
		 return currentPatients
	}
	
	fun listAllPatient(): ArrayList<Patient> {
		  val patients: ArrayList<Patient> = Patient.PatientAllInstances    
		  return patients
	}
	

			    
    fun stringListPatient(): ArrayList<String> {
        val res: ArrayList<String> = ArrayList()
        for (x in currentPatients.indices) {
            res.add(currentPatients[x].toString())
        }
        return res
    }

    fun getPatientByPK(value: String): Patient? {
        return Patient.PatientIndex[value]
    }
    
    fun retrievePatient(value: String): Patient? {
            return getPatientByPK(value)
    }

    fun allPatientPatientIds(): ArrayList<String> {
        val res: ArrayList<String> = ArrayList()
            for (x in currentPatients.indices) {
                res.add(currentPatients[x].getPatientId())
            }
        return res
    }
    
    fun setSelectedPatient(i: Int) {
        if (i < currentPatients.size) {
            currentPatient = currentPatients[i]
        }
    }

    fun getSelectedPatient(): PatientVO? {
        return currentPatient
    }

    fun persistPatient(x: Patient) {
        val vo = PatientVO(x)
        cdb.persistPatient(x)
        currentPatient = vo
    }

		
}
