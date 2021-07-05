import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  allProducts() {
    return HTTP.get(BASE_URL + "/product", { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },

  create(product) {
    return HTTP.post(BASE_URL + "/product", product, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  edit(product) {
    return HTTP.patch(BASE_URL + "/product/" + product.id, product, product.id, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  deleteProduct(Id){
    return HTTP.delete(BASE_URL + "/product/" + Id, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
},

csvR() {
   //console.log("maybeError");
    return HTTP.get(BASE_URL + "/product/export/CSV", {headers: authHeader()}).then(
        (response) => {
            
            return response.data;
        }
    );
  },
  
  // sell(id, quantity) {
  //   return HTTP.patch(
  //     BASE_URL + "/product/sell/" + id + "/" + quantity,
  //     {},
  //     {
  //       headers: authHeader(),
  //     }
  //   ).then((response) => {
  //     return response.data;
  //   });
  // },

  // searchTAG(name, author, genre){
  //   return HTTP.patch(BASE_URL + "/product/tag", name, author, genre, {headers: authHeader() }).then(
  //     (response)=>{
  //       return response.data;
  //     })
  // },

  // csvR() {
  //  //console.log("maybeError");
  //   return HTTP.get(BASE_URL + "/product/export/CSV", {headers: authHeader()}).then(
  //       (response) => {
            
  //           return response.data;
  //       }
  //   );
  // },
  // pdfR() {
  //   return HTTP.get(BASE_URL + "/product/export/PDF", {headers: authHeader()}).then(
  //       (response) => {
  //           return response.data;
  //       }
  //   );
  // },
  // pdfJasper() {
  //   return HTTP.get(BASE_URL + "/product/export/PDF2", {headers: authHeader()}).then(
  //       (response) => {
  //           return response.data;
  //       }
  //   );
  // },
};
