import authHeader, { BASE_URL, HTTP } from "../http";

export default{
    allConsultations() {
        return HTTP.get(BASE_URL + "/consultation", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    create(consultation) {
        
        return HTTP.post(BASE_URL + "/consultation", consultation,{ headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    edit(consultation) {
        return HTTP.patch(BASE_URL + "/consultation/" + consultation.id, consultation, consultation.id, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    delete(Id){
        return HTTP.delete(BASE_URL + "/consultation/" + Id, { headers: authHeader() }).then();
    },

    sendEmail(){
        console.log("entered in sendemail121");
        return HTTP.get(BASE_URL + "/consultation/email", { headers: authHeader() }).then((response) => {
                       return response.data;
                   });
    },
    
    sendSMS() {
        console.log("entered in sendSMS");
        return HTTP.post(BASE_URL + "/consultation/sms", { headers: authHeader() }).then((response) => {
                       return response.data;
                   });
    },
    
    pdfR() {
    return HTTP.get(BASE_URL + "/consultation/export/PDF", {headers: authHeader()}).then(
        (response) => {
            return response.data;
        }
    );
  },
}