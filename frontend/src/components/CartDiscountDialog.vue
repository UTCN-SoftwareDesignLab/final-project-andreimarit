<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          Do you want to apply a discount?
        </v-toolbar>
         <v-data-table
      :headers="headers"
      :items="discounts"
      @click:row="persist"
    ></v-data-table>
        <v-card-actions>
          <v-btn @click="persist">
            Apply Discount
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";
//import CartList from "../views/CartList.vue"

export default {
  name: "CartDiscountDialog",
  props: {
    consultation: Object,
    opened: Boolean,
  },
  data() {
      return{
          discounts: [],
          headers: [
                { text: "Id", value: "id" },
                { text: "Percent", value: "percent" },
          ],
          dialogVisible: false,
          selectedDiscount: {},
      }
  },

  methods: {
    persist(selectedDiscount) {
        
        this.$emit("messageFromChild",selectedDiscount.percent);
        
        
        //CartList.data().discount = selectedDiscount.percent;
        //CartList.methods.setDiscount(selectedDiscount.percent);
                
        this.$emit("refresh");
    },

    async refreshList() {
      this.dialogVisible = false;
      this.selectedDiscount = {};
      this.discounts = await api.discount.allDiscounts();
    },
  },
  created() {
    this.refreshList();
  },

};
</script>

<style scoped></style>