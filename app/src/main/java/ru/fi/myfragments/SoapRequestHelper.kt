package ru.fi.myfragments

import android.accounts.NetworkErrorException
import android.util.Log
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.PropertyInfo
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapPrimitive
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE


class SoapRequestHelper {
    private val URL = "http://www.dneonline.com/calculator.asmx"
    private val NAMESPACE = "http://tempuri.org/"
    suspend fun makeSoapRequest(
        methodName : String,
        parameters : List<Pair<String, Int>>
    ) : String?
    {
        return try {
            val request = SoapObject(NAMESPACE, methodName)

            parameters.forEach{ parameter ->
                val param = PropertyInfo()
                param.setName(parameter.first)
                param.value = parameter.second
                param.setType(Int::class.java)
                request.addProperty(param)
            }

            val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
            envelope.dotNet = true

            envelope.setOutputSoapObject(request)

            val httpTransport = HttpTransportSE(URL)

            val SOAP_ACTION = "$NAMESPACE$methodName"

            httpTransport.call(SOAP_ACTION, envelope)
            
            val response = envelope.bodyIn as SoapObject

            response.getProperty("AddResult").toString()
        }catch (e : Exception){
            e.printStackTrace()
            null
        }
    }
}