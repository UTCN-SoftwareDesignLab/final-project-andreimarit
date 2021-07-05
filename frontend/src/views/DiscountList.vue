<template>
  <v-card>
    <v-card-title>
      Discounts
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
    </v-card-title>
    <v-card class=text-center>
      <v-btn @click="addDiscount">Add Discount</v-btn>
      <v-btn color="red" @click="deleteDiscount">Delete Discount</v-btn>
      <v-btn @click="editDiscount">Edit Discount</v-btn>
      <v-btn @click="csvReport">Report CSV</v-btn>
      <v-btn @click="sendEmail">Send Email</v-btn>
      <v-btn @click="productGoTO">GoTo Products</v-btn>
    </v-card>
    <v-data-table
      :headers="headers"
      :items="discounts"
      @click:row="editDiscount"
      :search="search"
    ></v-data-table>
    <hr />
    <v-card-title>
      Messages
      <v-spacer></v-spacer>
    </v-card-title>
    <v-data-table
      
      :headers="headersMessage"
      :items="messages"
      @click:row="deleteMessage"
    ></v-data-table>

    <DiscountDialog
      :opened="dialogVisible"
      :discount="selectedDiscount"
      @refresh="refreshList"
    ></DiscountDialog>

  </v-card>
</template>

<script>
import api from "../api";
import DiscountDialog from "../components/DiscountDialog";
import router from "../router";
//import { bus } from "../main";

export default {
  name: "DiscountList",
  components: { DiscountDialog},
  
  data() {
    return {
      discounts: [],
      messages: [],
      search: "",
      headers: [
        { text: "Id", value: "id" },
        { text: "Client", value: "client" },
        { text: "Percent", value: "percent" },
      ],
      headersMessage: [
        { text: "Client", value: "client" },
        { text: "Message", value: "textMessage" },
      ],
      dialogVisible: false,
      selectedDiscount: {},
    };
  },
  methods: {

      async refreshList() {
      this.dialogVisible = false;
      this.selectedDiscount = {};
      this.discounts = await api.discount.allDiscountsWithoutClient();
    },

    addMessage() {
      
        console.log("intra in Add Message");
    },

    deleteMessage() {

    },

    sendEmail() {
        api.discount.sendEmail();
    },
    csvReport() {
      api.discount.csvR();
    },
    
    productGoTO(){
      router.push("./product");
    },
    editDiscount(discount) {
      this.selectedDiscount = discount;
      this.dialogVisible = true;
    },
    deleteDiscount() {
      //this.selectedDiscount = discount;
      this.dialogVisible = true;
    },
    addDiscount() {
      this.dialogVisible = true;
    },
    // sellDiscount(discount) {
    //   this.selectedDiscount = discount;
    //   this.dialogVisible = true;
    // },
   
  },
  
  created() {
    this.refreshList();
    console.log("o intrat in mounted");
    this.$root.$on("messageFromClient", () => {
      
      console.log("o intrat in Discount!");
      
    })
  },
};
</script>

<style scoped></style>