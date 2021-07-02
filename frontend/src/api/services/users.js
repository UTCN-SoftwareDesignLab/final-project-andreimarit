import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  allUsers() {
    return HTTP.get(BASE_URL + "/user", { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },

 addUser(user){
   return HTTP.post(BASE_URL + "/user", user, {headers: authHeader() }).then(
      (response)=>{
        return response.data;
      });
 },
 editUser(user){
  return HTTP.patch(BASE_URL + "/user/" + user.id, user.id, user, {
    headers: authHeader(),
  }).then((response) => {
    return response.data;
  });
 },
 deleteUser(id){
   return HTTP.delete(BASE_URL + "/user/" + id, id, {
     headers: authHeader(),
   }).then((response) => {return response.data});
 }


};