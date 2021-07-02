import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  allPatients() {
    return HTTP.get(BASE_URL + "/patient", { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },

  create(patient) {
    return HTTP.post(BASE_URL + "/patient", patient, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  edit(patient) {
    return HTTP.patch(BASE_URL + "/patient/" + patient.id, patient, patient.id, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  deletePatient(Id){
    return HTTP.delete(BASE_URL + "/patient/" + Id, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
},

csvR() {
   //console.log("maybeError");
    return HTTP.get(BASE_URL + "/patient/export/CSV", {headers: authHeader()}).then(
        (response) => {
            
            return response.data;
        }
    );
  },
  
  // sell(id, quantity) {
  //   return HTTP.patch(
  //     BASE_URL + "/patient/sell/" + id + "/" + quantity,
  //     {},
  //     {
  //       headers: authHeader(),
  //     }
  //   ).then((response) => {
  //     return response.data;
  //   });
  // },

  // searchTAG(name, author, genre){
  //   return HTTP.patch(BASE_URL + "/patient/tag", name, author, genre, {headers: authHeader() }).then(
  //     (response)=>{
  //       return response.data;
  //     })
  // },

  // csvR() {
  //  //console.log("maybeError");
  //   return HTTP.get(BASE_URL + "/patient/export/CSV", {headers: authHeader()}).then(
  //       (response) => {
            
  //           return response.data;
  //       }
  //   );
  // },
  // pdfR() {
  //   return HTTP.get(BASE_URL + "/patient/export/PDF", {headers: authHeader()}).then(
  //       (response) => {
  //           return response.data;
  //       }
  //   );
  // },
  // pdfJasper() {
  //   return HTTP.get(BASE_URL + "/patient/export/PDF2", {headers: authHeader()}).then(
  //       (response) => {
  //           return response.data;
  //       }
  //   );
  // },
};
