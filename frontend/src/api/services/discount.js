import authHeader, { BASE_URL, HTTP } from "../http";
import { auth as store } from "../../store/auth.module";

export default{
    allDiscounts() {
        return HTTP.get(BASE_URL + "/discount", { headers: authHeader() }).then(
            (response) => {
                return response.data.filter(
                    x => {
                        return x.client == store.state.user.id;
                    }
                )
            }
        );
    },
    allDiscountsWithoutClient(){
        return HTTP.get(BASE_URL + "/discount/" , { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    sendEmail(){
            return HTTP.get(BASE_URL + "/discount/email", { headers: authHeader() }).then((response) => {
                           return response.data;
                       });
        },
    create(discount) {
        
        return HTTP.post(BASE_URL + "/discount", discount,{ headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    edit(discount) {
        return HTTP.patch(BASE_URL + "/discount/" + discount.id, discount, discount.id, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    delete(Id){
        return HTTP.delete(BASE_URL + "/discount/" + Id, { headers: authHeader() }).then();
    },
    csvR() {
        //console.log("maybeError");
         return HTTP.get(BASE_URL + "/discount/export/CSV2", {headers: authHeader()}).then(
             (response) => {
                 
                 return response.data;
             }
         );
       },

//     sendEmail(){
//         console.log("entered in sendemail121");
//         return HTTP.get(BASE_URL + "/discount/email", { headers: authHeader() }).then((response) => {
//                        return response.data;
//                    });
//     },
    
//     sendSMS() {
//         console.log("entered in sendSMS");
//         return HTTP.post(BASE_URL + "/discount/sms", { headers: authHeader() }).then((response) => {
//                        return response.data;
//                    });
//     },
    
//     pdfR() {
//     return HTTP.get(BASE_URL + "/discount/export/PDF", {headers: authHeader()}).then(
//         (response) => {
//             return response.data;
//         }
//     );
//   },
}