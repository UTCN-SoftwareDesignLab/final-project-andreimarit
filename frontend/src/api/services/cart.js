import authHeader, { BASE_URL, HTTP } from "../http";

export default{
    // allCarts() {
    //     return HTTP.get(BASE_URL + "/cart", { headers: authHeader() }).then(
    //         (response) => {
    //             return response.data;
    //         }
    //     );
    // },
    create(cart, id) {
        
        return HTTP.post(BASE_URL + "/cart/" + id, cart, id, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    // edit(cart) {
    //     return HTTP.patch(BASE_URL + "/cart/" + cart.id, cart, cart.id, { headers: authHeader() }).then(
    //         (response) => {
    //             return response.data;
    //         }
    //     );
    // },
    // delete(Id){
    //     return HTTP.delete(BASE_URL + "/cart/" + Id, { headers: authHeader() }).then();
    // },

    // sendEmail(){
    //     console.log("entered in sendemail121");
    //     return HTTP.get(BASE_URL + "/cart/email", { headers: authHeader() }).then((response) => {
    //                    return response.data;
    //                });
    // },
    
    // sendSMS() {
    //     console.log("entered in sendSMS");
    //     return HTTP.post(BASE_URL + "/cart/sms", { headers: authHeader() }).then((response) => {
    //                    return response.data;
    //                });
    // },
    
    // pdfR() {
    // return HTTP.get(BASE_URL + "/cart/export/PDF", {headers: authHeader()}).then(
    //     (response) => {
    //         return response.data;
    //     }
    // );
 // },
}