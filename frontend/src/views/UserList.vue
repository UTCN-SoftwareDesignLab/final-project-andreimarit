<template>
  <v-card>
    <v-card-title>
      Users
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
      </v-card-title>
  <v-card class=text-center color="brown">
      <v-btn color="green" @click="addUser">Add User</v-btn>
      <v-btn color="red" @click="deleteUser">Delete User</v-btn>
      <v-btn color="yellow" @click="editUser">Edit User</v-btn>
  </v-card> 
    <v-data-table
      :headers="headers"
      :items="users"
      :search="search"
       @click:row="editUser"
    ></v-data-table>
    <UserDialog 
      :opened="dialogVisible"
      :user="selectedUser"
      @refresh="refreshList"
     
    ></UserDialog>
  </v-card>
</template>

<script>
import api from "../api";
import UserDialog from "../components/UserDialog";

export default {
  name: "UserList",
  components: {
    UserDialog
    },
  data() {
    return {
      users: [],
      search: "",
      headers: [
        { text: "Email", value: "email" },
        { text: "Role", value: "role" },
      ],
      dialogVisible: false,
      selectedUser: {},
    };
  },
  methods: {

    async refreshList() {
      this.dialogVisible = false;
      this.selectedUser = {};
      this.users = await api.users.allUsers();
    },
     async editUser(user) {
    this.selectedUser = user;
    this.dialogVisible=true;
  },
  async addUser(){
    this.dialogVisible=true;
  },
  async deleteUser(user){
    this.selectedUser = user;
    this.dialogVisible=true;
  },
   
  },
  
  async created() {
    this.users = await api.users.allUsers();
  },
 
};
</script>

<style scoped></style>
